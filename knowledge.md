# SpringBoot

## root package结构

![结构图](http://file.youdianpinwei.com/ypw/1b8bba47-22b3-4f42-821e-908578237d30.png)
1. Application.java 建议放到根目录下面,主要用于做一些框架配置

2. domain目录主要用于实体（Entity）与数据访问层（Repository）

3. service 层主要是业务类代码

4. controller 负责页面访问控制

## pom.xml配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
 
    <groupId>springboot</groupId>
    <artifactId>springboot-helloworld</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot-helloworld :: HelloWorld Demo</name>
 
    <!-- Spring Boot 启动父依赖 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.3.3.RELEASE</version>
    </parent>
 
    <dependencies>
        <!-- Spring Boot web依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
 
        <!-- Junit测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>
</project>
```
只要加入一个SpringBoot启动父依赖就可以了

**屏蔽命令行**
`SpringApplication.setAddCommandLineProperties(false)`

## 自动配置
> Spring Boot 提供了对应用进行自动化配置。相比以前 XML 配置方式，很多显式方式申明是不需要的
> Spring Boot 提供了默认的配置，如默认的 Bean ，去运行 Spring 应用。它是非侵入式的，只提供一个默认实现。大多数情况下，自动配置的 Bean 满足了现有的业务场景，不需要去覆盖。但如果自动配置做的不够好，需要覆盖配置

Spring Boot 不单单从 application.properties 获取配置，所以我们可以在程序中多种设置配置属性。按照以下列表的优先级排列：
1. 命令行参数
2. java:comp/env 里的 JNDI 属性
3. JVM系统属性
4. 操作系统环境变量
5. RandomValuePropertySource 属性类生成的 random.* 属性
6. 应用以外的 application.properties（或 yml）文件
7. 打包在应用内的 application.properties（或 yml）文件
8. 在应用 @Configuration 配置类中，用 @PropertySource 注解声明的属性文件
9. SpringApplication.setDefaultProperties 声明的默认属性

可见，命令行参数优先级最高。这个可以根据这个优先级，可以在测试或生产环境中快速地修改配置参数值，而不需要重新打包和部署应用
还有第 6 点，根据这个在多 moudle 的项目中，比如常见的项目分 api 、service、dao 等 moudles，往往会加一个 deploy moudle 去打包该业务各个子 moudle，应用以外的配置优先





## 注解

lombok
* @Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
* @Setter：注解在属性上；为属性提供 setting 方法
* @Getter：注解在属性上；为属性提供 getting 方法
* @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
* @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
* @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
* @ComponentScan:@ComponentScan告诉Spring 哪个注解标识的类会被spring自动扫描并且装入bean容器。如果你有个类用@Controller注解标识了，那么，如果不加上@ComponentScan自动扫描该controller，那么该Controller就不会被spring扫描到，更不会装入spring容器中，Controller就不会起作用


* @RestController:Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
* @RequestMapping:配置url映射 ,提供路由信息,"/“路径的HTTP Request都会被映射到它注解的函数中进行处理。
* @SpringBootApplication：Spring Boot 应用的标识; Application很简单，一个main函数作为主入口。SpringApplication引导应用，并将Application本身作为参数传递给run方法。具体run方法会启动嵌入式的Tomcat并初始化Spring环境及其各Spring组件
* @RequestParam:url连接的参数注解;name和value等价都是 xxx?key=content中的key,defauleValue为默认值,即没有值得时候把它赋给函数,required判断改参数是否必须
* @EnableSwagger2Doc // 开启 Swagger
* @Controller修饰class，用来创建处理http请求的对象

* @PathVaribale 获取url中的数据xxx/username....

```java
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable Long id) {
		return bookService.findById(id);
	}
```
* @RequestParam 注解接受的是来自于requestHeader中，即请求头，也就是在url中，格式为xxx?username=123&password=456;也同样支持multipart/form-data请求,适用于name-valueString类型的请求域;

```java
	/**
	 * http://localhost:8080/book/get?id=3
	 * 获取数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Book getBook1(@RequestParam Long id) {
		return bookService.findById(id);
	}
	

	 /**
     * 上传字符串
     * @param stringFile
     * @param bucket
     * @return
     */
    @RequestMapping("uploadStringFile")
    public JsonResult uploadStringFile(@RequestParam("stringFile") String stringFile, @RequestParam("bucket") String bucket){

        String fileUrl = aliossService.uploadStringFile(stringFile, bucket);
        Map<String,String> result = new HashMap<>();
        result.put("fileUrl",fileUrl);

        return success(result);
    }
```
* @GetMapping 组合注解，是 @RequestMapping(method = RequestMethod.GET) 的缩写
* @RequestBody 注解接收的参数则是来自于requestBody中，即请求体中

```java
	/**
	 * 创建 Book 处理 "/book/create" 的 POST 请求，用来新建 Book 信息 通过 @RequestBody
	 * 绑定实体参数，也通过 @RequestParam 传递参数
	 * </p>
	 * 
	 * raw json(application/json) {"id":1, "name":"book1", "writer":"fengzi1",
	 * "introduction":"aaaaaaaaaaa"}
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Book postBook(@RequestBody Book book) {
		return bookService.insertByBook(book);
	}
```
* @RequestAttribute
* @RequestHeader 可以把Request请求header部分的值绑定到方法的参数上

```java
	// 这是一个请求头的信息
    Host                    localhost:8080  
    Accept                  text/html,application/xhtml+xml,application/xml;q=0.9  
    Accept-Language         fr,en-gb;q=0.7,en;q=0.3  
    Accept-Encoding         gzip,deflate  
    Accept-Charset          ISO-8859-1,utf-8;q=0.7,*;q=0.7  
    Keep-Alive              300  
    // -----------------------------
	@RequestMapping("/displayHeaderInfo")  
	public void displayHeaderInfo(@RequestHeader("Accept-Encoding") String encoding,  
	                              @RequestHeader("Keep-Alive") long keepAlive)  {  
	  //把request header部分的 Accept-Encoding的值，绑定到参数encoding上了， Keep-Alive header的值绑定到参数keepAlive上 
	} 
```
* @RequestPart 用在multipart/form-data表单提交请求的方法上,支持的请求方法的方式MultipartFile，属于Spring的MultipartResolver类。这个请求是通过http协议传输的,适用于复杂的请求域（像JSON，XML）;

```java
    /**
     * 单文件上传
     * @param file
     * @param bucket
     * @return
     */
    @RequestMapping("uploadFile")
    public JsonResult uploadFile(@RequestPart("file") MultipartFile file, @RequestParam String bucket){

        String fileUrl = aliossService.uploadFile(file, bucket);
        Map<String,String> result = new HashMap<>();
        result.put("fileUrl",fileUrl);

        return success(result);
    }
```
* @RequestScope
* @RequestWrapper
* @CookieValue 把Request header中关于cookie的值绑定到方法的参数上

```java
	例:Cookie:JSESSIONID=415A4AC178C59DACE0B2C9CA727CDD84 
	@RequestMapping("/displayHeaderInfo.do")  
	public void displayHeaderInfo(@CookieValue("JSESSIONID") String cookie)  {  
	  //将它绑定到cookie中 
	}
```
* @SessionAttributes 来绑定HttpSession中的attribute对象的值，便于在方法中的参数里使用

```java
	// 该注解有value、types两个属性，可以通过名字和类型指定要使用的attribute 对象；
	@Controller  
	@RequestMapping("/editPet")  
	@SessionAttributes("pet")  
	public class EditPetForm {  
	    // ...  
	}
```
* @ModelAttribute 注解有两个用法，一个是用于方法上，一个是用于参数上
用于方法上时：  通常用来在处理@RequestMapping之前，为请求绑定需要从后台查询的model；
用于参数上时： 用来通过名称对应，把相应名称的值绑定到注解的参数bean上；要绑定的值来源于：

```java
	// 用到方法上@ModelAttribute的示例代码
	// 这种方式实际的效果就是在调用@RequestMapping的方法之前，为request对象的model里put（“account”， Account）；
	@ModelAttribute  
	public Account addAccount(@RequestParam String number) {  
	    return accountManager.findAccount(number);  
	} 
	
	
	// 用在参数上的@ModelAttribute示例代码： 参数类型:application/x-www-form-urlencoded
	// 首先查询 @SessionAttributes有无绑定的Pet对象，若没有则查询@ModelAttribute方法层面上是否绑定了Pet对象，若没有则将URI template中的值按对应的名称绑定到Pet对象的各属性上。
	@RequestMapping(value="/owners/{ownerId}/pets/{petId}/edit", method = RequestMethod.POST)  
		public String processSubmit(@ModelAttribute Pet pet) {  
	}
```
* @Component
是所有受Spring 管理组件的通用形式，@Component注解可以放在类的头上，@Component不推荐使用
* @Controller
@Controller对应表现层的Bean，也就是Action，Api接口

* @Service对应的是业务层Bean

```java
@Service("userService")
public class UserServiceImpl implements UserService {
………
}
```
使用

```java
// 注入userService
@Resource(name = "userService")
private UserService userService;
```
* @Repository;对应数据访问层Bean

```java
@Repository(value="userDao")
public class UserDao {
	@Autowired(required = true)
    JdbcTemplate jdbcTemplate;
	
	public void save(User user) {
        String sql = "insert into t_user(username, userid,age) values(?,?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getUserId(),user.getAge());
    }
	
}
```

@Repository(value="userDao")注解是告诉Spring，让Spring创建一个名字叫“userDao”的UserDao实例
当Service需要使用Spring创建的名字叫“userDao”的UserDaoImpl实例时，就可以使用@Resource(name = "userDao")注解告诉Spring，Spring把创建好的userDao注入给Service即可。

```java
// 注入userDao，从数据库中根据用户Id取出指定用户时需要用到
@Resource(name = "userDao")
private BaseDao<User> userDao;
```



**其实能用get方法的基本都能用Post方法，反之不然。**

## 运行环境
* application-dev.properties：开发环境
* application-test.properties：测试环境
* application-prod.properties：生产环境

具体运行那个环境,在application.properties文件中通过spring.profiles.active属性来设置
如：spring.profiles.active=test就会加载application-test.properties配置文件内容

直接右键Run运行Application类,然后在浏览器中访问 http://localhost:8080/就可以看到我们的数据


## Swagger2
> 将api接口生成页面

## 静态资源访问

### 默认配置
Spring Boot默认提供静态资源目录位置需置于classpath下，目录名需符合如下规则：
* /static
* /public
* /resources
* /META-INF/resources

举例：我们可以在src/main/resources/目录下创建static，在该位置放置一个图片文件。启动程序后，尝试访问http://localhost:8080/xxx.jpg。如能显示图片，配置成功

## 渲染Web页面
写Api接口的时候我们都是通过@RestController来处理请求的,所以返回的内容都是json对象,那么如果需要渲染html页面的时候,要如何实现呢?

### 模板引擎
在动态HTML上实现SpringBoot依然可以完美胜任,并且提供了多种模板引擎的默认配置,所以在推荐的模板引擎下,我们可以很快的上手开发动态网站.

Spring Boot提供了默认配置的模板引擎主要有以下几种：
* Thymeleaf
* FreeMarker
* Velocity
* Groovy
* Mustache

Spring Boot建议使用这些模板引擎，避免使用JSP，若一定要使用JSP将无法实现Spring Boot的多种特性，具体可见后文：支持JSP的配置

当你使用上述模板引擎中的任何一个，它们默认的模板配置路径为：src/main/resources/templates。当然也可以修改这个路径，具体如何修改，可在后续各模板引擎的配置属性中查询并修改。

#### Thymeleaf
> Thymeleaf是一个XML/XHTML/HTML5模板引擎，可用于Web与非Web环境中的应用开发。它是一个开源的Java库，基于Apache License 2.0许可，由Daniel Fernández创建，该作者还是Java加密库Jasypt的作者。

Thymeleaf提供了一个用于整合Spring MVC的可选模块，在应用开发中，你可以使用Thymeleaf来完全代替JSP或其他模板引擎，如Velocity、FreeMarker等。Thymeleaf的主要目标在于提供一种可被浏览器正确显示的、格式良好的模板创建方式，因此也可以用作静态建模。你可以使用它创建经过验证的XML与HTML模板。相对于编写逻辑或代码，开发者只需将标签属性添加到模板中即可。接下来，这些标签属性就会在DOM（文档对象模型）上执行预先制定好的逻辑。

示例模板:

```html
<table>
  <thead>
    <tr>
      <th th:text="#{msgs.headers.name}">Name</td>
      <th th:text="#{msgs.headers.price}">Price</td>
    </tr>
  </thead>
  <tbody>
    <tr th:each="prod : ${allProducts}">
      <td th:text="${prod.name}">Oranges</td>
      <td th:text="${#numbers.formatDecimal(prod.price,1,2)}">0.99</td>
    </tr>
  </tbody>
</table>
```
可以看到Thymeleaf主要以属性的方式加入到html标签中，浏览器在解析html时，当检查到没有的属性时候会忽略，所以Thymeleaf的模板可以通过浏览器直接打开展现，这样非常有利于前后端的分离.

在Spring Boot中使用Thymeleaf，只需要引入下面依赖，并在默认的模板路径src/main/resources/templates下编写模板文件即可完成。

``` java
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

在完成配置之后，举一个简单的例子，在快速入门工程的基础上，举一个简单的示例来通过Thymeleaf渲染一个页面。

``` java
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";  
    }

}

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8" />
    <title></title>
</head>
<body>
<h1 th:text="${host}">Hello World</h1>
</body>
</html>
```

如上页面，直接打开html页面展现Hello World，但是启动程序后，访问http://localhost:8080/，则是展示Controller中host的值：http://blog.didispace.com，做到了不破坏HTML自身内容的数据逻辑分离

Thymeleaf的默认参数配置
如有需要修改默认配置的时候，只需复制下面要修改的属性到application.properties中，并修改成需要的值，如修改模板文件的扩展名，修改默认的模板路径等。

```xml
# Enable template caching.
spring.thymeleaf.cache=true 
# Check that the templates location exists.
spring.thymeleaf.check-template-location=true 
# Content-Type value.
spring.thymeleaf.content-type=text/html 
# Enable MVC Thymeleaf view resolution.
spring.thymeleaf.enabled=true 
# Template encoding.
spring.thymeleaf.encoding=UTF-8 
# Comma-separated list of view names that should be excluded from resolution.
spring.thymeleaf.excluded-view-names= 
# Template mode to be applied to templates. See also StandardTemplateModeHandlers.
spring.thymeleaf.mode=HTML5 
# Prefix that gets prepended to view names when building a URL.
spring.thymeleaf.prefix=classpath:/templates/ 
# Suffix that gets appended to view names when building a URL.
spring.thymeleaf.suffix=.html  spring.thymeleaf.template-resolver-order= # Order of the template resolver in the chain. spring.thymeleaf.view-names= # Comma-separated list of view names that can be resolved.
```
###支持JSP的配置
Spring Boot并不建议使用，但如果一定要使用，可以参考此工程作为脚手架：[JSP支持](https://github.com/spring-projects/spring-boot/tree/v1.3.2.RELEASE/spring-boot-samples/spring-boot-sample-web-jsp)

## Spring Boot中Web应用的统一异常处理 

> 我们在做Web应用的时候，请求处理过程中发生错误是非常常见的情况。Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容

选择一个之前实现过的Web应用（Chapter3-1-2）为基础，启动该应用，访问一个不存在的URL，或是修改处理内容，直接抛出异常，如:

```java
@RequestMapping("/hello")
public String hello() throws Exception {
    throw new Exception("发生错误");
}
```
启动后,我们可以看到报错页面

### 统一异常处理
虽然，Spring Boot中实现了默认的error映射，但是在实际应用中，上面你的错误页面对用户来说并不够友好，我们通常需要去实现我们自己的异常提示。
* 创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。@ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中

```java
@ControllerAdvice
class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
```

* 实现error.html页面展示：在templates目录下创建error.html，将请求的URL和Exception对象的message输出。

```java
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8" />
    <title>统一异常处理</title>
</head>
<body>
    <h1>Error Handler</h1>
    <div th:text="${url}"></div>
    <div th:text="${exception.message}"></div>
</body>
</html>
```
启动该应用，访问：http://localhost:8080/hello，可以看到报错信息

过实现上述内容之后，我们只需要在Controller中抛出Exception，当然我们可能会有多种不同的Exception。然后在@ControllerAdvice类中，根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理。

### 返回JSON格式
在上述例子中，通过@ControllerAdvice统一定义不同Exception映射到不同错误处理页面。而当我们要实现RESTful API时，返回的错误是JSON格式的数据，而不是HTML页面，这时候我们也能轻松支持。
本质上，只需在@ExceptionHandler之后加入@ResponseBody，就能让处理函数return的内容转换为JSON格式。
下面以一个具体示例来实现返回JSON格式的异常处理。
* 创建统一的JSON返回对象，code：消息类型，message：消息内容，url：请求的url，data：请求返回的数据

```java
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;

    // 省略getter和setter

}
```

* 创建一个自定义异常，用来实验捕获该异常，并返回json

```java
public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }
    
}
```
* Controller中增加json映射，抛出MyException异常

```java
@Controller
public class HelloController {

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

}
```
* 为MyException异常创建对应的处理

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
```
* 启动应用，访问：http://localhost:8080/json，可以得到如下返回内容：

```java
{
    code: 100，
    data: "Some Data"，
    message: "发生错误2"，
    url: "http://localhost:8080/json"
}
```


## 异常
访问数据出错:
"status": 500,
"error": "Internal Server Error",
"message": "No converter found for return value of type: class com.example.demo.domain.UserBean"

@Data没效果
??? 发现是UserBean缺少了getter和setter方法.

## 数据库
### 使用JdbcTemplate访问数据库 





