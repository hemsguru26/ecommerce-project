package com.eccom.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eccom.dao.CategoryDAO;
import com.eccom.model.Category;

public class ManualTest 
{
	public static void main(String arg[])
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.eccom");
		context.refresh();
		
		CategoryDAO categoryDAO=(CategoryDAO)context.getBean("categoryDAO");
		
		Category category=new Category();
		category.setCategoryName("SamsungMobile");
		category.setCategoryDesc("All Variety of Samsung Mobile");
		
		categoryDAO.addCategory(category);
	}
}