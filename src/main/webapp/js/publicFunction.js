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
 * 权限管理设置
 */
function findmodulesByAdminId() {
    $.ajax({
        url:"/",
        success:function (result) {
            if (result.code == "0"){

            }
        }
    });
}


