package ru.vk.competition.minbenchmark.dto.report;

import lombok.Data;

import java.util.List;

@Data
public class ReportDto {
    private int reportId;
    private String tableAmount;
    private List<TablesDto> tables;
}
