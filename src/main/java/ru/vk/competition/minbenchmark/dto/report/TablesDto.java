package ru.vk.competition.minbenchmark.dto.report;

import lombok.Data;

import java.util.List;

@Data
public class TablesDto {
    private String tableName;
    private List<Columns> columns;
}
