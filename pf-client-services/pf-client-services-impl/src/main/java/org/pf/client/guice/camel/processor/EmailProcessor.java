/**
 * 
 */
package org.pf.client.guice.camel.processor;

import java.net.URI;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.EmailAddressKey;
import microsoft.exchange.webservices.data.core.enumeration.property.PhoneNumberKey;
import microsoft.exchange.webservices.data.core.enumeration.search.ResolveNameSearchLocation;
import microsoft.exchange.webservices.data.core.service.item.Contact;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.misc.NameResolution;
import microsoft.exchange.webservices.data.misc.NameResolutionCollection;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressDictionary;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.PhoneNumberDictionary;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public class EmailProcessor implements Processor {
	
	private String userEmail = null;
	private String userPass = null;
	
	/**
	 * 
	 */
	public EmailProcessor(String email, String pass) {
		this.userEmail= email;
		this.userPass = pass;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
//		exchange.getIn().setBody(body+" and a response from Route",String.class);
		System.out.println("inside the EMail Processor");
		
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials credentials = new WebCredentials(userEmail, userPass);
		service.setCredentials(credentials);
		service.setUrl(new URI("https://outlook.office365.com/EWS/Exchange.asmx"));
//		service.autodiscoverUrl(userEmail, new RedirectionUrlCallback());
		
		System.out.println("url = "+service.getUrl());
		
		NameResolutionCollection nameResolutions = service.resolveName(body,ResolveNameSearchLocation.ContactsOnly, true);
		System.out.println("nameResolutions==="+nameResolutions.getCount());
		JSONArray jsonArray = new JSONArray();
//		jsonArray.put(JSONObject)
		for (NameResolution nameResolution : nameResolutions) {
			ItemId itemId = nameResolution.getMailbox().getId();
			if(null!=itemId){
				Contact contact =  Contact.bind(service, itemId);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", nameResolution.getMailbox().getName());
				jsonObject.put("phoneNumber", displayContactPhoneNumber(contact.getPhoneNumbers()));
				jsonObject.put("email", displayContactEmail(contact.getEmailAddresses()));
				jsonObject.put("department", contact.getDepartment());
				jsonArray.put(jsonObject);
			}
		}
		service.close();
		exchange.getIn().setBody(jsonArray.toString());
	}

	/**
	 * @param emailAddresses
	 * @return
	 * @throws JSONException
	 */
	private JSONObject displayContactEmail(EmailAddressDictionary emailAddresses) throws JSONException {
		JSONObject buffer = new JSONObject();
		for(EmailAddressKey key : EmailAddressKey.values()){
			if(emailAddresses.contains(key)){
				EmailAddress emailAddress = emailAddresses.getEmailAddress(key);
				if(!StringUtils.isBlank(emailAddress.getAddress())){
					buffer.put(key.toString(),emailAddress.toString());
				}
			}
		}
		
		return buffer;
	}

	/**
	 * @param phoneNumbers
	 * @return
	 * @throws JSONException
	 */
	private JSONObject displayContactPhoneNumber(PhoneNumberDictionary phoneNumbers) throws JSONException {
		JSONObject buffer = new JSONObject();
		for(PhoneNumberKey key : PhoneNumberKey.values()){
			String phoneNumber = phoneNumbers.getPhoneNumber(key);
			if(!StringUtils.isBlank(phoneNumber)){
				buffer.put(key.toString(),phoneNumber.toString());
			}
		}
		
		return buffer;
	}

}
