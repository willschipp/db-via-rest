package com.github.willschipp.client;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class PerformanceClient {

    public void run(int connections,String url) throws Exception {
        //create a rest client and use a task executor to spool up a series of threads and hit an endpoint
        
    }

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<String> hitEndpoint() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String result = restTemplate.postForObject(url,String.class);
                
            } catch (Exception e) {
                return "Error hitting endpoint";
            }
        })
    }

}
