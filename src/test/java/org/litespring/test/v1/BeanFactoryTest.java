package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeansDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {

	private DefaultBeanFactory factory = null;
	private XmlBeanDefinitionReader reader = null;

	@Before
	public void setUp() {
		factory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(factory);
	}

	@Test
	public void testGetBean() {

		reader.loadBeanDefinition("petstore-v1.xml");

		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		Assert.assertTrue(bd.isSingleton());
		
		Assert.assertFalse(bd.isPrototype());
		
		Assert.assertEquals(BeanDefinition.SCOPE_DEFAULT, bd.getScope());

		Assert.assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());

		PetStoreService petStore = (PetStoreService) factory.getBean("petStore");

		Assert.assertNotNull(petStore);
		
		PetStoreService petStore1 = (PetStoreService) factory.getBean("petStore");
		
		Assert.assertTrue(petStore.equals(petStore1));
	}

	@Test
	public void testInvalidBean() {
		reader.loadBeanDefinition("petstore-v1.xml");
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
			reader.loadBeanDefinition("xxx.xml");
		} catch (BeansDefinitionStoreException e) {
			return;
		}
		Assert.fail("expect BeansDefinitionStoreException");
	}
}
