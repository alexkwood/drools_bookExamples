package com.tacoshop.listeners;

import java.util.LinkedList;
import java.util.List;

import org.drools.event.rule.ActivationCancelledEvent;
import org.drools.event.rule.ActivationCreatedEvent;
import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.AgendaEventListener;
import org.drools.event.rule.AgendaGroupPoppedEvent;
import org.drools.event.rule.AgendaGroupPushedEvent;
import org.drools.event.rule.BeforeActivationFiredEvent;
import org.drools.event.rule.RuleFlowGroupActivatedEvent;
import org.drools.event.rule.RuleFlowGroupDeactivatedEvent;

/**
 * custom listener implementation of the AgendaEventListener used
 * to assert rules that fired by name and order.
 * 
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class CustomAgendaEventListener implements AgendaEventListener {

	/** a string list that we'll use to keep order and names of rules that have fired */
	private List<String> rulesFired = new LinkedList<String>();
	
	/**
	 * getter for rulesFired property
	 * 
	 * @return list of rule names that fired
	 */
	public List<String> getRulesFired() {
		return this.rulesFired;
	}
	
	public void activationCancelled(ActivationCancelledEvent event) {
	}

	public void activationCreated(ActivationCreatedEvent event) {
	}

	/**
	 * capture all afterActivationFiredEvents so that we can record some
	 * information about them
	 * 
	 * @param event captured activation event
	 */
	public void afterActivationFired(AfterActivationFiredEvent event) {
		rulesFired.add(event.getActivation().getRule().getName());
	}

	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	}

	public void afterRuleFlowGroupDeactivated(
			RuleFlowGroupDeactivatedEvent event) {
	}

	public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
	}

	public void agendaGroupPushed(AgendaGroupPushedEvent event) {
	}

	public void beforeActivationFired(BeforeActivationFiredEvent event) {
	}

	public void beforeRuleFlowGroupActivated(
			RuleFlowGroupActivatedEvent event) {
	}

	public void beforeRuleFlowGroupDeactivated(
			RuleFlowGroupDeactivatedEvent event) {
	}
}