package com.eccom.dao;

import java.util.List;

import com.eccom.model.Supplier;


public interface SupplierDAO 
{
	public boolean addSupplier(Supplier supplier);
	public boolean deleteSupplier(Supplier supplier);
	public boolean updateSupplier(Supplier supplier);
	public Supplier getSupplier(int supplierId);
	public List<Supplier> listSupplier();
}


