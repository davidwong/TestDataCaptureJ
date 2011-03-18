package au.com.dw.testdatacapturej.tutorial;

public class CreateTestCart_Step1_Edited {
	
public org.springframework.samples.jpetstore.domain.Cart createParam2Cart_org_springframework_samples_jpetstore_domain_Order_initOrder() {

org.springframework.samples.jpetstore.domain.Cart cart0 = new org.springframework.samples.jpetstore.domain.Cart();

//java.util.Collections$SynchronizedMap collections$SynchronizedMap0 = new java.util.Collections$SynchronizedMap();
java.util.Map collections$SynchronizedMap0 = java.util.Collections.synchronizedMap(new java.util.HashMap());
// Default constructor for java.util.Collections$SynchronizedMap does not exist.

org.springframework.samples.jpetstore.domain.CartItem cartItem1 = new org.springframework.samples.jpetstore.domain.CartItem();

org.springframework.samples.jpetstore.domain.Item item2 = new org.springframework.samples.jpetstore.domain.Item();
item2.setItemId("EST-14");
item2.setProductId("FL-DSH-01");
item2.setListPrice(58.5d);
item2.setUnitCost(12.0d);
item2.setSupplierId(1);
item2.setStatus("P");
item2.setAttribute1("Tailless");
item2.setAttribute2(null);
item2.setAttribute3(null);
item2.setAttribute4(null);
item2.setAttribute5(null);

org.springframework.samples.jpetstore.domain.Product product3 = new org.springframework.samples.jpetstore.domain.Product();
product3.setProductId("FL-DSH-01");
product3.setCategoryId("CATS");
product3.setName("Manx");
product3.setDescription("<image src=\"../images/cat3.gif\">Great for reducing mouse populations");
item2.setProduct(product3);
item2.setQuantity(9999);
cartItem1.setItem(item2);
cartItem1.setQuantity(3);
cartItem1.setInStock(true);
collections$SynchronizedMap0.put("EST-14", cartItem1);

//cart0.setItemMap(collections$SynchronizedMap0);

org.springframework.beans.support.PagedListHolder pagedListHolder4 = new org.springframework.beans.support.PagedListHolder();

java.util.ArrayList arrayList0 = new java.util.ArrayList();

org.springframework.samples.jpetstore.domain.CartItem cartItem5 = new org.springframework.samples.jpetstore.domain.CartItem();

org.springframework.samples.jpetstore.domain.Item item6 = new org.springframework.samples.jpetstore.domain.Item();
item6.setItemId("EST-14");
item6.setProductId("FL-DSH-01");
item6.setListPrice(58.5d);
item6.setUnitCost(12.0d);
item6.setSupplierId(1);
item6.setStatus("P");
item6.setAttribute1("Tailless");
item6.setAttribute2(null);
item6.setAttribute3(null);
item6.setAttribute4(null);
item6.setAttribute5(null);

org.springframework.samples.jpetstore.domain.Product product7 = new org.springframework.samples.jpetstore.domain.Product();
product7.setProductId("FL-DSH-01");
product7.setCategoryId("CATS");
product7.setName("Manx");
product7.setDescription("<image src=\"../images/cat3.gif\">Great for reducing mouse populations");
item6.setProduct(product7);
item6.setQuantity(9999);
cartItem5.setItem(item6);
cartItem5.setQuantity(3);
cartItem5.setInStock(true);
arrayList0.add(cartItem5);

pagedListHolder4.setSource(arrayList0);

java.util.Date date8 = new java.util.Date();
//date8.setFastTime(1279213622453L);
//date8.setCdate(null);
//pagedListHolder4.setRefreshDate(date8);

org.springframework.beans.support.MutableSortDefinition mutableSortDefinition9 = new org.springframework.beans.support.MutableSortDefinition();
mutableSortDefinition9.setProperty("");
mutableSortDefinition9.setIgnoreCase(true);
mutableSortDefinition9.setAscending(true);
mutableSortDefinition9.setToggleAscendingOnProperty(true);
pagedListHolder4.setSort(mutableSortDefinition9);
//pagedListHolder4.setSortUsed(null);
pagedListHolder4.setPageSize(4);
pagedListHolder4.setPage(0);
//pagedListHolder4.setNewPageSet(false);
pagedListHolder4.setMaxLinkedPages(10);
//cart0.setItemList(pagedListHolder4);

return cart0;
}

}
