package org.jsp.merchant_Product_App.DTO;
import javax.persistence.*;
public class MerchantDao {
	EntityManagerFactory fac = Persistence.createEntityManagerFactory("dev");
	EntityManager man = fac.createEntityManager();
	
	public Merchant saveMerchant(Merchant m) {
		EntityTransaction tran = man.getTransaction();
		tran.begin();
		man.persist(m);
		tran.commit();
		return m;
	}
	
	public Merchant updateMerchant(Merchant m) {
		EntityTransaction tran = man.getTransaction();
		tran.begin();
		Merchant mdb = man.find(Merchant.class, m.getId());
		if(mdb != null) {
			mdb.setName(m.getName());
			mdb.setGst_number(m.getGst_number());
			mdb.setEmail(m.getEmail());
			tran.commit();
			return mdb;
		}
		else {
			return null;
		}
	}
	
	public Merchant findMerchantById(int mid) {
		return man.find(Merchant.class, mid);
	}

	public Merchant verifyMerchantByEmailAndPassword(String email, String password) {
		Query q = man.createQuery("select m from Merchant m where m.email = ?1 and m.password = ?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			Merchant m = (Merchant)q.getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Merchant verifyMerchantByPhoneAndPassword(long phone, String password) {
		Query q = man.createQuery("select m from Merchant m where m.phone = ?1 and m.password = ?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			Merchant m = (Merchant)q.getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
	}
}
