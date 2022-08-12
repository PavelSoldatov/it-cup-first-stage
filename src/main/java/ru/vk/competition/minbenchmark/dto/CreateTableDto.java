package ru.vk.competition.minbenchmark.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateTableDto {
    private String tableName;
    private int columnsAmount;
    private String primaryKey;
    private List<ColumnInfos> columnInfos;
}

