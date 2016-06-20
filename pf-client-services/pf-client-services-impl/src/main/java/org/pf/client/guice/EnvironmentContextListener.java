/**
 * 
 */
package org.pf.client.guice;

import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.pf.client.guice.module.ClientServletModule;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author U25276
 *
 */
public final class EnvironmentContextListener extends GuiceServletContextListener {

	/* (non-Javadoc)
	 * @see com.google.inject.servlet.GuiceServletContextListener#getInjector()
	 */
	@Override
	protected Injector getInjector() {
		try{
			CompositeConfiguration compositeConfiguration = new CompositeConfiguration();
			compositeConfiguration.addConfiguration(new SystemConfiguration());
			List<Module> modules = Lists.newArrayList();
			modules.add(new ClientServletModule());
			return Guice.createInjector(modules);
		} catch(Exception ex){
			throw new RuntimeException("Some thing happened");
		}
	}

}
