package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;
import ru.vk.competition.minbenchmark.service.TableService;

@RequestMapping("/api/table")
@RestController
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping("/create-table")
    public Integer createTable(@RequestBody CreateTableDto createTableDto) {
        tableService.createTable();
        return 1;
    }

    @GetMapping("/get-table-by-name/{name}")
    public ResponseEntity<CreateTableDto> getTableByName(@PathVariable("name") String name) {

        return null;
    }

    @DeleteMapping("/drop-table-by-name/{name}")
    public Integer dropTableByName(@PathVariable("name") String name) {
        return 1;
    }
}
