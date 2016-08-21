package com.smg.octopus.core.component;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.util.ObjectHelper;

@UriEndpoint(scheme = "core", title = "CORE", syntax = "core:resourceUri", producerOnly = true, label = "transformation")
public class CoreEndpoint extends ResourceEndpoint {

	private String uri, remaining;
	private Map<String, Object> parameters;

	public CoreEndpoint(String uri, String remaining, Map<String, Object> parameters) {
		this.uri = uri;
		this.remaining = remaining;
		this.parameters = parameters;
	}

	@Override
	protected void onExchange(Exchange exchange) throws Exception {
		ProducerTemplate template = getCamelContext().createProducerTemplate();
		exchange = template.request("http4://www.google.com", new Processor() {
			public void process(Exchange exchange) throws Exception {
				exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET");
				exchange.getIn().setBody("".getBytes("UTF-8"));
			}
		});

		// Message out = exchange.getOut();
		// if (getOutputType() == JoltInputOutputType.JsonString) {
		// out.setBody(JsonUtils.toJsonString(output));
		// } else {
		// out.setBody(output);
		// }
		// out.setHeaders(exchange.getIn().getHeaders());
		// out.setAttachments(exchange.getIn().getAttachments());
	}

	protected String createEndpointUri() {
		return "kasim";
	}

	public CoreEndpoint findOrCreateEndpoint(String uri, String newResourceUri) {
		String newUri = uri.replace(getResourceUri(), newResourceUri);
		log.debug("Getting endpoint with URI: {}", newUri);
		return getCamelContext().getEndpoint(newUri, CoreEndpoint.class);
	}

}
