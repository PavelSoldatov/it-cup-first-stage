package ru.vk.competition.minbenchmark.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "table_query")
@NoArgsConstructor
@AllArgsConstructor
public class TableQuery {

    @Id
    @Column(name = "queryId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer queryId;

    @Column(name = "query")
    private String query;

    @Column(name = "tableName")
    private String tableName;
}
