package com.tacoshop.filters;

import java.util.Arrays;
import java.util.List;

import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;

/**
 * custom filter implementation which we can use to
 * only allow rules that we specify by name to fire.
 * 
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class CustomAgendaFilter implements AgendaFilter {

	/** list of names of rules we'd like to allow */
	private List<String> rulesToAllow;
	
	/**
	 * constructor that will set our list of rules to allow
	 * 
	 * @param ruleNames names of rules to allow
	 */
	public CustomAgendaFilter(String[] ruleNames) {
		this.rulesToAllow = Arrays.asList(ruleNames);
	}
	
	/**
	 * check an activation for a rule against our list and see if we'd like to allow it
	 * 
	 * @param activation proposed 
	 * @returns boolean activation allowed
	 */
	public boolean accept(Activation activation) {
		if (this.rulesToAllow.contains(activation.getRule().getName())) {
			return true;
		} else {
			return false;
		}
	}
}
