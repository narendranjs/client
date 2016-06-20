/**
 * 
 */
package org.pf.client.guice;

import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.pf.client.guice.module.ClientCamelModule;
import org.pf.client.guice.module.ClientServletModule;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author Narendran (narendran.js@gmail.com)
 *
 */
public final class EnvironmentContextListener extends GuiceServletContextListener {
	private static final String CONFIG = "client.config";

	/* (non-Javadoc)
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 */
	@Override
	protected Injector getInjector() {
		String configFile = "configuration.properties";
		if(System.getProperty(CONFIG) != null){
			configFile = System.getProperty(CONFIG);
		}
		try{
			CompositeConfiguration compositeConfiguration = new CompositeConfiguration();
			PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(configFile);
			compositeConfiguration.addConfiguration(new SystemConfiguration());
			compositeConfiguration.addConfiguration(propertiesConfiguration);
			List<Module> modules = Lists.newArrayList();
			modules.add(new ClientServletModule());
			modules.add(new ClientCamelModule(compositeConfiguration));
			return Guice.createInjector(modules);
		} catch(Exception ex){
			throw new RuntimeException("Some thing happened");
		}
	}

}
