package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.ModifyQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;
import ru.vk.competition.minbenchmark.service.TableQueryService;

@RequestMapping("/api/table-query")
@RestController
@RequiredArgsConstructor
public class TableQueryController {

    private final TableQueryService tableQueryService;

    @PostMapping("/add-new-query-to-table")
    @ResponseStatus(HttpStatus.CREATED)
    public void tableQuery(@RequestBody AddNewQueryDto addNewQueryDto) {
        tableQueryService.addNewTableQuery(addNewQueryDto);
    }

    @PutMapping("/modify-query-in-table")
    public void modifyQueryInTable(@RequestBody ModifyQueryDto modifyQueryDto) {
        tableQueryService.modifyTableQuery(modifyQueryDto);
    }

    @DeleteMapping("/delete-table-query-by-id/{id}")
    public ResponseEntity<Void> deleteTableQueryById(@PathVariable("id") int queryId) {

        return null;
    }

    @GetMapping("/execute-table-query-by-id/{id}")
    public ResponseEntity<Void> executeTableQueryById(@PathVariable("id") int queryId) {

        return null;
    }

    @GetMapping("/get-all-queries-by-table-name/{name}")
    public ResponseEntity<TableQueriesResponseDto> getAllQueriesByTableName(@PathVariable("name") String name) {

        return null;
    }

    @GetMapping("/get-table-query-by-id/{id}")
    public ResponseEntity<TableQueriesResponseDto> getTableQueryById(@PathVariable("id") int queryId) {

        return null;
    }

    @GetMapping("/get-all-table-queries")
    public ResponseEntity<TableQueriesResponseDto> getAllTableQueries() {

        return null;
    }
}
