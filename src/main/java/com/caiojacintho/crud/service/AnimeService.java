package com.caiojacintho.crud.service;

import com.caiojacintho.crud.dominio.Anime;
import com.caiojacintho.crud.dominio.Anime;
import com.caiojacintho.crud.dominio.Producer;
import com.caiojacintho.crud.repository.AnimeRepository;

import java.util.Optional;
import java.util.Scanner;

public class AnimeService {
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
        }
    }



    private static void findbyName() {
        System.out.println("Type the name or empty to all");
        String name = SCANNER.nextLine();
        AnimeRepository.findbyName(name).forEach(p -> System.out.printf("[%d] - %s %d  %s%n", p.getId(), p.getName(),p.getEpisodes(),p.getProducer().getName()));
    }

    private static void delete() {
        System.out.println("Type the id of the anime you want to delete");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Are you sure? S/N");
        String choice = SCANNER.nextLine();
        if ("s".equalsIgnoreCase(choice)) {
            AnimeRepository.delete(id);
        }

    }

    private static void save() {
        System.out.println("Type the name of the anime");
        String name = SCANNER.nextLine();
        System.out.println("Type the number of episodes");
        Integer episodes = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Type the id of the producer");
        Integer producerId = Integer.parseInt(SCANNER.nextLine());
        Anime anime = Anime.builder()
                .name(name)
                .episodes(episodes)
                .producer(Producer.builder().id(producerId).build())
                .build();
        AnimeRepository.save(anime);
    }

    private static void update() {
        System.out.println("Type the id of the anime you want to update");
        Optional<Anime> animeOptional = AnimeRepository.findById(Integer.parseInt(SCANNER.nextLine()));
        if (animeOptional.isEmpty()) {
            System.out.println("Anime not found");
        }
        Anime animeFromDb = animeOptional.get();
        System.out.println("Anime found " + animeFromDb);
        System.out.println("Type the name of the anime you want to update");
        String name = SCANNER.nextLine();
        name = name.isEmpty() ? animeFromDb.getName() : name;

        System.out.println("Type the new number of episodes");
        int episodes = Integer.parseInt(SCANNER.nextLine());
        Anime animeToUpdate = Anime.builder().name(name).id(animeFromDb.getId()).episodes(episodes).producer(animeFromDb.getProducer()).build();
        AnimeRepository.update(animeToUpdate);


    }

}
