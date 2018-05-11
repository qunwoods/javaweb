# javaweb
### 数据库连接池
* 数据库连接性能瓶颈问题
JDBC1向数据库申请连接需要建立通讯、分配资源、权限认证等，影响性能，因此出现了**数据库连接池**
* 数据库连接池功能
	1. 对象容器：存储、管理建立好的连接对象；
	2. 对象容器的初始化、大小可设置
	3. 连接对象的获取、释放、超时回收（单子模式）；
* 文件目录
	1. dbpool.properties：数据库配置文件；
	2. ConnectionPool.java：连接池实现：
		1. readConfig：读取配置文件；
		2. addConnection：新建数据库连接并加入到连接池；
		3. getConnection：获取可用的连接对象；
		4. Release：释放并重新添加到连接池中；
		5. closePool：关闭连接池（关闭所有连接对象）；
		6. getInstance：静态方法获取当前ConnectionPool对象（单例模式）；
### cookie
### cookie产生
	1. Http协议无状态：为了提高http协议的效率，http不记录通信双方的状态；
	2. cookie：服务器通过浏览器存放在用户本地磁盘的文本文件，以key/value形式存储信息（**明文存储**），服务器可进行文件读写操作；
#### cookie应用场景：
	1. “记住我”：用户登录时，web应用可记录用户名和密码；
	2. 定制个性化页面；
	3. 记录用户操作；
#### cookie使用
	浏览器向web服务器发送请求时，会读取本地存储的cookie文件内容（包括过期时间和路径），一起发送给服务器；
#### 服务器设置和获取cookie
	文件目录：
	1. ServletSetCookie：服务器将cookie内容保存在浏览器所在的客户端；
	2. ServletGetCookie：由于保存cookie后，浏览器发送请求会在header中带上cookie信息，所以直接从request中获取cookie即可；
	3. CookieInput.html：输入姓名和密码，提交表单至服务器；
### session
#### 目标
* 使用session实现记录访客访问次数；
* 使用session实现购物车功能；
#### Session和cookie的区别与联系
* 联系：相辅相成，sessionId存储在cookie中，客户端发送请求时将cookie放在header中，服务器通过解析可获得用户的sessionId，取出服务器端存储的用户的相关信息；
> 对Tomcat而言，Session是一块在服务器开辟的内存空间，其存储结构为** ConcurrentHashMap**；
> [Tomcat中的Session小结 - 风一样的码农 - 博客园](https://www.cnblogs.com/chenpi/p/5434537.html)【分布式服务器之间的session共享问题】

* 区别：cookie存储在客户端、文本文件；session存储在服务器端、存储session对象；
* 生命周期：可设置；
### session实现访客记录
### session实现购物车
