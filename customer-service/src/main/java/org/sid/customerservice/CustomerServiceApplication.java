package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.CustomerRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRespository customerRespository, RepositoryRestConfiguration repositoryRestConfiguration){

        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args ->{
            customerRespository.save(new Customer(null,"HASSAN", "hassan@gmail.com"));
            customerRespository.save(new Customer(null,"AMINE", "amine@hotmail.com"));
            customerRespository.save(new Customer(null,"SALIM", "salim@outlook.com"));
            customerRespository.findAll().forEach(c->{
                System.out.println(c.getName());
            });
        };
    }


}
