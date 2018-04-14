package org.warungikan.db.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.warungikan.db.model.TopupWalletHistory;
import org.warungikan.db.model.Transaction;
import org.warungikan.db.model.TransactionDetail;
import org.warungikan.db.model.User;
import org.warungikan.db.repository.ShopItemRepository;
import org.warungikan.db.repository.ShopItemStockRepository;
import org.warungikan.db.repository.TopupWalletRepository;
import org.warungikan.db.repository.TransactionDetailRepository;
import org.warungikan.db.repository.TransactionRepository;
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
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	TransactionDetailRepository transactionDetailRepository;
	
	@Autowired
	TopupWalletRepository walletRepository;
	
	private User cus;
	private User agent;
	private List<ShopItem> items;
	private List<ShopItemStock> defaultStockItem;


	@Test
	@Rollback(false)
	public void testAll(){
		test_generate_customer();
		test_generate_agent();
		test_generate_shop_items();
		test_generate_stocks();
		
		test_transaction();
		test_topup_wallet();
	}
	
	private void test_topup_wallet() {
		TopupWalletHistory topup = new TopupWalletHistory();
		topup.setAmount(new Long(400000));
		topup.setUser(cus);
		topup.setCreationDate(new Date());
		walletRepository.save(topup);
	}

	private void test_transaction() {
		
		TransactionDetail detail1 = new TransactionDetail();
		detail1.setCreationDate(new Date());
		detail1.setAmount(3);
		detail1.setItem(items.get(4));
		
		TransactionDetail detail2 = new TransactionDetail();
		detail2.setAmount(2);
		detail2.setItem(items.get(2));
		detail2.setCreationDate(new Date());
		
		TransactionDetail detail3 = new TransactionDetail();
		detail3.setAmount(1);
		detail3.setItem(items.get(7));
		detail3.setCreationDate(new Date());
		
		TransactionDetail detail4 = new TransactionDetail();
		detail4.setAmount(2);
		detail4.setItem(items.get(6));
		detail4.setCreationDate(new Date());
		
		Set<TransactionDetail> set = new HashSet<TransactionDetail>();
		set.add(detail1);
		set.add(detail2);
		set.add(detail3);
		set.add(detail4);
		
		Transaction t = createTransaction(agent,cus,set);
		
		Assert.assertTrue(t.getOid() != null && t.getOid() > 0);
		
		List<TransactionDetail> trxDetailList = transactionDetailRepository.findTransactionDetailByTransactionId(t);
		trxDetailList.stream().forEach( detail -> {
			Assert.assertTrue(detail.getOid() != null);
		});
		
		//Update stock agent
		List<ShopItemStock> stock = stockItemRepository.findShopItemStockByAgentId(agent);
		for(TransactionDetail d: set) {
			
			ShopItemStock aaa = stock.stream().filter( e -> e.getItem().getId() == d.getItem().getId()).
			findFirst().get();
			
			Integer actualAmount = aaa.getAmount() - d.getAmount();
			aaa.setAmount(actualAmount);
			stockItemRepository.save(aaa);
			
			Assert.assertTrue(aaa.getAmount() > 0 && aaa.getAmount().intValue() == actualAmount.intValue());
		}
		
	}

	private Transaction createTransaction(User agent, User cus, Set<TransactionDetail> set) {
		Transaction t = new Transaction();
		t.setAgent(agent);
		t.setCustomer(cus);
		t.setCreationDate(new Date());
		Long totalAll = new Long(0);
		t.setTotalPrice(totalAll);
		
		transactionRepository.save(t);
		for(TransactionDetail d : set) {
			d.setTransaction(t);
			totalAll = totalAll.longValue() + (d.getAmount() * d.getItem().getPrice());
			transactionDetailRepository.save(d);
		}
		t.setTotalPrice(totalAll);
		t = transactionRepository.save(t);
		
//		Long agentNewBalance = agent.getBalance() + totalAll;
//		agent.setBalance(agentNewBalance);
//		userRepository.save(agent);
		
		//Update customer balance after transaction
		Long customerBalance = cus.getBalance()-totalAll;
		cus.setBalance(customerBalance);
		userRepository.save(cus);
		return t;
	}

	public void test_generate_customer(){
		cus = generateCustomer();
		cus = userRepository.save(cus);
		cus = userRepository.findCustomerById(cus.getId());
		Assert.assertNotNull(cus.getId());
		List<User> customers = userRepository.findAllCustomer();
		
		Assert.assertTrue(customers.size() > 0);
		Assert.assertTrue( (cus.getId() != null) && (cus.getId() > 0) );
	}
	
	public void test_generate_shop_items(){
		items = generateDefaultShopsItem();
		shopItemRepository.save(items);
		items = shopItemRepository.findAll();
		Assert.assertTrue(items.size() == 12);
	}
	
	public void test_generate_stocks(){
		defaultStockItem = generateDefaultStockItem(agent);
		stockItemRepository.save(defaultStockItem);
		
		defaultStockItem = stockItemRepository.findAll();
		Assert.assertTrue(defaultStockItem.size() == 12);
	}

	public void test_generate_agent(){
		agent = generateAgent();
		agent = userRepository.save(agent);
		Assert.assertNotNull(agent.getId());
		
		agent = userRepository.findAgentById(agent.getId());
		List<User> agents = userRepository.findAllAgent();
		
		Assert.assertTrue(agents.size() > 0);
		Assert.assertTrue( (agent.getId() != null) && (agent.getId() > 0) );
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
		u.setEmail("customer1@email.com").setAddress("addressxxxxxxxxx").setUserid("xxxxuser1")
		.setAddressInfo("xxxxAddressInfo").setCity("xxxxCity").setLatitude(12345.922221d)
		.setLongitude(98765.3333221).setName("customer1").setPassword("123asdasdsadpwdasdsadasdsadsadas12345678998765432112345d")
		.setTelpNo("123456789120").setBalance(1000000l)
		.setCreationDate(new Date());
		
		return u;
	}
	
	User generateAgent(){
		User u = new User();
		u.setEmail("agent1@email.com").setAddress("addressxxxxxxxxx").setUserid("xxxxuser2")
		.setAddressInfo("xxxxAddressInfo").setCity("xxxxCity").setLatitude(1.922222221d)
		.setLongitude(96665.3333221).setName("agent1").setPassword("123asdasdsadpwdasdsadasdsadsadas12345678998765432112345d")
		.setTelpNo("123456789120").setBalance(1000000l)
		.setCreationDate(new Date());
		
		return u;
	}
	private List<ShopItem> generateDefaultShopsItem() {
		List<ShopItem> items = new ArrayList();
		
		ShopItem s1 = new ShopItem();
		s1.setCreationDate(new Date());
		s1.setDescription("ikan1");
		s1.setName("ikan1");
		s1.setPrice(40000L);
		s1.setUrl("http://ikan1");
		
		ShopItem s2 = new ShopItem();
		s2.setCreationDate(new Date());
		s2.setDescription("ikan2");
		s2.setName("ikan2");
		s2.setUrl("http://ikan2");
		s2.setPrice(52000L);
		
		ShopItem s3 = new ShopItem();
		s3.setCreationDate(new Date());
		s3.setDescription("ikan3");
		s3.setName("ikan3");
		s3.setUrl("http://ikan3");
		s3.setPrice(32000L);
		
		ShopItem s4 = new ShopItem();
		s4.setCreationDate(new Date());
		s4.setDescription("ikan1");
		s4.setName("ikan4");
		s4.setUrl("http://ikan4");
		s4.setPrice(82000L);
		
		ShopItem s5 = new ShopItem();
		s5.setCreationDate(new Date());
		s5.setDescription("ikan5");
		s5.setName("ikan5");
		s5.setUrl("http://ikan5");
		s5.setPrice(32000L);
		
		ShopItem s6 = new ShopItem();
		s6.setCreationDate(new Date());
		s6.setDescription("ikan6");
		s6.setName("ikan6");
		s6.setUrl("http://ikan6");
		s6.setPrice(51000L);
		
		ShopItem s7 = new ShopItem();
		s7.setCreationDate(new Date());
		s7.setDescription("ikan7");
		s7.setName("ikan7");
		s7.setUrl("http://ikan7");
		s7.setPrice(23000L);
		
		ShopItem s8 = new ShopItem();
		s8.setCreationDate(new Date());
		s8.setDescription("ikan8");
		s8.setName("ikan8");
		s8.setUrl("http://ikan8");
		s8.setPrice(61000L);
		
		ShopItem s9 = new ShopItem();
		s9.setCreationDate(new Date());
		s9.setDescription("ikan9");
		s9.setName("ikan9");
		s9.setUrl("http://ikan9");
		s9.setPrice(870000L);
		
		ShopItem s10 = new ShopItem();
		s10.setCreationDate(new Date());
		s10.setDescription("ikan1");
		s10.setName("ikan1");
		s10.setUrl("http://ikan10");
		s10.setPrice(78000L);
		
		ShopItem s11 = new ShopItem();
		s11.setCreationDate(new Date());
		s11.setDescription("ikan11");
		s11.setName("ikan11");
		s11.setUrl("http://ikan11");
		s11.setPrice(40000L);
		
		ShopItem s12 = new ShopItem();
		s12.setCreationDate(new Date());
		s12.setDescription("ikan12");
		s12.setName("ikan12");
		s12.setUrl("http://ikan12");
		s12.setPrice(62000L);
		
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
