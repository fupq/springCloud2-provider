/**
* @Title: AppProvider.java
* @Package com.fpq
* @Description: TODO(��һ�仰�������ļ���ʲô)
* @author slx
* @date 2019��3��20��
* @version V1.0
*/
package com.fpq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;

import io.swagger.annotations.Api;

/**
* @classDesc: ����������
* @author ��Ʒ��
* @createTime 2019��3��20�� ����11:41:39
* @version: V1.0
* @copyright:���ڿ�������Ƽ����޹�˾
* @wechat:qhjx666888
*/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients  //开启feign
@EnableHystrix //开启hystrix断路器
@Configuration
@EnableApolloConfig //开启apollo客户端
@EnableAutoConfiguration
@EnableSwagger2Doc  //生产swagger2Doc文档
public class AppProvider {

	public static void main(String[] args) {
		SpringApplication.run(AppProvider.class, args);
	}

}
