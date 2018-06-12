package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.BeansDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {
	
	@Test
	public void testGetBean() {
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		Assert.assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());
		
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		
		Assert.assertNotNull(petStore);
		
	}
	
	@Test
	public void testInvalidBean() {
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			return;
		}
		Assert.fail("expect BeanCreationException");
	}
	
	@Test
	public void testInvalidXML() {
		try {
			BeanFactory factory = new DefaultBeanFactory("xxx.xml");
		} catch (BeansDefinitionStoreException e) {
			// TODO: handle exception
			return;
		}
		Assert.fail("expect BeansDefinitionStoreException");
	}
}
