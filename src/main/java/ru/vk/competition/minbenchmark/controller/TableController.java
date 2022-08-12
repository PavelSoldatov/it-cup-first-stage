package ru.vk.competition.minbenchmark.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;

@RequestMapping("/api/table")
public class TableController {

    @PostMapping("/create-table")
    public Mono<Integer> createTable(@RequestBody CreateTableDto createTableDto) {

        return Mono.just(1);
    }


    @GetMapping("/get-table-by-name/{name}")
    public Mono<CreateTableDto> getTableByName(@PathVariable("name") String name) {

        return Mono.just(null);
    }

    @DeleteMapping("/drop-table-by-name/{name}")
    public Mono<Integer> dropTableByName(@PathVariable("name") String name) {
        return Mono.just(1);
    }
}
