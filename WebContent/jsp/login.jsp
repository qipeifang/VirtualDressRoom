<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + 
                                      request.getServerName() + ":" +
                                      request.getServerPort() + path;
%>
<html>
	<head>
		<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/main.js"></script>
		<meta charset="utf-8" />
		<title>登录界面</title>
		<link rel="stylesheet" href="<%=basePath%>/css/login.css" />
	</head>
	<body onkeydown="enterLogin()">
		<div id="outter">
			<center>
				<div id="loginDiv">
					<div id="title">
						<center><img style="margin-bottom: 8px;" src="../img/touxiang.png" /></center>
						<br />
						登录试衣间账号
					</div>
					<form action="" method="post">
						<div id="main">
							<input type="text" class="input" id="userName" placeholder="用户名">
							<input type="password" class="input" id="password" placeholder="密码 " />
							<input type="button" class="bt" id="bt1" value="登录" />
						</div>
					</form>
					<div id="footer">
						<input type="button" class="bt" id="bt2" value="注册" />
					</div>
				</div>
			</center>

		</div>

	</body>
	<script>
		var bt1 = document.getElementById("bt1");
		var bt2 = document.getElementById("bt2");
		var userName = document.getElementById("userName");
		var pwd = document.getElementById("password");
		/* 判断按下回车键是否登录 */
		function enterLogin(){
			if(window.event.keyCode == 13){
				bt1.click();
			}
		}
		bt1.onclick = function() {
			if (userName.value == '' || pwd.value == '') {
				alert("用户名或者密码不能为空！");
			} else {
			    var user = {
                    "userName": userName.value,
                    "password": pwd.value
                };
				request("POST", "<%=basePath%>/userAPI/login", user,loginSuccess , serverError, true);
			}
		}
		bt2.onclick = function() {
			window.location.href = "<%=basePath%>/jsp/register.jsp";
		}
		
		function loginSuccess(data) {
			if (data.code == 1) {
                window.location.href = "<%=basePath%>/jsp/main.jsp";
			} else if (data.code == -1) {
                alert(data.description);
				pwd.value = "";
			}
		}

		function serverError(XMLHttpRequest, textStatus) {
			alert("!!!");
		}

	</script>
</html>
