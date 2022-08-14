package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.ModifyQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;
import ru.vk.competition.minbenchmark.service.TableQueryService;

import java.util.List;

@RequestMapping("/api/table-query")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TableQueryController {

    private final TableQueryService tableQueryService;

    @PostMapping("/add-new-query-to-table")
    @ResponseStatus(HttpStatus.CREATED)
    public void tableQuery(@RequestBody AddNewQueryDto addNewQueryDto) {
        log.info("tableQuery {}", addNewQueryDto);
        tableQueryService.addNewTableQuery(addNewQueryDto);
    }

    @PutMapping("/modify-query-in-table")
    @ResponseStatus(HttpStatus.CREATED)
    public void modifyQueryInTable(@RequestBody ModifyQueryDto modifyQueryDto) {
        log.info("modifyQueryInTable {}", modifyQueryDto);
        tableQueryService.modifyTableQuery(modifyQueryDto);
    }

    @DeleteMapping("/delete-table-query-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTableQueryById(@PathVariable("id") int queryId) {
        log.info("deleteTableQueryById {}", queryId);
        tableQueryService.deleteTableQueryById(queryId);
    }

    @GetMapping("/execute-table-query-by-id/{id}")
    public void executeTableQueryById(@PathVariable("id") int queryId) {
        log.info("executeTableQueryById {}", queryId);
        tableQueryService.executeTableQueryById(queryId);
    }

    @GetMapping("/get-all-queries-by-table-name/{name}")
    public List<TableQueriesResponseDto> getAllQueriesByTableName(@PathVariable("name") String name) {
        log.info("getAllQueriesByTableName {}", name);
        return tableQueryService.getAllQueriesByTableName(name);
    }

    @GetMapping("/get-table-query-by-id/{id}")
    public TableQueriesResponseDto getTableQueryById(@PathVariable("id") int queryId) {
        log.info("getTableQueryById {}", queryId);
        return tableQueryService.getTableQueryById(queryId);
    }

    @GetMapping("/get-all-table-queries")
    public List<TableQueriesResponseDto> getAllTableQueries() {
        log.info("getAllTableQueries");
        return tableQueryService.getAllTableQueries();
    }
}
