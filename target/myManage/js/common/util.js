var getElementById = function (val) {
    if (null == val || "" == val.trim())return null;
    return document.getElementById(val);
}

var _ajax = function (url, type, param, succallback, failcallback) {
    $.ajax({
        type: type,
        url: url,
        async: true,
        data: param || {},
        dataType: "text",
        success: succallback||function (data) {
            console.log(data);
        },
        error: failcallback || function (data) {
            console.log(data);
        }
    });

}
var strtojson = function (jsons) {
    return eval('(' + jsons + ')');
}
var FormDataUtil = function () {
    this.obj = {};
    this.param = "";
    this.put = function (key, val) {
        if (Object.prototype.toString.call(key) != "[object String]")return;
        if (!key || "" == key.trim())return;
        this.obj[key] = val;
        this.param += key + "=" + val + "&";
    }
    this.get = function (key) {
//		return this.array[key];
    }
    this.getajaxdata = function () {
        return this.param.substring(0, this.param.lastIndexOf('&'));
    }
};

var changeCode = function () {
}


