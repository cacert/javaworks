package com.example;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CamelCoreApplicationTests extends CamelTestSupport {
    
    @Test
    public void testFirstSampleCore() throws Exception {
        Exchange exchange = template.request("direct://start", new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Map<String, String> body = new HashMap<>();
                body.put("Hello", "World");
                exchange.getIn().setBody(body);
            }
        });
        
        assertEquals(3, exchange.getOut().getBody(Map.class).size());
        assertEquals("aa", exchange.getOut().getBody(Map.class).get("a"));
        assertEquals("bb", exchange.getOut().getBody(Map.class).get("b"));
        assertEquals("World", exchange.getOut().getBody(Map.class).get("Hello"));
        
    }
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct://start")
                    .to("core://1");
            }
        };
    }

}
