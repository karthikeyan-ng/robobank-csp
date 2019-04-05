package com.robobank.csp.process;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.robobank.csp.data.Record;
import com.robobank.csp.utils.ValidationUtils;

/**
 * 
 * @author Karthikeyan N
 *
 */
public class MyCustomHandler extends DefaultHandler {

	private List<Record> records = new ArrayList<>();
	private Record record;

	private boolean accountNumber = false;
	private boolean description = false;
	private boolean startBalance = false;
	private boolean mutation = false;
	private boolean endBalance = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if ("record".equals(qName)) {

			record = new Record();

			String referenceValue = attributes.getValue("reference");
			record.setTransactionReference(ValidationUtils.validateAndGetTransactionReference(referenceValue));
		}

		switch (qName) {

		case "accountNumber":
			accountNumber = true;
			break;

		case "description":
			description = true;
			break;

		case "startBalance":
			startBalance = true;
			break;

		case "mutation":
			mutation = true;
			break;

		case "endBalance":
			endBalance = true;
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		if (accountNumber) {
			record.setAccountNumber(ValidationUtils.validateAndGetAccountNumber(new String(ch, start, length)));
			accountNumber = false;
		}

		if (description) {
			record.setDescription(new String(ch, start, length));
			description = false;
		}

		if (startBalance) {
			record.setStartBalance(Double.valueOf(new String(ch, start, length)));
			startBalance = false;
		}

		if (mutation) {
			record.setMutationSymbol(ValidationUtils.getMutationSymbol(new String(ch, start, length)));
			record.setMutationValue(Double.valueOf(new String(ch, start, length).substring(1)));
			mutation = false;
		}

		if (endBalance) {
			record.setEndBalance(Double.valueOf(new String(ch, start, length)));
			endBalance = false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if ("record".equals(qName)) {
			records.add(record);
		}
	}

	public List<Record> getUsers() {
		return records;
	}
}

