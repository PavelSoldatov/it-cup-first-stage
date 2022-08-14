package ru.vk.competition.minbenchmark.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.competition.minbenchmark.dto.report.ReportDto;
import ru.vk.competition.minbenchmark.log.ReportService;

@RequestMapping("/api/report")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get-report-by-id/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportDto getReportById(@PathVariable("id") int id){
        log.info("getReportById, id is: {}", id);
        return reportService.getReportById(id);
    }

    @PostMapping("/create-report")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createReport(@RequestBody ReportDto reportDto) {
        log.info("createReport dto is: {}", reportDto);
        return reportService.createReport(reportDto);
    }
}
