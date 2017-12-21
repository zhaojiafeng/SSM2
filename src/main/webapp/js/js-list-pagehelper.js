/**
 * Created by dllo on 17/12/21.
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
