# RESTfulFinal

#### 项目介绍
RESTful 大作业：试衣间

#### 项目规范
- 用户名密码长度为6-16位 
- 在session中保存已登录的用户实例 user: User
- 用户相关接口url：/userAPI/……
- 服装、服装类别相关接口url：/dressAPI/……
- 管理员相关接口url：/adminAPI/……
- 穿着服装相关接口url：/onWearAPI/……

#### 接口说明
##### /userAPI/register	用户注册
###### request

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|userName	|String		|用户名		|
|realName	|String		|用户真实姓名|
|password	|String		|密码		|
|rePassword	|String		|重复密码	|
|sex		|String		|性别		|
|headType	|int		|头像(模型)类别|
|model		|String		|			|
###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注		|
|-----------|-----------|-----------|
|1			|User对象	|注册成功	|
|-1			|null		|注册失败	|

###### 接口调试说明
```
调用userAPI的时候设置为 Method: post Content-Type: application/json 然后数据放放json字符串
发送的json格式：
{
	"userId": "0164888",
	"userName": "王老六",
	"realName": "王六",
	"password": "123456",
	"sex": "female",
	"headTypeId": 1
}
目前一些无需传参的userAPI如"/suit/admin/list"就传{} 
```
##### /userAPI/login	用户登录
###### request

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|userName	|String		|用户名		|
|realName	|String		|用户真实姓名|
|password	|String		|密码		|
|rePassword	|String		|重复密码	|
|sex		|String		|性别		|
|headType	|int		|头像(模型)类别|
###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|User对象	|注册成功			|
|-1			|根据表单生成的User对象|用户名或密码错误	|

##### /userAPI/updateInfo	更新用户信息（要求登录，即session中有user对象）
###### request

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|userName	|String		|用户名		|
|realName	|String		|用户真实姓名|
|password	|String		|密码，为空则不修改		|
|rePassword	|String		|重复密码，为空则不修改	|
|sex		|String		|性别		|
|headType	|int		|头像(模型)类别|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|User对象	|修改个人信息成功	|
|-1			|User对象	|修改个人信息失败	|

##### /userAPI/getInfo	获取用户信息（要求登录，即session中有user对象）
###### request

无需参数即可直接调用

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|User对象	|获取个人信息成功	|
|-1			|null		|获取个人信息失败（如：未登录、两次密码不一致）|

##### /dressAPI/addDressType	增加服装类别（要求管理员）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressTypeId	|String		|服装类别英文|
|dressTypeName	|String		|服装类别中文|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|DressType对象	|增加服装类别成功	|
|-1			|null		|增加服装类别失败（如：权限不够）|

##### /dressAPI/updateDressType	修改服装类别（要求管理员）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressTypeId	|String		|服装类别英文|
|dressTypeName	|String		|服装类别中文|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|DressType对象	|修改服装类别成功	|
|-1			|null		|修改服装类别失败（如：权限不够）|

##### /dressAPI/deleteDressType	 删除服装类别（要求管理员）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressTypeId	|String		|服装类别英文|
|dressTypeName	|String		|服装类别中文|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data			|备注				|
|-----------|-----------	|-------------------|
|1			|null			|删除服装类别成功	|
|-1			|null			|删除服装类别失败（如：权限不够）|

##### /dressAPI/getAllDressType	 获取所有服装类别
###### request

无需参数即可直接调用，且不要求管理员权限

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data			|备注				|
|-----------|-----------	|-------------------|
|1			|DressType数组	|获取所有服装类别成功	|
|-1			|null			|获取所有服装类别失败|


##### /dressAPI/addDress	增加服装（要求管理员）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressName		|String		|服装名称	|
|dress_url		|String		|服装图片url	|
|dressType		|String		|服装类别id	|
|dressPrice		|double		|服装价格	|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|Dress对象	|增加服装成功	|
|-1			|null		|增加服装失败（如：权限不够）|

##### /dressAPI/updateDress	修改服装（要求管理员）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressName		|String		|服装名称	|
|dress_url		|String		|服装图片url	|
|dressType		|String		|服装类别id	|
|dressPrice		|double		|服装价格	|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|Dress对象	|修改服装成功	|
|-1			|null		|修改服装失败（如：权限不够）|

##### /dressAPI/deleteDressType	 删除服装（要求管理员）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressName		|String		|服装名称	|
|dress_url		|String		|服装图片url	|
|dressType		|String		|服装类别id	|
|dressPrice		|double		|服装价格	|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data			|备注				|
|-----------|-----------	|-------------------|
|1			|null			|删除服装成功	|
|-1			|null			|删除服装失败（如：权限不够）|

##### /dressAPI/getAllDress	 获取所有服装
###### request

无需参数即可直接调用，且不要求管理员权限

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data			|备注				|
|-----------|-----------	|-------------------|
|1			|Dress数组		|获取所有服装成功	|
|-1			|null			|获取所有服装失败	|

#### 最新onWearAPI数据说明
##### /onWearAPI/addOnWear
---
* 注: 前端在请求的时候onWearId是一个对象, 两个属性 userName(String) 和 dressId(Integer/int)
---
* 当前onWearAPI的所有操作的response返回的格式都和getAllOnWear返回格式一致(无子对象)
---
```
request
{
    "onWearId": {
        "dressId": 222222
    },
    "layer": 5
}
response
{
    "code": 1,
    "description": "获取用户穿着服装成功",
    "data": [
        {
            "layer": 15,
            "userName": "adminadmin",
            "realName": "qwerasd",
            "password": "adminadmin",
            "sex": 0,
            "isAdmin": 1,
            "model": "modelWoman1",
            "dressId": 32,
            "dressName": "雀金裙",
            "dress_url": "wSkirt06.png",
            "dressType": "skirt",
            "dressPrice": 290
        },
        {
            "layer": 15,
            "userName": "adminadmin",
            "realName": "qwerasd",
            "password": "adminadmin",
            "sex": 0,
            "isAdmin": 1,
            "model": "modelWoman1",
            "dressId": 33,
            "dressName": "闪金裙",
            "dress_url": "wSkirt07.png",
            "dressType": "skirt",
            "dressPrice": 350
        }
    ],
    "nextAction": ""
}
```
##### /onWearAPI/deleteOnWear
```
request
{
    "onWearId": {
        "dressId": 222222
    }
}
response
和getAllOnWear的一致
```
##### /onWearAPI/dressMoveUp
```
request
delete 和 move up 和 move down
{
    "onWearId": {
        "dressId": 111111
    }
}
response
和getAllOnWear的一致
```
##### /onWearAPI/dressMoveDown
```
request
{
    "onWearId": {
        "dressId": 222222
    }
}
response
和getAllOnWear的一致
```
##### /onWearAPI/addOnWear	增加穿着服装（要求登录）
###### request


|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressId		|String		|服装id		|
|userName		|String		|用户名(从session获取)|
|layer			|int		|服装层次	|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|OnWear对象	|增加穿着服装成功	|
|-1			|null		|增加穿着服装失败（如：用户未登录）|

##### /onWearAPI/updateOnWear	修改穿着服装（要求登录）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressId		|String		|服装id		|
|userName		|String		|用户名(从session获取)|
|layer			|int		|服装层次	|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data		|备注				|
|-----------|-----------|-------------------|
|1			|OnWear对象	|修改穿着服装成功	|
|-1			|null		|修改穿着服装失败（如：未登录）|

##### /onWearAPI/deleteOnWear	 删除穿着服装（要求登录）
###### request

|参数名			|类型		|备注		|
|---------------|-----------|-----------|
|dressId		|String		|服装id		|
|userName		|String		|用户名(从session获取)|
|layer			|int		|服装层次	|

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data			|备注				|
|-----------|-----------	|-------------------|
|1			|null			|删除穿着服装成功	|
|-1			|null			|删除穿着服装失败（如：未登录）|

##### /onWearAPI/getAllOnWear	 获取所有穿着服装（要求登录）
###### request

无需参数即可直接调用

###### response

|参数名		|类型		|备注		|
|-----------|-----------|-----------|
|code		|int		|状态码		|
|description|String		|状态描述	|
|data		|Object		|相关数据对象|
|nextAction	|String 	|下一个动作	|

|code		|data			|备注				|
|-----------|-----------	|-------------------|
|1			|OnWear数组		|获取所有穿着服装成功	|
|-1			|null			|获取所有穿着服装失败|


