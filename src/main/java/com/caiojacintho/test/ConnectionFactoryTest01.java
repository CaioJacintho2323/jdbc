package com.caiojacintho.test;


import com.caiojacintho.conn.ConnectionFactory;
import com.caiojacintho.dominio.Producer;
import com.caiojacintho.repository.ProducerRepository;
import com.caiojacintho.service.ProducerService;
import lombok.extern.log4j.Log4j2;

import java.util.List;
@Log4j2
public class ConnectionFactoryTest01 {
    public static void main(String[] args) {
        Producer producer = Producer.builder().name("cA").build();
        Producer producerToUpdate = Producer.builder().id(2).name("Rivanda Mota").build();
//        ProducerService.save(producer);
//        ProducerService.delete();
//        ProducerService.update(producerToUpdate);
//        List<Producer> producers = ProducerService.findAll();
//        log.info("Producers found: {}", producers);
        List<Producer> caioJacintho = ProducerService.findByName("Caio");
        log.info(caioJacintho);


    }
}