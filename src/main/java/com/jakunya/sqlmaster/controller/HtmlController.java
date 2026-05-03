package com.jakunya.sqlmaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping({"/profile/{id}", "/profile/me"})
    public String userProfile() {
        return "forward:/profile.html";
    }

    @GetMapping("/trainer")
    public String userTrainer(){
        return "forward:/trainer.html";
    }

    @GetMapping("/static")
    public String Index(){return  "forward:/index.html"; }

    @GetMapping("/lessons")
    public String lessons(){return  "forward:/lessons.html";}

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    @GetMapping("/sandbox")
    public String sandbox() {return "forward:/sandbox.html";}

    @GetMapping("/leaderboard")
    public String leaderboard() {return "forward:/leaderboard.html";}



}
