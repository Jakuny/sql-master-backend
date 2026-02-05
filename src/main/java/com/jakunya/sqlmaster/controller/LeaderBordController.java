package com.jakunya.sqlmaster.controller;

import com.jakunya.sqlmaster.Service.LeaderboardService;
import com.jakunya.sqlmaster.dto.leaderboard.LeaderboardXpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/leaderboard")
public class LeaderBordController {
    private final LeaderboardService service;

    @GetMapping("/xp")
    public List<LeaderboardXpDto> getTopXpPlayers(){
        return service.getTopXpPlayers(100);
    }

}
