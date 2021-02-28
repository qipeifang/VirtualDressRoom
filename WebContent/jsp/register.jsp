
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" +
            request.getServerPort() + path;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
﻿<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/main.js"></script>
    <meta charset="utf-8" />
    <title>注册界面</title>
	<link rel="stylesheet" href="<%=basePath%>/css/register.css" />
</head>
﻿<body onkeydown="enterLogin()">
<div id="outter">
    <center>
        <div id="loginDiv">
            <div id="title">
                <center><img style="margin-bottom: -20px;" src="../img/touxiang.png" /></center>
                <br />
                注册试衣间账号
            </div>
            <div id="main">
                <input type="text" class="input" id="userName" placeholder="用户名">
                <font style="color: red;font-size: 12px;float: left;padding-left: 50px;z-index:100;display: none;" id="namecheck">用户名长度不少于6位!</font>
                <font style="color: red;font-size: 12px;float: left;padding-left: 50px;z-index:100;display: none;" id="namecheck1">用户名长度不多于16位!</font>
                <input type="text" class="input" id="realName" placeholder="用户实名">
                <input type="password" class="input" id="password" placeholder="密码" />
                <font style="color: red;font-size: 12px;float: left;padding-left: 50px;z-index:100;display: none;" id="pwdcheck">密码长度不少于6位!</font>
                <font style="color: red;font-size: 12px;float: left;padding-left: 50px;z-index:100;display: none;" id="pwdcheck1">密码长度不多于16位!</font>
                <font style="color: red;font-size: 12px;float: left;padding-left: 50px;z-index:100;display: none;" id="repwdcheck">两次密码不一致!</font>
                <input type="password" class="input_3" id="repassword" placeholder="密码确认" />
                <div>
                    <input type="button" class="sexbt" id="btman" value="男" />
                    &nbsp;&nbsp;&nbsp;
                    <input type="button" class="sexbt" id="btwoman" value="女" />
                </div>

                <div id="modelTypeMan" style="display: none;">
                    <div style="font-family: TradeGothicforNike365-BdCn,Helvetica!important;font-size: 20px;font-weight: 700;color: #334DC4;margin-bottom: 8px;">模型选择</div>
                    <button id="modelMan1" class="modelunclick"><img src="../img/mheadA.png"></button>
                    &nbsp;&nbsp;&nbsp;
                    <button id="modelMan2" class="modelunclick"><img src="../img/mheadB.png"></button>
                </div>
                <div id="modelTypeWoman" style="display: none;">
                    <div style="font-family: TradeGothicforNike365-BdCn,Helvetica!important;font-size: 20px;font-weight: 700;color: #334DC4;margin-bottom: 8px;">模型选择</div>
                    <button id="modelWoman1" class="modelunclick"><img src="../img/wheadA.png"></button>
                    &nbsp;&nbsp;&nbsp;
                    <button id="modelWoman2" class="modelunclick"><img src="../img/wheadB.png"></button>
                </div>
                <input type="button" class="bt" id="bt1" value="点击注册" />
                &nbsp;&nbsp;&nbsp;
                <input type="button" class="bt" id="bt2" value="返回登录" />
            </div>
        </div>
    </center>

</div>

</body>

﻿<script>
    var bt1 = document.getElementById("bt1");
    var bt2 = document.getElementById("bt2");
    var btman = document.getElementById("btman");
    var btwoman = document.getElementById("btwoman");
    var modelTypeMan = document.getElementById("modelTypeMan");
    var modelTypeWoman = document.getElementById("modelTypeWoman");
    var modelMan1 = document.getElementById("modelMan1");
    var modelMan2 = document.getElementById("modelMan2");
    var modelWoman1 = document.getElementById("modelWoman1");
    var modelWoman2 = document.getElementById("modelWoman2");
    var userName = document.getElementById("userName");
    var pwd = document.getElementById("password");
    var realName = document.getElementById("realName");
    var repassword = document.getElementById("repassword");
    var pwdcheck = document.getElementById("pwdcheck");
    var pwdcheck1 = document.getElementById("pwdcheck1");
    var namecheck = document.getElementById("namecheck");
    var namecheck1 = document.getElementById("namecheck1");
    var repwdcheck = document.getElementById("repwdcheck");
    var sex = 3;
    var model = '';
    userName.onkeyup = function(){
        var userName1 = this.value;
        if (userName1.length < 6) {
            userName.className = "input_2";
            realName.className = "input_1";
            namecheck.style.display = "block";
            namecheck1.style.display = "none";
            bt1.disabled = "true";
        }else if(userName1.length >16){
            userName.className = "input_2";
            realName.className = "input_1";
            namecheck.style.display = "none";
            namecheck1.style.display = "block";
            bt1.disabled = "true";
        }else{
            userName.className = "input";
            realName.className = "input";
            namecheck.style.display = "none";
            namecheck1.style.display = "none";
            bt1.disabled = 0;
        }
    }
    pwd.onkeyup = function() {
        var pwd1 = this.value;
        if (pwd1.length < 6) {
            pwd.className = "input_2";
            repassword.className = "input_1";
            pwdcheck.style.display = "block";
            repwdcheck.style.display = "none";
            pwdcheck1.style.display = "none";
            repassword.disabled = "true";
            bt1.disabled = "true";
        }else if(pwd1.length >16){
            pwd.className = "input_2";
            repassword.className = "input_1";
            repwdcheck.style.display = "none";
            pwdcheck.style.display = "none";
            pwdcheck1.style.display = "block";
            repassword.disabled = "true";
            bt1.disabled = "true";
        }else{
            pwd.className = "input";
            repassword.className = "input";
            pwdcheck.style.display = "none";
            pwdcheck1.style.display = "none";
            repassword.disabled = 0;
            bt1.disabled = 0;
        }
    }
    repassword.onkeyup = function() {
        var pwd1 = pwd.value;
        var pwd2 = this.value;
        if (pwd2 != pwd1) {
            pwd.className = "input_2";
            repassword.className = "input_1";
            repwdcheck.style.display = "block";
            bt1.disabled = "true";
        }else{
            pwd.className = "input";
            repassword.className = "input";
            repwdcheck.style.display = "none";
            bt1.disabled = 0;
        }
    }
    /* 判断按下回车键是否登录 */
	function enterLogin(){
		if(window.event.keyCode == 13){
			bt1.click();
		}
	}
    bt1.onclick = function() {
        if (userName.value == '' || realName.value == '' || pwd.value == '' || repassword.value == '' || sex == 3 || model ==
            '') {
            alert("填写内容不能为空！");
        } else {
            if (pwd.value == repassword.value) {
                var user = {
                    "userName": userName.value,
                    "realName": realName.value,
                    "password": pwd.value,
                    "rePassword": repassword.value,
                    "sex":sex,
                    "model":model
                };
                request("POST", "<%=basePath%>/userAPI/register",user,registerSuccess , serverError, true);
            } else {
                alert("两次填写的密码不相同！");
                pwd.value = '';
                repassword.value = '';
            }
        }
    }
    bt2.onclick = function() {
        window.location.href = "<%=basePath%>/jsp/login.jsp";
    }
    btman.onclick = function() {
        sex = 1;
        model = '';
        btman.className = "sexbtclick";
        btwoman.className = "sexbt";
        modelTypeMan.style.display = "block";
        modelTypeWoman.style.display = "none";
        modelWoman1.className = "modelunclick";
        modelWoman2.className = "modelunclick";
    }
    btwoman.onclick = function() {
        sex = 0;
        model = '';
        btman.className = "sexbt";
        btwoman.className = "sexbtclick";
        modelTypeMan.style.display = "none";
        modelTypeWoman.style.display = "block";
        modelMan1.className = "modelunclick";
        modelMan2.className = "modelunclick";
    }
    modelMan1.onclick = function() {
        model = "modelMan1";
        modelMan1.className = "modelclick";
        modelMan2.className = "modelunclick";
        modelWoman1.className = "modelunclick";
        modelWoman2.className = "modelunclick";
    }
    modelMan2.onclick = function() {
        model = "modelMan2";
        modelMan1.className = "modelunclick";
        modelMan2.className = "modelclick";
        modelWoman1.className = "modelunclick";
        modelWoman2.className = "modelunclick";
    }
    modelWoman1.onclick = function() {
        model = "modelWoman1";
        modelMan1.className = "modelunclick";
        modelMan2.className = "modelunclick";
        modelWoman1.className = "modelclick";
        modelWoman2.className = "modelunclick";
    }
    modelWoman2.onclick = function() {
        model = "modelWoman2";
        modelMan1.className = "modelunclick";
        modelMan2.className = "modelunclick";
        modelWoman1.className = "modelunclick";
        modelWoman2.className = "modelclick";
    }
    function registerSuccess(data) {
        if (data.code == 1) {
            window.location.href = "<%=basePath%>/jsp/login.jsp";
        } else if (data.code == -1) {
            alert(data.description);
        }
    }
    function serverError(XMLHttpRequest, textStatus) {
        alert("!!!");
    }
</script>
</html>
