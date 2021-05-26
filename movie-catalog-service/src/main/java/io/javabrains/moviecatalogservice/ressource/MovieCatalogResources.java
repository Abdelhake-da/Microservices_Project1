package io.javabrains.moviecatalogservice.ressource;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")String userId){
        //get all rated movie Ids
        RestTemplate restTemplat=new RestTemplate();

        List<Rating> ratings = Arrays.asList(
                new Rating("1234",3),
                new Rating("321",4)
        );
        return ratings.stream().map(rating-> {
           Movie movie= restTemplat.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);
            return new CatalogItem(movie.getName(), "test", rating.getRating());
        })
                .collect(Collectors.toList());
        //for each movie Id , call movie info service and get details

        //put them all together
    }
}
