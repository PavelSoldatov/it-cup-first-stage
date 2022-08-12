package ru.vk.competition.minbenchmark.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.entity.SingleQuery;
import ru.vk.competition.minbenchmark.repository.SingleQueryRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SingleQueryService {

    private final SingleQueryRepository queryRepository;

    public List<SingleQuery> getAllQueries() {
        return (List<SingleQuery>) queryRepository.findAll();
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

    public ResponseEntity<Void> executeSingleQuery(Integer id) {
            Connection connection = null;
            Statement statement = null;
            Optional<String> createSql = null;
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "123"
                );
                log.debug("Database connected hahaha....");
                statement = connection.createStatement();
                createSql = queryRepository.findByQueryId(id).map(SingleQuery::getQuery);
                statement.execute(createSql.get());
                statement.close();
                connection.close();
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }
    }
}