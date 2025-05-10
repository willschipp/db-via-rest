package com.github.willschipp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class ApplicationIT {
    
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testStartup() throws Exception {
        assertTrue(applicationContext.getBeanDefinitionCount() > 0);
    }
}