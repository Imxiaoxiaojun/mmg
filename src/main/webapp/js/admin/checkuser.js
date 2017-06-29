var login = function (val) {
    getElementById("EEE").innerHTML = "";
    var username = getElementById("userName");
    var password = getElementById("passWord");
    var captchaid = getElementById("captchaId");
    if (null == username || username.value.trim() == "") {
        getElementById("EEE").innerHTML = "用户名不能为空";
        return false;
    }
    if (null == password || "" == password.value.trim()) {
        getElementById("EEE").innerHTML = "密码不能为空";
        return false;
    }
    if (null == captchaid || "" == captchaid.value.trim() || captchaid.value.trim().length < 4) {
        getElementById("EEE").innerHTML = "验证码格式错误";
        return false;
    }
//	getElementById("box-login").action= "admin/adminConsole.xhtml"
    var formDataUtil = new FormDataUtil();
    formDataUtil.put("userName", username.value);
    formDataUtil.put("passWord", password.value);
    formDataUtil.put("captchaId", captchaid.value);
    formDataUtil.put(1, captchaid.value);
    document.forms[0].action = "checkUser.xhtml";

    // $.post("admin/adminConsole.xhtml",formDataUtil.getajaxdata(),null);

    _ajax("checkCaptchaId.xhtml", "get", formDataUtil.getajaxdata(), function (data) {
        document.forms[0].submit();
    }, function (data) {
        getElementById("EEE").innerHTML = strtojson(data.responseText).errMsg;
        getElementById("verifyImg").src += "?code=" + Math.random();
    })
}
