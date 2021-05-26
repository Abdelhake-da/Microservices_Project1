package io.javabrains.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilderFactory;

import java.nio.file.Watchable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootApplication
public class MovieCatalogServiceApplication {
@Bean
//hado les types li momkin yasha9ohom w y3aytolhom bal @Autowired
public  RestTemplate getRestTemplate(){
	return new RestTemplate();
}
public WebClient.Builder getWebClientBuilder() {
	return  WebClient.builder();
}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}

