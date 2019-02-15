package com.eccom.dao;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eccom.model.Supplier;
@Repository("SupplierDAO")
@Transactional
public class SupplierDAOImpl implements SupplierDAO 
{
	@Autowired
	SessionFactory sessionFactory; 
	
	@Override
	public boolean addSupplier(Supplier supplier) 
	{
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		return true;	
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) 
	{
		try
		{
		sessionFactory.getCurrentSession().delete(supplier);
		return true;	
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean updateSupplier(Supplier supplier) 
	{
		try
		{
		sessionFactory.getCurrentSession().update(supplier);
		return true;	
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public Supplier getSupplier(int supplierId) 
	{
		Session session=sessionFactory.openSession();
		Supplier supplier=(Supplier)session.get(Supplier.class,supplierId);
		session.close();
		return supplier;
	}

	@Override
	public List<Supplier> listSupplier() 
	{
		Session session=sessionFactory.openSession();
		List<Supplier> listSuppliers=(List<Supplier>)session.createQuery("from Supplier").list();
		session.close();
		return listSuppliers;
	}
}
