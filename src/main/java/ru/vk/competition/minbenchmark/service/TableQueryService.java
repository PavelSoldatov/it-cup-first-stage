package ru.vk.competition.minbenchmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.ModifyQueryDto;
import ru.vk.competition.minbenchmark.entity.TableQuery;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;

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
}
