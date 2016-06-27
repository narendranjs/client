/**
 * 
 */
package org.pf.client.guice.module;

import org.apache.camel.guice.CamelModuleWithMatchingRoutes;
import org.apache.commons.configuration.CompositeConfiguration;
import org.pf.client.guice.camel.processor.EmailProcessor;
import org.pf.client.guice.camel.route.ClientRoute;

import com.google.inject.Singleton;
/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public class ClientCamelModule extends CamelModuleWithMatchingRoutes{
	
	private CompositeConfiguration compositeConfiguration;

	public ClientCamelModule(CompositeConfiguration configuration) {
		this.compositeConfiguration = configuration;
	}

	@Override
	protected void configure() {
		super.configure();
		bind(ClientRoute.class).in(Singleton.class);
		bind(EmailProcessor.class).toInstance(new EmailProcessor(compositeConfiguration.getString("user.Email"), compositeConfiguration.getString("user.Pass")));
	}

}
