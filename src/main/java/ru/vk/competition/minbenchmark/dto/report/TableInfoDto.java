package ru.vk.competition.minbenchmark.dto.report;

import lombok.Data;

import java.util.List;

@Data
public class TableInfoDto {
    private String tableName;
    private List<Columns> columns;
//    private int priority;
}
