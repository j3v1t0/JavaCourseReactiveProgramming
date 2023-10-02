package com.project.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class TestUsers {
    private static final Logger log = LoggerFactory.getLogger(TestUsers.class);

    public static void main(String[] args) {
        Flux<String> names = Flux.just(
                "Christian Ramires",
                "Mery Ramirez",
                "Biaggio Ramirez",
                "Julen Oliva",
                "Adrian Ramirez",
                "Steven Oliva",
                "Makol Jimenez");
        Flux<Users> usersFlux = names.map(
                name -> new Users(name.split(" ")[0].toUpperCase(),
                        name.split(" ")[1].toUpperCase()))
                .filter(users -> users.getLastname().equalsIgnoreCase("Jimenez"))
                .doOnNext(users -> {
                    if(users == null){
                        throw new RuntimeException("The names cannot be empty");
                    }
                    System.out.println(users.getFirsname().concat(" ").concat(users.getLastname()));
                })
                .map(users -> {
                    String name = users.getFirsname().toLowerCase();
                    users.setFirsname(name);
                    return users;
                });
        usersFlux.subscribe(e -> log.info(
                        e.toString()
                ),
                error -> log.error(
                        error.getMessage()
                ), new Runnable() {
                    @Override
                    public void run() {
                        log.info("The observable has been successfully completed!");
                    }
                });
    }

}
