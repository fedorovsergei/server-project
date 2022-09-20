package com.sf.weather.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/db")
@RequiredArgsConstructor
public class DbHealthCheckController {

    private final JdbcTemplate template;

    @GetMapping("/health")
    public ResponseEntity dbHealthCheck() {
        log.info("checking db health");
        try {
            int errorCode = check(); // perform some specific health check
            if (errorCode != 1) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error check db" + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public int check() {
        List<Object> results = template.query("select 1 from dual", new SingleColumnRowMapper<>());
        return results.size();
    }
}
