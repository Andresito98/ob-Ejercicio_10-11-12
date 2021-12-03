package com.example.obEjercicio_101112.Controller;

import com.example.obEjercicio_101112.Entities.Laptop;
import com.example.obEjercicio_101112.Repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class LaptopController {

    private  final Logger log = LoggerFactory.getLogger(LaptopRepository.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //Metodo findAll
    @GetMapping("/ejercicio5/laptop/findeAll")
    public List<Laptop> findeAll(){
        return laptopRepository.findAll();
    }

    // Metodo findOneById
    // FindOneById/Laptop-ID
    @GetMapping("/FindOneById/Laptop-ID/{id}")
    @ApiOperation("Buscar un libro por clave primaria id Long")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave Primaria tipo Long") @PathVariable Long id){

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);  // con la opcion optional comprobamos si hay datos o no.
        // Opcion 2
        if (laptopOpt.isPresent()) // Si se encuentra en libro
            return ResponseEntity.ok(laptopOpt.get());    // Lo devuelve como OK
        else
            return ResponseEntity.notFound().build();   // No lo encuentra 404
    }


    //Metodo Create
    @PostMapping("/ejercicio6/laptop/Create") // No hay problema con el mapping por que uno es Get y otro es Post
    public Laptop create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //Guardar el libro recibido por parametros en la base de datos
        return laptopRepository.save(laptop);
    }


    // Metodo Update
    // Actualizar un libro existente en base de datos
    // @PutMapping("/Update/Laptop")
    @PutMapping("/Update/Laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){ //Si no tiene id quiere decir que si es una creacion
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())){
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }


    // Metodo de Delete
    // Borrar un libro en base de datos
    // Delete-ID/Laptop
    @DeleteMapping("/Delete-ID/Laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if (!laptopRepository.existsById(id)){
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // Metodo de DeleteAll
    // Borrar todos los libros en base de datos
    @DeleteMapping("/Delete-All")
    public ResponseEntity<Laptop> deleteAll(){

        log.warn("Trying to update a non existent laptop");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
