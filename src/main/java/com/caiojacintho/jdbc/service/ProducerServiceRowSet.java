package com.caiojacintho.jdbc.service;

import com.caiojacintho.jdbc.dominio.Producer;
import com.caiojacintho.jdbc.repository.ProducerRepositoryRowSet;

import java.util.List;

public class ProducerServiceRowSet {

    public static List<Producer> findByNameJdbcRowSet(String name) {
        return ProducerRepositoryRowSet.findByNameJdbcRowSet(name);
    }

    public static void updateJdbcRowSet(Producer producer) {
        ProducerRepositoryRowSet.updateJdbcRowSet(producer);
    }

    public static void updateCachedRowSet (Producer producer) {
        ProducerRepositoryRowSet.updateCachedRowSet(producer);
    }
}
