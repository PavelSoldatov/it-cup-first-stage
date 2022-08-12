package ru.vk.competition.minbenchmark.dto.query;

import lombok.Data;

@Data
public class ModifyQueryDto {
    private Integer queryId;
    private String tableName;
    private String query;
}
