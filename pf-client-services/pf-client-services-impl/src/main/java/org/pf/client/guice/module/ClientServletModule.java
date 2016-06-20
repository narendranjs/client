/**
 * 
 */
package org.pf.client.guice.module;

import java.util.Map;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.pf.client.service.jersey.PeopleFinderResourceJersey;
import org.pf.client.services.api.services.jersey.PeopleFinderResource;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public class ClientServletModule extends JerseyServletModule {
	
	@Override
	protected void configureServlets() {
		bind(PeopleFinderResource.class).to(PeopleFinderResourceJersey.class).in(Singleton.class);
		
		bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
		bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
		
		Map<String, String> params=Maps.newHashMap();
		params.put("com.sun.jersey.spi.container.ContainerRequestFilters"
				, "org.pf.client.guice.filter.RequestFilter");
		params.put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE.toString());
		serve("/rest/*").with(GuiceContainer.class, params);
	}
}
