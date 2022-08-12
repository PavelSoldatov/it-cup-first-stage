package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void createTable(@RequestBody CreateTableDto createTableDto) {
        tableService.createTable(createTableDto);
    }

    @GetMapping("/get-table-by-name/{name}")
    public CreateTableDto getTableByName(@PathVariable("name") String name) {
        return tableService.findTableByName(name);
    }

    @DeleteMapping("/drop-table-by-name/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void dropTableByName(@PathVariable("name") String name) {
        tableService.deleteTableByName(name);
    }
}
