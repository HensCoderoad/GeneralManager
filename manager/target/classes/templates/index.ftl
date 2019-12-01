<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台登录页面</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/style.css">
    <style>
        html,body{
            height: 100%;
        }
        body{
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: rgba(0, 0, 0, 0.1);
            background-size: cover;
        }
        .error {
            color:red;
        }
        .ok {
            color:green;
        }
    </style>
</head>
<body>
<div class="login-div">
    <h1>登录</h1>
    <form id="login-form" action="#">
        <div class="login-form-item">
            <input name="username" id="username" placeholder="用户名">
            <span id="tip-name"></span>
        </div>
        <div class="login-form-item">
            <input name="password" type="password" id="password" placeholder="密码">
            <span id="tip-login"></span>
        </div>
        <button class="btn-login" type="button" id="submit-botton" onclick="doLogin()">登录</button>
    </form>
</div>
</body>
<script src="/js/jquery-3.4.1.min.js"></script>
<#--<script src="/layui/layui.all.js"></script>-->
<script>
    var isLogin = false;
    $(function () {
        $('#username').blur(function () {
            var re = /^\w{4,20}$/;
            if ( $(this).parent().find( "div" ).length > 0 ) {
                $(this).parent().find( "div" ).remove();
            }
            if ( !re.test( $(this).val() ) ) {
                $(this).after( "<div class='error' >用户名长度为6~20位</div>" );
            }
        });
        $("#password").blur(function(){
            var re = /^\w{4,20}$/;
            if ( $(this).parent().find( "div" ).length > 0 ) {
                $(this).parent().find( "div" ).remove();
            }
            if ( !re.test( $(this).val() ) ) {
                $(this).after( "<div class='error'>格式错误</div>" );
            }
        });
        $("#submit-botton").click(function(){
            $("#username").trigger("blur");
            $("#password").trigger("blur");
            if ( $("#login-form").find(".error").length <= 0 ) {
                isLogin = true;
            }
        });
    })
    
    function doLogin(){
        var username = $('#username').val();
        var password = $('#password').val();
        var options ={username: username, password: password};
        if(isLogin){
            $.ajax({
                method: "post",
                url: "http://localhost:8080/doLogin",
                async: true,
                data: JSON.stringify(options),
                dataType: "json",
                contentType: 'application/json;charset=UTF-8',
                success: function (result) {
                    if(result.code == 1003 || result.code == 1004){
                        $('#tip-login').after( "<div class='error'>用户名或密码错误！</div>" );
                    }else if(result.code == 1){
                        window.location.href = "http://localhost:8080/authc/home?username="+result.data.username;
                    }
                },
                error: function (result) {

                }
            })
        }
    }

</script>
</html>