#EQ Demo Server Document
***
##Request Methods
###*GET* 用于从服务器获取某个资源的信息
- 完成请求后返回状态码 200 OK （HttpStatus.OK）
- 完成请求后需要返回被请求的资源详细信息
###*POST* 用于创建新资源
- 创建完成后返回状态码 201 Created （HttpStatus.CREATED）
- 完成请求后需要返回被创建的资源详细信息


##Host 地址
	http://54.238.250.170:8080


##数据格式
#### EQ 
	{
		volume:int,		音量 
		power:boolean,	电源 
		x: int,			warm & cold 
		y: int,			exciting & relaxed 
		z: int,			按钮大小 
	}
#### Tone
	请根据EQ计算，如 if x>0 than Tone=warm

##URL
#### POST *http://54.238.250.170:8080/avs/eq/status*	
- 功能描述
    - 用于提交当前数据到服务端，设置服务端的EQ数据，设置成功返回状态码 201和JSON格式EQ数据。每次语音请求都需要发送客户端初始状态到服务端。
- Request 	
	- POST方法 
    	- RequestBody  
    	    - JSON Object String 
- Response 
	- 状态码:HttpStatus.CREATED && ContentBody为JSON格式的EQ数据
    	- 成功获取设置服务端的EQ数据。

#### POST *http://54.238.250.170:8080/avs/eq/change*	
- 功能描述
    - 设置EQ数据的变化量，设置成功返回状态码 201和JSON格式EQ数据。可变化的参数包括音量，开关，x,y。不变的数值设定为0。
- Request 	
	- POST方法 
    	- RequestBody  
    	    - JSON Object String 
- Response 
	- 状态码:HttpStatus.CREATED && ContentBody为JSON格式的EQ数据
    	- 成功将数据变化量发送到服务端。
    	
#### GET *http://54.238.250.170:8080/avs/eq/client* 	
- 功能描述
    - 客户端获取服务端计算完的的EQ数据，**若数据无变动***（没有接收到改变量的POST请求）*则挂起1秒。当数据有变动时候返回JSON格式EQ数据，返回状态码 200。	
- Request 	
	- GET方法 	
	    - 无参数	
- Response 	
	- 状态码:HttpStatus.OK && ContentBody为JSON格式的EQ数据
	    - 成功获取服务端计算完的EQ数据。
#### GET *http://54.238.250.170:8080/avs/eq/ask* 	
- 功能描述
    - ASK端获取服务端计算完的的EQ数据，若**无最新客户端状态数据且无历史数据***（没有接收到客户端的POST请求发送的最新数据且历史数据为0）*则返回状态码404。当接收到最新数据有时候返回JSON格式EQ数据，返回状态码 200。	
- Request 	
	- GET方法 	
	    - 无参数	
- Response 	
	- 状态码:HttpStatus.OK && ContentBody为JSON格式的EQ数据
	    - 成功获取服务端计算完的EQ数据。
	- 状态码:HttpStatus.NOT_FOUND && ContentBody为空
	    - 成功获取服务端计算完的EQ数据。



#### POST *http://54.238.250.170:8080/avs/eq/reset* 	
- 功能描述
    - 重置服务端的EQ数据，设置成功返回状态码 201。
- Request 	
	- POST方法 
	    - 无参数	
- Response 
	- 状态码:HttpStatus.CREATED
    	- 成功重置服务端的EQ数据。

#### POST *http://54.238.250.170:8080/avs/eq/killthread* 	
- 功能描述
    - 结束服务端delay获取EQ数据的线程，设置成功返回状态码 201。
- Request 
	- POST方法 
	    - 无参数	
- Response 
	- 状态码:HttpStatus.CREATED
    	- 成功结束服务端等待EQ数据变化的线程。