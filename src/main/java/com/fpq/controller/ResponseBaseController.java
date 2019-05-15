/**
* @Title: ResponseBaseController.java
* @Package com.fpq.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author slx
* @date 2019年3月30日
* @version V1.0
*/
package com.fpq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpq.base.BaseApiService;
import com.fpq.base.ResponseBase;
import com.fpq.entity.User;
import com.spring4all.swagger.EnableSwagger2Doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @classDesc: 功能描述：对返回结果进行封装
* @author 付品欣
* @createTime 2019年3月30日 下午3:00:53
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@RestController
@RequestMapping("/response")
//@Api("对返回结果进行封装") //api的描述
public class ResponseBaseController {

	
	/**
	 * http://127.0.0.1:8200/response/testResponse
	* @Title: testResponse
	* @Description: 返回结果进行封装
	* @param @return    参数
	* @return ResponseBase    返回类型
	* @throws
	 */
	@GetMapping("/testResponse")
	@ApiOperation("返回结果进行封装")
	public ResponseBase testResponse() {
		ResponseBase response = new ResponseBase();
		response.setRtnCode(2000);
		response.setMsg("对返回结果封装，执行成功！");
		User carson = new User();
		carson.setAge(5);
		carson.setName("carson");
		response.setData(carson);
		return response;
	}
	
	
	/**
	 * http://127.0.0.1:8200/response/responseApiTest
	* @Title: testResponse
	* @Description: 返回结果进行封装
	* @param @return    参数
	* @return ResponseBase    返回类型
	* @throws
	 */
	@GetMapping("/responseApiTest")
	@ApiOperation("返回结果进行封装")
	public ResponseBase responseApiTest() {
		BaseApiService baseApi = new BaseApiService();
		ResponseBase response = baseApi.setResultSuccess("测试使用BaseApiService封装ResponseBase成功！");
		return response;
		
	}
}
