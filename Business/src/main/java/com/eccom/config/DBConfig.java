package com.eccom.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.eccom.dao.CategoryDAO;
import com.eccom.dao.CategoryDAOImpl;
import com.eccom.dao.ProductDAO;
import com.eccom.dao.ProductDAOImpl;
import com.eccom.dao.SupplierDAO;
import com.eccom.dao.SupplierDAOImpl;
import com.eccom.dao.UserDAO;
import com.eccom.dao.UserDAOImpl;
import com.eccom.model.Category;
import com.eccom.model.Product;
import com.eccom.model.Supplier;
import com.eccom.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.eccom")
public class DBConfig 
{
	
	//1. Create a DataSource object
	@Bean(name="dataSource") 
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("ROOT");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test2");
		System.out.println("===Creating the DataSource Bean=====");
		return dataSource;
	}
	
	//2. Create a SessionFactory object
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateProperties=new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
		LocalSessionFactoryBuilder factory1=new LocalSessionFactoryBuilder(getH2DataSource());
		factory1.addProperties(hibernateProperties);
		factory1.addAnnotatedClass(Category.class);
		factory1.addAnnotatedClass(Product.class);
		factory1.addAnnotatedClass(Supplier.class);
		factory1.addAnnotatedClass(User.class);
		//We need to add the annotated class of Model later once it is created.
		SessionFactory sessionFactory=factory1.buildSessionFactory();
		
		System.out.println("===Creating the SessionFactory Bean=====");
		return sessionFactory;
	}
	@Bean(name="categoryDAO")
	public CategoryDAO getCategoryDAO()
	{
		System.out.println("----Category DAO Implementation---");
		return new CategoryDAOImpl();
	}
	@Bean(name="supplierDAO")
	public SupplierDAO getSupplierDAO()
	{
		System.out.println("----Supplier DAO Implementation---");
		return new SupplierDAOImpl();
	}
	
	@Bean(name="userDAO")
	public UserDAO getUserDAO()
	{
		System.out.println("----User DAO Implementation---");
		return new UserDAOImpl();
	}

	@Bean(name="ProductDAO")
	public ProductDAO getProductDAO()
	{
		System.out.println("----Product DAO Implementation---");
		return new ProductDAOImpl();
	}
	


	//3. Create a HibernateTransactionManager
	
	@Bean(name="txManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("===Creating the TransactionManager Bean=====");
		return new HibernateTransactionManager(getSessionFactory());
	}
}





