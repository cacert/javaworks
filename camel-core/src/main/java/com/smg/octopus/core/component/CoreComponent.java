package com.smg.octopus.core.component;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;

public class CoreComponent extends UriEndpointComponent {

	public CoreComponent() {
		super(CoreEndpoint.class);
	}

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		return new CoreEndpoint(uri, remaining, parameters);
	}

}
