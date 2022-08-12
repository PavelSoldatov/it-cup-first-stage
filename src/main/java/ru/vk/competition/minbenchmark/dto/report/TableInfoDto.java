package ru.vk.competition.minbenchmark.dto.report;

import java.util.List;

public class TableInfoDto {
    private String tableName;
    private List<Columns> columns;
    private int priority;
}

class Columns{
    //"name": "id",
    private String nameAndType;
}
