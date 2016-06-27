/**
 * 
 */
package org.pf.client.guice.camel.processor.callback;

import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;
import microsoft.exchange.webservices.data.autodiscover.exception.AutodiscoverLocalException;

/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl {

	/* (non-Javadoc)
	 * @see microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl#autodiscoverRedirectionUrlValidationCallback(java.lang.String)
	 */
	@Override
	public boolean autodiscoverRedirectionUrlValidationCallback(String redirectionUrl)
			throws AutodiscoverLocalException {
		// TODO Auto-generated method stub
		return redirectionUrl.toLowerCase().startsWith("https://");
	}

}
