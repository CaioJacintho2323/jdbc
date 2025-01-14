package com.caiojacintho.crud.service;

import com.caiojacintho.crud.dominio.Producer;
import com.caiojacintho.crud.repository.ProducerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProducerService {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void buildMenu(int op) {
//        int i = switch (op){
//            case 1,2,3,4,5: yield 100;
//            default: yield 0;
//        };
        switch (op) {
            case 1 -> findbyName();
            case 2 -> delete();
            case 3 -> save();
            case 4 -> update();
            default -> throw new IllegalArgumentException("Invalid operation");
        }
    }



    private static void findbyName() {
        System.out.println("Type the name or empty to all");
        String name = SCANNER.nextLine();
        ProducerRepository.findbyName(name).forEach(p -> System.out.printf("[%d] - %s%n", p.getId(), p.getName()));
    }

    private static void delete() {
        System.out.println("Type the id of the producer you want to delete");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Are you sure? S/N");
        String choice = SCANNER.nextLine();
        if ("s".equalsIgnoreCase(choice)) {
            ProducerRepository.delete(id);
        }

    }

    private static void save() {
        System.out.println("Type the name of the producer");
        String name = SCANNER.nextLine();
        Producer build = Producer.builder().name(name).build();
        ProducerRepository.save(build);
    }

    private static void update() {
        System.out.println("Type the id of the producer you want to update");
        Optional<Producer> producerOptional = ProducerRepository.findById(Integer.parseInt(SCANNER.nextLine()));
        if (producerOptional.isEmpty()) {
            System.out.println("Producer not found");
        }
        Producer producer = producerOptional.get();
        System.out.println("Producer found " + producer);
        System.out.println("Type the name of the producer you want to update");
        String name = SCANNER.nextLine();
        name = name.isEmpty() ? producer.getName() : name;
        Producer producerToUpdate = Producer.builder().name(name).id(producer.getId()).build();
        ProducerRepository.update(producerToUpdate);


    }

}
