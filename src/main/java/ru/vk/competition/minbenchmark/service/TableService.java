package ru.vk.competition.minbenchmark.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@Service
@RequiredArgsConstructor
public class TableService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void createTable() {
        String sql = "CREATE TABLE zalupa (id integer);";
        jdbcTemplate.execute(sql);
        int a = 5;
    }
}
