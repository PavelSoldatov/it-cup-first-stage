package ru.vk.competition.minbenchmark.exception;

public class QueryNotFoundException extends RuntimeException{
    public QueryNotFoundException(String message) {
        super(message);
    }
}
