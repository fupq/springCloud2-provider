/**
* @Title: ListenApolloNameSpaceChange.java
* @Package com.fpq.service
* @Description: TODO(用一句话描述该文件做什么)
* @author slx
* @date 2019年4月11日
* @version V1.0
*/
package com.fpq.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import lombok.extern.slf4j.Slf4j;

/**
* @classDesc: 功能描述：apollo客户端监听Namespace配置变化
* @author 付品欣
* @createTime 2019年4月11日 上午1:19:43
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@Slf4j
@Component
public class ListenApolloNameSpaceChange implements ApplicationContextAware {

	
	private ApplicationContext applicationContext;
	
	/* (非 Javadoc)
	* <p>Title: setApplicationContext</p>
	* <p>Description: </p>
	* @param applicationContext
	* @throws BeansException
	* @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	*/
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	//@ApolloConfigChangeListener
	@ApolloConfigChangeListener
	public void listenApolloNameSpaceChanged(ConfigChangeEvent changeEvent) {
		Config config = ConfigService.getConfig("TEST1.springCloud.hystrix");
		config.addChangeListener(new ConfigChangeListener() {
		  @Override
		  public void onChange(ConfigChangeEvent changeEvent) {
		    System.out.println("监听到配置参数变化的命名空间的名称是： " + changeEvent.getNamespace());
		    for (String key : changeEvent.changedKeys()) {
		      ConfigChange change = changeEvent.getChange(key);
		      System.out.println(String.format(
		        "发现改变 - key: %s, oldValue: %s, newValue: %s, changeType: %s",
		        change.getPropertyName(), change.getOldValue(),
		        change.getNewValue(), change.getChangeType()));
		     }
		  }
		});
	}
	
}
