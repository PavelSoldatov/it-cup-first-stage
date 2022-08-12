package ru.vk.competition.minbenchmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vk.competition.minbenchmark.entity.TableQuery;

import java.util.List;

@Repository
public interface TableQueryRepository extends JpaRepository<TableQuery, Integer> {

    TableQuery findByQueryId(Integer id);

    List<TableQuery> findAllByTableName(String tableName);

    List<TableQuery> findAll();
}
