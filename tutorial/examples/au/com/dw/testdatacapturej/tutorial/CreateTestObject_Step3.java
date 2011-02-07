package au.com.dw.testdatacapturej.tutorial;

public class CreateTestObject_Step3 {
	
	// Generated 12:58:21,953 
	// org.springframework.samples.jpetstore.domain.Order.initOrder
	public org.springframework.samples.jpetstore.domain.Account createParam1Account_org_springframework_samples_jpetstore_domain_Order_initOrder() {

	org.springframework.samples.jpetstore.domain.Account account0 = new org.springframework.samples.jpetstore.domain.Account();
	account0.setUsername("j2ee");
	account0.setPassword(null);
	account0.setEmail("yourname@yourdomain.com");
	account0.setFirstName("ABC");
	account0.setLastName("XYX");
	account0.setStatus("OK");
	account0.setAddress1("901 San Antonio Road");
	account0.setAddress2("MS UCUP02-206");
	account0.setCity("Palo Alto");
	account0.setState("CA");
	account0.setZip("94303");
	account0.setCountry("USA");
	account0.setPhone("555-555-5555");
	account0.setFavouriteCategoryId("DOGS");
	account0.setLanguagePreference("english");
	account0.setListOption(true);
	account0.setBannerOption(true);
	account0.setBannerName("<image src=\"../images/banner_dogs.gif\">");

	return account0;
	}

	public org.springframework.samples.jpetstore.domain.Cart createParam2Cart_org_springframework_samples_jpetstore_domain_Order_initOrder() {

	org.springframework.samples.jpetstore.domain.Cart cart0 = new org.springframework.samples.jpetstore.domain.Cart();

	java.util.Collections$SynchronizedMap collections$SynchronizedMap0 = new java.util.Collections$SynchronizedMap();
	// Default constructor for java.util.Collections$SynchronizedMap does not exist.

	org.springframework.samples.jpetstore.domain.CartItem cartItem1 = new org.springframework.samples.jpetstore.domain.CartItem();

	org.springframework.samples.jpetstore.domain.Item item2 = new org.springframework.samples.jpetstore.domain.Item();
	item2.setItemId("EST-18");
	item2.setProductId("AV-CB-01");
	item2.setListPrice(193.5d);
	item2.setUnitCost(92.0d);
	item2.setSupplierId(1);
	item2.setStatus("P");
	item2.setAttribute1("Adult Male");
	item2.setAttribute2(null);
	item2.setAttribute3(null);
	item2.setAttribute4(null);
	item2.setAttribute5(null);

	org.springframework.samples.jpetstore.domain.Product product3 = new org.springframework.samples.jpetstore.domain.Product();
	product3.setProductId("AV-CB-01");
	product3.setCategoryId("BIRDS");
	product3.setName("Amazon Parrot");
	product3.setDescription("<image src=\"../images/bird4.gif\">Great companion for up to 75 years");
	item2.setProduct(product3);
	item2.setQuantity(-4990005);
	cartItem1.setItem(item2);
	cartItem1.setQuantity(2);
	cartItem1.setInStock(false);
	collections$SynchronizedMap0.put("EST-18", cartItem1);

	org.springframework.samples.jpetstore.domain.CartItem cartItem4 = new org.springframework.samples.jpetstore.domain.CartItem();

	org.springframework.samples.jpetstore.domain.Item item5 = new org.springframework.samples.jpetstore.domain.Item();
	item5.setItemId("EST-14");
	item5.setProductId("FL-DSH-01");
	item5.setListPrice(58.5d);
	item5.setUnitCost(12.0d);
	item5.setSupplierId(1);
	item5.setStatus("P");
	item5.setAttribute1("Tailless");
	item5.setAttribute2(null);
	item5.setAttribute3(null);
	item5.setAttribute4(null);
	item5.setAttribute5(null);

	org.springframework.samples.jpetstore.domain.Product product6 = new org.springframework.samples.jpetstore.domain.Product();
	product6.setProductId("FL-DSH-01");
	product6.setCategoryId("CATS");
	product6.setName("Manx");
	product6.setDescription("<image src=\"../images/cat3.gif\">Great for reducing mouse populations");
	item5.setProduct(product6);
	item5.setQuantity(9993);
	cartItem4.setItem(item5);
	cartItem4.setQuantity(3);
	cartItem4.setInStock(true);
	collections$SynchronizedMap0.put("EST-14", cartItem4);

	org.springframework.samples.jpetstore.domain.CartItem cartItem7 = new org.springframework.samples.jpetstore.domain.CartItem();

	org.springframework.samples.jpetstore.domain.Item item8 = new org.springframework.samples.jpetstore.domain.Item();
	item8.setItemId("EST-13");
	item8.setProductId("RP-LI-02");
	item8.setListPrice(18.5d);
	item8.setUnitCost(12.0d);
	item8.setSupplierId(1);
	item8.setStatus("P");
	item8.setAttribute1("Green Adult");
	item8.setAttribute2(null);
	item8.setAttribute3(null);
	item8.setAttribute4(null);
	item8.setAttribute5(null);

	org.springframework.samples.jpetstore.domain.Product product9 = new org.springframework.samples.jpetstore.domain.Product();
	product9.setProductId("RP-LI-02");
	product9.setCategoryId("REPTILES");
	product9.setName("Iguana");
	product9.setDescription("<image src=\"../images/lizard2.gif\">Friendly green friend");
	item8.setProduct(product9);
	item8.setQuantity(9997);
	cartItem7.setItem(item8);
	cartItem7.setQuantity(1);
	cartItem7.setInStock(true);
	collections$SynchronizedMap0.put("EST-13", cartItem7);

	cart0.setItemMap(collections$SynchronizedMap0);

	org.springframework.beans.support.PagedListHolder pagedListHolder10 = new org.springframework.beans.support.PagedListHolder();

	java.util.ArrayList arrayList0 = new java.util.ArrayList();

	org.springframework.samples.jpetstore.domain.CartItem cartItem11 = new org.springframework.samples.jpetstore.domain.CartItem();

	org.springframework.samples.jpetstore.domain.Item item12 = new org.springframework.samples.jpetstore.domain.Item();
	item12.setItemId("EST-14");
	item12.setProductId("FL-DSH-01");
	item12.setListPrice(58.5d);
	item12.setUnitCost(12.0d);
	item12.setSupplierId(1);
	item12.setStatus("P");
	item12.setAttribute1("Tailless");
	item12.setAttribute2(null);
	item12.setAttribute3(null);
	item12.setAttribute4(null);
	item12.setAttribute5(null);

	org.springframework.samples.jpetstore.domain.Product product13 = new org.springframework.samples.jpetstore.domain.Product();
	product13.setProductId("FL-DSH-01");
	product13.setCategoryId("CATS");
	product13.setName("Manx");
	product13.setDescription("<image src=\"../images/cat3.gif\">Great for reducing mouse populations");
	item12.setProduct(product13);
	item12.setQuantity(9993);
	cartItem11.setItem(item12);
	cartItem11.setQuantity(3);
	cartItem11.setInStock(true);
	arrayList0.add(cartItem11);

	org.springframework.samples.jpetstore.domain.CartItem cartItem14 = new org.springframework.samples.jpetstore.domain.CartItem();

	org.springframework.samples.jpetstore.domain.Item item15 = new org.springframework.samples.jpetstore.domain.Item();
	item15.setItemId("EST-13");
	item15.setProductId("RP-LI-02");
	item15.setListPrice(18.5d);
	item15.setUnitCost(12.0d);
	item15.setSupplierId(1);
	item15.setStatus("P");
	item15.setAttribute1("Green Adult");
	item15.setAttribute2(null);
	item15.setAttribute3(null);
	item15.setAttribute4(null);
	item15.setAttribute5(null);

	org.springframework.samples.jpetstore.domain.Product product16 = new org.springframework.samples.jpetstore.domain.Product();
	product16.setProductId("RP-LI-02");
	product16.setCategoryId("REPTILES");
	product16.setName("Iguana");
	product16.setDescription("<image src=\"../images/lizard2.gif\">Friendly green friend");
	item15.setProduct(product16);
	item15.setQuantity(9997);
	cartItem14.setItem(item15);
	cartItem14.setQuantity(1);
	cartItem14.setInStock(true);
	arrayList0.add(cartItem14);

	org.springframework.samples.jpetstore.domain.CartItem cartItem17 = new org.springframework.samples.jpetstore.domain.CartItem();

	org.springframework.samples.jpetstore.domain.Item item18 = new org.springframework.samples.jpetstore.domain.Item();
	item18.setItemId("EST-18");
	item18.setProductId("AV-CB-01");
	item18.setListPrice(193.5d);
	item18.setUnitCost(92.0d);
	item18.setSupplierId(1);
	item18.setStatus("P");
	item18.setAttribute1("Adult Male");
	item18.setAttribute2(null);
	item18.setAttribute3(null);
	item18.setAttribute4(null);
	item18.setAttribute5(null);

	org.springframework.samples.jpetstore.domain.Product product19 = new org.springframework.samples.jpetstore.domain.Product();
	product19.setProductId("AV-CB-01");
	product19.setCategoryId("BIRDS");
	product19.setName("Amazon Parrot");
	product19.setDescription("<image src=\"../images/bird4.gif\">Great companion for up to 75 years");
	item18.setProduct(product19);
	item18.setQuantity(-4990005);
	cartItem17.setItem(item18);
	cartItem17.setQuantity(2);
	cartItem17.setInStock(false);
	arrayList0.add(cartItem17);

	pagedListHolder10.setSource(arrayList0);

	org.springframework.beans.support.MutableSortDefinition mutableSortDefinition21 = new org.springframework.beans.support.MutableSortDefinition();
	mutableSortDefinition21.setProperty("");
	mutableSortDefinition21.setIgnoreCase(true);
	mutableSortDefinition21.setAscending(true);
	mutableSortDefinition21.setToggleAscendingOnProperty(true);
	pagedListHolder10.setSort(mutableSortDefinition21);
	pagedListHolder10.setSortUsed(null);
	pagedListHolder10.setPageSize(4);
	pagedListHolder10.setPage(0);
	pagedListHolder10.setMaxLinkedPages(10);
	cart0.setItemList(pagedListHolder10);

	return cart0;
	}

}
