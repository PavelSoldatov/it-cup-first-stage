package ru.vk.competition.minbenchmark.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.ModifyQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;

@RequestMapping("/api/table-query")
public class TableQueryController {

    @PostMapping("/add-new-query-to-table")
    public Mono<Void> tableQuery(@RequestBody AddNewQueryDto addNewQueryDto){

        return Mono.empty();
    }

    @PutMapping("/modify-query-in-table")
    public Mono<Void> modifyQueryInTable(@RequestBody ModifyQueryDto modifyQueryDto) {

        return Mono.empty();
    }

    @DeleteMapping("/delete-table-query-by-id/{id}")
    public Mono<Void> deleteTableQueryById(@PathVariable("id") int queryId) {

        return Mono.empty();
    }

    @GetMapping("/execute-table-query-by-id/{id}")
    public Mono<Void> executeTableQueryById(@PathVariable("id") int queryId) {

        return Mono.empty();
    }

    @GetMapping("/get-all-queries-by-table-name/{name}")
    public Flux<TableQueriesResponseDto> getAllQueriesByTableName(@PathVariable("name") String name) {

        return Flux.just(new TableQueriesResponseDto(1, "", ""));
    }

    @GetMapping("/get-table-query-by-id/{id}")
    public Mono<TableQueriesResponseDto> getTableQueryById(@PathVariable("id") int queryId) {

        return Mono.just(new TableQueriesResponseDto(1, "", ""));
    }

    @GetMapping("/get-all-table-queries")
    public Flux<TableQueriesResponseDto> getAllTableQueries() {

        return Flux.just(new TableQueriesResponseDto(1, "", ""));
    }
}
