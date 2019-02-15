package com.eccom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eccom.model.Category;
@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO 
{
	@Autowired
	SessionFactory sessionFactory; 
	
	@Override
	public boolean addCategory(Category category) 
	{
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;	
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean deleteCategory(Category category) 
	{
		try
		{
		sessionFactory.getCurrentSession().delete(category);
		return true;	
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean updateCategory(Category category) 
	{
		try
		{
		sessionFactory.getCurrentSession().update(category);
		return true;	
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public Category getCategory(int categoryId) 
	{
		Session session=sessionFactory.openSession();
		Category category=(Category)session.get(Category.class,categoryId);
		session.close();
		return category;
	}

	@Override
	public List<Category> listCategories() 
	{
		Session session=sessionFactory.openSession();
		List<Category> listCategory=(List<Category>)session.createQuery("from Category").list();
		session.close();
		return listCategory;
	}
}