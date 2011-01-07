package com.loiane.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.loiane.dao.ContactDAO;
import com.loiane.model.Contact;

/**
 * Test Case
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
public class TestiBatis {
	
	private static ContactDAO contactDAO;

	@BeforeClass
	public static  void runBeforeClass() {
		contactDAO = new ContactDAO();
	}

	@AfterClass
	public static void runAfterClass() {
		contactDAO = null;
	}

	/**
	 * Test method for {@link com.loiane.dao.ContactDAO#selectAll()}.
	 */
	@Test
	public void testSelectAll() {
		List<Contact> list = contactDAO.selectAll();
		assertNotNull(list);
		assertEquals(20, list.size());
	}

	/**
	 * Test method for {@link com.loiane.dao.ContactDAO#selectById(int)}.
	 */
	@Test
	public void testSelectById() {
		
		Contact actual = new Contact(2, "Contact1", "(000) 000-0000", "contact1@loianetest.com");
		
		Contact expected = contactDAO.selectById(2);
		
		assertNotNull(expected);
		assertEquals(actual, expected);
		assertNotSame(actual, expected);
	}

	/**
	 * Test method for {@link com.loiane.dao.ContactDAO#update(com.loiane.model.Contact)}.
	 */
	@Test
	public void testUpdate() {

		Contact actual = new Contact(3, "Contact2Updated", "(000) 111-1111", "contact1updated@loianetest.com");
		
		Contact expected = contactDAO.selectById(3);
		expected.setEmail("contact1updated@loianetest.com");
		expected.setName("Contact2Updated");
		expected.setPhone("(000) 111-1111");
		contactDAO.update(expected);
		expected = contactDAO.selectById(3);
	
		assertNotNull(expected);
		assertEquals(actual, expected);
		assertNotSame(actual, expected);
	}

	/**
	 * Test method for {@link com.loiane.dao.ContactDAO#insert(com.loiane.model.Contact)}.
	 */
	@Test
	public void testInsert() {
		
		Contact actual = new Contact();
		actual.setName("Loiane");
		actual.setPhone("(000) 111-1111");
		actual.setEmail("loianeg@gmail.com");
		contactDAO.insert(actual);
		
		assertEquals(21, actual.getId());
		
		Contact expected = contactDAO.selectById(actual.getId()); //id = 21
		
		assertEquals(actual, expected);
		assertNotSame(actual, expected);
		
	}

	/**
	 * Test method for {@link com.loiane.dao.ContactDAO#delete(int)}.
	 */
	@Test
	public void testDelete() {
		
		contactDAO.delete(21);
		
		Contact expected = contactDAO.selectById(21);
		
		assertNull(expected);
	}

}
