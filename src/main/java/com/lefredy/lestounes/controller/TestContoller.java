package com.lefredy.lestounes.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v{version}",version = "1")
class TestContoller {

    @GetMapping
    public String findAll() {
        return "Hello world";
    }
    @GetMapping("admin/")
    public String isAdmin(){
        return "I am an admin!";
    }
}
