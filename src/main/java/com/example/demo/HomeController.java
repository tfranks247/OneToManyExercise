package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String index(Model model){
        Director director = new Director();

        director.setName("Steven Spielberg");
        director.setGenre("Sci-fi");

        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About stars...");

        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("Deathstar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks and deathstar...");
        movies.add(movie);

        director.setMovies(movies);

        directorRepository.save(director);

        model.addAttribute("directors", directorRepository.findAll());
        return "index";

    }
}
