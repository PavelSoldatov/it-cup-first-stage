package ru.vk.competition.minbenchmark.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;

@RequestMapping("/api/table")
@RestController
public class TableController {

    @PostMapping("/create-table")
    public Integer createTable(@RequestBody CreateTableDto createTableDto) {

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
