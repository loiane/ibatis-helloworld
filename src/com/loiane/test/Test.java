package com.loiane.test;

import java.util.List;

import com.loiane.dao.ContactDAO;
import com.loiane.model.Contact;

/**
 * Class Test
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
public class Test {

	public static void main(String[] args) {
		
		try {
			
			ContactDAO contactDAO = new ContactDAO();
			
			//select All
			List<Contact> list = contactDAO.selectAll();
			for (Contact c : list){
				System.out.println(c.toString());
			}
			
			//select by id
			Contact contact = contactDAO.selectById(2);
			//System.out.println(contact.toString());
			
			//update
			contact.setEmail("updated@loianetest.com");
			contactDAO.update(contact);
			contact = contactDAO.selectById(2);
			//System.out.println(contact.toString());
			
			//insert
			contact = new Contact();
			contact.setName("Loiane");
			contact.setPhone("(000) 111-1111");
			contact.setEmail("loianeg@gmail.com");
			contactDAO.insert(contact);
			System.out.println("inserted contact = " + contact.getId());
			
			//delete
			//contactDAO.delete(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
