package ru.vk.competition.minbenchmark.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.report.ReportDto;

@RequestMapping("/api/report")
@RestController
public class ReportController {

    @GetMapping("/get-report-by-id/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable("id") int id){

        return null ;
    }

    @PostMapping("/create-report")
    public ResponseEntity<Void> createReport(@RequestBody ReportDto reportDto) {
        return null;
    }
}
