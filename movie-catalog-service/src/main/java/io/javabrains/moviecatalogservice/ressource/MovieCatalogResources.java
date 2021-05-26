package io.javabrains.moviecatalogservice.ressource;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {
    @Autowired
    //yhawas 3al @Bean li 3andah nafs type ta3 variable li tahtah
    private RestTemplate restTemplate;
    @Autowired
    private  WebClient.Builder webclientBuilder;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")String userId){
        //
        WebClient.Builder builder = WebClient.builder();

        //get all rated movie Ids

        List<Rating> ratings = Arrays.asList(
                new Rating("1234",3),
                new Rating("321",4)
        );
        return ratings.stream().map(rating-> {
          // Movie movie= restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);

            Movie movie=webclientBuilder
                    .build().get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve().bodyToMono(Movie.class).block();

            return new CatalogItem(movie.getName(), "test", rating.getRating());
        })
                .collect(Collectors.toList());
        //for each movie Id , call movie info service and get details

        //put them all together
    }
}
