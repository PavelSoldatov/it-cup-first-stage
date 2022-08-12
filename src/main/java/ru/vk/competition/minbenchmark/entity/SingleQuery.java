package ru.vk.competition.minbenchmark.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "single_query")
@NoArgsConstructor
@AllArgsConstructor
public class SingleQuery {

    @Id
    @Column(name = "queryId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer queryId;

    @Column(name = "query")
    private String query;
}