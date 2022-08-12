package ru.vk.competition.minbenchmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.entity.SingleQuery;

import java.util.Optional;

@Repository
public interface SingleQueryRepository extends JpaRepository<SingleQuery, Integer> {

    Optional<SingleQuery> findByQueryId(Integer id);

    @Transactional
    void deleteByQueryId(Integer id);
}
