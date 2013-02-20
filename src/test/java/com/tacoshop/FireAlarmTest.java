package com.tacoshop;

import com.tacoshop.listeners.CustomAgendaEventListener;
import com.tacoshop.model.Fire;
import com.tacoshop.model.Sprinkler;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test file allowing execution and assertions of fire rules with and without inference
 *
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class FireAlarmTest {

    /**
     * knowledgeBase to be populated and used in each test
     */
    private KnowledgeBase knowledgeBase;

    /**
     * rule session to used in test
     */
    private StatefulKnowledgeSession session;

    /**
     * clean-up method that will ensure we don't have any memory leaks from lingering
     * session resources after each test.
     */
    @After
    public void tearDown() {

        // if session exists, dispose of it for GC
        if (session != null) {
            session.dispose();
        }
    }

    /**
     * test that loads up our rules and exercises a few to make sure no exceptions occur
     *
     * @throws Exception
     */
    @Test
    public void testManualRemovalOfAlarm() throws Exception {

        // let's build a knowledge base from rules not using inference
        try {
            // load a new knowledgeBase with our rules
            knowledgeBase = populateKnowledgeBase("com/tacoshop/rules/fireAlarmNoInference.drl");

            // initialize a session for usage
            session = knowledgeBase.newStatefulKnowledgeSession();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // let's add one of our custom listeners so we can assert what rules fire
        CustomAgendaEventListener listener = new CustomAgendaEventListener();
        session.addEventListener(listener);

        // we need to indicate that room "kitchen" has a sprinkler in it
        session.insert(new Sprinkler("kitchen"));

        // now let's indicate that a fire has started in the kitchen
        session.insert(new Fire("kitchen"));

        // we expect all 4 of our rules to fire in this scenario
        Assert.assertEquals(4, session.fireAllRules(50));

        // fire's detected
        Assert.assertEquals(listener.getRulesFired().get(0), "fire detected, turn on alarm");

        // sprinkler's turned on
        Assert.assertEquals(listener.getRulesFired().get(1), "alarm should turn on sprinklers, extinguishing fire");

        // fire gets extinguished
        Assert.assertEquals(listener.getRulesFired().get(2), "sprinkler came on, that puts out the fire");

        // and finally, we remove the alarm
        Assert.assertEquals(listener.getRulesFired().get(3), "fire's been put out, we should remove the alarm now");
    }

    @Test
    public void testInferenceRemovalOfAlarm() throws Exception {

        // let's build a knowledge base from rules not using inference
        try {
            // load a new knowledgeBase with our rules
            knowledgeBase = populateKnowledgeBase("com/tacoshop/rules/fireAlarmWithInference.drl");

            // initialize a session for usage
            session = knowledgeBase.newStatefulKnowledgeSession();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // let's add one of our custom listeners so we can assert what rules fire
        CustomAgendaEventListener listener = new CustomAgendaEventListener();
        session.addEventListener(listener);

        // we need to indicate that room "kitchen" has a sprinkler in it
        session.insert(new Sprinkler("kitchen"));

        // now let's indicate that a fire has started in the kitchen
        session.insert(new Fire("kitchen"));

        // we expect all 4 of our rules to fire in this scenario
        Assert.assertEquals(3, session.fireAllRules(50));

        // fire's detected
        Assert.assertEquals(listener.getRulesFired().get(0), "fire detected, turn on alarm");

        // sprinkler's turned on
        Assert.assertEquals(listener.getRulesFired().get(1), "alarm should turn on sprinklers, extinguishing fire");

        // fire gets extinguished
        Assert.assertEquals(listener.getRulesFired().get(2), "sprinkler came on, that puts out the fire");

        // assert that working memory contains only the sprinkler, meaning alarm was removed
        Assert.assertTrue(session.getObjects().size() == 1);
        Assert.assertTrue(session.getObjects().toArray()[0] instanceof Sprinkler);
    }

    /**
     * helper method that will read in our rules and populate a knowledgeBase
     *
     * @return knowledgeBase loaded from our rules package
     */
    private KnowledgeBase populateKnowledgeBase(String ruleFilePath) {

        // seed a builder with our rules file from classpath
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource(ruleFilePath), ResourceType.DRL);
        if (builder.hasErrors()) {
            throw new RuntimeException(builder.getErrors().toString());
        }

        // create a knowledgeBase from our builder packages
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

        return knowledgeBase;
    }
}