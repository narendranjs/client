/**
 * 
 */
package org.pf.client.service.jersey;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.pf.client.core.dto.PeopleFinderUser;
import org.pf.client.guice.camel.route.ClientRoute;
import org.pf.client.services.api.services.jersey.PeopleFinderResource;

/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public class PeopleFinderResourceJersey implements PeopleFinderResource {
	
	@Produce
	private ProducerTemplate producer;

	/**
	 * @param producer the producer to set
	 */
	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}

	/* (non-Javadoc)
	 * @see org.pf.client.services.api.services.jersey.PeopleFinderResource#getPeopleFinderDetails(org.pf.client.core.dto.PeopleFinderUser)
	 */
	public Response postPeopleFinderDetails(PeopleFinderUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getTestDetails() {
		String responseText = producer.requestBody(ClientRoute.DIRECT_FROM_ROUTE, "Calling From Service", String.class);
		ResponseBuilder response = Response.ok(responseText);
		return response.build();
	}
}
