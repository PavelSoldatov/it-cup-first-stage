package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.entity.SingleQuery;
import ru.vk.competition.minbenchmark.service.SingleQueryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/single-query")
@RequiredArgsConstructor
public class SingleQueryController {

    private final SingleQueryService singleQueryService;

    @GetMapping("/get-all-single-queries")
    public List<SingleQuery> getAllTableQueries() {
        return singleQueryService.getAllQueries();
    }

    @GetMapping("/get-single-query-by-id/{id}")
    public SingleQuery getTableQueryById(@PathVariable Integer id) {
        return singleQueryService.getQueryById(id);
    }

    @DeleteMapping("/delete-single-query-by-id/{id}")
    public ResponseEntity<Void> deleteTableQueryById(@PathVariable Integer id) {
        return singleQueryService.deleteQueryById(id);
    }

    @PostMapping("/add-new-query")
    public ResponseEntity<Void> addNewQueryToTable(@RequestBody SingleQuery singleQuery) {
        return singleQueryService.addQueryWithQueryId(singleQuery);
    }

    @PutMapping("/modify-single-query")
    public ResponseEntity<Void> modifyQueryInTable(@RequestBody SingleQuery singleQuery) {
        return singleQueryService.updateQueryWithQueryId(singleQuery);
    }

    @GetMapping("/execute-single-query-by-id/{id}")
    public void executeSingleQueryById(@PathVariable Integer id) {
        singleQueryService.executeSingleQuery(id);
    }
}