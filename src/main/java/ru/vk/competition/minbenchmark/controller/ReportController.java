package ru.vk.competition.minbenchmark.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.vk.competition.minbenchmark.dto.report.ReportDto;

@RequestMapping("/api/report")
@RestController
public class ReportController {

    @GetMapping("/get-report-by-id/{id}")
    public Mono<ReportDto> getReportById(@PathVariable("id") int id){

        return Mono.empty();
    }

    @PostMapping("/create-report")
    public Mono<Void> createReport(@RequestBody ReportDto reportDto) {
        return Mono.empty();
    }
}
