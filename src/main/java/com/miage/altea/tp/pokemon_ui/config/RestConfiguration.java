package com.miage.altea.tp.pokemon_ui.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration 
public class RestConfiguration {
	
	@Value("${trainer.service.username}")
	String username;
	@Value("${trainer.service.password}")
	String password;
	
    @Bean
	public RestTemplate restTemplate(){
        return new RestTemplate(); 
    }
    
    @Bean
    public RestTemplate trainerApiRestTemplate(){
    	RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new BasicAuthenticationInterceptor(username, password));
        interceptors.add((request, body, execution) -> {
            request.getHeaders().add("Accept-Language", LocaleContextHolder.getLocale().getLanguage());
            return execution.execute(request, body);
        });

        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

}