package com.eccom.test;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eccom.dao.ProductDAO;
import com.eccom.model.Product;

public class ProductUnitTest {
	static ProductDAO ProductDAO;

	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.eccom");
		context.refresh();
		
		ProductDAO=(ProductDAO)context.getBean("ProductDAO");
	}
	
	@Test
	public void addProductTest()
	{
		Product product=new Product();
		product.setProductId(5);
		product.setProductName("redmi 4");
		product.setCategoryName("Xiaomi");
		product.setSupplierId(44);		
		product.setPrice(9000);
		product.setStock(20);
		product.setProdDesc("with snapdragon 435");
		assertTrue("Problem in Category Insertion",ProductDAO.addProduct(product));
	}
	
	@Ignore
	@Test
	public void getProductTest()
	{
		assertNotNull("Problem in get Category",ProductDAO.getProduct(5));
	}
	
	@Ignore
	@Test
	public void deleteProductTest()
	{
		Product product=ProductDAO.getProduct(2);
		assertTrue("Problem in Deletion:",ProductDAO.deleteProduct(product));
	}
    @Ignore
	@Test
	public void updateProductTest()
	{
		Product product=ProductDAO.getProduct(1);
		product.setPrice(699);
		product.setStock(9);
		assertTrue("Problem in Updation",ProductDAO.updateProduct(product));
	}
	
    @Ignore
	@Test
	public void listProductTest()
	{   
		List<Product> listProducts=ProductDAO.listProducts();
		assertNotNull("No Products",listProducts);
		
		for(Product product:listProducts)
		{
			System.out.print(product.getStock()+":::");
			System.out.print(product.getPrice()+":::");
			System.out.println(product.getProdDesc());
		}
	}


}



