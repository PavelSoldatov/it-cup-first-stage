package ru.vk.competition.minbenchmark.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.entity.SingleQuery;
import ru.vk.competition.minbenchmark.repository.SingleQueryRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SingleQueryService {

    private final SingleQueryRepository queryRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<SingleQuery> getAllQueries() {
        return queryRepository.findAll();
    }

    public SingleQuery getQueryById(Integer id) {
        return queryRepository.findByQueryId(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find tableQuery by Id %s", id.toString())
        ));
    }

    public ResponseEntity<Void> deleteQueryById(Integer id) {
        try {
            if (queryRepository.findByQueryId(id).map(SingleQuery::getQueryId).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            } else {
                queryRepository.deleteByQueryId(id);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<Void> addQueryWithQueryId(SingleQuery singleQuery) {
        queryRepository.save(singleQuery);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> updateQueryWithQueryId(SingleQuery singleQuery) {
        queryRepository.findByQueryId(singleQuery.getQueryId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find tableQuery by ID %s", singleQuery.getQueryId())
                ));
        queryRepository.save(singleQuery);
        return ResponseEntity.<Void>ok(null);
    }

    public void executeSingleQuery(Integer id) {
        var byQueryId = queryRepository.findByQueryId(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find tableQuery by ID %s", id)
                ));
        jdbcTemplate.execute(
                byQueryId.getQuery());
    }
}