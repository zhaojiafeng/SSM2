/**
 * Created by dllo on 17/12/21.
 */
/**
 * 更改实现显示方式
 * @param time时间戳
 * @returns {string} XXXX/XX/XX XX:XX:XX
 */
function slewTime(time) {
    var normalTime = new Date(time);
    var year = normalTime.getFullYear();
    var month = normalTime.getMonth();
    var date = normalTime.getDate();
    var hour = normalTime.getHours();
    var minute = normalTime.getMinutes();
    var seconds = normalTime.getSeconds();
    var revertime = [year, month, date].join('/') + ' ' + [hour, minute, seconds].join(':');
    return revertime;
}


/**
 * 整体部分
 * 登录
 * 权限
 * 退出登录
 */



/**
 * 退出登录
 */
function signOut() {
    if (confirm("系统提示，您确定要退出本次登录吗?")) {
        $.ajax({
            url: "/signOut",
            success: function (result) {
                if (result.code == "0") {
                    window.location.href = '/loginPage';
                }
            }
        })
    }
}


/**
 * 角色管理部分
 * 角色分页
 */




/**
 * 管理员部分
 *
 */
function addAdminAndRoles() {
    var adminame = $("#adminame").val();
    var admincode = $("#admincode").val();
    var password = $("#password").val();
    var repeatpassword = $("#repeatpassword").val();
    var telephone = $("#telephone").val();
    var email = $("#email").val();

    var obj = document.getElementsByName('role');
    var roles = '';
    for (var i = 0; i < obj.length; i++) {
        if (obj[i].checked) {
            roles += obj[i].value + ',';
        }
    }

    $("#adminNameError").empty();
    $("#adminCodeError").empty();
    $("#passwordError").empty();
    $("#repeatPasswordError").empty();
    $("#telephoneError").empty();
    $("#emailError").empty();
    $("#roleError").empty();

    if (
        adminame != '' && adminame > 0 && adminame < 20 &&
        admincode != '' && admincode > 0 && admincode < 30 &&
        password != '' && password > 0 && password < 30 &&
        repeatpassword != '' && repeatpassword > 0 && repeatpassword < 30 &&
        password == repeatpassword &&
        rolenames != ''
    ) {
        $.ajax({
            url: "/addAdminAndRole",
            data: {
                "name": adminame, "adminCode": admincode, "password": password,
                "telephone": telephone, "email": email, "roles": rolenames
            },
            success: function (result) {
                console.log(result);
                if (result.code == "0") {
                    showResultDiv(true);
                    window.setTimeout("showResultDiv(false);", 3000);
                    backToAdmin_list();
                } else if (result.code == "-1") {
                    var message = result.message;
                    $("#adminNameError").val(message.nameError);
                    $("#adminCodeError").val(message.adminCodeError);
                    $("#passwordError").val(message.passwordError);
                    $("#telephoneError").val(message.telephoneError);
                    $("#emailError").val(message.emailError);
                }
            }
        })
    } else {
        if (adminame == '' || adminame < 0 || adminame > 20) {
            $("#adminNameError").val("20长度以内的汉字、字母、数字的组合");
        }
        if (admincode == '' && admincode < 0 && admincode > 30) {
            $("#adminCodeError").val("30长度以内的字母、数字和下划线的组合");
        }
        if (password == '' && password < 0 && password > 30) {
            $("#passwordError").val("30长度以内的字母、数字和下划线的组合");
        }
        if (repeatpassword == '' && repeatpassword < 0 && repeatpassword > 30) {
            $("#repeatPasswordError").val("30长度以内的字母、数字和下划线的组合");
        } else {
            if (password != repeatpassword) {
                $("#repeatPasswordError").val("两次密码必须相同");
            }
        }
        if (rolenames == '') {
            $("#roleError").val("至少选择一个");
        }
    }
}


//返回admin_list
function backToAdmin_list() {
    window.location.href = '/admin_list';
}


//保存成功的提示消息
function showResult() {
    showResultDiv(true);
    window.setTimeout("showResultDiv(false);", 3000);
}


function showResultDiv(flag) {
    var divResult = document.getElementById("save_result_info");
    if (flag)
        divResult.style.display = "block";
    else
        divResult.style.display = "none";
}


/**
 * 资费管理部分
 *
 */


