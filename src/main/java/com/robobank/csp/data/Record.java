/**
 * 
 */
package com.robobank.csp.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Karthikeyan N
 *
 */
@ToString
@Getter @Setter
@NoArgsConstructor
public class Record {
	
	private Long transactionReference;
	
	private String accountNumber;
	
	private Double startBalance;
	
	private String mutationSymbol;
	
	private Double mutationValue;
	
	private String description;
	
	private Double endBalance;

}
