package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;
import ru.vk.competition.minbenchmark.service.TableService;

@RequestMapping("/api/table")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TableController {

    private final TableService tableService;

    @PostMapping("/create-table")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTable(@RequestBody CreateTableDto createTableDto) {
        log.info("createTable " + createTableDto.toString());
        tableService.createTable(createTableDto);
    }

    @GetMapping("/get-table-by-name/{name}")
    public CreateTableDto getTableByName(@PathVariable("name") String name) {
        log.info("getTableByName " + name);
        return tableService.findTableByName(name);
    }

    @DeleteMapping("/drop-table/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public void dropTableByName(@PathVariable("name") String name) {
        log.info("dropTableByName " + name);
        tableService.deleteTableByName(name);
    }
}
