/**
 * Created by dllo on 17/12/26.
 */

/**
 * serving中公共的方法
 */

/**
 * modi和detail中的
 * 根据servingId获取serving数据
 */
function findServiceByServiceId() {
    var serviceId = window.location.search.substr(1).toLowerCase().split("=")[1];
    $.ajax({
        url: "/findServiceByserviceId",
        data: {"serviceId": serviceId},
        success: function (result) {
            if (result.code == "0") {

            }
        }
    });
}


/**
 * add和modi中的返回service_list
 */
function backToservice_list() {
    window.location.href = '/service_list';
}




/**
 * service_list中的方法
 */

/**
 * serving_list显示所有的serving
 */
function findAllServing() {
    var currentPage = 1;
    var pageSize = 4;

    $.ajax({
        url: "/findAllService",
        data: {"currentPage": currentPage, "pageSize": pageSize},
        success: function (result) {
            console.log(result);
            if (result.code == "0") {
                showServingResult(result);
            }
        }
    });
}


/**
 * 根据页数获取servings
 * @param pageNum
 */
function findServiceByPage(pageNum) {
    var pageSize = 4;

    $("#body").empty();
    $("#pages").empty();

    $.ajax({
        url: "/advanceSearchService",
        data: {
            "pageNum": pageNum,
            "pageSize": pageSize
        },
        success: function (result) {
            console.log(result);
            if (result.code == "0") {
                showServingResult(result);
            }
        }
    })
}


/**
 * 高级搜索
 * @constructor
 */
function advanceSearchService() {
    var pageNum = 1;
    var pageSize = 4;
    var osUsername = $("#osUsername").val();
    var unixhost = $("#unixhost").val();
    var idcardNo = $("#idcardNo").val();
    var status = $("#status").val();
    
    $("#body").empty();
    $("#pages").empty();

    $.ajax({
        url: "/advanceSearchService",
        data: {
            "pageNum": pageNum,
            "pageSize": pageSize,
            "osUsername": osUsername,
            "unixHost": unixhost,
            "idcardNo": idcardNo,
            "status": status
        },
        success: function (result) {
            console.log(result);
            if (result.code == "0") {
                showServingResult(result);
            }
        }
    })
}


/**
 * 开通或暂停service
 * @param serviceId
 */
function alterServiceStatus(serviceId) {
    var r = window.confirm("确定要开通此业务账号吗？");
    if (r) {
        $.ajax({
            url: "/alterServingStatus",
            data: {"serviceId": serviceId},
            success: function (result) {
                if (result.code == "0") {
                    document.getElementById("operate_result_info").style.display = "block";
                    window.setTimeout("backToservice_list();", 1500);
                }
            }
        });
    }
}


/**
 * 删除service
 * @param serviceId
 */
function deleteService(serviceId) {
    var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
    if (r) {
        $.ajax({
            url: "/deleteServing",
            data: {"serviceId": serviceId},
            success: function (result) {
                if (result.code == "0") {
                    document.getElementById("operate_result_info").style.display = "block";
                    window.setTimeout("backToservice_list();", 1500);
                }
            }
        })
    }
}


/**
 * 显示serving的result
 * @param result
 */
function showServingResult(result) {
    showServingPages(result);
    for (var i = 0; i < result.data.list.length; i++) {
        var serving = result.data.list[i];
        showServingInfo(serving);
    }
}
/**
 * 显示serving的pages
 * @param result
 */
function showServingPages(result) {
    var pageNum = result.data.pageNum;
    $("#pageNum").val(pageNum);
    var totalPage = result.data.pages;
    $("#totalPage").val(totalPage);
    var pages = "";
    for (var i = 1; i < totalPage; i++) {
        if (pageNum == i) {
            pages += "<a href='#' class='current_page' onclick='findServiceByPage(" + i + ");'>" + i + "</a>"
        } else {
            pages += "<a href='#' onclick='findServiceByPage(" + i + ")'>" + i + "</a>"
        }
    }

    var spanF = $("<span></span>").append("第" + pageNum + "/" + totalPage + "页");
    var spanL = $("<span></span>").append("<a href='#' onclick='findServiceByPage(1);'>首页</a>" +
        "<a href='#' onclick='toLastServicePage();'>上一页</a>").append(pages).append("<a href='#' onclick='toNextServicePage();'>下一页</a>" +
        "<a href='#' onclick='findServiceByPage(" + totalPage + ");'>尾页</a>");
    $("#pages").append(spanF).append(spanL);
}
/**
 * 显示serving的info
 * @param serving
 */
function showServingInfo(serving) {
    var tdservingId = $("<td></td>").text(serving.serviceId);
    var tdaccountId = $("<td></td>").text(serving.accountId);
    var tdidcardNo = $("<td></td>").text(serving.account.idcardNo);
    var tdrealname = $("<td></td>").text(serving.account.realName);
    var tdosUsername = $("<td></td>").text(serving.osUsername);
    var tdstatus;
    var tdunixHost = $("<td></td>").text(serving.unixHost);
    var tdcostname = $("<td></td>").text(serving.cost.name);
    var tdoption;
    if (serving.status == "s") {
        tdstatus = $("<td></td>").text("开通");
        tdoption = $("<td></td>").append(
            "<input type='button' value='暂停' class='btn_pause' onclick='alterServiceStatus(" + serving.serviceId + ");'/>" +
            "<input type='button' value='修改' class='btn_modify' onclick='toservice_modi(" + serving.serviceId + ");'/>" +
            "<input type='button' value='删除' class='btn_delete' onclick='deleteService(" + serving.serviceId + ");'/>");
    } else {
        tdstatus = $("<td></td>").text("暂停");
        tdoption = $("<td></td>").append(
            "<input type='button' value='开通' class='btn_start' onclick='alterServiceStatus(" + serving.serviceId + ");'/>" +
            "<input type='button' value='修改' class='btn_modify' onclick='toservice_modi(" + serving.serviceId + ");'/>" +
            "<input type='button' value='删除' class='btn_delete' onclick='deleteService(" + serving.serviceId + ");'/>");
    }
    var tr = $("<tr></tr>").append(tdservingId).append(tdaccountId).append(tdidcardNo).append(tdrealname).append(tdosUsername).append(tdstatus).append(tdunixHost).append(tdcostname).append(tdoption)
    $("#body").append(tr);
}
/**
 * service_list中的上一页
 */
function toLastServicePage() {
    var pageNum = parseInt($("#pageNum").val()) - 1;
    findAccountsByPage(pageNum);
}
/**
 * service_list中的下一页
 */
function toNextServicePage() {
    var pageNum = parseInt($("#pageNum").val()) + 1;
    findAccountsByPage(pageNum);
}


/**
 * 跳转至serving_add
 */
function toservice_add() {
    window.location.href = '/service_add';
}
/**
 * 跳转至serving_modi
 */
function toservice_modi() {
    window.location.href = '/service_modi';
}


/**
 * 显示角色详细信息
 */
function showDetail(flag, a) {
    var detailDiv = a.parentNode.getElementsByTagName("div")[0];
    if (flag) {
        detailDiv.style.display = "block";
    }
    else
        detailDiv.style.display = "none";
}




/**
 * service_add中的方法
 */
function addServing() {

}


/**
 * service_modi中的方法
 */
function editServing() {

}