package org.warungikan.db.main;

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
import org.warungikan.db.model.User;
import org.warungikan.db.repository.UserRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class DBTest {

	
	@Autowired
	UserRepository userRepository;
	
	
	private User cus;
	private User agent;
	
	@Test
	public void test_agent(){
		cus = generateCustomer();
		cus = userRepository.save(cus);
		cus = userRepository.findCustomerById(cus.getId());
		Assert.assertNotNull(cus.getId());
		List<User> customers = userRepository.findAllCustomer();
		
		Assert.assertTrue(customers.size() > 0);
		Assert.assertTrue( (cus.getId() != null) && (cus.getId() > 0) );
	}
	
	@Test
	public void test_customer(){
		agent = generateAgent();
		agent = userRepository.save(agent);
		Assert.assertNotNull(agent.getId());
		
		agent = userRepository.findAgentById(agent.getId());
		List<User> agents = userRepository.findAllAgent();
		
		Assert.assertTrue(agents.size() > 0);
		Assert.assertTrue( (agent.getId() != null) && (agent.getId() > 0) );
	}
	
	

	User generateCustomer(){
		User u = new User();
		u.setEmail("customer1@email.com").setAddress("addressxxxxxxxxx")
		.setAddressInfo("xxxxAddressInfo").setCity("xxxxCity").setLatitude(12345.922221d)
		.setLongitude(98765.3333221).setName("customer1").setPassword("123asdasdsadpwdasdsadasdsadsadas12345678998765432112345d")
		.setTelpNo("123456789120").setType(new Short("1"))
		.setCreationDate(new Date());
		
		return u;
	}
	
	User generateAgent(){
		User u = new User();
		u.setEmail("agent1@email.com").setAddress("addressxxxxxxxxx")
		.setAddressInfo("xxxxAddressInfo").setCity("xxxxCity").setLatitude(1.922222221d)
		.setLongitude(96665.3333221).setName("agent1").setPassword("123asdasdsadpwdasdsadasdsadsadas12345678998765432112345d")
		.setTelpNo("123456789120").setType(new Short("2"))
		.setCreationDate(new Date());
		
		return u;
	}
}
