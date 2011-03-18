// Generated 2011-03-14 17:49:42,406
// org.springframework.samples.jpetstore.domain.Order.initOrder:Parameter2
public org.springframework.samples.jpetstore.domain.Cart createParam2Cart_org_springframework_samples_jpetstore_domain_Order_initOrder() {

org.springframework.samples.jpetstore.domain.Cart cart0 = new org.springframework.samples.jpetstore.domain.Cart();

java.util.Collections$SynchronizedMap collections$SynchronizedMap0 = new java.util.Collections$SynchronizedMap();
// Default constructor for java.util.Collections$SynchronizedMap does not exist.

org.springframework.samples.jpetstore.domain.CartItem cartItem0 = new org.springframework.samples.jpetstore.domain.CartItem();

org.springframework.samples.jpetstore.domain.Item item0 = new org.springframework.samples.jpetstore.domain.Item();
item0.setItemId("EST-15");
item0.setProductId("FL-DSH-01");
item0.setListPrice(23.5d);
item0.setUnitCost(12.0d);
item0.setSupplierId(1);
item0.setStatus("P");
item0.setAttribute1("With tail");
item0.setAttribute2(null);
item0.setAttribute3(null);
item0.setAttribute4(null);
item0.setAttribute5(null);

org.springframework.samples.jpetstore.domain.Product product0 = new org.springframework.samples.jpetstore.domain.Product();
product0.setProductId("FL-DSH-01");
product0.setCategoryId("CATS");
product0.setName("Manx");
product0.setDescription("<image src=\"../images/cat3.gif\">Great for reducing mouse populations");
item0.setProduct(product0);
item0.setQuantity(9975);
cartItem0.setItem(item0);
cartItem0.setQuantity(1);
cartItem0.setInStock(true);
collections$SynchronizedMap0.put("EST-15", cartItem0);

cart0.setItemMap(collections$SynchronizedMap0);

org.springframework.beans.support.PagedListHolder pagedListHolder0 = new org.springframework.beans.support.PagedListHolder();

java.util.ArrayList arrayList0 = new java.util.ArrayList();

org.springframework.samples.jpetstore.domain.CartItem cartItem1 = new org.springframework.samples.jpetstore.domain.CartItem();

org.springframework.samples.jpetstore.domain.Item item1 = new org.springframework.samples.jpetstore.domain.Item();
item1.setItemId("EST-15");
item1.setProductId("FL-DSH-01");
item1.setListPrice(23.5d);
item1.setUnitCost(12.0d);
item1.setSupplierId(1);
item1.setStatus("P");
item1.setAttribute1("With tail");
item1.setAttribute2(null);
item1.setAttribute3(null);
item1.setAttribute4(null);
item1.setAttribute5(null);

org.springframework.samples.jpetstore.domain.Product product1 = new org.springframework.samples.jpetstore.domain.Product();
product1.setProductId("FL-DSH-01");
product1.setCategoryId("CATS");
product1.setName("Manx");
product1.setDescription("<image src=\"../images/cat3.gif\">Great for reducing mouse populations");
item1.setProduct(product1);
item1.setQuantity(9975);
cartItem1.setItem(item1);
cartItem1.setQuantity(1);
cartItem1.setInStock(true);
arrayList0.add(cartItem1);

pagedListHolder0.setSource(arrayList0);

org.springframework.beans.support.MutableSortDefinition mutableSortDefinition0 = new org.springframework.beans.support.MutableSortDefinition();
mutableSortDefinition0.setProperty("");
mutableSortDefinition0.setIgnoreCase(true);
mutableSortDefinition0.setAscending(true);
mutableSortDefinition0.setToggleAscendingOnProperty(true);
pagedListHolder0.setSort(mutableSortDefinition0);
pagedListHolder0.setPageSize(4);
pagedListHolder0.setPage(0);
pagedListHolder0.setMaxLinkedPages(10);
cart0.setItemList(pagedListHolder0);

return cart0;
}


