/**
 * 
 */
package com.robobank.csp.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Karthikeyan N
 *
 */
@JsonPropertyOrder(value = { "transactionReference", "description" })
public class ValidationResponse {

	private Record record;
	
	public ValidationResponse(Record record) {
		this.record = record;
	}
	
	public Long getTransactionReference() {
		return this.record.getTransactionReference();
	}
	
	public String getDescription() {
		return this.record.getDescription();
	}
	
}
