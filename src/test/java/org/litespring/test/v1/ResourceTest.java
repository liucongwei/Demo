package org.litespring.test.v1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws IOException {
		Resource r = new ClassPathResource("petstore-v1.xml");
		InputStream in = null;
		try {
			in = r.getInputStream();
			Assert.assertNotNull(in);
		} catch (Exception e) {
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	@Test
	public void testFileSystemResource() throws IOException {
		Resource r = new FileSystemResource("E:/从零开始造spring/0615/petstore-v1.xml");
		InputStream in = null;
		try {
			in = r.getInputStream();
			Assert.assertNotNull(in);
		} catch (Exception e) {
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

}
