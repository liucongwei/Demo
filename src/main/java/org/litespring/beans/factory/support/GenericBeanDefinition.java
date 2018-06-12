package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
	
	private String id;
	private String beanClassName;
	

	public GenericBeanDefinition(String id2, String beanClassName2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.beanClassName = beanClassName2;
	}


	@Override
	public String getBeanClassName() {
		// TODO Auto-generated method stub
		return this.beanClassName;
	}

}
