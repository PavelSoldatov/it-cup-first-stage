package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.report.ReportDto;
import ru.vk.competition.minbenchmark.service.ReportService;

@RequestMapping("/api/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get-report-by-id/{id}")
    public ReportDto getReportById(@PathVariable("id") int id){
        return reportService.getReportById(id);
    }

    @PostMapping("/create-report")
    public ResponseEntity<Void> createReport(@RequestBody ReportDto reportDto) {
        return reportService.createReport(reportDto);
    }
}
