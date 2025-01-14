package com.caiojacintho.test;

import com.caiojacintho.dominio.Producer;
import com.caiojacintho.service.ProducerService;

import java.util.List;

public class ConnectionFactoryTest03 {
    public static void main(String[] args) {
        Producer producer1 = Producer.builder().name("Humberto").build();
        Producer producer2 = Producer.builder().name("Enzo").build();
        Producer producer3 = Producer.builder().name("Monteiro").build();
        ProducerService.saveTransaction(List.of(producer1, producer2, producer3));
    }
}
