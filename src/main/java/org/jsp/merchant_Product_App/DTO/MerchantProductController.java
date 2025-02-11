package org.jsp.merchant_Product_App.DTO;

import java.util.List;
import java.util.Scanner;

public class MerchantProductController {
	static Scanner sc = new Scanner(System.in);
	static MerchantDao mdao = new MerchantDao();
	static ProductDao pdao = new ProductDao();
	
	public static void main(String[] args) {
		System.out.println("1.Save Merchant");
		System.out.println("2.update Merchant");
		System.out.println("3.Find Merchant by id");
		System.out.println("4.Verify Merchant by Email and Password");
		System.out.println("5.Verify Merchant by Phone and Password");
		System.out.println("6.Add Product");
		System.out.println("7.update Product");
		System.out.println("8.Find Products by Merchant id");
		System.out.println("9.Find Products by Brand");
		System.out.println("10.Find Products by Category");
		System.out.println("Enter Your Choice??");
		
		int Choice = sc.nextInt();
		switch (Choice) {
		case 1:saveMerchant();
			break;
		case 2:updateMerchant();
		break;
		case 3:findMerchantById();
		break;
		case 4:verifyMerchantByEmailAndPassword();
		break;
		case 5:VerifyMerchantbyPhoneandPassword();
		break;
		case 6:AddProduct();
		break;
		case 7:updateProduct();
		break;
		case 8:FindProductsbyMerchantid();
		break;
		case 9:FindProductsbyBrand();
		break;
		case 10:FindProductsbyCategory();
		break;

		default:
			System.out.println("Enter The Valid Number.");
			break;
		}
	}

	private static void FindProductsbyCategory() {
		System.out.println("Enter product category to display product info");
		String category = sc.next();
		List<Product> lpsdb = pdao.findProductByCategory(category);
		
		if(lpsdb.size() > 0) {
			for(Product p:lpsdb) {
				System.out.println(p);
			}
		}
		else {
			System.err.println("No Product info found since you have entered invalid brand");
		}
	}

	private static void FindProductsbyBrand() {
		System.out.println("Enter product brand to display product info");
		String brand = sc.next();
		List<Product> lpsdb = pdao.findProductByBrand(brand);
		
		if(lpsdb.size() > 0) {
			for(Product p:lpsdb) {
				System.out.println(p);
			}
		}
		else {
			System.err.println("No Product info found since you have entered invalid brand");
		}
	}

	private static void FindProductsbyMerchantid() {
		System.out.println("Enter Merchant id to display Products");
		int mid = sc.nextInt();
		
		List<Product> lpsdb = pdao.findProductByMerchantId(mid);
		if(lpsdb.size() > 0) {
			for(Product p:lpsdb) {
				System.out.println(p);
			}
		}
		else {
			System.err.println("No Product info found since you have entered invalid merchant id");
		}
	}

	private static void updateProduct() {
		System.out.println("Enter the Product info to update---Id, brand, name, cost");
		Product p = new Product();
		p.setId(sc.nextInt());
		p.setBrand(sc.next());
		p.setName(sc.next());
		p.setCost(sc.nextDouble());
		
		Product mdb = pdao.updateProduct(p);
		if(mdb != null) {
			System.out.println("Merchant is Updated");
		}
		else {
			System.err.println("Merchant is Not Updated");
		}
	}

	private static void AddProduct() {
		System.out.println("Enter Merchant id:-");
		int mid = sc.nextInt();
		System.out.println("Enter the Product info --- name, brand, category, cost");
		Product p = new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setCost(sc.nextDouble());
		
		Product pdb = pdao.addProduct(mid, p);
		if(pdb != null) {
			System.out.println("Product is saved with an id "+pdb.getId());
		}
		else {
			System.err.println("No Product info found since you have entered invalid merchant id...");
		}
	}

	private static void VerifyMerchantbyPhoneandPassword() {
		System.out.println("Enter Phone??");
		long phone=sc.nextLong();
		System.out.println("Enter Password??");
		String password = sc.next();
		Merchant mdb = mdao.verifyMerchantByPhoneAndPassword(phone, password);
		if(mdb != null) {
			System.out.println("Merchant is Verified");
		}
		else {
			System.err.println("Merchant is not verified");
		}		
	}

	private static void verifyMerchantByEmailAndPassword() {
		System.out.println("Enter Email??");
		String email=sc.next();
		System.out.println("Enter Password??");
		String password = sc.next();
		Merchant mdb = mdao.verifyMerchantByEmailAndPassword(email, password);
		if(mdb != null) {
			System.out.println("Merchant is Verified");
		}
		else {
			System.err.println("Merchant is not verified");
		}
	}

	private static void findMerchantById() {
		System.out.println("Enter Merchant id??");
		int mid = sc.nextInt();
		Merchant mdb = mdao.findMerchantById(mid);
		if(mdb != null) {
			System.out.println(mdb);
		}
		else {
			System.err.println("No Merchant info found since you have entered wrong id");
		}
	}

	private static void updateMerchant() {
		System.out.println("Enter the Merchant info to update---id, name, gst, email");
		Merchant m = new Merchant();
		m.setId(sc.nextInt());
		m.setName(sc.next());
		m.setGst_number(sc.next());
		m.setEmail(sc.next());
		
		Merchant mdb = mdao.updateMerchant(m);
		if(mdb != null) {
			System.out.println("Merchant is Updated");
		}
		else {
			System.err.println("Merchant is Not Updated");
		}
	}

	private static void saveMerchant() {
		System.out.println("Enter the Merchant info---name,phone,gst,email,password");
		String name = sc.next();
		long phone = sc.nextLong();
		String gst_num = sc.next();
		String email = sc.next();
		String password = sc.next();
		
		Merchant m = new Merchant();
		m.setName(name);
		m.setPhone(phone);
		m.setGst_number(gst_num);
		m.setEmail(email);
		m.setPassword(password);
		
		Merchant mdb = mdao.saveMerchant(m);
		System.out.println("Merchant is saved with an id "+ mdb.getId());
	}
}
