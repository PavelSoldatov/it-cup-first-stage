package ru.vk.competition.minbenchmark.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableQueriesResponseDto {
    private Integer queryId;
    private String tableName;
    private String query;
}
