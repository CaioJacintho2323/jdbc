package com.caiojacintho.test;


import com.caiojacintho.conn.ConnectionFactory;
import com.caiojacintho.dominio.Producer;
import com.caiojacintho.repository.ProducerRepository;

public class ConnectionFactoryTest01 {
    public static void main(String[] args) {
        Producer producer = Producer.ProducerBuilder.builder().name("KPT").build();
        ProducerRepository.save(producer);
    }
}