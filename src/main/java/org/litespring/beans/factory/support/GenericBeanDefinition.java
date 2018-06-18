package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {

	private String id;
	private String beanClassName;
	private String scope = SCOPE_DEFAULT;
	private boolean singleton = true;
	private boolean prototype = false;

	public GenericBeanDefinition(String id2, String beanClassName2) {
		this.id = id2;
		this.beanClassName = beanClassName2;
	}

	@Override
	public String getBeanClassName() {
		return this.beanClassName;
	}

	@Override
	public boolean isSingleton() {
		return this.singleton;
	}

	@Override
	public boolean isPrototype() {
		return this.prototype;
	}

	@Override
	public String getScope() {
		return this.scope;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = (SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope));
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

}
