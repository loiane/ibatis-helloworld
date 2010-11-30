package com.loiane.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.loiane.model.Contact;

/**
 * Contact DAO
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
public class ContactDAO {

	private SqlSessionFactory sqlMapper;

	public ContactDAO() throws IOException{
		String resource = "SqlMapConfig.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		sqlMapper = new SqlSessionFactoryBuilder().build(reader);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> selectAll(){

		SqlSession session = sqlMapper.openSession();
		try {
			List<Contact> list = session.selectList("Contact.getAll");
			return list;
		} finally {
			session.close();
		}
	}

	public Contact selectById(int id){

		SqlSession session = sqlMapper.openSession();
		try {
			Contact contact = (Contact) session.selectOne("Contact.getById",id);
			return contact;
		} finally {
			session.close();
		}
	}

	public void update(Contact contact){

		SqlSession session = sqlMapper.openSession();
		try {
			session.update("Contact.update", contact);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void insert(Contact contact){

		SqlSession session = sqlMapper.openSession();
		try {
			session.insert("Contact.insert", contact);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void delete(int id){

		SqlSession session = sqlMapper.openSession();
		try {
			session.delete("Contact.deleteById", id);
			session.commit();
		} finally {
			session.close();
		}
	}
}
