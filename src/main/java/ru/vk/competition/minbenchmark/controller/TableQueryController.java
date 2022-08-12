package ru.vk.competition.minbenchmark.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.ModifyQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;

@RequestMapping("/api/table-query")
@RestController
public class TableQueryController {

    @PostMapping("/add-new-query-to-table")
    public ResponseEntity<Void> tableQuery(@RequestBody AddNewQueryDto addNewQueryDto){

        return null;
    }

    @PutMapping("/modify-query-in-table")
    public ResponseEntity<Void> modifyQueryInTable(@RequestBody ModifyQueryDto modifyQueryDto) {

        return null;
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
