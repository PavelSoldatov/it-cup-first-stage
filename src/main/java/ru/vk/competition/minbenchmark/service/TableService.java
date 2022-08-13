package ru.vk.competition.minbenchmark.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;
import ru.vk.competition.minbenchmark.util.QueryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Service
@Slf4j
@RequiredArgsConstructor
public class TableService {

    public static ConcurrentHashMap<String, CreateTableDto> tableStorage = new ConcurrentHashMap<>();

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @SneakyThrows
    public void createTable(CreateTableDto createTableDto) {
        if(createTableDto.getColumnInfos().isEmpty() && createTableDto.getPrimaryKey() != null) {
            throw new RuntimeException("no columns for create");
        }

        QueryUtils.validateCharacter(createTableDto.getTableName());
        StringBuilder sb = new StringBuilder();
        List<String> creationParams = new ArrayList<>();
        String createTableQuery = "CREATE TABLE " + createTableDto.getTableName() + " (";

        createTableDto.getColumnInfos().forEach(info -> {
            creationParams.add(info.getTitle() + " " + info.getType());
            info.setTitle(info.getTitle().toUpperCase());
            info.setType(info.getType().toUpperCase());
        });

        String url = jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();
        System.out.println(url);

        String resultParams = String.join(",", creationParams);
        sb.append(createTableQuery).append(resultParams).append(", PRIMARY KEY (").append(createTableDto.getPrimaryKey()).append("));");
        log.info("create table query is: {}", sb);
        jdbcTemplate.execute(sb.toString());

        tableStorage.put(createTableDto.getTableName(), createTableDto);
    }

    public CreateTableDto findTableByName(String tableName) {
        if(tableName.length() >= 50) {
            throw new RuntimeException("oversize table name");
        }
        return tableStorage.get(tableName);
    }

    public void deleteTableByName(String tableName) {
        if(tableName.length() >= 50) {
            throw new RuntimeException("oversize table name");
        }
        jdbcTemplate.execute("DROP TABLE " + tableName);
        tableStorage.remove(tableName);
    }
}
