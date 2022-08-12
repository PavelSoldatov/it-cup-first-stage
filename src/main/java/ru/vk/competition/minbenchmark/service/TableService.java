package ru.vk.competition.minbenchmark.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Service
@RequiredArgsConstructor
public class TableService {

    public static ConcurrentHashMap<String, CreateTableDto> tableStorage = new ConcurrentHashMap<>();

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @SneakyThrows
    public void createTable(CreateTableDto createTableDto) {
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
        System.out.println(sb);
        jdbcTemplate.execute(sb.toString());

        tableStorage.put(createTableDto.getTableName(), createTableDto);
    }

    public CreateTableDto findTableByName(String tableName) {
        return tableStorage.get(tableName);
    }

    public void deleteTableByName(String tableName) {
        jdbcTemplate.execute("DROP TABLE " + tableName);
        tableStorage.remove(tableName);
    }
}
