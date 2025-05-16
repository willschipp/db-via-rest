package com.github.willschipp.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.willschipp.model.Person;

@Profile("client")
@Service
public class PerformanceClient {

    private static final Log logger = LogFactory.getLog(PerformanceClient.class);

    public List<String> run(int connections,String url) throws Exception {
        //create a rest client and use a task executor to spool up a series of threads and hit an endpoint
        List<CompletableFuture<String>> results = IntStream.range(0,connections)
            .mapToObj(i -> hitEndpoint(url))
            .collect(Collectors.toList());

        List<String> timestamps = new ArrayList<>();

        for (CompletableFuture<String> result : results) {
            timestamps.add(result.get());
        }

        return timestamps;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TaskExecutor taskExecutor;


    public CompletableFuture<String> hitEndpoint(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                //build the object
                Person person = new Person();
                person.setFirstName("john");
                person.setLastName(Long.toString(System.currentTimeMillis()));
                //serializable

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<Person> requestEntity = new HttpEntity<Person>(person,headers);
                
                long startTime = System.currentTimeMillis();

                restTemplate.postForObject(url,requestEntity,String.class);

                long endTime = System.currentTimeMillis();

                return endTime - startTime + "ms";
                
            } catch (Exception e) {
                logger.error(e);
                return "Error hitting endpoint";
            }
        }, taskExecutor);
    }

}
