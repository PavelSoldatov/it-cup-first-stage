package ru.vk.competition.minbenchmark.dto.query;

import lombok.Data;

@Data
public class AddNewQueryDto {
    private Integer queryId;
    private String tableName;
    private String query;
}
