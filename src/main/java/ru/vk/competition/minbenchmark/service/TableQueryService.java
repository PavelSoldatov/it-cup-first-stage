package ru.vk.competition.minbenchmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.ModifyQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;
import ru.vk.competition.minbenchmark.entity.TableQuery;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableQueryService {

    private final TableQueryRepository tableQueryRepository;

    public void addNewTableQuery(AddNewQueryDto newQueryDto) {
        String tableName = newQueryDto.getTableName();
        boolean tableExist = TableService.tableStorage.containsKey(tableName);

        if (tableExist) {
            TableQuery tableQuery = new TableQuery().setQueryId(newQueryDto.getQueryId())
                    .setTableName(tableName)
                    .setQuery(newQueryDto.getQuery());

            tableQueryRepository.save(tableQuery);
        } else {
            throw new RuntimeException("Таблицы не существует");
        }
    }

    public void modifyTableQuery(ModifyQueryDto modifyQueryDto) {
        String tableName = modifyQueryDto.getTableName();
        boolean tableExist = TableService.tableStorage.containsKey(tableName);

        if (tableExist) {
            TableQuery existedQuery = tableQueryRepository.findByQueryId(modifyQueryDto.getQueryId());

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
}
