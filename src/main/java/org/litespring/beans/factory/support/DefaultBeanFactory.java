package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {

	public static final String ID_ATTRIBUTE = "id";
	public static final String CLASS_ATTRIBUTE = "class";

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	private ClassLoader beanClassLoader;

	@Override
	public BeanDefinition getBeanDefinition(String string) {
		return this.beanDefinitionMap.get(string);
	}

	@Override
	public Object getBean(String beanID) {
		// TODO Auto-generated method stub
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if (bd == null) {
			throw new BeanCreationException("");
		}
		
		if (bd.isSingleton()) {
			Object obj = this.getSingleton(beanID);
			if (obj==null) {
				obj = createBean(bd);
				this.registerSingleton(beanID, obj);
			}
			return obj;
		}
		
		return createBean(bd);
		
	}
	
	private Object createBean(BeanDefinition bd) {
		ClassLoader cl = this.getBeanClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("");
		}
	}

	@Override
	public void registerBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.beanClassLoader = classLoader;
	}

	@Override
	public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader != null ? beanClassLoader : ClassUtils.getDefaultClassLoader());
	}

}
