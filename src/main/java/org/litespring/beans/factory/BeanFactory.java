package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

public interface BeanFactory {

	BeanDefinition getBeanDefinition(String string);

	Object getBean(String beanID);

}
