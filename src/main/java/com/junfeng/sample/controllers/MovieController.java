package com.junfeng.sample.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    @GetMapping
    public List<Object> getMovies(){

        String uri = "https://api.sampleapis.com/movies/western";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);

        Object[] movies = restTemplate.getForObject(uri,Object[].class);

        if (movies == null) throw new AssertionError("Unable to get movie list");

        return Arrays.asList(movies);
    }
}

