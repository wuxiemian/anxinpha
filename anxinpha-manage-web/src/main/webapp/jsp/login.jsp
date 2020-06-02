<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>安心后台管理系统</title>
    <link rel="stylesheet" href="../lib/layer/2.4/skin/layer.css"> <!-- Font-Awesome-Icons-CSS -->
    <script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script>
    <script type="text/javascript" src="../lib/jquery.validation/1.14.0/validate-methods.js"></script>

    <style>
        * {
            padding: 0;
            margin: 0;
        }

        html,
        body {
            width: 100%;
            height: 100%;
        }

        .cms_login {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            background-repeat: no-repeat;
            background-image: url(icon/bg.png);
            background-size: 100% 100%;
        }

        .flexCenter {
            text-align: center;
            margin: auto;
        }

        .cms_login_container {
            width: 70%;
            height: 70%;
            display: flex;
            justify-content: center;
        }

        .cms_login_image {
            width: 60%;
            height: 100%;
        }

        .cms_login_from {
            width: 40%;
            height: 100%;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #ccc;
        }

        /* .cms_login_container {
            width: 30%;
            height: 70%;
            border: 1px solid red;
        } */

        img {
            width: 100%;
            height: 100%;
        }

        .divBox {
            display: flex;
            justify-content: center;
            width: 80%;
            height: 46px;
            margin-top: 20px;
            margin-left: 10%;
            position: relative;
        }

        .cms_login_logo {
            display: flex;
            justify-content: center;
            width: 80%;
            height: 60px;
            /* border: 1px solid green; */
            margin-left: 10%;
            margin-bottom: 22px;
            margin-top: 25px;
        }

        .cms_login_img {
            width: 44px;
            height: 44px;
            position: absolute;
            top: 1px;
            left: 1px;
        }

        .cms_login_ipt {
            width: 100%;
            outline: none;
            padding-left: 50px;
        }

        .cms_login_ipt60 {
            width: 60%;
            outline: none;
            padding-left: 50px;
        }

        .cms_login_ver {
            width: 30%;
            outline: none;
            margin-left: 8%;
            border: 1px solid #c32c2c;
            background-color: #bd7070;
            line-height: 46px;
            letter-spacing: 4px;
            border-radius: 4px;
            cursor: default;
            color: white;
        }

        .margin_top60 {
            margin-top: 40px;
        }

        .login_btn {
            width: 100%;
            height: 60px;
            letter-spacing: 10px;
            font-size: 24px;
            background-color: green;
            border: none;
            outline: none;
            border-radius: 10px;
            color: white;
        }

        .login_btn:hover {
            background-color: #044468;
            border: none;
            outline: none;
        }
    </style>
</head>

<body>
    <div class="cms_login">
        <div class="cms_login_container flexCenter" style="min-height:390px;">
            <div class="cms_login_image" style="min-width: 380px;">
                <!-- <img src="./leftimg_04.png" alt=""> -->
            </div>
            <div class="cms_login_from" style="min-width: 380px;">
                <form>
                    <div class="cms_login_logo"><span style="font-size:24px;">安心后台管理系统</span></div>
                    <div class="divBox"> <img class="cms_login_img" src="icon/username_mark.png"> <input
                            class="cms_login_ipt" type="text" placeholder="请输入账号" id="username"></div>
                    <div class="divBox"> <img class="cms_login_img" src="icon/password_mark.png"><input type="password"
                            placeholder="请输入密码" class="cms_login_ipt" id="password"></div>
                    <div class="divBox"> <img class="cms_login_img" src="icon/verify_mark.png"><input type="text"
                            placeholder="请输入验证码" class="cms_login_ipt60" id="cms_login_ver_ipt">
                        <img name="code" id="code" width="116" height="36" onclick="init_captcha()">
                    </div>
                    <div class="divBox margin_top60" style="border:none;">
                        <input class="login_btn" type="button" value="登录" name="submit">
                    </div>
                </form>
            </div>
        </div>
    </div>

<script src="../lib/jquery/jquery-2.1.4.min.js"></script>
<script>
    // 随机验证码
    var uuid
    $(document).ready(function () {
        init_captcha()
    });
    function init_captcha() {
        $.ajax({
            type: 'get',
            url: 'http://api.anxinpha.com/api/user/captcha/init?t='+Math.random(),
            success: function (res) {
                uuid = res
                $("#code").attr("src",'http://api.anxinpha.com/api/user/captcha/draw/'+uuid+'?t='+Math.random())
            }
        });
    }
    var m = parseInt(Math.random(0, 9) * (9999 - 1000) + 1000);
    var n = $(".cms_login_ver").text(m)
    // 原生获取文本内容
    // var n = document.getElementsByClassName("cms_login_ver")[0].innerHTML;
    // console.log(n)
    // 点击按钮判断账户密码

    $('.login_btn').click(function () {
        $(".login_btn").val("登录中...");
        $(".login_btn").attr("disabled", "disabled");
        var username = $("#username").val();
        var password = $("#password").val();
        var code = $("#code").val();
        if (username == "" || password == "") {
            layer.msg("用户名或密码不能为空");
            $(".login_btn").val("登录");
            $(".login_btn").removeAttr("disabled");
            return;
        }
        var params = {
            username,
            password,
            code
        }
        $.ajax({
            type: "post",
            url: "http://api.anxinpha.com/api/auth/login",
            data: params,
            success: function (res) {
                window.location.href="http://manage.anxinpha.com/index"
            }
        })
    })

</script>

</body>
</html>
