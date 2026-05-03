package com.jakunya.sqlmaster.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;

//@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {
    private final StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        String key = "rate_limit:" + ip;
        Long CurrentRequest = redisTemplate.opsForValue().increment(key);
        if(CurrentRequest == 1){
            redisTemplate.expire(key, Duration.ofSeconds(10));
        } else if(CurrentRequest > 100){
            response.setStatus(429);
            return;
        }
        filterChain.doFilter(request, response);

    }
}
