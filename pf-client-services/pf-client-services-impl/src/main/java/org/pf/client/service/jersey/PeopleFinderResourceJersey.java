/**
 * 
 */
package org.pf.client.service.jersey;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.pf.client.core.dto.PeopleFinderUser;
import org.pf.client.services.api.services.jersey.PeopleFinderResource;

/**
 * @author U25276
 *
 */
public class PeopleFinderResourceJersey implements PeopleFinderResource {

	/* (non-Javadoc)
	 * @see org.pf.client.services.api.services.jersey.PeopleFinderResource#getPeopleFinderDetails(org.pf.client.core.dto.PeopleFinderUser)
	 */
	public Response postPeopleFinderDetails(PeopleFinderUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getTestDetails() {
		System.out.println("some test");
		ResponseBuilder response = Response.ok("sending a message");
		return response.build();
	}
}
