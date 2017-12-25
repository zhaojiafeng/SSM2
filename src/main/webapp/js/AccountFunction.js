/**
 * Created by dllo on 17/12/25.
 */

/**
 * account中公共的方法
 */

/**
 * detail和modi中，根据id显示account信息
 */
function findAccountByAccountId() {
    var accountId = window.location.search.substr(1).toLowerCase().split("=")[1];
    $.ajax({
        url: "/findAccountByAccountId",
        data: {"accountId": accountId},
        success: function (result) {
            console.log(result);
            if (result.code == "0") {
                var account = result.data[0];
                $("#accountId").val(account.accountId);
                $("#realName").val(account.realName);
                $("#idcardNo").val(account.idcardNo);
                $("#loginName").val(account.loginName);
                $("#telephone").val(account.telephone);
                $("#recommenderId").val(account.recommenderId);
                $("#accountStatus").val(account.status);
                $("#pauseDate").val(slewTime(account.pauseDate));
                $("#lastLoginTime").val(slewTime(account.lastLoginTime));
                $("#lastLoginIp").val(account.lastLoginIp);
                $("#birthdate").val(slewTime(account.birthdate));
                $("#email").val(account.email);
                $("#selectOccu").val(account.occupation);
                $("input:radio[value=" + account.gender + "]").attr('checked', true);
                $("#mailaddress").val(account.mailaddress);
                $("#zipcode").val(account.zipcode);
                $("#qq").val(account.qq);
            }
        }
    });
}


/**
 * add和modi中编辑成功，失败
 * @param flag
 */
function showResultDiv(flag) {
    var divResult = document.getElementById("save_result_info");
    if (flag)
        divResult.style.display = "block";
    else
        divResult.style.display = "none";
}


/**
 * account_add中的方法
 */

/**
 * 添加account
 */
function addAccount() {
    var realName = $("#realName").val();
    var idcardNo = $("#idcardNo").val();
    var loginName = $("#loginName").val();
    var loginPasswd = $("#loginPasswd").val();
    var repeatPasswd = $("#repeatPasswd").val();
    var telephone = $("#telephone").val();
    var birthdate = $("#birthdate").value;
    var email = $("#email").val();
    var gender = $('input:radio:checked').val();
    var occupation = $('select#selectOccu option:selected').val();
    var mailaddress = $("#mailaddress").val();
    var zipcode = $("#zipcode").val();
    var qq = $("#qq").val();

    $("#realNameError").empty();
    $("#idcardNoError").empty();
    $("#loginNameError").empty();
    $("#loginPasswdError").empty();
    $("#repeatPasswdError").empty();
    $("#telephoneError").empty();
    $("#recommenderIdError").empty();
    $("#emailError").empty();
    $("#mailaddressError").empty();
    $("#zipcodeError").empty();
    $("#qqError").empty();

    if (realName.length > 0 && realName.length < 21 &&
        loginName.length > 0 && loginName.length < 31 &&
        loginPasswd.length > 0 && loginPasswd.length < 31 &&
        repeatPasswd.length > 0 && repeatPasswd.length < 31 &&
        loginPasswd == repeatPasswd
    ) {
        $.ajax({
            url: "/addAccount",
            data: {
                "realName": realName, "idcardNo": idcardNo, "loginName": loginName, "loginPasswd": loginPasswd,
                "telephone": telephone, "birthdate": birthdate, "email": email, "gender": gender,
                "occupation": occupation, "mailaddress": mailaddress, "zipcode": zipcode, "qq": qq
            },
            success: function (result) {
                if (result.code == "0") {
                    showResultDiv(true);
                    window.setTimeout("showResultDiv(true);", 1500);
                    window.setTimeout("backToAccount_list();", 1500)
                } else if (result.code == "-1") {
                    var accountError = result.message;
                    $("#realNameError").text(accountError.realNameError);
                    $("#idcardNoError").text(accountError.idcardNoError);
                    $("#loginNameError").text(accountError.loginNameError);
                    $("#loginPasswdError").text(accountError.loginPasswdError);
                    $("#telephoneError").text(accountError.telephoneError);
                    $("#recommenderIdError").text(accountError.recommenderIdError);
                    $("#emailError").text(accountError.emailError);
                    $("#mailaddressError").text(accountError.mailaddressError);
                    $("#zipcodeError").text(accountError.zipcodeError);
                    $("#qqError").text(accountError.qqError);

                    showResultDiv(false);
                    window.setTimeout("showResultDiv(false);", 1500);
                }
            }
        });
    } else {
        if (realName.length <= 0 && realName.length >= 21) {
            $("#realNameError").text("20长度以内的汉字、字母和数字的组合");
        }
        if (loginName.length <= 0 && loginName.length >= 31) {
            $("#loginNameError").text("30长度以内的字母、数字和下划线的组合");
        }
        if (loginPasswd.length <= 0 && loginPasswd.length >= 31) {
            $("#loginPasswdError").text("30长度以内的字母、数字和下划线的组合");
        }
        if (repeatPasswd.length <= 0 && repeatPasswd.length >= 31) {
            $("#repeatPasswdError").text("30长度以内的字母、数字和下划线的组合");
            if (loginPasswd != repeatPasswd) {
                $("#repeatPasswdError").text("两次密码必须相同");
            }
        }
    }
}


/**
 * 显示选填的信息项
 */
function showOptionalInfo(imgObj) {
    var div = document.getElementById("optionalInfo");
    if (div.className == "hide") {
        div.className = "show";
        imgObj.src = "../images/hide.png";
    }
    else {
        div.className = "hide";
        imgObj.src = "../images/show.png";
    }
}


/**
 * account_list中的方法
 */

/**
 * 根据页数，条件获取数据
 * @param currentPage 当前页
 */
function findAccountsByPage(currentPage) {
    var pageSize = 4;
    var idcardNo = $("#idcardNo").val();
    var realName = $("#realName").val();
    var loginName = $("#loginName").val();
    var status = $("#accountstatus").val();

    $("#body").empty();
    $("#pages").empty();

    $.ajax({
        url: "/advanceSearchAccount",
        data: {
            "idcardNo": idcardNo,
            "realName": realName,
            "loginName": loginName,
            "status": status,
            "currentPage": currentPage,
            "pageSize": pageSize
        },
        success: function (result) {
            console.log(result);
            showAccountResult(result);
        }
    });
}


/**
 * 高级搜索
 * @constructor
 */
function AdvanceSearchAccount() {
    var currentPage = 1;
    var pageSize = 4;
    var idcardNo = $("#idcardNo").val();
    var realName = $("#realName").val();
    var loginName = $("#loginName").val();
    var status = $("#accountstatus").val();

    $("#body").empty();
    $("#pages").empty();

    $.ajax({
        url: "/advanceSearchAccount",
        data: {
            "idcardNo": idcardNo, "realName": realName,
            "loginName": loginName, "status": status,
            "currentPage": currentPage, "pageSize": pageSize
        },
        success: function (result) {
            console.log(result);
            showAccountResult(result);
        }
    });
}


/**
 * 开通或暂停account
 */
function alterAccountStatus(accountId) {
    var r = window.confirm("确定要开通此账务账号吗？");
    if (r) {
        $.ajax({
            url: "/alterAccountStatus",
            data: {"accountId": accountId},
            success: function (result) {
                if (result.code == "0") {
                    document.getElementById("operate_result_info").style.display = "block";
                }
            }
        })
    }
}


/**
 * 删除account
 */
function deleteAccount(accountId) {
    var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
    if (r) {
        $.ajax({
            url: "/deleteAccount",
            data: {"accountId": accountId},
            success: function (result) {
                if (result.code == "0") {
                    document.getElementById("operate_result_info").style.display = "block";
                }
            }
        })
    }
}


/**
 * 显示account_list的result
 */
function showAccountResult(result) {
    showAccountPages(result);
    showAccountsInfo(result);
}


/**
 * 显示account_list中的pages
 * @param result
 */
function showAccountPages(result) {
    var pageNum = result.data.pageNum;
    $("#currentPage").val(pageNum);
    var totalPage = result.data.pages;
    $("#totalPage").val(totalPage);
    var pages = "";
    for (var i = 1; i <= totalPage; i++) {
        if (pageNum == i) {
            pages += "<a href='#' class='current_page' onclick='findAccountsByPage(" + i + ")' >" + i + "</a>";
        } else {
            pages += "<a href='#' onclick='findAccountsByPage(" + i + ")'>" + i + "</a>";
        }
    }

    var spanF = $("<span></span>").append("第" + pageNum + "/" + totalPage + "页");
    var spanL = $("<span></span>").append("<a href='#' onclick='findAccountsByPage(1);'>首页</a>" +
        "<a href='#' onclick='toLastAccountPage();'>上一页</a>").append(pages).append("<a href='#' onclick='toNextAccountPage();'>下一页</a>" +
        "<a href='#' onclick='findAccountsByPage(" + totalPage + ");'>尾页</a>");
    $("#pages").append(spanF).append(spanL);
}


/**
 * 显示account_list中的accountInfo
 */
function showAccountsInfo(result) {
    for (var i = 0; i < result.data.list.length; i++) {
        var account = result.data.list[i];
        var tdaccountId = $("<td></td>").text(account.accountId);
        var tdrealname = $("<td></td>").append("<a href='/account_detail?accountId=" + account.accountId + "'>" + account.realName + "</a>");
        var tdidcardNo = $("<td></td>").text(account.idcardNo);
        var tdloginName = $("<td></td>").text(account.loginName);
        var tdstatus;
        var tdcreateDate = $("<td></td>").text(slewDate123(account.createDate));
        var tdpauseDate = $("<td></td>").text(slewTime(account.pauseDate));
        var tdoption;
        if (account.status == 'b') {
            tdstatus = $("<td></td>").text("暂停");
            tdoption = $("<td></td>").append(
                "<input type='button' value='开通' class='btn_start' onclick='alterAccountStatus(" + account.accountId + ");'/> " +
                "<input type='button' value='修改' class='btn_modify' onclick='toaccount_modi(" + account.accountId + ")';'/> " +
                "<input type='button' value='删除' class='btn_delete' onclick='deleteAccount(" + account.accountId + ");'/>"
            );
        } else if (account.status == 's') {
            tdstatus = $("<td></td>").text("开通");
            tdoption = $("<td></td>").append(
                "<input type='button' value='暂停' class='btn_pause' onclick='alterAccountStatus(" + account.accountId + ");'/> " +
                "<input type='button' value='修改' class='btn_modify' onclick='toaccount_modi(" + account.accountId + ")';'/> " +
                "<input type='button' value='删除' class='btn_delete' onclick='deleteAccount(" + account.accountId + ");'/>"
            );
        } else {
            tdstatus = $("<td></td>").text("");
            tdoption = $("<td></td>").text("");
        }
        var tr = $("<tr></tr>").append(tdaccountId).append(tdrealname).append(tdidcardNo).append(tdloginName).append(tdstatus).append(tdcreateDate).append(tdpauseDate).append(tdoption);
        $("#body").append(tr);
    }
}


/**
 * account_list中的上一页
 * @constructor
 */
function toLastAccountPage() {
    var currentPage = parseInt($("#currentPage").val()) - 1;
    findAccountsByPage(currentPage);
}


/**
 * account_list中的下一页
 * @constructor
 */
function toNextAccountPage() {
    var currentPage = parseInt($("#currentPage").val()) + 1;
    findAccountsByPage(currentPage);
}


/**
 * 跳转至添加账户界面
 * @constructor
 */
function toaccount_add() {
    window.location.href = '/account_add';
}


/**
 * 跳转至编辑账户界面
 * @param accountId 账户ID
 * @constructor
 */
function toaccount_modi(accountId) {
    window.location.href = '/account_modi?accountId=' + accountId;
}


/**
 * 返回至account_list界面
 */
function backToAccount_list() {
    window.location.href = '/account_list';
}


/**
 * account_modi中的方法
 */

/**
 * 编辑account
 */
function editAccount() {
    var accountId = $("#accountId").val();
    var realName = $("#realName").val();
    var telephone = $("#telephone").val();
    var recommendId = $("#recommendId").val();
    var email = $("#email").val();
    var occupation = $('select#selectOccu option:selected').val();
    var gender = $('input:radio:checked').val();
    var mailaddress = $("#mailaddress").val();
    var zipcode = $("#zipcode").val();
    var qq = $("#qq").val();

    $("#realNameError").empty();
    $("#telephoneError").empty();
    $("#recommenderIdError").empty();
    $("#emailError").empty();
    $("#mailaddressError").empty();
    $("#zipcodeError").empty();
    $("#qqError").empty();

    if (realName.length > 0 && realName.length < 21 && email.length < 51 && mailaddress<51) {
        $.ajax({
            url: "/addAccount",
            data: {
                "accountId": accountId,
                "realName": realName,
                "telephone": telephone,
                "email": email,
                "gender": gender,
                "occupation": occupation,
                "mailaddress": mailaddress,
                "zipcode": zipcode,
                "qq": qq
            },
            success: function (result) {
                if (result.code == "0") {
                    showResultDiv(true);
                    window.setTimeout("showResultDiv(true);", 1500);
                    window.setTimeout("backToAccount_list();", 1500)
                } else if (result.code == "-1") {
                    $("#realNameError").text(result.message.realNameError);
                    $("#telephoneError").text(result.message.telephoneError);
                    $("#recommenderIdError").text(result.message.recommenderIdError);
                    $("#emailError").text(result.message.emailError);
                    $("#mailaddressError").text(result.message.mailaddressError);
                    $("#zipcodeError").text(result.message.zipcodeError);
                    $("#qqError").text(result.message.qqError);

                    showResultDiv(false);
                    window.setTimeout("showResultDiv(true);", 1500);
                }
            }
        });
    } else {
        if (realName.length <= 0 || realName.length >= 21) {
            $("#realNameError").text("20长度以内的汉字、字母和数字的组合");
        }
        if (email.length >= 51) {
            $("#emailError").text("50长度以内，合法的 Email 格式");
        }
        if (mailaddress.length >= 51) {
            $("#mailaddressError").text("50长度以内");
        }
    }
}


/**
 * 显示修改密码的信息项
 */
function showPwd(chkObj) {
    if (chkObj.checked)
        document.getElementById("divPwds").style.display = "block";
    else
        document.getElementById("divPwds").style.display = "none";
}

