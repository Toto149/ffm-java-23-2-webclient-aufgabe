package com.example.ffmjava232webclient;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rick-and-morty")
public class RickAndMortyController {


    @GetMapping("/characters")
    public List<RickAndMortyCharacter> getCharacter(){
        RickAndMortyCharacters characters = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character")
                        .retrieve()
                        .toEntity(RickAndMortyCharacters.class)
                        .block()
        ).getBody();

        List<RickAndMortyCharacter> listOfCharacters = characters.results();

        return listOfCharacters;
    }
    @GetMapping("/characters?")
    public List<RickAndMortyCharacter> getCharacter(@RequestParam String status){
        RickAndMortyCharacters characters = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character")
                        .retrieve()
                        .toEntity(RickAndMortyCharacters.class)
                        .block()
        ).getBody();

        List<RickAndMortyCharacter> listOfCharacters = characters.results()
                .stream()
                .filter(rickAndMortyCharacter -> rickAndMortyCharacter.status().equals(status) )
                .toList();

        return listOfCharacters;
    }
    @GetMapping("/characters/{id}")
    public RickAndMortyCharacter getCharacterById(@PathVariable int id){
        RickAndMortyCharacter character = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character/" + id)
                        .retrieve()
                        .toEntity(RickAndMortyCharacter.class)
                        .block()
        ).getBody();
        return character;
    }
    @GetMapping("/characters/filter/status")
    public List<RickAndMortyCharacter> listAllWithStatusAlive(@RequestParam String status){
        List<RickAndMortyCharacter> listOfAliveCharacters =
                getCharacter().stream()
                .filter(r -> r.status().equals(status))
                .collect(Collectors.toList());

        return listOfAliveCharacters;

    }
    @GetMapping("/characters/species-statistic")
    public int getNumberOfCharactersOfCertainSpecies(@RequestParam String species){
        long amountOfSpeciesCharacters =
                getCharacter().stream()
                        .filter(r-> r.species().equals(species))
                        .count();
        return (int) amountOfSpeciesCharacters;
    }

}
