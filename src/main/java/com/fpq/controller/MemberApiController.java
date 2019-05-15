/**
* @Title: MemberApiController.java
* @Package com.fpq.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author slx
* @date 2019年3月25日
* @version V1.0
*/
package com.fpq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpq.base.BaseApiService;
import com.fpq.base.ResponseBase;
import com.fpq.entity.User;
import com.spring4all.swagger.EnableSwagger2Doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
* @classDesc: 功能描述：
* @author 付品欣
* @createTime 2019年3月25日 上午12:39:13
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@Slf4j
@RestController
//@Api("服务提供者测试类") //api的描述
public class MemberApiController {

	@Autowired
	private BaseApiService baseApiService;
	
	/**
	 * 获取配置文件中配置的端口号
	 */
	@Value("${server.port}")
	private String serverPort;
	
	/**
	 * http://127.0.0.1:8200/getMember
	* @Title: getMember
	* @Description: 测试ribbon实现客户端负责均衡
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@GetMapping("/getMember")
	@ApiOperation("测试ribbon实现客户端负责均衡")
	public String getMember() {
		long  time = 400L;
		System.out.println("我是服务提供端：会员信息fupinxin，开始休眠"+time+"毫秒......");
		String threadInfo = "getMember服务，线程池名称："+Thread.currentThread().getName()+"，线程id:"+Thread.currentThread().getId();
		log.info(threadInfo);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("我是服务提供端：会员信息fupinxin，休眠"+time+"毫秒结束。");
		return "this is member,我是会员服务，springCloud2.0版本，学习分布式和微服务架构！服务提供者端口号："+this.serverPort+";"+threadInfo;
	}
	
	@GetMapping("/getMember2")
	@ApiOperation("测试ribbon实现客户端负责均衡")
	public ResponseBase getMember2() {
		long  time = 6000L;
		System.out.println("我是服务提供端：会员信息fupinxin，开始休眠"+time+"毫秒......");
		String threadInfo = "getMember服务，线程池名称："+Thread.currentThread().getName()+"，线程id:"+Thread.currentThread().getId();
		log.info(threadInfo);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("我是服务提供端：会员信息fupinxin，休眠"+time+"毫秒结束。");
		ResponseBase responseBase = baseApiService.setResultSuccess("this is member,我是会员服务，springCloud2.0版本，学习分布式和微服务架构！服务提供者端口号："+this.serverPort+";"+threadInfo);
		log.info(responseBase.toString());
		return responseBase;
	}
	
	/**
	 * http://127.0.0.1:8200/getUser?name=carson
	* @Title: getUser
	* @Description: TODO(获取用户名，测试ribbin)
	* @param @param name
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@GetMapping("/getUser")
	@ApiOperation("获取用户名，测试ribbin")
	public String getUser(String name) {
		String threadInfo = "getMember服务，线程池名称："+Thread.currentThread().getName()+"，线程id:"+Thread.currentThread().getId();
		log.info(threadInfo);
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "服务提供者：会员服务受到订单服务发送的name请求参数结果为："+ name+";"+threadInfo;
	}
	
	
	/**
	 * http://127.0.0.1:8200/getUser
	* @Title: getUser
	* @Description: 参数为user对象，测试post请求
	* @param @param user
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@PostMapping("/getUserObject")
	@ApiOperation("测试post请求，参数为user对象")
	public String getUserObject(User user) {
		System.out.println("服务提供者：会员服务受到订单服务发送的name请求参数结果为："+ user.getName());
		return "服务提供者：会员服务受到订单服务发送的name请求参数结果为："+ user.getName();
	}
	
	
}
