package com.caiojacintho.test;

import com.caiojacintho.dominio.Producer;
import com.caiojacintho.service.ProducerServiceRowSet;
import lombok.extern.log4j.Log4j2;

import java.util.List;
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
