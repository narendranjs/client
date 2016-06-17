/**
 * 
 */
package org.pf.client.services.api.services.jersey;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.pf.client.core.dto.PeopleFinderUser;

/**
 * @author U25276
 *
 */
@Path("/client")
public interface PeopleFinderResource {
	
	@POST
	@Produces({"application/json", "application/json"})
	@Path("queryClient")
	Response getPeopleFinderDetails(PeopleFinderUser user);

}
