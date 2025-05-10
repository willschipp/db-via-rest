package com.github.willschipp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.willschipp.service.DataInit;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class DataEndpoint {
    
    @Autowired
    DataInit dataInit;

    @PostMapping("/generate")
    public void generate(HttpServletResponse httpServletResponse) throws Exception {
        dataInit.generateAndPopulate();
        httpServletResponse.setStatus(HttpStatus.CREATED.value());
    }

    @DeleteMapping("/purge")
    public void purge(HttpServletResponse httpServletResponse) throws Exception {
        dataInit.purge();
        httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
