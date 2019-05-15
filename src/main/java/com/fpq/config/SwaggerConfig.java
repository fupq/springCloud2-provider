///**
//* @Title: SwaggerConfig.java
//* @Package com.fpq.config
//* @Description: TODO(用一句话描述该文件做什么)
//* @author slx
//* @date 2019年5月9日
//* @version V1.0
//*/
//package com.fpq.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.ctrip.framework.apollo.Config;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
//* @classDesc: 功能描述：swaggerConfig配置类
//* @author 付品欣
//* @createTime 2019年5月9日 下午11:12:56
//* @version: V1.0
//* @copyright:深圳科翔教育科技有限公司
//* @wechat:qhjx666888
//*/
//@Configuration
//@EnableSwagger2 //开启swagger2
//public class SwaggerConfig {
//
//	
//	//用来apollo的@ApolloConfig注解注入Config对象
//	//@ApolloConfig
//	@ApolloConfig("application")
//	private Config application;
//		
//	/**
//	 * 
//	* @Title: createRestApi
//	* @Description: TODO(swagger2配置)
//	* @param @return    参数
//	* @return Docket    返回类型
//	* @throws
//	 */
//	@Bean
//	public Docket createRestApi() {
//		Docket docket = new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo())  //设置api文档信息
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.fpq.controller"))  //配置api接口的扫包范围
//				.paths(PathSelectors.any())
//				.build();
//		return docket;
//	}
//
//	/**
//	 * 
//	* @Title: apiInfo
//	* @Description: TODO(创建api文档信息)
//	* @param @return    参数
//	* @return ApiInfo   返回类型
//	* @throws
//	 */
//	private ApiInfo apiInfo() {
//		ApiInfo apiInfo = new ApiInfoBuilder()
//				.title(this.application.getProperty("swagger.title","carson教育|member:provider"))  //添加文档标题
//				.description(this.application.getProperty("swagger.description","carson教育|服务提供者"))  //添加文档描述
//				.termsOfServiceUrl(this.application.getProperty("document.termsOfServiceUrl","http://www.baidu.com:80/provider/apollo/getMemberName?userToken=fupinxin144&password=admin")) //设置官方网址
//				// .contact(contact)
//				.version("1.0").build(); //添加文档版本号
//		return apiInfo;
//	}
//
//}
