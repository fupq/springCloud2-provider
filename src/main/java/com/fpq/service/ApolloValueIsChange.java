/**
* @Title: ApolloValueIsChange.java
* @Package com.fpq.service
* @Description: TODO(用一句话描述该文件做什么)
* @author slx
* @date 2019年4月7日
* @version V1.0
*/
package com.fpq.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import lombok.extern.slf4j.Slf4j;

/**
* @classDesc: 功能描述：监听apollo中配置的参数是否修改了
* @author 付品欣
* @createTime 2019年4月7日 下午2:50:25
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@Slf4j
@Component
public class ApolloValueIsChange implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;


	/**
	 * 
	* @Title: someChangeHandler
	* @Description: 监听apollo中参数的值是否修改，若修改则打印出来
	* @param @param changeEvent    参数
	* @return void    返回类型
	* @throws
	 */
	@ApolloConfigChangeListener
	private void someChangeHandler(ConfigChangeEvent changeEvent) {
	    for (String key : changeEvent.changedKeys()) {
	        ConfigChange change = changeEvent.getChange(key);
	        log.info("Found change - {}", change.toString());
	    }
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
