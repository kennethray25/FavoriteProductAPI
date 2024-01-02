package com.safestreets.controller;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/")
public class HomeController {
    @Get // <2>
    @Produces(MediaType.TEXT_PLAIN) // <3>
    String index() {
        return "Hello"; // <4>
    }
}

