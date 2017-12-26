/**
 * Created by dllo on 17/12/21.
 */


/**
 * 显示具体时间
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
 * 显示日期
 * @param time
 * @returns {string}
 */
function slewDate123(time) {
    var normalTime = new Date(time);
    var year = normalTime.getFullYear();
    var month = normalTime.getMonth();
    var date = normalTime.getDate();
    var revertime = [year, month, date].join('/');
    return revertime;
}


/**
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
 * 根据session中的adminId获取权限信息
 */
function findModulesByAdminId() {
    $.ajax({
        url: "/findModulesByAdminId",
        success: function (result) {
            console.log(result);
            if (result.code == "0") {
                var modules = "";
                for (var i = 0; i < result.data.length; i++) {
                    modules += result.data[i] + ',';
                }
                console.log(modules);
                showModules(modules);
            }
        }
    });
}


/**
 * 显示权限modules
 * @param modules
 */
function showModules(modules) {
    var path = window.location.pathname;
    console.log(path);
    if (isContain(path, "/index")) {
        $("<li><a href='/index' class='index_on'></a></li>").appendTo($("#menu"));
    } else {
        $("<li><a href='/index' class='index_off'></a></li>").appendTo($("#menu"));
    }
    if (isContain(modules, "角色管理")) {
        if (isContain(path, "/role_list")) {
            $("<li><a href='/role_list' class='role_on'></a></li>").appendTo($("#menu"));
        } else {
            $("<li><a href='/role_list' class='role_off'></a></li>").appendTo($("#menu"));
        }
    }
    if (isContain(modules, "管理员")) {
        if (isContain(path, "/admin_list")) {
            $("<li><a href='/admin_list' class='admin_on'></a></li>").appendTo($("#menu"));
        } else {
            $("<li><a href='/admin_list' class='admin_off'></a></li>").appendTo($("#menu"));
        }
    }
    if (isContain(modules, "资费管理")) {
        if (isContain(path, "/fee_list")) {
            $("<li><a href='/fee_list' class='fee_on'></a></li>").appendTo($("#menu"));
        } else {
            $("<li><a href='/fee_list' class='fee_off'></a></li>").appendTo($("#menu"));
        }
    }
    if (isContain(modules, "账务账号")) {
        if (isContain(path, "/account_list")) {
            $("<li><a href='/account_list' class='account_on'></a></li>").appendTo($("#menu"));
        } else {
            $("<li><a href='/account_list' class='account_off'></a></li>").appendTo($("#menu"));
        }
    }
    if (isContain(modules, "业务账号")) {
        if (isContain(path, "/service_list")) {
            $("<li><a href='/service_list' class='service_on'></a></li>").appendTo($("#menu"));
        } else {
            $("<li><a href='/service_list' class='service_off'></a></li>").appendTo($("#menu"));
        }
    }
    if (isContain(modules, "账单管理")) {
        if (isContain(path, "/bill_list")) {
            $("<li><a href='/bill_list' class='bill_on'></a></li>").appendTo($("#menu"));
        }
        $("<li><a href='/bill_list' class='bill_off'></a></li>").appendTo($("#menu"));
    }
    if (isContain(modules, "报表")) {
        if (isContain(path, "/report_list")) {
            $("<li><a href='/report_list' class='report_on'></a></li>").appendTo($("#menu"));
        }
        $("<li><a href='/report_list' class='report_off'></a></li>").appendTo($("#menu"));
    }
    if (isContain(path, "/user_info")) {
        $("<li><a href='/user_info' class='information_on'></a></li>").appendTo($("#menu"));
    } else {
        $("<li><a href='/user_info' class='information_off'></a></li>").appendTo($("#menu"));
    }
    if (isContain(path, "/user_modi_pwd")) {
        $("<li><a href='/user_modi_pwd' class='password_on'></a></li>").appendTo($("#menu"));
    } else {
        $("<li><a href='/user_modi_pwd' class='password_off'></a></li>").appendTo($("#menu"));
    }
}


/**
 * 判断str是否包含substr
 * @param str
 * @param substr
 * @returns {boolean}
 */
function isContain(str, substr) {
    return str.indexOf(substr) >= 0;
}