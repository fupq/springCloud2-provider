/**
* @Title: ApolloController.java
* @Package com.fpq.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author slx
* @date 2019年4月6日
* @version V1.0
* apollo客户端获取配置项的三个注解：
    @ApolloConfig：用来自动注入Config对象
    @ApolloConfigChangeListener：用来自动注册ConfigChangeListener
    @ApolloJsonValue：用来把配置的json字符串自动注入为对象
*/
package com.fpq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.spring4all.swagger.EnableSwagger2Doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
* @classDesc: 功能描述：apollo分布式配置中心测试
* @author 付品欣
* @createTime 2019年4月6日 上午00:09:49
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@RestController
@Slf4j
@RequestMapping("/apollo")
//@Api("apollo测试类") //api的描述
public class ApolloController {

	

	/**
	 * 通过value注解读取配置文件中配置的memberName参数的值
	 * 冒号参数名":"后是给参数定义的默认值
	 */
	@Value("${memberName:administrator}")
	private String memberName;
	
	
	//用来apollo的@ApolloConfig注解注入Config对象
	//@ApolloConfig
	@ApolloConfig("application")
	private Config application;
	
	//注入apollo中namespace名称为jfykptjg.springCloud.hystrix的配置
	@ApolloConfig("jfykptjg.springCloud.hystrix")
	private Config hystrixCf;
	

	
	/**
	 * 
	* @Title: getApplicationName
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param key
	* @param @param defaultValue
	* @param @return    参数
	* @return String    返回类型
	* @throws
	* http://127.0.0.1:8200/apollo/getApplicationName?key=memberName&defaultValue=default
	 */
//	@RequestMapping("/getApplicationName")
	@GetMapping("/getApplicationName")
	@ApiOperation("获取apollo中application命名空间中配置项的值")
	public String getApplicationName(String key,String defaultValue) {
		String configValue = this.application.getProperty(key, defaultValue);
		log.info("使用apollo的@ApolloConfig注解注获取的namespace'"+key+"'的值是"+configValue);
		return "使用apollo的@ApolloConfig注解注获取的namespace'"+key+"'的值是"+configValue;
	}
	
	/**
	 * 
	* @Title: getHystrixCfName
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param key
	* @param @param defaultValue
	* @param @return    参数
	* @return String    返回类型
	* @throws
	* http://127.0.0.1:8200/apollo/getHystrixCfName?key=feign.hystrix.enabled&defaultValue=default
	 */
	@GetMapping("/getHystrixCfName")
	@ApiOperation("获取apollo中jfykptjg.springCloud.hystrix命名空间中配置项的值")
	public String getHystrixCfName(String key,String defaultValue) {
		String configValue = this.hystrixCf.getProperty(key, defaultValue);
		log.info("使用apollo的@ApolloConfig注解注获取的namespace'"+key+"'的值是"+configValue);
		return "使用apollo的@ApolloConfig注解注获取的namespace'"+key+"'的值是"+configValue;
	}
	
  @ApolloConfigChangeListener
  private void someOnChange(ConfigChangeEvent changeEvent) {
    //update injected value of batch if it is changed in Apollo
    if (changeEvent.isChanged("memberName")) {
    	memberName = application.getProperty("memberName", "default");
    	log.info("使用apollo的@ApolloConfigChangeListener注解监听'memberName'的值是"+memberName);
    }
  }
	
	/**
	 * http://127.0.0.1:8200/apollo/getMemberName
	* @Title: getMemberName
	* @Description: 读取配置文件中配置的参数的值
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
    @GetMapping("/getMemberName")
	@ApiOperation("读取配置文件中配置的参数的值")
	public String getMemberName() {
		log.info("配置文件中配置的参数memberName的值："+this.memberName);
		return this.memberName;
	}
	
	
	/**
	 * http://127.0.0.1:8200/apollo/getApolloValue?paraName=memberName&defaultValue=carson
	* @Title: getapolloValue
	* @Description: 从配置中心获取String类型的参数
	* @param  paraName 参数名称
	* @param  defaultValue 参数的默认值
	* @return String 获取到的参数的值
	* @throws
	* Config config = ConfigService.getAppConfig();配置获取规则如下：
	    首先查找运行时cluster的配置（通过apollo.cluster指定）
	    如果没有找到，则查找数据中心cluster的配置
	    如果还是没有找到，则返回默认cluster的配置
	 */
    @GetMapping("/getApolloValue")
	@ApiOperation("从配置中心获取String类型的参数")
	public String getApolloValue(String paraName,String defaultValue) {
		Config config = ConfigService.getAppConfig();
		String paraValue = config.getProperty(paraName, defaultValue);
		return "从apollo配置中心获取到的参数‘"+paraName+"'的值是："+paraValue;
	}
	
	//客户端获取namespace的配置
	/**
	 * http://127.0.0.1:8200/apollo/getNameSapceParaValue?nameSpaceName=TEST1.springCloud.hystrix&paraName=feign.hystrix.enabled&defaultValue=false
	* @Title: getNameSapceParaValue
	* @Description: 客户端获取namespace的配置参数的值
	* @param  nameSpaceName name space的名称
	* @param  paraName 参数名称
	* @param  defaultValue 参数的默认值
	* @return 参数的值
	* @throws
	* Config config = ConfigService.getConfig(nameSpaceName);配置获取规则如下：
	    首先获取当前应用下的FX.Hermes.Producer namespace的配置
	    然后获取hermes应用下FX.Hermes.Producer namespace的配置
	    上面两部分配置的并集就是最终使用的配置，如有key一样的部分，以当前应用优先
	 */
    @GetMapping("/getNameSapceParaValue")
	@ApiOperation("客户端获取namespace的配置参数的值")
	public String getNameSapceParaValue(String nameSpaceName,String paraName,String defaultValue) {
		//公共组件配置的获取规则
		Config config = ConfigService.getConfig(nameSpaceName);
		String feignHystrixEnabled = config.getProperty(paraName, defaultValue);
		log.info("apollo中namespace ‘"+nameSpaceName+"’中参数"+paraName+"的值是："+feignHystrixEnabled);
		return "apollo中namespace ‘"+nameSpaceName+"’中参数"+paraName+"的值是："+feignHystrixEnabled;
	}
	
}
