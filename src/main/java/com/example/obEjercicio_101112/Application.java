package com.example.obEjercicio_101112;

import com.example.obEjercicio_101112.Entities.Laptop;
import com.example.obEjercicio_101112.Repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		// Crear Laptop
		Laptop laptop1 = new Laptop(null,"PrimerPc","xRyce",1000.90,LocalDate.of(2020,12,2));
		Laptop laptop2 = new Laptop(null,"SegundoPc","xRyce2",800.00,LocalDate.of(2018,12,2));

		//Almacenar un Laptop
		System.out.println("Num de Laptops en base de datos "+ repository.findAll().size());
		repository.save(laptop1);
		repository.save(laptop2);

		//Recuperar todos los Laptop
		System.out.println("Num de Laptop en base de datos "+repository.findAll().size());

		//repository.deleteById(1L);
		System.out.println("Num de Laptop en base de datos "+repository.findAll().size());

	}

}
