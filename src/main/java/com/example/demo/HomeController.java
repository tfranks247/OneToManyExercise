package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String index(Model model) {
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

        @GetMapping("/add")
        public String movieForm(Model model){
            model.addAttribute("movie", new Movie());
            model.addAttribute("directors", directorRepository.findAll());
            return "movieform";
    }


    @PostMapping("/process")
    public String processForm(@ModelAttribute movie movie1, @Valid Movie movie, BindingResult result){
        if(result.hasErrors()){
            return "movieform";
        }
        else
            movieRepository.save();
        return "redirect:/";
    }



    @GetMapping("/adddirector")
    public String directorForm(Model model){
        model.addAttribute("director", new Director());
        return "directorform";
    }
    @PostMapping("/process")
    public String processForm(@ModelAttribute director director1, @Valid Director director, BindingResult result){
        if(result.hasErrors()){
            return "directorform";
        }
        else
            directorRepository.save(director);
        return "redirect:/";
    }
}

