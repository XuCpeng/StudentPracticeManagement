# Student Practice Management 学生实践活动管理

使用了Springboot、Shiro、Kaptcha、druid等框架，实现了自定义aspect、自定义枚举类型响应代码、自定义异常。
结构清晰、符合RESTful规范，使用了大量教新的框架(在当时)，适合初学者使用。

- 能够录入学生活动信息
- 能够分角色对学生活动信息进行管理
- 能够导出活动证明文档

## How to use
1. 安装mysql，并创建一个名为`j2ee`的数据库
2. 如果mysql版本小于8.0，需要更换连接驱动。更改`build.gradle`中的`compile group: 'mysql', name: 'mysql-connector-java', version: '8.0.18'`的`version`,例如改为`5.1.48`
3. 修改`resources/application.yml`中的数据库配置，根据需要更改驱动名
4. 运行`J2eeApplication.java`即可
5. 访问`http://127.0.0.1:8080/j2ee`