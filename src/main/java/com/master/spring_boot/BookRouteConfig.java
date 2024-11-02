package com.master.spring_boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;


@Configuration
public class BookRouteConfig {



    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        BookHandler bookHandler = new BookHandler();
        return RouterFunctions.route()
                .GET("/api/v1/books" , bookHandler::getAllBooks)
                .GET("/api/v1/book/{name}" , bookHandler::getBookByName)
                .build();
    }


}
