
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        var captchaid = $(this).find('.captchaId').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
        if(captchaid == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '165px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.captchaId').focus();
            });
            return false;
        }

        var formDataUtil = new FormDataUtil();
        formDataUtil.put("userName", username);
        formDataUtil.put("passWord", password);
        formDataUtil.put("captchaId", captchaid);
        document.forms[0].action = "checkUser.xhtml";


        _ajax("checkCaptchaId.xhtml", "get", formDataUtil.getajaxdata(), function (data) {
            document.forms[0].submit();
        }, function (data) {
            getElementById("EEE").innerHTML = strtojson(data.responseText).errMsg;
            getElementById("verifyImg").src += "?code=" + Math.random();
        })
    });

    $('.page-container form .username, .page-container form .password,.page-container form .captchaId').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});
