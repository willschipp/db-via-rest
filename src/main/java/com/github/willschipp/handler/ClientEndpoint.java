package com.github.willschipp.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.willschipp.client.PerformanceClient;

@Profile("client")
@RestController
@RequestMapping("/api")
public class ClientEndpoint {
    
    @Autowired
    PerformanceClient performanceClient;

    @Value("${client.url}")
    String url;

    @PostMapping("/client/{records}")
    public List<String> trigger(@PathVariable("records") int records) throws Exception {
        List<String> results = performanceClient.run(records, url);
        return results;
    }

}
