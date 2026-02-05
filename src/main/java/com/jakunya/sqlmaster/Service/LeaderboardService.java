package com.jakunya.sqlmaster.Service;

import com.jakunya.sqlmaster.dto.leaderboard.LeaderboardXpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderboardService {
    private static final String LEADERBOARD_KEY = "ranking";
    private final StringRedisTemplate redisTemplate;

    public void updateUserScore(String username, double score){
        redisTemplate.opsForZSet().add(LEADERBOARD_KEY, username, score);
    }

    public List<LeaderboardXpDto> getTopXpPlayers(int limit) {
        var rawData = redisTemplate.opsForZSet().reverseRangeWithScores(LEADERBOARD_KEY, 0, limit - 1);
        if (rawData == null) return List.of();
        return rawData.stream()
                .map(tuple -> {
                    LeaderboardXpDto dto = new  LeaderboardXpDto();
                    dto.setUsername(tuple.getValue());
                    dto.setXp(tuple.getScore());
                    return dto;
                })
                .collect(Collectors.toList());
    }





}
