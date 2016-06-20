/**
 * 
 */
package org.pf.client.guice.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public class ClientRoute extends RouteBuilder {

	public static final String DIRECT_FROM_ROUTE = "direct:fromRoute";
	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {
		from(DIRECT_FROM_ROUTE).id(DIRECT_FROM_ROUTE)
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String body = exchange.getIn().getBody(String.class);
				exchange.getIn().setBody(body+" and a response from Route",String.class);
			}
		}).end();

	}

}
