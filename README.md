SpringBoot整合Thymeleaf入门项目案列

0. 参考：https://www.bilibili.com/video/BV1eX4y1A7Sv?p=1

1. 需求分析：分析这个项目含有的哪些功能模块
		用户模块：
			注册
			登录
			验证码
			安全退出
			真是用户
		员工模块：
			添加员工+上传头像
			展示员工列表+展示员工头像
			删除员工信息+删除员工头像
			更新员工信息+更新员工头像

2. 库表设计（概要设计）1.分析系统有哪些表 2.分析表与表关系 3.确定表中字段（显性字段 隐性字段（业务字段））
	2.1 数据表
		1.用户表 user
			（id, username, realname, password, gender）
		2.员工表 employee
			(id, name, salary, birthday, photo)
	
	2.2 创建数据库

3. 详细设计
		验证库表的正确性
		
4. 编码（环境搭建+业务代码开发）
	4.1 创建一个springboot项目（项目名字：ems-thymeleaf）
	4.2 修改配置文件为application.yml pom.xml 2.5.0
	4.3 修改端口: 8080 项目名：ems-thymeleaf
	4.4 springboot整合 thymeleaf 使用
		4.4.1 引入依赖
		4.4.2 配置文件中指定thymeleaf相关配置
		4.4.3 编写控制器测试
	4.5 springboot整合mybatis
		4.5.1 引入依赖
			mysql、druid、mybatis-springboot-starter
		4.5.2 配置相关文件
	4.6 导入项目页面
		static 存放静态资源
		templates 存放模版文件
		
=================
