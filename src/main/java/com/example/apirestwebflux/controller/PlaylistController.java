package com.example.apirestwebflux.controller;

import com.example.apirestwebflux.document.Playlist;
import com.example.apirestwebflux.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public Flux<Playlist> getPlaylist(){
        return playlistService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Playlist> getPlaylistId(@PathVariable(value = "id") String id){
        return playlistService.findById(id);
    }

    @PostMapping
    public Mono<Playlist> save(@RequestBody Playlist playlist){
        return playlistService.save(playlist);
    }

    @GetMapping(value="/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByWebflux(){

        System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> playlistFlux = playlistService.findAll();

        return Flux.zip(interval, playlistFlux);

    }

    @GetMapping(value="/playlist/mvc")
    public List<String> getPlaylistByMvc() throws InterruptedException {

        System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());


        List<String> playlistList = new ArrayList<>();
        playlistList.add("Java 8");
        playlistList.add("Spring Security");
        playlistList.add("Github");
        playlistList.add("Deploy de uma aplicação java no IBM Cloud");
        playlistList.add("Bean no Spring Framework");
        TimeUnit.SECONDS.sleep(15);

        return playlistList;

    }

}
