# springboot

2019-03-16

**内容**

使用JdbcTemplate对Mysql进行操作

**问题**
* **写完数据库的配置和代码后,运行程序时发生了这么一个问题?就是有些类找不到?**</br>

解决:

* 把Application入口类写在包的最外层和dao,service,domain,controller同一级别
* 在Application类上添加注解@ComponentScan(basePackages = { "com.example.demo.controller", "com.example.demo.service", "com.example.demo.dao",
"com.example.demo.domain" }),在程序运行时扫描需要用到的包


* **解决完上面的问题后,在运行程序发现说找不到JdbcTemplate,需要去配置,但是在pom中配置好了,这是怎么回事呢?**

原因:在程序创建时我就一些数据库jar包,然后程序启动时,说没有设置数据库配置,这时在Application中的@SpringBootApplication上添加了exclude = { DataSourceAutoConfiguration.class },结果因为没有设置数据源程序运行时就一直包这个错,由于以前没有做过这些,所有不知道的配置它,后来发现网上说使用数据库需要配置数据源,但是使用JdbcTemplate时,第三方jar包会自动配置它,后再仔细查找了一遍代码发现这个地方多写了
