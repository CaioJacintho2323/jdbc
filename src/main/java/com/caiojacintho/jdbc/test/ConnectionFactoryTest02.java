package com.caiojacintho.jdbc.test;

import com.caiojacintho.jdbc.dominio.Producer;
import com.caiojacintho.jdbc.service.ProducerServiceRowSet;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConnectionFactoryTest02 {
    public static void main(String[] args) {
        Producer producerToUpdate = Producer.builder().id(1).name("Joana Karoline Ribeiro").build();
        ProducerServiceRowSet.updateCachedRowSet(producerToUpdate);
//        log.info("---------------");
//        List<Producer> findByname = ProducerServiceRowSet.findByNameJdbcRowSet("");
//        log.info("Producers found '{}'", findByname);

    }
}
