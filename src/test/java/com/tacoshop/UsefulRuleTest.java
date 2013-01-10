package com.tacoshop;

import java.math.BigDecimal;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tacoshop.filters.CustomAgendaFilter;
import com.tacoshop.listeners.CustomAgendaEventListener;

/**
 * Test file showing different methods of testing expected outcomes when working with rules
 * 
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class UsefulRuleTest {

	/** knowledgeBase to be populated and used in each test */
	private KnowledgeBase knowledgeBase;
	
	/** rule session to used in each test */
	private StatefulKnowledgeSession session;
	
	/**
	 * set-up method that will take care of building our knowledge base and session 
	 * prior to executing each test.
	 */
	@Before
	public void setUp() {
	       
		try {
		   // load a new knowledgeBase with our rules
		   knowledgeBase = populateKnowledgeBase();
		   
		   // initialize a session for usage
		   session = knowledgeBase.newStatefulKnowledgeSession();
		   
		} catch ( Exception e ) {
			e.printStackTrace();
		    Assert.fail( e.getMessage() );
		}
	}

	/**
	 * clean-up method that will ensure we don't have any memory leaks from lingering 
	 * session resources after each test.
	 */
	@After
	public void tearDown() {
		
		// if session exists, dispose of it for GC
		if ( session != null ) {
			session.dispose();
		}
	}
	
	/**
	 * test that loads up our rules and exercises a few to make sure no exceptions occur
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRulesCauseNoExceptions() throws Exception {
			       
		// should trigger our first and last rule
		session.insert(new Purchase(new BigDecimal("16"), 2, true));
		
		Assert.assertEquals(2, session.fireAllRules(50));
    }
	
	/**
	 * test that uses a custom agenda filter to only allow rules we've named to fire
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOnlyComboRuleFires() throws Exception {
		
		session.insert(new Purchase(new BigDecimal("16"), 2, true));
		
		String[] expectedRules = {"purchase contains combo"};
		Assert.assertEquals(1, session.fireAllRules(new CustomAgendaFilter(expectedRules)));
	}
	
	/**
	 * test that uses a custom agenda listener to verify rules by name and order
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVerifyRulesViaListener() throws Exception {
		
		CustomAgendaEventListener listener = new CustomAgendaEventListener();
		session.addEventListener(listener);
		
		// should trigger our first and last rule
		session.insert(new Purchase(new BigDecimal("16"), 2, true));
		
		Assert.assertEquals(2, session.fireAllRules(50));
		Assert.assertEquals(listener.getRulesFired().get(0), "purchase over 15 and less than 25 dollars");
		Assert.assertEquals(listener.getRulesFired().get(1), "purchase contains combo");
	}
	   
	/**
	 * helper method that will read in our rules and populate a knowledgeBase
	 * 
	 * @return knowledgeBase loaded from our rules package
	 */
	private KnowledgeBase populateKnowledgeBase() {
			   
		// seed a builder with our rules file from classpath
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		builder.add(new ClassPathResource("discountRules.drl", getClass()), ResourceType.DRL);
		if (builder.hasErrors()) {
		    throw new RuntimeException(builder.getErrors().toString());
		}

		// create a knowledgeBase from our builder packages
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
		
		return knowledgeBase;
	}
}