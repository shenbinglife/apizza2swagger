#Apizza2Swagger

[Apizza](https://apizza.net) 是个api协作管理工具，提供Restful API接口文档管理的功能。  
为了方便迁移Apizza中的接口文档，开发了apizza2swagger工具,用来将apizza的接口文档导出为swagger2.0文档。

因为导出的文档可能存在问题，建议到[Swagger Editor](http://editor.swagger.io/)去查看下文档内容。

*目前只提供导出协作项目*

##使用方法
1. 运行CommandMain类的main方法
   ```
      ---------------Apizza导出为Swagger工具------------------
      启用默认？回车启用默认用户，输入任意值则不启用默认。q
      输入你的邮箱：qqq.com
      输入你的密码：qqq.com
      输入导出文件夹路径：d:
   ```
   
2. 打包
   ```
    mvn clean package -DskipTests
   ``` 
   运行apizza2swagger-1.0-SNAPSHOT.jar
   ```
    D:\workspace\gitlab\apizza2swagger\target> java -jar .\apizza2swagger-1.0-SNAPSHOT.jar
    ---------------Apizza导出为Swagger工具------------------
    启用默认？回车启用默认用户，输入任意值则不启用默认。q
    输入你的邮箱：qqq.com
    输入你的密码：qqq.com
    输入导出文件夹路径：d:
   ```