package ru.vk.competition.minbenchmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.ModifyQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;
import ru.vk.competition.minbenchmark.entity.TableQuery;
import ru.vk.competition.minbenchmark.exception.QueryNotFoundException;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;
import ru.vk.competition.minbenchmark.util.QueryUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableQueryService {

    private final TableQueryRepository tableQueryRepository;
    private final JdbcTemplate jdbcTemplate;

    public void addNewTableQuery(AddNewQueryDto newQueryDto) {
//        QueryUtils.validateSizeTableName(newQueryDto.getTableName());
//        QueryUtils.validateSizeQuery(newQueryDto.getQuery());
//        QueryUtils.validateCharacter(newQueryDto.getQuery());

        String tableName = newQueryDto.getTableName();
        boolean tableExist = TableService.tableStorage.containsKey(tableName);

        if (tableExist) {
            TableQuery tableQuery = new TableQuery()
                    .setQueryId(newQueryDto.getQueryId())
                    .setTableName(tableName)
                    .setQuery(newQueryDto.getQuery());

            tableQueryRepository.save(tableQuery);
        } else {
            throw new RuntimeException("Таблицы не существует");
        }
    }

    public void modifyTableQuery(ModifyQueryDto modifyQueryDto) {
//        QueryUtils.validateSizeTableName(modifyQueryDto.getTableName());
//        QueryUtils.validateSizeQuery(modifyQueryDto.getQuery());
//        QueryUtils.validateCharacter(modifyQueryDto.getQuery());

        String tableName = modifyQueryDto.getTableName();
        boolean tableExist = TableService.tableStorage.containsKey(tableName);

        if (tableExist) {
            TableQuery existedQuery = tableQueryRepository.findByQueryId(modifyQueryDto.getQueryId());

            if(existedQuery == null) {
                throw new RuntimeException("запроса с таким id не существует");
            }

            existedQuery.setQuery(modifyQueryDto.getQuery());
            existedQuery.setTableName(modifyQueryDto.getTableName());
        } else {
            throw new RuntimeException("Таблицы не существует");
        }
    }

    public void deleteTableQueryById(int queryId) {
        tableQueryRepository.deleteById(queryId);
    }

    public List<TableQueriesResponseDto> getAllQueriesByTableName(String name) {
        return tableQueryRepository.findAllByTableName(name)
                .stream().map(
                        e -> new TableQueriesResponseDto(
                                e.getQueryId(),
                                e.getTableName(),
                                e.getQuery()
                        )
                ).collect(Collectors.toList());
    }

    public TableQueriesResponseDto getTableQueryById(int queryId) {
        TableQuery byQueryId = tableQueryRepository.findByQueryId(queryId);
        if(byQueryId == null) {
           throw new QueryNotFoundException("query not found");
        }
        return new TableQueriesResponseDto(byQueryId.getQueryId(), byQueryId.getTableName(), byQueryId.getQuery());
    }

    public List<TableQueriesResponseDto> getAllTableQueries() {
        return tableQueryRepository.findAll()
                .stream().map(
                        e -> new TableQueriesResponseDto(
                                e.getQueryId(),
                                e.getTableName(),
                                e.getQuery()
                        )
                ).collect(Collectors.toList());
    }

    public void executeTableQueryById(int id) {
        TableQuery byQueryId = tableQueryRepository.findByQueryId(id);
        if(byQueryId != null){
            jdbcTemplate.execute(
                    byQueryId.getQuery()
            );

            if(QueryUtils.containsAlterTableRename(byQueryId.getQuery())){
                String newTableName = QueryUtils.getNewQueryName(byQueryId.getQuery());
                var newQueries = tableQueryRepository.findAllByTableName(byQueryId.getTableName())
                        .stream().map(e -> e.setTableName(newTableName)).toList();
                tableQueryRepository.saveAll(newQueries);
            }
        } else {
            throw new RuntimeException("Таблицы не существует");
        }
    }
}
