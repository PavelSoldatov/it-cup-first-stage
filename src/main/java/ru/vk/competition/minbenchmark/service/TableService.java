package ru.vk.competition.minbenchmark.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.ColumnInfos;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;
import ru.vk.competition.minbenchmark.repository.SingleQueryRepository;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;
import ru.vk.competition.minbenchmark.util.QueryUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Data
@Service
@Slf4j
@RequiredArgsConstructor
public class TableService {

    public static ConcurrentHashMap<String, CreateTableDto> tableStorage = new ConcurrentHashMap<>();

    private final JdbcTemplate jdbcTemplate;
    private final TableQueryRepository tableQueryRepository;

    @Transactional
    @SneakyThrows
    public void createTable(CreateTableDto createTableDto) {
        log.info("createTable {}", createTableDto);
        if (createTableDto.getColumnInfos().isEmpty() && createTableDto.getPrimaryKey() != null) {
            throw new RuntimeException("no columns for create");
        }

//        QueryUtils.validateCharacter(createTableDto.getTableName());
        StringBuilder sb = new StringBuilder();
        List<String> creationParams = new ArrayList<>();
        String createTableQuery = "CREATE TABLE " + createTableDto.getTableName() + " (";

        createTableDto.getColumnInfos().forEach(info -> {
            creationParams.add(info.getTitle() + " " + info.getType());
            info.setTitle(info.getTitle().toUpperCase());
            info.setType(info.getType().toUpperCase());
        });
        String resultParams = String.join(",", creationParams);
        sb.append(createTableQuery).append(resultParams).append(", PRIMARY KEY (").append(createTableDto.getPrimaryKey()).append("));");
        log.info("create table query is: {}", sb);
        jdbcTemplate.execute(sb.toString());

        tableStorage.put(createTableDto.getTableName(), createTableDto);
    }

    public CreateTableDto findTableByName(String tableName) {
        log.info("findTableByName {}", tableName);
        if (tableName.length() >= 50) {
            throw new RuntimeException("oversize table name");
        }

        var result = jdbcTemplate.queryForList(
                "SHOW COLUMNS FROM " + tableName);

        var columns = result.stream().map(e -> new ColumnInfos(
                ).setTitle((String) e.get("FIELD")).setType((String) e.get("TYPE"))
        ).toList();

        var primaryKey = result.stream().filter(
                e -> e.get("KEY").equals("PRI")
        ).map(e -> ((String) e.get("FIELD")).toLowerCase()).collect(Collectors.joining());

        return new CreateTableDto()
                .setTableName(tableName)
                .setColumnInfos(columns)
                .setColumnsAmount(columns.size())
                .setPrimaryKey(primaryKey);
    }

    public void deleteTableByName(String tableName) {
        log.info("deleteTableByName {}", tableName);
        if (tableName.length() >= 50) {
            throw new RuntimeException("oversize table name");
        }
        jdbcTemplate.execute("DROP TABLE " + tableName);
        tableStorage.remove(tableName);
        tableQueryRepository.deleteAllByTableName(tableName);
    }
}
