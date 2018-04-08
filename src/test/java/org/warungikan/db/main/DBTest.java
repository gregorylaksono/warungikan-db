package org.warungikan.db.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.warungikan.db.model.ShopItem;
import org.warungikan.db.model.ShopItemStock;
import org.warungikan.db.model.User;
import org.warungikan.db.repository.ShopItemRepository;
import org.warungikan.db.repository.ShopItemStockRepository;
import org.warungikan.db.repository.UserRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class DBTest {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ShopItemStockRepository stockItemRepository;
	
	@Autowired
	ShopItemRepository shopItemRepository;
	
	private User cus;
	private User agent;
	private List<ShopItem> items;
	private List<ShopItemStock> defaultStockItem;

	@Test
	@Rollback(false)
	public void testAll(){
		test_customer();
		test_agent();
		test_shop_items();
		test_stocks();
	}
	
	public void test_customer(){
		cus = generateCustomer();
		cus = userRepository.save(cus);
		cus = userRepository.findCustomerById(cus.getId());
		Assert.assertNotNull(cus.getId());
		List<User> customers = userRepository.findAllCustomer();
		
		Assert.assertTrue(customers.size() > 0);
		Assert.assertTrue( (cus.getId() != null) && (cus.getId() > 0) );
	}
	
	public void test_shop_items(){
		items = generateDefaultShopsItem();
		shopItemRepository.save(items);
		items = shopItemRepository.findAll();
		Assert.assertTrue(items.size() == 12);
	}
	
	public void test_stocks(){
		defaultStockItem = generateDefaultStockItem(agent);
		stockItemRepository.save(defaultStockItem);
		
		defaultStockItem = stockItemRepository.findAll();
		Assert.assertTrue(defaultStockItem.size() == 12);
	}

	public void test_agent(){
		agent = generateAgent();
		agent = userRepository.save(agent);
		Assert.assertNotNull(agent.getId());
		
		agent = userRepository.findAgentById(agent.getId());
		List<User> agents = userRepository.findAllAgent();
		
		Assert.assertTrue(agents.size() > 0);
		Assert.assertTrue( (agent.getId() != null) && (agent.getId() > 0) );
	}
	
	public void test_transaction(){
		
	}
	
	
	private List<ShopItemStock> generateDefaultStockItem(User agent) {
		List<ShopItemStock> stocks = new ArrayList();
		int index = 1;
		for(ShopItem i: items){
			ShopItemStock s = new ShopItemStock();
			s.setAgent(agent);
			s.setCreationDate(new Date());
			s.setAmount(index * 1000);
			s.setItem(i);
			stocks.add(s);
			index++;
		}
		return stocks;
	}



	User generateCustomer(){
		User u = new User();
		u.setEmail("customer1@email.com").setAddress("addressxxxxxxxxx")
		.setAddressInfo("xxxxAddressInfo").setCity("xxxxCity").setLatitude(12345.922221d)
		.setLongitude(98765.3333221).setName("customer1").setPassword("123asdasdsadpwdasdsadasdsadsadas12345678998765432112345d")
		.setTelpNo("123456789120").setType(new Short("1")).setBalance(0l)
		.setCreationDate(new Date());
		
		return u;
	}
	
	User generateAgent(){
		User u = new User();
		u.setEmail("agent1@email.com").setAddress("addressxxxxxxxxx")
		.setAddressInfo("xxxxAddressInfo").setCity("xxxxCity").setLatitude(1.922222221d)
		.setLongitude(96665.3333221).setName("agent1").setPassword("123asdasdsadpwdasdsadasdsadsadas12345678998765432112345d")
		.setTelpNo("123456789120").setType(new Short("2")).setBalance(0l)
		.setCreationDate(new Date());
		
		return u;
	}
	private List<ShopItem> generateDefaultShopsItem() {
		List<ShopItem> items = new ArrayList();
		
		ShopItem s1 = new ShopItem();
		s1.setCreationDate(new Date());
		s1.setDescription("ikan1");
		s1.setName("ikan1");
		s1.setUrl("http://ikan1");
		
		ShopItem s2 = new ShopItem();
		s2.setCreationDate(new Date());
		s2.setDescription("ikan2");
		s2.setName("ikan2");
		s2.setUrl("http://ikan2");
		
		ShopItem s3 = new ShopItem();
		s3.setCreationDate(new Date());
		s3.setDescription("ikan3");
		s3.setName("ikan3");
		s3.setUrl("http://ikan3");
		
		ShopItem s4 = new ShopItem();
		s4.setCreationDate(new Date());
		s4.setDescription("ikan1");
		s4.setName("ikan4");
		s4.setUrl("http://ikan4");
		
		ShopItem s5 = new ShopItem();
		s5.setCreationDate(new Date());
		s5.setDescription("ikan5");
		s5.setName("ikan5");
		s5.setUrl("http://ikan5");
		
		ShopItem s6 = new ShopItem();
		s6.setCreationDate(new Date());
		s6.setDescription("ikan6");
		s6.setName("ikan6");
		s6.setUrl("http://ikan6");
		
		ShopItem s7 = new ShopItem();
		s7.setCreationDate(new Date());
		s7.setDescription("ikan7");
		s7.setName("ikan7");
		s7.setUrl("http://ikan7");
		
		ShopItem s8 = new ShopItem();
		s8.setCreationDate(new Date());
		s8.setDescription("ikan8");
		s8.setName("ikan8");
		s8.setUrl("http://ikan8");
		
		ShopItem s9 = new ShopItem();
		s9.setCreationDate(new Date());
		s9.setDescription("ikan9");
		s9.setName("ikan9");
		s9.setUrl("http://ikan9");
		
		ShopItem s10 = new ShopItem();
		s10.setCreationDate(new Date());
		s10.setDescription("ikan1");
		s10.setName("ikan1");
		s10.setUrl("http://ikan10");
		
		ShopItem s11 = new ShopItem();
		s11.setCreationDate(new Date());
		s11.setDescription("ikan11");
		s11.setName("ikan11");
		s11.setUrl("http://ikan11");
		
		ShopItem s12 = new ShopItem();
		s12.setCreationDate(new Date());
		s12.setDescription("ikan12");
		s12.setName("ikan12");
		s12.setUrl("http://ikan12");
		
		items.add(s1);
		items.add(s2);
		items.add(s3);
		items.add(s4);
		items.add(s5);
		items.add(s6);
		items.add(s7);
		items.add(s8);
		items.add(s9);
		items.add(s10);
		items.add(s11);
		items.add(s12);
		return items;
	}
}
