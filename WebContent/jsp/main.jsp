
<%@ page import="restful.bean.User"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" +
            request.getServerPort() + path;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=basePath%>/js/jquery-3.3.1.js" ></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.fileupload.js" ></script>
<script type="text/javascript" src="<%=basePath%>/js/main.js"></script>

<meta charset="UTF-8">
<title>主页面</title>
<link rel="stylesheet" href="<%=basePath%>/css/personInfo.css" />
<link rel="stylesheet" href="<%=basePath%>/css/userManage.css" />
<link rel="stylesheet" href="<%=basePath%>/css/suitManage.css" />
<link rel="stylesheet" href="<%=basePath%>/css/clothesManage.css" />
<link rel="stylesheet" href="<%=basePath%>/css/fittingManage.css" />

<style type="text/css">
body {
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
	border: none;
	background-image: url(../img/beijing-2.png);
}

#mainInfo {
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
	position: relative;
}

#header {
	width: 100%;
	height: 40px;
	padding-top: 10px;
	background-color: #bfbfbf;
	/* border-bottom: white solid 1px; */
	border-radius: 25px;
}

#menu {
	border-radius: 20px;
	background-color: #bfbfbf;
	width: 118px;
	height: 630px;
	text-align: center;
	padding-top: 18px;
	float: left;
	margin-left: 15px;
	margin-top: 15px;
}

.menuBt img:hover {
	background-color: #DCDCDC;
	/* -webkit-transform:scale(1.05); */
	cursor: pointer;
	border-radius: 10px;
}

.menuBt img {
	width: 85px;
	height: 85px;
	background-color: white;
	border-radius: 13px;
	margin-bottom: 16px;
}

#content {
	width: 100%;
	height: 700px;
}

</style>
</head>
<body>
<div id ="mainInfo">

		<!-- 主页顶部 -->
		<div id = "header">
			<span
				id = "headerUserName" style="font-family: TradeGothicforNike365-BdCn, Helvetica !important; font-size: 26px; font-weight: 700; color: #334DC4; letter-spacing: 3px; margin-bottom: 8px; padding-left: 30px;"></span>
			<span
				style="font-family: TradeGothicforNike365-BdCn, Helvetica !important; font-size: 26px; font-weight: 700; color: #334DC4; float: right; letter-spacing: 3px; margin-bottom: 8px; padding-right: 30px;">梦想试衣间</span>
		</div>
		
		<!--主页菜单栏  -->
		<div id = "menu">
			<div id="personInfo" class="menuBt">
				<img src="../img/person.png">
			</div>
			<div id="userManage" class="menuBt">
				<img src="../img/usermanage.png">
			</div>
			<div id="suitManage" class="menuBt">
				<img src="../img/suitType.png">
			</div>
			<div id="clothesManage" class="menuBt">
				<img src="../img/clothesManage.png">
			</div>
			<div id="fittingManage" class="menuBt">
				<img src="../img/shiyijian.png">
			</div>
			<div id="loginOut" class="menuBt">
				<img src="../img/menjian.png">
			</div>
		</div>
		
		<!-- 主页点击菜单显示区域 -->
		<div id="content">
		
			<!-- 个人信息内容开始 -->
			<div id="personInfoDiv">
				<div id="title">用户信息</div>
				<div id="main">
					<div>
						用户名称：<input type="text" class="input" id="userName" readonly>
					</div>
					<div>
						用户实名：<input type="text" class="input" id="realName" >
					</div>
					<font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="realNamecheck">用户实名不能为空！</font>
					<div>
						密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 码：<input type="password"
							class="input" id="password" />
					</div>
					<font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100;"
						id="pwdcheck">密码如果不需要修改请本项留空</font> <font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="pwdcheck1">密码长度不少于6位!</font> <font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="repwdcheck1">两次密码不一致!</font> <font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="pwdcheck2">密码长度不多于16位!</font>
					<div>
						密码确认：<input type="password" class="input_3" id="repassword" />
					</div>
					<font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100;"
						id="repwdcheck">密码如果不需要修改请本项留空</font>
					<div>
						<input type="button" id="btman" value="男" />
						&nbsp;&nbsp;&nbsp; <input type="button" id="btwoman" value="女" />
					</div>

					<div id="modelTypeMan" style="display: none;">
						<div
							style="font-family: TradeGothicforNike365-BdCn, Helvetica !important; font-size: 20px; font-weight: 700; color: #334DC4; margin-bottom: 8px;">模型选择</div>
						<button id="modelMan1">
							<img src="../img/mheadA.png">
						</button>
						&nbsp;&nbsp;&nbsp;
						<button id="modelMan2">
							<img src="../img/mheadB.png">
						</button>
					</div>
					<div id="modelTypeWoman" style="display: none;">
						<div
							style="font-family: TradeGothicforNike365-BdCn, Helvetica !important; font-size: 20px; font-weight: 700; color: #334DC4; margin-bottom: 8px;">模型选择</div>
						<button id="modelWoman1">
							<img src="../img/wheadA.png">
						</button>
						&nbsp;&nbsp;&nbsp;
						<button id="modelWoman2">
							<img src="../img/wheadB.png">
						</button>
					</div>
					<input type="button" class="bt" id="saveuserInfoBt" value="保存信息" />

				</div>
			</div>
			<!-- 个人信息内容结束 -->

			<!-- 用户信息管理开始 -->
			<div id="usersManageInfoDiv">
				<table>
					<thead>
						<tr>
							<th>id</th>
							<th>用户名称</th>
							<th>用户实名</th>
							<th>性别</th>
							<th>模型选择</th>
							<th>是否管理员</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody id="tableBody"></tbody>
				</table>
			</div>

			<div id="updatePersonInfoDiv">
				<div id="title">用户信息</div>
				<div id="main">
					<div>
						用户名称：<input type="text" class="input" id="adminuserName" readonly>
					</div>
					<div>
						用户实名：<input type="text" class="input" id="adminrealName">
					</div>
					<font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="adminrealNamecheck">用户实名不能为空！</font>
					<div>
						密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password"
							class="input" id="adminpassword" />
					</div>
					<font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100;"
						id="adminpwdcheck">密码如果不需要修改请本项留空</font> <font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="adminpwdcheck1">密码长度不少于6位!</font> <font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="adminrepwdcheck1">两次密码不一致!</font> <font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100; display: none;"
						id="adminpwdcheck2">密码长度不多于16位!</font>
					<div>
						密码确认：<input type="password" class="input_3" id="adminrepassword" />
					</div>
					<font
						style="color: red; font-size: 12px; padding-left: 50px; z-index: 100;"
						id="adminrepwdcheck">密码如果不需要修改请本项留空</font>
					<div>
						<input type="button" class="sexbt" id="adminbtman" value="男" />
						&nbsp;&nbsp;&nbsp; <input type="button" class="sexbt"
							id="adminbtwoman" value="女">
					</div>

					<div id="adminmodelTypeMan" style="display: none;">
						<div
							style="font-family: TradeGothicforNike365-BdCn, Helvetica !important; font-size: 20px; font-weight: 700; color: #334DC4; margin-bottom: 8px;">模型选择</div>
						<button id="adminmodelMan1">
							<img src="../img/mheadA.png">
						</button>
						&nbsp;&nbsp;&nbsp;
						<button id="adminmodelMan2">
							<img src="../img/mheadB.png">
						</button>
					</div>
					<div id="adminmodelTypeWoman" style="display: none;">
						<div
							style="font-family: TradeGothicforNike365-BdCn, Helvetica !important; font-size: 20px; font-weight: 700; color: #334DC4; margin-bottom: 8px;">模型选择</div>
						<button id="adminmodelWoman1">
							<img src="../img/wheadA.png">
						</button>
						&nbsp;&nbsp;&nbsp;
						<button id="adminmodelWoman2">
							<img src="../img/wheadB.png">
						</button>
					</div>
					<div id="adminDiv">
						是否是管理员：&nbsp;&nbsp;&nbsp; <label><input id="isAdmin"
							class="adminRadio" type="radio" name="adminRadio" />是</label> <label><input
							id="notAdmin" class="adminRadio" type="radio" name="adminRadio" />否</label>
					</div>
					<input type="button" class="bt" id="adminsavebt" value="保存信息" /> <input
						type="button" class="bt" id="admincloseWindow" value="关闭窗口" />
				</div>
			</div>
			<!-- 用户信息管理结束 -->

			<!-- 服装类别管理开始 -->
			<div id="suitManageInfoDiv">
				<div class="dressType" id="dressTypeAddDIv">
        		<div class="dressTitle" >服饰类别</div>
        		<div>编号：<input type="text" class="dressInput" id="dressId" ></div>
        		<div>名称：<input type="text" class="dressInput" id="dressName"></div>
        		<input type="button" class="dressAdd" id="dressAdd" value="添加" />
        		</div>
        	</div>
			<!--服装类别管理结束  -->
			
			<!-- 服饰管理开始 -->
			<div id="clothesManageInfoDiv">
	        	<div id="searchClothes">
	        		性别：
	        		<select name="sex" id="selectSexTop">
	        			<option value="男">男</option>
	        			<option value="女">女</option>
	        		</select>
	        		服饰类别：
	        		<select name="clothesType" id="selectTypeTop">
	        			<!--<option value="">饰品</option>-->
	        			
	        		</select>
	        		<input type="button" id="search" value="查询" />
	        	</div>
	        	<div id="clothesDetailAddDiv">
	        		<div class="dressTitle" >服饰细目</div>
	        		<div>编号：<input type="text" class="clothesInput" id="clothesId" ></div>
	        		<div>名称：<input type="text" class="clothesInput" id="clothesName"></div>
	        		<div>价格：<input type="text" class="clothesInput" id="clothesPrice"></div>
	        		<div>性别：<select name="sex" class="selectInput" id="selectSexLeft">
	        				<option value="男">男</option>
	        				<option value="女">女</option>
	        			</select>
	        		</div>
	        		<div>分类：<select name="clothesType" class="selectInput" id="selectTypeLeft">
		        			<!--<option value="">饰品</option>-->
		        			
	        			</select>
	        		</div>
	        		<input type="button" class="clothesAdd" id="clothesAdd" value="添加" />
	        	</div>
        		
        			<div id="allClothesDetail"></div>
        	</div>
			<!-- 服饰管理结束 -->

			<!--试衣间开始  -->
			<div id="fittingRoomDiv">
			<!-- A区 -->
        	<div id="alreadyDressedDiv">
        	
        		<div class="showAlreadyDress">
        			<div class="alDress">编号：<span class="dressContentId" id="dressContentId">adsda</span></div>
        			<div class="alDress">名称：<span class="dressContentName" id="dressContentName">裤子</span></div>
        			<div class="alDress">单价：<span class="dressContentPrice" id="dressContentPrice">1,1122.00</span></div>
        			<div class="manageImg">
        				<img src="img/zIndex.png" /><span class="zindexValue" id="zindexValue">0</span>
        				<button class="manageBt"><img src="img/up.png" /></button>
        				<button class="manageBt"><img src="img/down.png" /></button>
        				<button class="manageBt"><img src="img/remove.png" /></button>
        			</div>
        		</div>
        		
        	</div>
        	<!-- B区 -->
        	<div id="modelDisplayDiv">
        		<div>
        		<img id="ground" src="../img/ground.png" style="position: absolute;z-index: 0;width: 500px;height: 500px; margin-top: 140px;margin-left: -60px;"/>
        		<img id="modelShadow" src="../img/modelShadow.png" style="position: absolute;z-index: 0;"/>
        		<img id="basicModel" style="position: absolute;z-index: 0;"/>
        		</div>
        		<div id="modelOnwearDress">
        			<!--<img src="img/wSkirt01.png" style="position: absolute;z-index: 10;"/>-->
        		</div>
        		<div id="totlePriceDiv">
        			总价
        			<p id="totlePrice">0</p>
        		</div>
        	</div>
        	<!-- C区 -->
        	<div id="clothesSelectDiv">
        		<div id="chooseClothes">
        			选择分类：
        			<select name="type" id="chooseType" onchange="chooseDressByDressTypeName()"></select>
        		</div>
        		<div id="allClothesContent"></div>
        		 <div class="clothesContent" id="clothesContent">
        			<img src="../dressImgs/wSkirt01.png" />
        			<div class="alDress">编号：<span class="dressContentId">adsda</span></div>
        			<div class="alDress">名称：<span class="dressContentName">裤子</span></div>
        			<div class="alDress">单价：<span class="dressContentPrice">1122</span></div>
        		</div> 
        	</div>
        </div>
			<!--试衣间结束  -->
			
			<!--退出界面开始  -->
			<!--退出界面结束  -->
		</div>
	</div>
</body>
	<script language="JavaScript">
    var saveuserInfoBt = document.getElementById("saveuserInfoBt");
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
    var realNamecheck = document.getElementById("realNamecheck");
    var repassword = document.getElementById("repassword");
    var pwdcheck = document.getElementById("pwdcheck");
    var pwdcheck1 = document.getElementById("pwdcheck1");
    var pwdcheck2 = document.getElementById("pwdcheck2");
    var repwdcheck = document.getElementById("repwdcheck");
    var repwdcheck1 = document.getElementById("repwdcheck1");
    var sex = 3;
    var model;
    var listen;
    var personInfoDiv = document.getElementById("personInfoDiv");
    var personInfo = document.getElementById("personInfo");
    var usersManageInfoDiv = document.getElementById("usersManageInfoDiv");
    var userManage = document.getElementById("userManage");
    var suitManage = document.getElementById("suitManage");
    var suitManageInfoDiv = document.getElementById("suitManageInfoDiv");
    var menuImag1 = personInfo.getElementsByTagName("img")[0];
    var menuImag2 = userManage.getElementsByTagName("img")[0];
    var menuImag3 = suitManage.getElementsByTagName("img")[0];
   	
    /* 个人信息内容开始 */
    reloadUserInfo();
    personInfo.onclick = function(){
        if(personInfoDiv.style.display=="block"){
        	personInfoDiv.style.display="none";
        	menuImag1.style.backgroundColor = "white";
            listen = "0";
        }else{
        	if (listen == "1") {
        		usersManageInfoDiv.style.display = "none";
                menuImag2.style.backgroundColor = "white";
                updatePersonInfoDiv.style.display = "none";
                suitManageInfoDiv.style.display = "none";
                menuImag3.style.backgroundColor = "white";
                clothesManageInfoDiv.style.display="none";
				menuImag4.style.backgroundColor="white";
				fittingRoomDiv.style.display = "none";
				menuImag5.style.backgroundColor = "white";
				listen = "0";
            }
        	personInfoDiv.style.display="block";
        	menuImag1.style.backgroundColor = "#DCDCDC";
            listen = "1";
        }

    }
    realName.onkeyup = function(){
    	var realName1 = this.value;
    	if(realName1.length==0){
    		realNamecheck.style.display = "block";
    		saveuserInfoBt.disabled = "true";
    		realName.className = "input_2";
    	}else{
    		realNamecheck.style.display = "none";
    		saveuserInfoBt.disabled = 0;
    		realName.className = "input";
    	}
    }
    pwd.onkeyup = function() {
        var pwd1 = this.value;
        if (pwd1.length > 0 && pwd1.length < 6) {
            pwd.className = "input_2";
            repassword.className = "input_1";
            pwdcheck.style.display = "none";
            pwdcheck1.style.display = "block";
            pwdcheck2.style.display = "none";
            repwdcheck.style.display = "none";
            repassword.disabled = "true";
            saveuserInfoBt.disabled = "true";
        }else if (pwd1.length > 16){
            pwd.className = "input_2";
            repassword.className = "input_1";
            pwdcheck.style.display = "none";
            pwdcheck1.style.display = "none";
            pwdcheck2.style.display = "block";
            repwdcheck.style.display = "none";
            repassword.disabled = "true";
            saveuserInfoBt.disabled = "true";
        }else if(pwd1.length == 0){
            pwd.className = "input_2";
            repassword.className = "input_1";
            pwdcheck.style.display = "block";
            pwdcheck1.style.display = "none";
            pwdcheck2.style.display = "none";
            repwdcheck.style.display = "block";
            repassword.disabled = "true";
            repwdcheck1.style.display = "none";
            saveuserInfoBt.disabled = 0;
        }
        else if(pwd1.length >= 6 && pwd1.length <= 16){
            pwd.className = "input";
            repassword.className = "input";
            pwdcheck.style.display = "none";
            pwdcheck1.style.display = "none";
            pwdcheck2.style.display = "none";
            repwdcheck.style.display = "none";
            repassword.disabled = 0;
            saveuserInfoBt.disabled = 0;
        }
    }
    repassword.onkeyup = function() {
        var pwd1 = pwd.value;
        var pwd2 = this.value;
        if (pwd2 != pwd1) {
            pwd.className = "input_2";
            repassword.className = "input_1";
            repwdcheck1.style.display = "block";
            saveuserInfoBt.disabled = "true";
        }else{
            pwd.className = "input";
            repassword.className = "input";
            repwdcheck1.style.display = "none";
            saveuserInfoBt.disabled = 0;
        }
    }

    saveuserInfoBt.onclick = function() {
        if (pwd.value == '' &&repassword.value == '' &&realName.value == '' && sex == 3) {
            alert("填写内容不能为空！");
        }else if(model ==''){
        	alert("填写内容不能为空！");
        } else {
            if(pwd.value != ''&& repassword.value != ''){
                if (pwd.value == repassword.value) {
                    var user = {
                        "userName": userName.value,
                        "password": pwd.value,
                        "rePassword": repassword.value,
                        "realName": realName.value,
                        "sex": sex,
                        "model": model
                    };
                    request("POST", "<%=basePath%>/userAPI/updateInfo", user,UpadteSuccess , serverError, true);
                } else {
                    alert("两次填写的密码不相同！");
                    pwd.value = '';
                    repassword.value = '';
                }
            }else{
            	var user = {
                        "userName": userName.value,
                        "password": pwd.value,
                        "rePassword": repassword.value,
                        "realName": realName.value,
                        "sex": sex,
                        "model": model
                    };
                    request("POST", "<%=basePath%>/userAPI/updateInfo", user,UpadteSuccess , serverError, true);
            }

        }
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
    /* 加载用户信息 */
    function loadUserInfo(username,realname,sex,model){
    	var headerUserName = document.getElementById("headerUserName");
    	headerUserName.innerHTML = "当前用户："+username;
   		userName.value = username;
   		realName.value = realname;
   		if(sex == 1){
   			btman.className = "sexbtclick";
   			btwoman.className = "sexbt";
   			modelTypeMan.style.display = "block";
            modelTypeWoman.style.display = "none";
   			if(model == 'modelMan1'){
   				modelMan1.className = "modelclick";
   			}else{
   				modelMan2.className = "modelclick";
   			}
   		}else{
   			btwoman.className = "sexbtclick";
   			btman.className = "sexbt";
   			modelTypeMan.style.display = "none";
	        modelTypeWoman.style.display = "block";
   			if(model == 'modelWoman1'){
   				modelWoman1.className = "modelclick";
   			}else{
   				modelWoman2.className = "modelclick";
   			}
   		}
   	}
    /* 请求用户信息 */
    function reloadUserInfo(){
    	request("POST","<%=basePath%>/userAPI/getUserInfo", "",getUserInfoSuccess, serverError, true);
    }
    function getUserInfoSuccess(data){
    	if (data.code == 1) {
    		loadUserInfo(data.data.userName,data.data.realName,data.data.sex,data.data.model);
        } else if (data.code == -1) {
            alert(data.description);
        }
    }
    function userUpdate(userNameData,realNameData,sexData,modelData) {
        userName.value = userNameData;
        realName.value = realNameData;
        if (sexData == "1"){
            sex = 1;
            model = "";
            btman.className = "sexbtclick";
            btwoman.className = "sexbt";
            modelTypeMan.style.display = "block";
            modelTypeWoman.style.display = "none";
            modelWoman1.className = "modelunclick";
            modelWoman2.className = "modelunclick";
            if (modelData == "modelMan1" ){
                model = "modelMan1";
                modelMan1.className = "modelclick";
                modelMan2.className = "modelunclick";
                modelWoman1.className = "modelunclick";
                modelWoman2.className = "modelunclick";
            }else{
                model = "modelMan2";
                modelMan1.className = "modelunclick";
                modelMan2.className = "modelclick";
                modelWoman1.className = "modelunclick";
                modelWoman2.className = "modelunclick";
            }
        }else {
            sex = 0;
            model = "";
            btman.className = "sexbt";
            btwoman.className = "sexbtclick";
            modelTypeMan.style.display = "none";
            modelTypeWoman.style.display = "block";
            modelMan1.className = "modelunclick";
            modelMan2.className = "modelunclick";
            if (modelData == "modelWoman1"){
                model = "modelWoman1";
                modelMan1.className = "modelunclick";
                modelMan2.className = "modelunclick";
                modelWoman1.className = "modelclick";
                modelWoman2.className = "modelunclick";
            }else{
                model = "modelWoman2";
                modelMan1.className = "modelunclick";
                modelMan2.className = "modelunclick";
                modelWoman1.className = "modelunclick";
                modelWoman2.className = "modelclick";
            }
        }

    }
    function UpadteSuccess(data) {
        if (data.code == 1) {
            alert(data.description);
            userUpdate(data.data.userName,data.data.realName,data.data.sex,data.data.model);
            pwd.value = "";
            repassword.value = "";
        } else if (data.code == -1) {
            alert(data.description);
        }
    }
    /* 个人信息内容结束 */
    
    /*用户管理信息界面开始 */
    var updatePersonInfoDiv = document.getElementById("updatePersonInfoDiv");
    var adminbtman = document.getElementById("adminbtman");
    var adminbtwoman = document.getElementById("adminbtwoman");
    var adminmodelTypeMan = document.getElementById("adminmodelTypeMan");
    var adminmodelTypeWoman = document.getElementById("adminmodelTypeWoman");
    var adminmodelMan1 = document.getElementById("adminmodelMan1");
    var adminmodelMan2 = document.getElementById("adminmodelMan2");
    var adminmodelWoman1 = document.getElementById("adminmodelWoman1");
    var adminmodelWoman2 = document.getElementById("adminmodelWoman2");
    var adminuserName = document.getElementById("adminuserName");
    var adminpwd = document.getElementById("adminpassword");
    var adminrealName = document.getElementById("adminrealName");
    var adminrealNamecheck = document.getElementById("adminrealNamecheck");
    var adminrepassword = document.getElementById("adminrepassword");
    var adminpwdcheck = document.getElementById("adminpwdcheck");
    var adminpwdcheck1 = document.getElementById("adminpwdcheck1");
    var adminpwdcheck2 = document.getElementById("adminpwdcheck2");
    var adminrepwdcheck = document.getElementById("adminrepwdcheck");
    var adminrepwdcheck1 = document.getElementById("adminrepwdcheck1");
    var revise = document.getElementById("revise");
    var adminsavebt = document.getElementById("adminsavebt");
    var admincloseWindow = document.getElementById("admincloseWindow");
    var adminsex = 3;
    var adminmodel;
    var isAllUserInfo = 0;
    var adminIsAdmindata = 3;
    var isAdmin = document.getElementById("isAdmin");
    var notAdmin = document.getElementById("notAdmin");
    isAdmin.onclick = function() {
    	adminIsAdmindata = 1;
    }
    notAdmin.onclick = function() {
    	adminIsAdmindata = 0;
    }
    adminrealName.onkeyup = function(){
    	var adminrealName1 = this.value;
    	if(adminrealName1.length==0){
    		adminrealNamecheck.style.display = "block";
    		adminsavebt.disabled = "true";
    		adminrealName.className = "input_2";
    	}else{
    		adminrealNamecheck.style.display = "none";
    		adminsavebt.disabled = 0;
    		adminrealName.className = "input";
    	}
    }
    adminpwd.onkeyup = function() {
        var adminpwd1 = this.value;
        if (adminpwd1.length > 0 && adminpwd1.length < 6) {
        	adminpwd.className = "input_2";
        	adminrepassword.className = "input_1";
        	adminpwdcheck.style.display = "none";
        	adminpwdcheck1.style.display = "block";
        	adminpwdcheck2.style.display = "none";
        	adminrepwdcheck.style.display = "none";
        	adminrepassword.disabled = "true";
        	adminsavebt.disabled = "true";
        }else if (adminpwd1.length > 16){
        	adminpwd.className = "input_2";
        	adminrepassword.className = "input_1";
        	adminpwdcheck.style.display = "none";
        	adminpwdcheck1.style.display = "none";
        	adminpwdcheck2.style.display = "block";
        	adminrepwdcheck.style.display = "none";
        	adminrepassword.disabled = "true";
            adminsavebt.disabled = "true";
        }else if(adminpwd1.length == 0){
        	adminpwd.className = "input_2";
        	adminrepassword.className = "input_1";
        	adminpwdcheck.style.display = "block";
        	adminpwdcheck1.style.display = "none";
        	adminpwdcheck2.style.display = "none";
        	adminrepwdcheck.style.display = "block";
        	adminrepassword.disabled = "true";
        	adminrepwdcheck1.style.display = "none";
            adminsavebt.disabled = 0;
        }
        else if(adminpwd1.length >= 6 && adminpwd1.length <= 16){
        	adminpwd.className = "input";
        	adminrepassword.className = "input";
        	adminpwdcheck.style.display = "none";
        	adminpwdcheck1.style.display = "none";
        	adminpwdcheck2.style.display = "none";
        	adminrepwdcheck.style.display = "none";
        	adminrepassword.disabled = 0;
            adminsavebt.disabled = 0;
        }
    }
    adminrepassword.onkeyup = function() {
        var adminpwd1 = adminpwd.value;
        var adminpwd2 = this.value;
        if (adminpwd2 != adminpwd1) {
        	adminpwd.className = "input_2";
        	adminrepassword.className = "input_1";
        	adminrepwdcheck1.style.display = "block";
        	adminsavebt.disabled = "true";
        }else{
        	adminpwd.className = "input";
            adminrepassword.className = "input";
            adminrepwdcheck1.style.display = "none";
            adminsavebt.disabled = 0;
        }
    }

    adminsavebt.onclick = function() {
        if (adminpwd.value == '' &&adminrepassword.value == '' &&adminrealName.value == '' && adminsex == 3 ) {
            alert("填写内容不能为空！");
        } else if(adminmodel ==''){
        	alert("填写内容不能为空！");
        } else {
            if(adminpwd.value != ''&& adminrepassword.value != ''){
                if (adminpwd.value == adminrepassword.value) {
                    var user = {
                        "userName": adminuserName.value,
                        "password": adminpwd.value,
                        "rePassword": adminrepassword.value,
                        "realName": adminrealName.value,
                        "sex": adminsex,
                        "model": adminmodel,
                        "isAdmin":adminIsAdmindata
                    };
                    request("POST", "<%=basePath%>/adminAPI/updateUser", user,adminUpadteSuccess , serverError, true);
                } else {
                    alert("两次填写的密码不相同！");
                    adminpwd.value = '';
                    adminrepassword.value = '';
                }
            }else{
            	var user = {
                        "userName": adminuserName.value,
                        "password": adminpwd.value,
                        "rePassword": adminrepassword.value,
                        "realName": adminrealName.value,
                        "sex": adminsex,
                        "model": adminmodel,
                        "isAdmin":adminIsAdmindata
                    };
                    request("POST", "<%=basePath%>/adminAPI/updateUser", user,adminUpadteSuccess , serverError, true);
            }

        }
    }

    adminbtman.onclick = function() {
    	adminsex = 1;
    	adminmodel = '';
        adminbtman.className = "sexbtclick";
        adminbtwoman.className = "sexbt";
        adminmodelTypeMan.style.display = "block";
        adminmodelTypeWoman.style.display = "none";
        adminmodelWoman1.className = "modelunclick";
        adminmodelWoman2.className = "modelunclick";
    }
    adminbtwoman.onclick = function() {
    	adminsex = 0;
    	adminmodel = '';
        adminbtman.className = "sexbt";
        adminbtwoman.className = "sexbtclick";
        adminmodelTypeMan.style.display = "none";
        adminmodelTypeWoman.style.display = "block";
        adminmodelMan1.className = "modelunclick";
        adminmodelMan2.className = "modelunclick";
    }
    adminmodelMan1.onclick = function() {
    	adminmodel = "modelMan1";
        adminmodelMan1.className = "modelclick";
        adminmodelMan2.className = "modelunclick";
        adminmodelWoman1.className = "modelunclick";
        adminmodelWoman2.className = "modelunclick";
    }
    adminmodelMan2.onclick = function() {
    	adminmodel = "modelMan2";
        adminmodelMan1.className = "modelunclick";
        adminmodelMan2.className = "modelclick";
        adminmodelWoman1.className = "modelunclick";
        adminmodelWoman2.className = "modelunclick";
    }
    adminmodelWoman1.onclick = function() {
    	adminmodel = "modelWoman1";
        adminmodelMan1.className = "modelunclick";
        adminmodelMan2.className = "modelunclick";
        adminmodelWoman1.className = "modelclick";
        adminmodelWoman2.className = "modelunclick";
    }
    adminmodelWoman2.onclick = function() {
    	adminmodel = "modelWoman2";
        adminmodelMan1.className = "modelunclick";
        adminmodelMan2.className = "modelunclick";
        adminmodelWoman1.className = "modelunclick";
        adminmodelWoman2.className = "modelclick";
    }
    
    function openAdminUpdate(adName){
    	updatePersonInfoDiv.style.display="block";
    	var userinfo =  document.getElementById(adName); 
		var cells = userinfo.getElementsByTagName("td");
		for(var i=0;i<cells.length;i++){
			if(i == 1){
				var adName = cells[i].innerHTML;
			}else if(i == 2){
				var adRealName = cells[i].innerHTML;
			}else if(i == 3){
				var adSex = cells[i].innerHTML;
			}else if(i == 4){
				if(cells[i].innerHTML =='<img src="../img/mheadA.png">'){
					var adType="modelMan1"
				}else if(cells[i].innerHTML =='<img src="../img/mheadB.png">'){
					var adType="modelMan2"
				}else if(cells[i].innerHTML =='<img src="../img/wheadA.png">'){
					var adType="modelWoman1"
				}else if(cells[i].innerHTML =='<img src="../img/wheadB.png">'){
					var adType="modelWoman2"
				}
			}else if(i == 5){
					var isAdmin = cells[i].innerHTML;
				}
			}
		adminuserUpdate(adName,adRealName,adSex,adType,isAdmin);
	}

    function adminuserUpdate(userNameData,realNameData,sexData,modelData,isAdmindata) {
    	adminuserName.value = userNameData;
    	adminrealName.value = realNameData;
        if (sexData == "男"){
        	adminsex = 1;
        	adminmodel = "";
        	adminbtman.className = "sexbtclick";
        	adminbtwoman.className = "sexbt";
        	adminmodelTypeMan.style.display = "block";
        	adminmodelTypeWoman.style.display = "none";
        	adminmodelWoman1.className = "modelunclick";
        	adminmodelWoman2.className = "modelunclick";
        	if(isAdmindata == "是"){
        		adminIsAdmindata = 1;
        		isAdmin.checked = true;
        		notAdmin.checked = false;
        	}else{
        		adminIsAdmindata = 0;
        		isAdmin.checked = false;
        		notAdmin.checked = true;
        	}
            if (modelData == "modelMan1" ){
            	adminmodel = "modelMan1";
            	adminmodelMan1.className = "modelclick";
            	adminmodelMan2.className = "modelunclick";
            	adminmodelWoman1.className = "modelunclick";
            	adminmodelWoman2.className = "modelunclick";
            }else{
            	adminmodel = "modelMan2";
            	adminmodelMan1.className = "modelunclick";
            	adminmodelMan2.className = "modelclick";
            	adminmodelWoman1.className = "modelunclick";
            	adminmodelWoman2.className = "modelunclick";
            }
        }else {
        	adminsex = 0;
        	adminmodel = "";
        	adminbtman.className = "sexbt";
        	adminbtwoman.className = "sexbtclick";
        	adminmodelTypeMan.style.display = "none";
        	adminmodelTypeWoman.style.display = "block";
        	adminmodelMan1.className = "modelunclick";
        	adminmodelMan2.className = "modelunclick";
            if (modelData == "modelWoman1"){
            	adminmodel = "modelWoman1";
            	adminmodelMan1.className = "modelunclick";
            	adminmodelMan2.className = "modelunclick";
            	adminmodelWoman1.className = "modelclick";
            	adminmodelWoman2.className = "modelunclick";
            }else{
            	adminmodel = "modelWoman2";
            	adminmodelMan1.className = "modelunclick";
            	adminmodelMan2.className = "modelunclick";
            	adminmodelWoman1.className = "modelunclick";
            	adminmodelWoman2.className = "modelclick";
            }
        }

    }
    
    
    function adminUpadteSuccess(data) {
        if (data.code == 1) {
            alert(data.description);
            UpdateInfoInSuface(data.data.userName,data.data.realName,data.data.sex,data.data.model,data.data.isAdmin);
            if(data.data.sex=="1"){
            	data.data.sex = "男";
    		}else{
    			data.data.sex = "女";
    		}
    		if(data.data.isAdmin == 1){
    			data.data.isAdmin = "是";
    		}else{
    			data.data.isAdmin = "否";
    		}
            adminuserUpdate(data.data.userName,data.data.realName,data.data.sex,data.data.model,data.data.isAdmin);
            adminpwd.value = "";
            adminrepassword.value = "";
        } else if (data.code == -1) {
            alert(data.description);
        }
    }
    
    admincloseWindow.onclick = function() {
        updatePersonInfoDiv.style.display = "none";
        adminpwd.value = '';
        adminrepassword.value = '';
        adminpwd.className = "input_2";
    	adminrepassword.className = "input_1";
    	adminpwdcheck.style.display = "block";
    	adminpwdcheck1.style.display = "none";
    	adminpwdcheck2.style.display = "none";
    	adminrepwdcheck.style.display = "block";
    	adminrepwdcheck1.style.display = "none";
        adminsavebt.disabled = 0;
    }
    
    userManage.onclick = function() {
        request("POST", "<%=basePath%>/adminAPI/getAllUser","",getAllUserSuccess , serverError, true);
    }
    
	function openuserManag() {
        if (usersManageInfoDiv.style.display == "block") {
            usersManageInfoDiv.style.display = "none";
            if(updatePersonInfoDiv.style.display = "block"){
            	updatePersonInfoDiv.style.display = "none";
            }
            menuImag2.style.backgroundColor = "white";
            listen = "0";
        } else {
            if (listen == "1") {
            	personInfoDiv.style.display = "none";
                menuImag1.style.backgroundColor = "white";
                suitManageInfoDiv.style.display = "none";
                menuImag3.style.backgroundColor = "white";
                clothesManageInfoDiv.style.display="none";
				menuImag4.style.backgroundColor="white";
				fittingRoomDiv.style.display = "none";
				menuImag5.style.backgroundColor = "white";
				listen = "0";
            }
            usersManageInfoDiv.style.display = "block";
            menuImag2.style.backgroundColor = "#DCDCDC";
            listen = "1";
        }
    }
	
    function getAllUserSuccess(data){
        if (data.code == 1) {
        	openuserManag();
        	if(isAllUserInfo == 0){
				for(var p in data.data){
					addTableList(data.data[p].id, data.data[p].userName, data.data[p].realName, data.data[p].sex, data.data[p].model, data.data[p].isAdmin);
					isAllUserInfo = 1;
				}
			}
        } else if (data.code == -1) {
            alert(data.description);
        }
    }

    
    var tableBody = document.getElementById("tableBody");
    function deleteTableList(adName){
		var aa = document.getElementById(adName);
		aa.parentNode.removeChild(aa);
	}
    function addTableList(adId, adName, adRealName, adSex, adType, isAdmin) {
		var tr = document.createElement("tr");
		tr.id = adName;
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		var td4 = document.createElement("td");
		var td5 = document.createElement("td");
		var td6 = document.createElement("td");
		var td7 = document.createElement("td");
		var input1 = document.createElement("input");
		var input2 = document.createElement("input");
		input1.type = "button";
		input1.id = "revise";
		input1.value = "修改";
		input1.onclick = function () {
			openAdminUpdate(adName);
		}
		input2.type = "button";
		input2.type = "button";
		input2.id = "delet";
		input2.value = "删除";
        input2.onclick = function () {                     
            adminDelete(adName);
        };
		td1.innerHTML = adId;
		td2.innerHTML = adName;
		td3.innerHTML = adRealName;
		if(adSex=="1"){
			adSex = "男";
		}else{
			adSex = "女";
		}
		if(isAdmin == 1){
			isAdmin = "是";
		}else{
			isAdmin = "否";
		}
		td4.innerHTML = adSex;
		if(adType=="modelMan1"){
			td5.innerHTML ='<img src="../img/mheadA.png">';
		}else if(adType=="modelMan2"){
			td5.innerHTML ='<img src="../img/mheadB.png">';
		}else if(adType=="modelWoman1"){
			td5.innerHTML ='<img src="../img/wheadA.png">';
		}else if(adType=="modelWoman2"){
			td5.innerHTML ='<img src="../img/wheadB.png">';
		}
		td6.innerHTML = isAdmin;
		td7.appendChild(input1);
		td7.appendChild(input2);
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
		tr.appendChild(td7);
		tableBody.appendChild(tr);
	}
    
    function adminDelete(userName){
        if (confirm("确定删除用户"+userName+"吗？")){
        	var user = {
                "userName": userName
        	};
            request("POST","<%=basePath%>/adminAPI/deleteUser", user,deleteUserSuccess, serverError, true);
		}
	}
	function deleteUserSuccess(data) {
		if (data.code == 1) {
			alert(data.description);
			deleteTableList(data.data.userName)
		} else if (data.code == -1) {
			alert(data.description);
		}
	}
	function UpdateInfoInSuface(UpdatedUserName, UpdatedRealName, UpdatedSex,
			UpdatedModel, UpdatedIsAdmin) {
		
		var userinfo = document.getElementById(UpdatedUserName);
		var cells = userinfo.getElementsByTagName("td");
		for (var i = 0; i < cells.length; i++) {
			if (i == 1) {
				cells[i].innerHTML = UpdatedUserName;
			} else if (i == 2) {
				cells[i].innerHTML = UpdatedRealName;
			} else if (i == 3) {
				if (UpdatedSex == "1") {
					UpdatedSex = "男";
				} else {
					UpdatedSex = "女";
				}
				cells[i].innerHTML = UpdatedSex;
			} else if (i == 4) {
				if (UpdatedModel == "modelMan1") {
					cells[i].innerHTML = '<img src="../img/mheadA.png">';
				} else if (UpdatedModel == "modelMan2") {
					cells[i].innerHTML = '<img src="../img/mheadB.png">';
				} else if (UpdatedModel == "modelWoman1") {
					cells[i].innerHTML = '<img src="../img/wheadA.png">';
				} else if (UpdatedModel == "modelWoman2") {
					cells[i].innerHTML = '<img src="../img/wheadB.png">';
				}
			} else if (i == 5) {
				if (UpdatedIsAdmin == 1) {
					UpdatedIsAdmin = "是";
				} else {
					UpdatedIsAdmin = "否";
				}
				cells[i].innerHTML = UpdatedIsAdmin;
			}
		}
		adminuserUpdate(UpdatedUserName, UpdatedRealName, UpdatedSex, UpdatedModel,UpdatedIsAdmin);
	}

	/*用户管理信息界面结束*/

	/* 服装类别管理开始  */
	var dressId = document.getElementById("dressId");
   	var dressName = document.getElementById("dressName");
   	var dressAdd = document.getElementById("dressAdd");
   	var isAllDressType = 0;
	suitManage.onclick = function() {
		request("POST","<%=basePath%>/dressAPI/getAllDressType", "",getAllDressTypeSuccess, serverError, true);
	}
	/* 打开服装类别管理界面  */
	function openSuitManage(){
		if (suitManageInfoDiv.style.display == "block") {
			suitManageInfoDiv.style.display = "none";
			menuImag3.style.backgroundColor = "white";
			listen = "0";
		} else {
			if (listen == "1") {
				personInfoDiv.style.display = "none";
				menuImag1.style.backgroundColor = "white";
				usersManageInfoDiv.style.display = "none";
				menuImag2.style.backgroundColor = "white";
				updatePersonInfoDiv.style.display = "none";
				clothesManageInfoDiv.style.display="none";
				menuImag4.style.backgroundColor="white";
				fittingRoomDiv.style.display = "none";
				menuImag5.style.backgroundColor = "white";
				listen = "0";
			}
			suitManageInfoDiv.style.display = "block";
			menuImag3.style.backgroundColor = "#DCDCDC";
			listen = "1";
		}
	}
	/* 判断是否获取所有服装类别成功并作出响应*/
	function getAllDressTypeSuccess(data){
   		if (data.code == 1) {
   			openSuitManage();
   			if(isAllDressType == 0){
   				for(var p in data.data){
   					showDressTypeInfo(data.data[p].dressTypeId,data.data[p].dressTypeName);
   					isAllDressType = 1;
   				}
   			}
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	/*删除服装类别  */
   	function deleteDressType(dressTypeId,dressName){
   		var dressType = {
                "dressTypeId": dressTypeId,
                "dressTypeName": dressName
        };
   		request("POST","<%=basePath%>/dressAPI/deleteDressType", dressType,deleteDressTypeSuccess, serverError, true);
	}
   	/* 判断是否删除服装类别成功并作出响应*/
   	function deleteDressTypeSuccess(data){
   		if (data.code == 1) {
			alert(data.description);
			var dressTypeId = document.getElementById(data.data.dressTypeId);
			dressTypeId.parentNode.removeChild(dressTypeId);
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	/* 动态生成服装类别信息div */
   	function showDressTypeInfo(id,name){
   		var divDress = document.createElement("div");
   		var divTitle = document.createElement("div");
   		var divId = document.createElement("div");
   		var divName = document.createElement("div");
   		var inputId = document.createElement("input");
   		var inputName = document.createElement("input");
   		var inputSave = document.createElement("input");
   		var inputDelete = document.createElement("input");
   		divDress.id = id;
   		divDress.className = "dressType";
   		divTitle.className = "dressTitle";
   		divTitle.innerHTML = "服饰类别"
   		inputId.type = "text";
   		inputId.id = "dressIdInfo"+id;
   		inputId.className = "dressInput";
   		inputId.value = id;
   		inputId.setAttribute("readOnly",true); 
   		inputName.type = "text";
   		inputName.id = "dressNameInfo"+id;
   		inputName.className = "dressInput";
   		inputName.value = name;
   		inputSave.type = "button";
   		inputSave.id = "dressSave"+id;
   		inputSave.className = "dressSave";
   		inputSave.value = "保存";
   		inputSave.onclick = function(){
   			if(inputName.value==''){
   				alert("填写内容不能为空！");
   			}else{
   				var dressType = {
   	                    "dressTypeId": inputId.value,
   	                    "dressTypeName": inputName.value
   	            };
   				request("POST","<%=basePath%>/dressAPI/updateDressType", dressType,updateDressSuccess, serverError, true);
   			}
   		}
   		inputDelete.type = "button";
   		inputDelete.id = "dressdelete";
   		inputDelete.className = "dressdelete";
   		inputDelete.value = "删除";
   		inputDelete.onclick = function(){
   			deleteDressType(id,name);
   		}
   		divId.innerHTML = "编号：";
   		divId.appendChild(inputId);
   		divName.innerHTML = "名称：";
   		divName.appendChild(inputName);
   		divDress.appendChild(divTitle);
   		divDress.appendChild(divId);
   		divDress.appendChild(divName);
   		divDress.appendChild(inputSave);
   		divDress.appendChild(inputDelete);
   		suitManageInfoDiv.appendChild(divDress);
   		
   	}
   	/* 在前端的服装类别做出修改*/
   	function updateDressInSuface(updatedDressId,updatedDressName){
   		document.getElementById("dressNameInfo"+updatedDressId).value = updatedDressName;
   	}
   	/* 判断是否修改成功并作出响应*/
   	function updateDressSuccess(data){
   		if (data.code == 1) {
			updateDressInSuface(data.data.dressTypeId,data.data.dressTypeName);
	   		dressId.value = '';
			dressName.value = '';
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	/* 判断是否显示服装类别信息iv*/
   	function IsshowDressTypeInfo(){
   		if(dressId.value == ''||dressName.value == ''){
   			alert("填写内容不能为空！");
   		}else{
   			var dressType = {
                    "dressTypeId": dressId.value,
                    "dressTypeName": dressName.value
            };
   			request("POST","<%=basePath%>/dressAPI/addDressType", dressType,addDressTypeSuccess, serverError, true);
   		}
   	}
   	/* 判断是否添加成功并作出响应*/
   	function addDressTypeSuccess(data){
   		if (data.code == 1) {
			alert(data.description);
			showDressTypeInfo(data.data.dressTypeId,data.data.dressTypeName);
	   		dressId.value = '';
			dressName.value = '';
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	/*点击添加按钮显示服装类别信息  */
   	dressAdd.onclick = function(){
   		IsshowDressTypeInfo();
   	}
   	
	/* 服装类别管理结束  */
	
	/* 服饰管理开始 */
	var clothesManage = document.getElementById("clothesManage");
   	var clothesManageInfoDiv = document.getElementById("clothesManageInfoDiv");
   	var menuImag4 = clothesManage.getElementsByTagName("img")[0];
   	var clothesId = document.getElementById("clothesId");
   	var clothesName = document.getElementById("clothesName");
   	var clothesPrice = document.getElementById("clothesPrice");
   	var selectSexTop = document.getElementById("selectSexTop");
   	var selectSexLeft = document.getElementById("selectSexLeft");
   	var selectSexContent = document.getElementById("selectSexContent"); 
   	var selectTypeTop = document.getElementById("selectTypeTop");
   	var selectTypeLeft = document.getElementById("selectTypeLeft");
   	var selectTypeContent = document.getElementById("selectTypeContent");
   	var clothesAdd = document.getElementById("clothesAdd");
   	var addimg = document.getElementById("addImg");
   	var uploadImg = document.getElementById("uploadImg");
   	var addSelect = document.getElementById("addSelect");
   	var search = document.getElementById("search");
   	var dressTypeMap = new Map();
   	var reDressTypeMap = new Map();
   	var allClothesDetail = document.getElementById("allClothesDetail");
   	
   	/*删除所有的option*/
   	function removeAllOption(){
   		selectTypeTop.options.length=0;
   		selectTypeLeft.options.length=0;
   		dressTypeMap.clear();
   		reDressTypeMap.clear();
   	}
	/*显示服饰管理界面*/
	function openclothesManage(data){
		if (clothesManageInfoDiv.style.display == "block") {
			clothesManageInfoDiv.style.display = "none";
			menuImag4.style.backgroundColor = "white";
			removeAllOption();
			removeAllClothes();
			listen = "0";
		} else {
			if (listen == "1") {
				personInfoDiv.style.display = "none";
				menuImag1.style.backgroundColor = "white";
				usersManageInfoDiv.style.display = "none";
				menuImag2.style.backgroundColor = "white";
				updatePersonInfoDiv.style.display = "none";
				suitManageInfoDiv.style.display = "none";
				menuImag3.style.backgroundColor = "white";
				fittingRoomDiv.style.display = "none";
				menuImag5.style.backgroundColor = "white";
				listen = "0";
				removeAllOption();
				removeAllClothes();
			}
			clothesManageInfoDiv.style.display = "block";
			menuImag4.style.backgroundColor = "#DCDCDC";
			listen = "1";
			for(var p in data.data){
				dressTypeMap.set(data.data[p].dressTypeName, data.data[p].dressTypeId);
				reDressTypeMap.set(data.data[p].dressTypeId, data.data[p].dressTypeName);
   				addClothesTypeOption(data.data[p].dressTypeName);
			}
		}
	}
	/*点击menu第四个图片*/
   	clothesManage.onclick = function(){
   		request("POST","<%=basePath%>/dressAPI/getAllDressType", "",getAllDressTypeSuccessOnClothesManage, serverError, true);
   	}
   	/* 判断是否获取衣服种类成功并作出响应*/
   	function getAllDressTypeSuccessOnClothesManage(data){
   		if (data.code == 1) {
   			openclothesManage(data);
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	/*点击查询显示信息*/
   	search.onclick = function(){
   		removeAllClothes();
   		selectSexLeft.value = selectSexTop.value;
   		selectTypeLeft.value = selectTypeTop.value;
   		request("POST","<%=basePath%>/dressAPI/getAllDress", "",getAllDressSuccess, serverError, true);
   	}
   	/* 判断是否获取衣服成功并作出响应*/
   	function getAllDressSuccess(data){
   		if (data.code == 1) {
   			if(selectSexTop.value == '男'){
   				var dressSex = 1;
   			}else{
   				var dressSex = 0;
   			}
   			for(var p in data.data){
   				if(data.data[p].sex == dressSex && data.data[p].dressType == dressTypeMap.get(selectTypeTop.value)){
   					addClothesDetailInfo(data.data[p].dressId,data.data[p].dressName,data.data[p].dressPrice,selectSexTop.value,selectTypeTop.value,data.data[p].dress_url);
   				}
			}
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	
   	/*点击添加类别*/
   	function addClothesTypeOption(clothesValue){
   		var optionClothes1 = document.createElement("option");
   		var optionClothes2 = document.createElement("option");
   		var optionClothes3 = document.createElement("option");
   		optionClothes1.innerHTML = clothesValue;
   		optionClothes2.innerHTML = clothesValue;
   		optionClothes3.innerHTML = clothesValue;
   		selectTypeTop.appendChild(optionClothes1);
   		selectTypeLeft.appendChild(optionClothes2);
   	}
   	
   	/*删除单个服饰细目*/
   	function deleteClothesDetail(clothesDetailId){
   		var dress = {
	                "dressId": clothesDetailId
	            };
   		request("POST","<%=basePath%>/dressAPI/deleteDress", dress,deleteDressSuccess, serverError, true);
	}
   	/* 判断是否删除单个服饰细目成功并作出响应*/
   	function deleteDressSuccess(data){
   		if (data.code == 1) {
   			alert(data.description);
   			var clothesDetailId = document.getElementById(data.data.dressId);
   			clothesDetailId.parentNode.removeChild(clothesDetailId);
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	
   	/*动态生成服饰细目*/
   	function addClothesDetailInfo(id,name,price,sex,type,url){
   		var divClothes = document.createElement("div");
   		var divTitle = document.createElement("div");
   		var divClothesLeft = document.createElement("div");
   		var divId = document.createElement("div");
   		var divName = document.createElement("div");
   		var divPrice = document.createElement("div");
   		var divSex = document.createElement("div");
   		var divType = document.createElement("div");
   		var divClothesRight = document.createElement("div");
   		var divAdd = document.createElement("div");
   		var divImg = document.createElement("div");
   		var inputId = document.createElement("input");
   		var inputName = document.createElement("input");
   		var inputPrice = document.createElement("input");
   		var inputFile = document.createElement("input");
   		var inputSave = document.createElement("input");
   		var inputDelete = document.createElement("input");
   		var selectSex = document.createElement("select");
   		var selectType = document.createElement("select");
   		var optionBoy = document.createElement("option");
   		var optionGirl = document.createElement("option");
   		divClothes.id = id;
   		divClothes.className = "clothesDetailInfoDiv";
   		divTitle.className = "dressTitle";
   		divTitle.innerHTML = "服饰细目"
   		divClothesLeft.style.float = "left";
   		inputId.type = "text";
   		inputId.id = "clothesInfoId"+id;
   		inputId.className = "clothesInput";
   		inputId.value = id;
   		inputId.readOnly = "readonly";
   		inputName.type = "text";
   		inputName.id = "clothesInfoName"+id;
   		inputName.className = "clothesInput";
   		inputName.value = name;
   		inputPrice.type = "text";
   		inputPrice.id = "clothesInfoPrice"+id;
   		inputPrice.className = "clothesInput";
   		inputPrice.value = price;
   		selectSex.name = "sex";
   		selectSex.className = "selectInput";
   		selectSex.id = "selectSexContent"+id;
   		optionBoy.innerHTML = "男";
   		optionGirl.innerHTML = "女";
   		selectSex.appendChild(optionBoy);
   		selectSex.appendChild(optionGirl);
   		selectSex.value = sex;
   		selectType.name = "clothesType";
   		selectType.className = "selectInput";
   		selectType.id = "selectTypeContent"+id;
   		var options = selectTypeLeft.getElementsByTagName("option");
   		for (var i=0;i<options.length;i++) {
   			var copyOption = document.createElement("option");
   			copyOption.innerHTML = options[i].innerHTML;
   			selectType.appendChild(copyOption);
   		}
   		selectType.value = type;
   		inputFile.type = "file";
   		inputFile.className = "uploadImg";
   		inputFile.id = "uploadImg"+id;
   		inputSave.type = "button";
   		inputSave.id = "clothesSave";
   		inputSave.className = "clothesSave";
   		inputSave.value = "保存";
   		inputSave.onclick = function(){
   			if(inputName.value==''||inputPrice.value==''){
   				alert("填写内容不能为空！");
   			}else{
   				if(selectSex.value == "男"){
   					var updateSex = 1;
   				}else{
   					var updateSex = 0;
   				}
   				updateDress(inputId.value,inputName.value,inputPrice.value,updateSex,dressTypeMap.get(selectType.value));
   			}
   		}
   		inputDelete.type = "button";
   		inputDelete.id = "clothesDelete";
   		inputDelete.className = "clothesDelete";
   		inputDelete.value = "删除";
   		inputDelete.onclick = function(){
   			deleteClothesDetail(id);
   		}
   		divId.innerHTML = "编号：";
   		divId.appendChild(inputId);
   		divName.innerHTML = "名称：";
   		divName.appendChild(inputName);
   		divPrice.innerHTML = "价格：";
   		divPrice.appendChild(inputPrice);
   		divSex.innerHTML = "性别：";
   		divSex.appendChild(selectSex);
   		divType.innerHTML = "分类：";
   		divType.appendChild(selectType);
   		divClothesLeft.appendChild(divId);
   		divClothesLeft.appendChild(divName);
   		divClothesLeft.appendChild(divPrice);
   		divClothesLeft.appendChild(divSex);
   		divClothesLeft.appendChild(divType);
   		divAdd.id = "addImg";
   		divAdd.innerHTML = "点击添加图片";
   		divImg.id = "imageInfo"+id;
   		divImg.className = "ImgInfoDiv";
   		if(url != ""){
   			divImg.innerHTML = "<img src=../dressImgs/"+url+">";
   		}else{
   			divImg.innerHTML = "<img src=../dressImgs/unknown.png>";
   		}
   		divAdd.onclick = function(){
   			inputFile.click();
   			$.inputDressImg(id);
   		}
   		divClothesRight.id = "clothesImageInfo";
   		divClothesRight.appendChild(divAdd);
   		divClothesRight.appendChild(inputFile);
   		divClothesRight.appendChild(divImg);
   		divClothes.appendChild(divTitle);
   		divClothes.appendChild(divClothesLeft);
   		divClothes.appendChild(divClothesRight);
   		divClothes.appendChild(inputSave);
   		divClothes.appendChild(inputDelete);
   		allClothesDetail.appendChild(divClothes);
   	}
   	
   	/* 上传图片 */
   	$.inputDressImg = function(id){
			$('#uploadImg'+id).fileupload({
  	    	url: "/suit/userAPI/upload",
  	    	autoUpload: true,
  	        dataType: 'json',
  	        done: function (e, data) {
  	            console.log({"code":0,"description":data.result.description+"上传成功！"});
  	            imgs=data.result.description;
  	      		var divImg=document.getElementById("imageInfo"+id);
  	      		divImg.innerHTML = "<img src=../dressImgs/"+data.result.description+">";
  	        }
  		 	});
	}
   	/*修改服装*/
   	function updateDress(id,name,price,sex,type){
   		var imgDiv=document.getElementById("imageInfo"+id);
   		var img = imgDiv.getElementsByTagName("img")[0];
   		var path=img.src;
   		if(path.indexOf("/")>0){
   		    filename=path.substring(path.lastIndexOf("/")+1,path.length);
   		}else{
   		    filename="unknown.png";
   		}
   		//alert(id+" "+name+" "+price+" "+sex+" "+type+" "+filename);
   		var dress = {
	                "dressId": id,
	                "dressName": name,
	              	"dressType": type,
	           		"dressPrice": price,
	           		"sex": sex,
	         		"dress_url": filename
	            };
   		request("POST","<%=basePath%>/dressAPI/updateDress", dress,updateDressSuccess, serverError, true);
   	}
   	/*在前端完成更新*/
   	function updateDressInSuface(id,name,price,sex,type){
   		if(sex != selectSexTop.value||type != selectTypeTop.value){
   			var clothesDetailId = document.getElementById(id);
   			clothesDetailId.parentNode.removeChild(clothesDetailId);
   		}else{
   			var clothesInfoName = document.getElementById("clothesInfoName"+id);
   			var clothesInfoPrice = document.getElementById("clothesInfoPrice"+id);
   			var selectSexContent = document.getElementById("selectSexContent"+id);
   			var selectTypeContent = document.getElementById("selectTypeContent"+id);
   			clothesInfoName.value = name;
   			clothesInfoPrice.value = price;
   			selectSexContent.value = sex;
   			selectTypeContent.value = type;
   		}
   	}
   	/*判断修改是否成功并做出响应*/
   	function updateDressSuccess(data){
   		if (data.code == 1) {
   			alert(data.description);
   			if(data.data.sex == 1){
   				var sex = "男";
   			}else{
   				var sex = "女";
   			}
   			updateDressInSuface(data.data.dressId,data.data.dressName,data.data.dressPrice,sex,reDressTypeMap.get(data.data.dressType));
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	/*判断输入价格是否为数字*/
   	function isNotNumber(priceDate){
   		if(parseFloat(priceDate).toString() == "NaN"){
   			alert("价格一栏请输入数字！");
   			return false;
   		}else{
   			return true;
   		}
   	}
   	
   	/*判断添加内容是否为空  */
   	function IsshowClothesDetailInfo(){
   		if(clothesId.value == ''||clothesName.value == ''||clothesPrice.value == ''){
   			alert("填写内容不能为空！");
   			clothesId.value = '';
   			clothesName.value = '';
   			clothesPrice.value = '';
   		}else{
   			if(isNotNumber(clothesPrice.value)){
   				if(selectSexLeft.value == '男'){
   	   				var dressSex = 1;
   	   			}else{
   	   				var dressSex = 0;
   	   			}
   				var dress = {
   	                "dressId": clothesId.value,
   	                "dressName": clothesName.value,
   	              	"dressType": dressTypeMap.get(selectTypeLeft.value),
   	           		"dressPrice": clothesPrice.value,
   	           		"sex": dressSex,
   	         		"dress_url": ""
   	            };
   				request("POST","<%=basePath%>/dressAPI/addDress", dress,addDressSuccess, serverError, true);
   			}
   			clothesId.value = '';
   			clothesName.value = '';
   			clothesPrice.value = '';
   		}
   	}
   	/* 判断是否添加服饰细目成功并作出响应*/
   	function addDressSuccess(data){
   		if (data.code == 1) {
   			if(data.data.sex == "1"){
   				var dressSex = '男';
   			}else{
   				var dressSex = '女';
   			}
   			addClothesDetailInfo(data.data.dressId,data.data.dressName,data.data.dressPrice,dressSex,reDressTypeMap.get(data.data.dressType),data.data.dress_url);
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	/* 点击添加按钮绑定事件 */
   	clothesAdd.onclick = function(){
   		IsshowClothesDetailInfo();
   	}
   	
   	/*删除所有服饰细目*/
   	function removeAllClothes(){
   		while(allClothesDetail.hasChildNodes()){
   			allClothesDetail.removeChild(allClothesDetail.firstChild);
   		}
   	}
   	
	/* 服饰管理结束 */
	
	/* 试衣间开始  */
	var fittingManage = document.getElementById("fittingManage");
  	var fittingRoomDiv = document.getElementById("fittingRoomDiv");
  	var menuImag5 = fittingManage.getElementsByTagName("img")[0];
  	var alreadyDressedDiv = document.getElementById("alreadyDressedDiv");
	var totlePrice = document.getElementById("totlePrice");
	var nomalTotalPrice = 0;
	var userSex = 3;
	var dressTypeMapOnFittingRoom = new Map();
	var modelOnwearDress = document.getElementById("modelOnwearDress");
  	/*点击menu显示试衣间界面*/
   	function openFittingRoom(){
		if (fittingRoomDiv.style.display == "block") {
			fittingRoomDiv.style.display = "none";
			menuImag5.style.backgroundColor = "white";
			listen = "0";
		} else {
			if (listen == "1") {
				personInfoDiv.style.display = "none";
				menuImag1.style.backgroundColor = "white";
				usersManageInfoDiv.style.display = "none";
				menuImag2.style.backgroundColor = "white";
				updatePersonInfoDiv.style.display = "none";
				suitManageInfoDiv.style.display = "none";
				menuImag3.style.backgroundColor = "white";
				clothesManageInfoDiv.style.display = "none";
				menuImag4.style.backgroundColor = "white";
				listen = "0";
			}
			fittingRoomDiv.style.display = "block";
			menuImag5.style.backgroundColor = "#DCDCDC";
			listen = "1";
		}
	}
   	fittingManage.onclick = function(){
   		cleanFittingRoom();
   		request("POST","<%=basePath%>/onWearAPI/getAllOnWear", "",getAllOnWearSuccess, serverError, true);
   	}
  	//清空模特模型
  	function removeBasicModel(){
  		basicModel.src = '';
  	}
  	//清空总价
  	function resetTotalPrice(){
  		nomalTotalPrice = 0;
  		var price = 0;
  		totlePrice.innerHTML = price.formatMoney(2,'¥',',','.');
  	}
  	//清空模特身上所有衣服
  	function removeAllModelOnwear(){
  		while(modelOnwearDress.hasChildNodes()){
   			modelOnwearDress.removeChild(modelOnwearDress.firstChild);
   		}
  	}
  	//清空A区域所有已着装的服饰
  	function removeAllAlreadyDress(){
  		while(alreadyDressedDiv.hasChildNodes()){
   			alreadyDressedDiv.removeChild(alreadyDressedDiv.firstChild);
   		}
  	}
  	
   	//清理试衣间页面
   	function cleanFittingRoom(){
   		//清除右边栏的服饰信息
   		removeDressTypeInfo();
   		//还原默认性别
   		userSex = 3;
   		//清除option
   		removeChooseOption();
   		//清除Map
   		dressTypeMapOnFittingRoom.clear();
   		//清除左边栏的已穿服饰
   		removeAllAlreadyDress();
   		//还原角色模型
   		removeBasicModel();
   		//清除中间角色所穿的衣服
   		removeAllModelOnwear();
   		//清空总价
   		resetTotalPrice();
   	}
   	//判断获取所有穿上的衣服成功并做出响应
   	function getAllOnWearSuccess (data){
   		openFittingRoom();
   		if (data.code == 1) {
   			request("POST","<%=basePath%>/userAPI/getUserInfo", "",getUserInfoOnfittingManage, serverError, true);
   			if(data.data&&data.data!=""){
   	   			for(var p in data.data){
   	   				showAlreadyDressedInfo(data.data[p].dressId,data.data[p].dressName,data.data[p].dressPrice,data.data[p].layer);
   	   				addModelDress(data.data[p].dressId,data.data[p].dress_url,data.data[p].layer);
   				}
   	   			request("POST","<%=basePath%>/dressAPI/getAllDressType", "",getAllDressTypeSuccessOnfittingManage, serverError, true);
   	   			request("POST","<%=basePath%>/dressAPI/getAllDress", "",getAllDressSuccessOnfittingManage, serverError, true);
   			}else{
   			}
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	function getUserInfoOnfittingManage(data){
   		if (data.code == 1) {
   			addBasicModel(data.data.model);
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
  //获取不同种类的衣服
   	function chooseDressByDressTypeName(){
   	 removeDressTypeInfo();
   		request("POST","<%=basePath%>/dressAPI/getAllDress", "",getAllDressSuccessOnfittingManage, serverError, true);
   	}
   	//获取对应种类的服饰成功并做出响应
   	function getAllDressSuccessOnfittingManage(data){
   		if (data.code == 1) {
   			for(var p in data.data){
   				if(data.data[p].sex == userSex && data.data[p].dressType == dressTypeMapOnFittingRoom.get(chooseType.value)){
   					addDressDetailInfo(data.data[p].dressId,data.data[p].dressName,data.data[p].dressPrice,data.data[p].dress_url);
   				}
   			}
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
  //判断获取所有衣服类别成功并做出响应
   	function getAllDressTypeSuccessOnfittingManage(data){
   		if (data.code == 1) {
   			for(var p in data.data){
   				dressTypeMapOnFittingRoom.set(data.data[p].dressTypeName, data.data[p].dressTypeId);
   	   			addDressChooseOption(data.data[p].dressTypeName);
   			}
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
   	//脱衣
   	function deleteDress(id){
   		var onWearId = {
				"onWearId":{"dressId": id}
		};
   		request("POST","<%=basePath%>/onWearAPI/deleteOnWear", onWearId,deleteOnWearSuccess, serverError, true);
   	}
   	//在页面上脱衣
   	function deleteDressOnSuface(id,price){
   		var dressBox = document.getElementById(id);
   		nomalTotalPrice = Number(nomalTotalPrice) - Number(price);
   		totlePrice.innerHTML = nomalTotalPrice.formatMoney(2,'¥',',','.');
   		dressBox.parentNode.removeChild(dressBox);
   		removeModelDress(id);
   	}
  //判断脱衣成功并做出响应
   	function deleteOnWearSuccess(data){
   		if (data.code == 1) {
   			deleteDressOnSuface(data.data.dressId,data.data.dressPrice);
		} else if (data.code == -1) {
			alert(data.description);
		}
   	}
	//生成已经穿上的衣服的div
	function showAlreadyDressedInfo(id,name,price,zIndex){
		var divAlreadyDress = document.createElement("div");
		var divId = document.createElement("div");
		var divName = document.createElement("div");
		var divPrice = document.createElement("div");
		var divManageImg = document.createElement("div");
		var spanId = document.createElement("span");
		var spanName = document.createElement("span");
		var spanPrice = document.createElement("span");
		var spanZindex = document.createElement("span");
		var btUpIndex = document.createElement("button");
		var btDownIndex = document.createElement("button");
		var btRemove = document.createElement("button");
		divAlreadyDress.id = id;
		spanId.className = "dressContentId";
		spanId.innerHTML = id;
		spanName.className = "dressContentName";
		spanName.innerHTML = name;
		spanPrice.className = "dressContentPrice";
		spanPrice.innerHTML = price.formatMoney(2,'¥',',','.');
		spanZindex.className = "zindexValue";
		spanZindex.id = "zindex"+id;
		spanZindex.innerHTML = zIndex;
		divId.className = "alDress";
		divId.innerHTML = "编号：";
		divId.appendChild(spanId);
		divName.className = "alDress";
		divName.innerHTML = "名称：";
		divName.appendChild(spanName);
		divPrice.className = "alDress";
		divPrice.innerHTML = "单价：";
		divPrice.appendChild(spanPrice);
		btUpIndex.className = "manageBt";
		btUpIndex.innerHTML = "<img src='../images/ui/up.png' />";
		btUpIndex.onclick = function(){
			upDressZindex(id);
		}
		btDownIndex.className = "manageBt";
		btDownIndex.onclick = function(){
			downDressZindex(id);
		}
		btDownIndex.innerHTML = "<img src='../images/ui/down.png' />";
		btRemove.onclick = function(){
			deleteDress(id);
		}
		btRemove.className = "manageBt";
		btRemove.innerHTML = "<img src='../images/ui/remove.png' />";
		divManageImg.className = "manageImg";
		divManageImg.innerHTML = "<img src='../images/ui/zIndex.png' />";
		divManageImg.appendChild(spanZindex);
		divManageImg.appendChild(btUpIndex);
		divManageImg.appendChild(btDownIndex);
		divManageImg.appendChild(btRemove);
		divAlreadyDress.className = "showAlreadyDress";
		divAlreadyDress.appendChild(divId);
		divAlreadyDress.appendChild(divName);
		divAlreadyDress.appendChild(divPrice);
		divAlreadyDress.appendChild(divManageImg);
		alreadyDressedDiv.appendChild(divAlreadyDress);
		nomalTotalPrice = Number(nomalTotalPrice) + Number(price);
		totlePrice.innerHTML = nomalTotalPrice.formatMoney(2,'¥',',','.');
	}
	
	var modelDisplayDiv = document.getElementById("modelDisplayDiv");
	//添加模特衣服
  	function addModelDress(id,url,zindex){
  		var imgDress = document.createElement("img");
  		imgDress.id = "img"+id;
  		imgDress.src = "../dressImgs/"+url;
  		imgDress.style.position = "absolute";
  		imgDress.style.zIndex = zindex;
  		modelOnwearDress.appendChild(imgDress);
  	}
  	
  	//删除模特衣服
  	function removeModelDress(id){
  		var imgDress = document.getElementById("img"+id);
  		imgDress.parentNode.removeChild(imgDress);
  	}
  	
  	//修改衣服zindex
  	//up
  	function upDressZindex(id){
  		var onWearId = {
				"onWearId":{"dressId": id}
		};
  		request("POST","<%=basePath%>/onWearAPI/dressMoveUp", onWearId,dressMoveUpSuccess, serverError, true);
  	}
  	function dressMoveUpSuccess(data){
  		if (data.code == 1) {
  			var imgDress = document.getElementById("img"+data.data.dressId);
  	  		var zindex = imgDress.style.zIndex;
  	  		imgDress.style.zIndex = Number(zindex) + 1;
  	  		var dressZindex = document.getElementById("zindex"+data.data.dressId);
  	  		dressZindex.innerHTML = Number(dressZindex.innerHTML) + 1;
		} else if (data.code == -1) {
			alert(data.description);
		}
  	}
  	//修改衣服zindex
  	//down
  	function downDressZindex(id){
  		var onWearId = {
				"onWearId":{"dressId": id}
		};
  		request("POST","<%=basePath%>/onWearAPI/dressMoveDown", onWearId,dressMoveDownSuccess, serverError, true);
  	}
  	function dressMoveDownSuccess(data){
  		if (data.code == 1) {
  			var imgDress = document.getElementById("img"+data.data.dressId);
  	  		var zindex = imgDress.style.zIndex;
  	  		imgDress.style.zIndex = Number(zindex) - 1;
  	  		var dressZindex = document.getElementById("zindex"+data.data.dressId);
	  		dressZindex.innerHTML = Number(dressZindex.innerHTML) - 1;
		} else if (data.code == -1) {
			alert(data.description);
		}
  	}
  	
  	var allClothesContent = document.getElementById("allClothesContent");
  	var chooseType = document.getElementById("chooseType");
  	var basicModel = document.getElementById("basicModel");
  	//显示模特身体类型
  	function addBasicModel(modelType){
  		if(modelType == "modelMan1"){
  			userSex = 1;
  			basicModel.src = "../img/mheadAModel.png";
  		}else if(modelType == "modelMan2"){
  			userSex = 1;
  			basicModel.src = "../img/mheadBModel.png";
  		}else if(modelType == "modelWoman1"){
  			userSex = 0;
  			basicModel.src = "../img/wheadAModel.png";
  		}else if(modelType == "modelWoman2"){
  			userSex = 0;
  			basicModel.src = "../img/wheadBModel.png";
  		}
  	}
  	//添加衣服的选择分类
  	function addDressChooseOption(dressValue){
   		var optionDress = document.createElement("option");
   		optionDress.innerHTML = dressValue;
   		chooseType.appendChild(optionDress);
   	}
  	
  	/*清空选择分类的所有的option*/
   	function removeChooseOption(){
   		chooseType.options.length=0;
   	}
   	
  	//添加分类中的衣服的具体信息
  	function addDressDetailInfo(id,name,price,url){
  		var divClothesContent = document.createElement("div");
  		var divaddOnDress = document.createElement("div");
		var divId = document.createElement("div");
		var divName = document.createElement("div");
		var divPrice = document.createElement("div");
		var spanId = document.createElement("span");
		var spanName = document.createElement("span");
		var spanPrice = document.createElement("span");
		spanId.className = "dressContentId";
		spanId.innerHTML = id;
		spanName.className = "dressContentName";
		spanName.innerHTML = name;
		spanPrice.className = "dressContentPrice";
		spanPrice.innerHTML = price.formatMoney(2,'¥',',','.');
		divaddOnDress.className = "addOnDressDiv";
		divaddOnDress.id = "addOnDress"+id;
		divaddOnDress.innerHTML = "<img src='../img/addOnDress.png' />";
		divaddOnDress.onclick = function(){
			var onWearId = {
					"onWearId":{"dressId": id}
			};
			request("POST","<%=basePath%>/onWearAPI/addOnWear",onWearId,addOnWearSuccess, serverError, true);
		}
		divId.className = "alDress";
		divId.innerHTML = "编号：";
		divId.appendChild(spanId);
		divName.className = "alDress";
		divName.innerHTML = "名称：";
		divName.appendChild(spanName);
		divPrice.className = "alDress";
		divPrice.innerHTML = "单价：";
		divPrice.appendChild(spanPrice);
		divClothesContent.className = "clothesContent";
		divClothesContent.id = "clothesContent"+id;
		divClothesContent.innerHTML = "<img src=../dressImgs/"+url+">";
		divClothesContent.appendChild(divaddOnDress);
		divClothesContent.appendChild(divId);
		divClothesContent.appendChild(divName);
		divClothesContent.appendChild(divPrice);
		allClothesContent.appendChild(divClothesContent);
  	}
  	//判断穿衣是否成功并作出响应
  	function addOnWearSuccess(data){
  		if (data.code == 1) {
  			addModelDress(data.data.dressId,data.data.dress_url,0);
			showAlreadyDressedInfo(data.data.dressId,data.data.dressName,data.data.dressPrice,0);
		} else if (data.code == -1) {
			alert(data.description);
		}
  	}
  	
  	//清空所有分类后的衣服信息
  	function removeDressTypeInfo(){
  		var dressDiv = allClothesContent.getElementsByTagName("div");
   		for (var i = 0; i < dressDiv.length;i) {
   			dressDiv[i].parentNode.removeChild(dressDiv[i]);
   		}
   	}
  	
	/* 试衣间结束  */

	/* 退出界面开始 */
	var loginOut = document.getElementById("loginOut");
	loginOut.onclick = function(){
		request("POST","<%=basePath%>/userAPI/logout","",loginOutSuccess, serverError, true);
	}
	function loginOutSuccess(data){
		alert(data.description);
		window.location.href = "<%=basePath%>/jsp/login.jsp";
	}
	/* 退出界面结束  */

	function serverError(XMLHttpRequest, textStatus) {
		alert("!!!");
	}
</script>
</html>