package ru.vk.competition.minbenchmark.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class TableService {

    private final JdbcTemplate jdbcTemplate;

    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE TBL_EMPLOYEES (\n" +
                "  id INT AUTO_INCREMENT  PRIMARY KEY,\n" +
                "  first_name VARCHAR(250) NOT NULL,\n" +
                "  last_name VARCHAR(250) NOT NULL,\n" +
                "  email VARCHAR(250) DEFAULT NULL\n" +
                ");");
    }
}
