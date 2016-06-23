/**
 * 
 */
package org.pf.client.guice.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.pf.client.guice.camel.processor.EmailProcessor;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public class ClientRoute extends RouteBuilder {

	public static final String DIRECT_FROM_ROUTE = "direct:fromRoute";

	private static final String DIRECT_CALL_TO_PF_SERVICE = "direct:callPFService";
		
	private EmailProcessor emailProcessor=null;
	
	@Inject
	@Named("connectionUrl")
	private String connectionUrl = "https://kubera.ust-global.com/hr/home/SitePages/peoplefinder.aspx";
	
	/**
	 * @param emailProcessor the emailProcessor to set
	 */
	@Inject
	public void setEmailProcessor(EmailProcessor emailProcessor) {
		this.emailProcessor = emailProcessor;
	}

	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {
		
		from(DIRECT_FROM_ROUTE).id(DIRECT_FROM_ROUTE)
		.process(emailProcessor).end();
		
		from(DIRECT_CALL_TO_PF_SERVICE).id(DIRECT_CALL_TO_PF_SERVICE)
		.log("");

	}

}
