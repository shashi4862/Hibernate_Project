package org.jsp.merchant_Product_App.DTO;
import java.util.List;

import javax.persistence.*;
public class ProductDao {
	EntityManagerFactory fac = Persistence.createEntityManagerFactory("dev");
	EntityManager man = fac.createEntityManager();
	
	
	public Product addProduct(int mid, Product p) {
		EntityTransaction tran = man.getTransaction();
		tran.begin();
		Merchant mdb = man.find(Merchant.class, mid);
		if(mdb != null) {
			mdb.getProduct().add(p);
			p.setMerchant(mdb);
			man.persist(p);
			tran.commit();
			return p;
		}
		else {
			return null;
		}
	}
	public List<Product> findProductByMerchantId(int mid){
		Query q = man.createQuery("select p from Product p where p.merchant.id = ?1");
		q.setParameter(1, mid);
		List<Product> lps = q.getResultList();
		return lps;
	}
	public Product updateProduct(Product p) {
		EntityTransaction tran = man.getTransaction();
		tran.begin();
		Product pdb = man.find(Product.class, p.getId());
		if(pdb != null) {
			pdb.setName(p.getName());
			pdb.setBrand(p.getBrand());
			pdb.setCost(p.getCost());
			tran.commit();
			return pdb;
		}
		else {
			return null;
		}
	}
	
	public List<Product> findProductByBrand(String brand) {
		Query q = man.createQuery("select p from Product p where p.brand = ?1");
		q.setParameter(1, brand);
		List<Product> lps = q.getResultList();
		return lps;
	}
	
	public List<Product> findProductByCategory(String category) {
		Query q = man.createQuery("select p from Product p where p.category = ?1");
		q.setParameter(1, category);
		List<Product> lps = q.getResultList();
		return lps;
	}
}
