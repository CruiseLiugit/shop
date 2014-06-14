记录每天修改的细节：

//----------------------------------------------
//2014-06-14
今天上午完成 增加、删除、修改会员功能
1、修改登录页面和对应的登录模块
2、增加登录状态过滤器，过滤器添加后有问题。暂时取消
3、查询 列表上面的工具栏无法显示的问题！！
  (1)MenusServiceImpl.java 中生成的 json 格式有问题
   


//----------------------------------------------
//2014-06-13 昨晚把数据库数据整理完毕，今天要解决几个问题
1、项目中包含 Spring、Nutz 框架。web.xml 中配置两个过滤器
   Spring 的过滤 *.do
   Nutz 过滤器忽略掉  *.do 请求
   <!-- 加载 nutz 框架 -->
  <filter>
  	<filter-name>nutz</filter-name>
  	<filter-class>org.nutz.mvc.NutFilter</filter-class>
  	<!-- 框架加载点 -->
  	<init-param>
  		<param-name>modules</param-name>
  		<param-value>com.liufuya.core.MainModule</param-value>
  	</init-param>
  	<!-- 忽略*.html *.do 后缀的请求 -->
  	<init-param>
            <param-name>ignore</param-name>
            <param-value>^(.+[.])(jsp|png|gif|jpg|js|css|jspx|jpeg|html|do)$</param-value>
    </init-param>
  </filter>
  
  <filter-mapping>
  	<filter-name>nutz</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
   
2、了解了 EasyJQueryUI 的异步请求流程和常用页面刷新方法
   //  表单保存
  function 	saveMenus(){
   var selectButtonIds = getSelectedButtons();  
   if(selectButtonIds!=null && selectButtonIds!=""){
      var menusInputForm=$("#menusInputForm");
    	 menusInputForm.form('submit', {
	        url: '${contextPath }/menus/saveMenus.do?selectButtonIds='+selectButtonIds,
	        success: function (msg) {
	            if (msg.match("1")) {	 
	            		//这里是刷新 数据表 关键代码                             
	                grid.datagrid('reload');
	                win.window('close');
	            } else {
	               $.messager.alert('提示', '失败', 'info');
	            }
	        }
       });
   }else{
   $.messager.alert('提示', '请为菜单配置按钮!', 'info');  
   return ;
   }

3、完成 "会员资料" 列表显示功能


//----------------------------------------------
//2014-06-12日修改完会员模块后，发现有一些  问题找不到原因。
决定放弃之前 jUI 框架版本。使用 EasyJqueryUI 版本
花今天一天时间完成。
1、学习 easyJqueryUI ，调整首页页面效果
2、完成自定义菜单功能。
3、复制 会员模块功能
4、完成 门店管理模块功能
4、



//----------------------------------------------
//2014-06-07
1、接着完成昨天的 在会员列表中选择一个，点击 <修改>按钮，得到会员信息
   昨天没有得到会员地址表中的数据
2、完成会员的修改、删除、拉黑


//----------------------------------------------
//2014-06-06
1、今天先完成所有会员的列表
2、完成增加会员
3、完成查询修改会员的基本信息

//----------------------------------------------
//2014-06-04   06-05 
两天折腾一个门店管理里面，三级联动，两天没有效果。
决定用纯 js 写了。

//----------------------------------------------
//2014-06-03
1、上午更新项目数据库，链接到 shopdb。遇到一个问题，数据库链接 url
   不能使用 127.0.0.1 而应该使用 localhost
2、登录成功，首页，右侧弹出 jUI 广告。删除该广告
3、设计门店数据库表格结构

//----------------------------------------------
//2014-05-30
几天没有安心写代码了，今天需要赶一下进度。主要是完成会员管理的功能
1、同步所有的数据库表，昨天已经整理好了。
2、需要插入新的菜单，所以先完成了一直没有完成的 sys_menus 操作
   增加菜单页面 sysMenusAdd.jsp 页面，中，一个树状态菜单的操作。有些 js 脚本问题，调试 2 个小时。
   解决方法参考 sysMenusAdd.jsp  function kkk(){} 及MenusAction.java 中的 createSysMenus()
3、完成菜单的数据库插入，同时插入 sys_authorities 数据
   插入，新菜单后，导致整个菜单读取失败。我再尝试一次，不行就手动创建菜单
   
4、手动编辑好菜单
5、设计好工作台页面
6、设计好门店管理界面
7、设计会员模块页面

未完成
1、设计门店数据库
2、完成会员性别分析报表


增加内容：
1、在 WEB-INF/jsp/目录中增加
  1desktop
  2store
  5member
  
2、门店和管理员模块中，关于省份、城市的联动搜索。需要把值单独保存在目录
  WebRoot/resources/citpart/
    

//----------------------------------------------
//2014-05-22
1、今天任务
   创建门店管理数据库，并实现
   实现门店管理增删改查
   实现门店管理列表分页
   实现菜单管理，增加门店管理菜单
   实现门店配送范围定位接口
   实现门店列表查询数据接口
   
未完成，第一步都没有完成


//----------------------------------------------
//2014-04-10
1、今日要解决问题
  完成菜单管理信息列表显示；
  完成菜单管理模块，查看菜单按钮；
  完成菜单管理模块，新增菜单按钮；
  完成菜单管理模块，删除菜单功能;
  完成菜单管理模块，修改菜单功能;
  
  sysMenusList.jsp
  
2、菜单列表显示完成
	LeftMenuAction.java 中，点击菜单后，查询所有菜单数据、按钮数据，跳转到 
	WEB-INF/jsp/sysmenus/menus.jsp 显示
3、菜单列表中，点击  查看   按钮，发出 Ajax 请求。查询当前菜单对象以及 关联的所有按钮数据
   组合成 json 返回页面
   MenusServiceImpl.java 类中完成
   String getSeeMenusJson(HttpServletRequest request,
			HttpServletResponse response, String menusCode)
   
4、菜单列表页面中，处理返回的 json 数据
   这个浪费了一天时间。终于解决  
  
  


//----------------------------------------------
//2014-04-09
1、今日要解决问题
  jQuery UI Dialog 自定义弹出界面；
  完成用户信息列表显示；
  完成用户模块用户查看；
  完成用户模块新增用户按钮；
  完成用户模块分页查询；
  完成用户模块输入用户名搜索；
  
  

//----------------------------------------------
//2014-04-08
1、开始做 用户的增删改
2、修改 leftmenu.jsp 页面中的 js 加载代码
   从数据库开始修改。修改 menu 表中的 url 路径字段，去掉 *.jsp ，
   所有请求全部提交给 nutz 中新建的 MenuAction.java 处理
   
   

//----------------------------------------------
//2014-04-07
1、在 top.jsp 页面中，增加异步获取当前用户名的功能
2、在 top.jsp 页面中，完成退出功能
3、增加 WEB-INF/jsp/account/editPassword.jsp 页面，修改用户信息
4、增加了 editPassword.jsp 旧密码异步验证
5、增加了 eidtPassword.jsp 各个输入框的输入验证
6、完成 用户个人设置内容修改，涉及到 
   editPassword.jsp
   SysUserAction.java
   SysUserServiceImpl.java
   SysUserDaoImpl.java
   
   /////////////////////////////////
   发现技巧
   (1)更新直接使用 BaseDao 中的 update(T)方法
   (2)在 Action 方法中，设置返回值类型 @Ok("json") ，可以直接把任意类型转换为  json 字符串返回 
   /////////////////////////////////



//----------------------------------------------
//2014-04-06
1、登录完成后，需要解决权限问题。权限首先要解决都是菜单。
   目前所有菜单全部静态的，但是需要在后台能够管理。每次用户登录时，动态取出分配给他的菜单项目
   
2、新建菜单项目的数据库表实现类
  com.liufuya.core.mvc.module.privilege.dao.impl.MenusDaoImpl
  
  
  com.liufuya.core.mvc.module.privilege.action.SysUserAction.java
  (1)当用户登录后，进入 index.jsp 页面。页面通过 jQuery 自动进行异步请求
  (2)代码在 leftmenu.jsp  底部。   $.post();
  (3)调用 SysUserAction 中的 
  	public void loadMenus(HttpServletRequest request,HttpServletResponse response)
  (4)该方法中，访问数据库类 MenusDaoImpl.java 
    public List<Menus> findUserMenus(Map<String, Object> map)
  (5)返回数据，经过 SysUserAction 中的方法，进行 JSON 转换。把对象转换为 json 字符串
    private StringBuffer processMenus(List<Menus> allMenusList,String fMenuCode,StringBuffer menuSBuffer)
  (6)loadMenus() 方法返回的数据给   leftmenu.jsp 页面。通过 js 函数
    InitLeftMenu('${base }');
    解析并显示到左边菜单区域
    
3、遇到问题
   目前设计有三级菜单。这个修改为二级
   
   

//----------------------------------------------
//2014-04-05
1、实现用户，权限模块
   com.liufuya.core.mvc.module.privilege.dao.impl.SysUserDaoImpl          数据库操作实现
   
   //////////////////////////////////////
   SysUserDaoImpl.java 类实现。
   调用了 父类 BasicDao 类中的大量方法。发现 Nutz 用于做 Dao 层思路很简单。
   updata 操作,通过一个不断增加的  Chain.make() 方法设置多个要修改的值
              通过一个不断增加的  Cnd.where().and() 方法设置多个条件
   很好用!!!!            
   //////////////////////////////////////
   
2、数据库操作实现后，做了一个服务层类 
  com.liufuya.core.mvc.module.privilege.service.impl.SysUserServiceImpl
  
  该类用于 Model 与 Controller 直接的层。
  
3、实现了登录的控制层代码    
   com.liufuya.core.mvc.module.privilege.action.SysUserAction
   
   //////////////////////////////////////
   为了让 Nutz ioc 容器管理上面的 三个类。统一都在类名前面添加 @IocBean
   为了让 Nutz ioc 容器注入需要都对象，在对象前面添加 @Inject("refer:sysUserServiceImpl")   
   //////////////////////////////////////	

4、实现了 WEB-INF/jsp/account/login.jsp 页面的 脚本验证

5、页面遇到一个问题。当把资源文件 image css js 文件夹放在 WebRoot 目录下。
   在设置访问路径的时候 增加一级目录会导致资源文件无法找到。
   目前都只使用一级目录
   
//----------------------------------------------
//2014-04-04
1、增加用户角色、权限表
   ddl/sys_role.sql
   ddl/sys_user_role.sql
   ddl/sys_user.sql
   ddl/sys_authorities.sql
   ddl/sys_roles_authorities.sql
   ddl/sys_menus.sql    子菜单
   ddl/sys_model.sql    增删改查功能按钮

2、增加表的对应实体类，并进行 Nutz 映射配置
  com.liufuya.core.mvc.module.privilege.model
  
3、完成 模型层和 service 层代码搭建
	//使用 Nutz 觉得麻烦，去掉了接口 包   mapper     inter
   com.liufuya.core.mvc.module.privilege.model  数据库表映射实体类 
   com.liufuya.core.mvc.module.privilege.dao.mapper        数据库操作接口 
   com.liufuya.core.mvc.module.privilege.dao.impl          数据库操作实现

   com.liufuya.core.mvc.module.privilege.service.inter MVC 服务层接口
   com.liufuya.core.mvc.module.privilege.service.impl  MVC 服务层实现
   
   com.liufuya.core.mvc.module.privilege.action        MVC 控制层
   
   
//----------------------------------------------
//2014-04-03
1、把 缪汉斌 工程中使用的工具类导入工程中
   com.liufuya.common
   com.liufuya.common.base
   com.liufuya.common.util
   com.liufuya.common.uuid
   
   这些包都不会被 Nutz 扫描到
   备注：在导入这些包中的类时，把所有的 json 解析换成 Nutz JSON 解析了；
com.liufuya.common.CommonExcel.java 中用 POI 处理 Excel ，添加了 poi-3.0-FINAL.jar
com.liufuya.common.ResponseUtils.java 使用了Apache Commons 语言工具 StringUtils，添加了 commons-lang-2.4.jar
   
2、把 页面常用的 工具页面也导入到工程中
  WEB-INF/jsp/common/taglibs.jsp
     



//----------------------------------------------
//2014-04-01
1、选中后台页面模版
2、准备好后台页面模版需要的 样式、脚本文件
  WebRoot/css
  WebRoot/img
  WebRoot/js
3、通过后台类，控制欢迎页面
  com.liufuya.core.mvc.module.BaseAction.java  父Action，获取数据库Dao对象，ioc 注入
  com.liufuya.core.mvc.module.IndexAction.java 首页Action，访问项目根路径/，跳转到
     WEB-INF/jsp/account/login.jsp 页面
  通过 Nutz 注解  @At(/) 实现   
     
4、login.jsp 页面中，通过 ${base} 获取项目根路径，读取 WEB-INF 目前外部的 css js 文件
5、login.jsp 登录表单，提交给 action="${base}/login" ,由 IndexAction.java 处理
6、com.liufuya.core.dao.ManagerDao.java  是专门为 管理员数据库操作新建的类。所有跟用户有关的数据库访问代码都放在这里
7、IndexAction.java  中通过 ioc 注入 ManagerDao 对象
   @Inject
   private ManagerDao managerDao;
  
   经过验证后，返回错误信息
   req.setAttribute("error", "用户名或者密码错误！");
   
   用户登录成功后，当前用户对象存入 HttpSession 中
   req.getSession().setAttribute("admin", m);
   
   跳转到相应页面，由于路径多余两个，所以使用  JspView 控制返回路径
   return new JspView("jsp.index");	
   	     
      


//----------------------------------------------
//2014-03-30
1、使用 Nutz 框架建立一个 能够连接数据库的 MVC 原型
2、com.liufuya.core.MainModeule.java 配置了系统的 IOC
3、com.liufuya.core.CoreSetup.java   创建了启动是提前或销毁时做的操作，暂无
4、一个初始的登录例子
  WebRoot/testlogin.jsp 
  com.liufuya.core.bean.User.java 
  com.liufuya.core.mvc.module.UserModule.java 
  
  
  