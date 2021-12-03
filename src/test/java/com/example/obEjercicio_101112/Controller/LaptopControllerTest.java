package com.example.obEjercicio_101112.Controller;

import com.example.obEjercicio_101112.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    //---------------------------------------------------------------------------------------------------------------
    private TestRestTemplate testRestTemplate; // Este es el que enlaza las peticiones.

    @Autowired
    private RestTemplateBuilder restTemplateBuilder; // Este es el que hace que se construya y funcione el primero (( private TestRestTemplate testRestTemplate;))

    @LocalServerPort
    private int port;
    //---------------------------------------------------------------------------------------------------------------

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port); //Aqui le ponemos nuestra direccion http
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Aqui vamos a comprobar el controlador de Hello") //Esto aparecera en la consola al cargarlo como titulo interno.
    @Test
    void hello() {
        // testRestTemplate este objeto nos permitira usar el http
        ResponseEntity<String> response = testRestTemplate.getForEntity("/Saludo", String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
       // assertEquals("Hola Mundo somos unos maquinas, probando jejeje",response.getBody());
    }

    @Test
    void findAll() {
        // testRestTemplate este objeto nos permitira usar el http
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/ejercicio5/laptop/findeAll", Laptop[].class);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        //pasar de array a arraylist
        List<Laptop> books = Arrays.asList(response.getBody());
        System.out.println(books.size());
    }

    // Este metodo aqui no funciona /FindOneById/Laptop-ID/{id} --  tienes que poner el Id De manera manual o da fallo.
    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/FindOneById/Laptop-ID/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void create() {
        //ESTO PARA PREPARAR LAS CABECERAS.

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //PREPARANDO EL JSON
        String json = """
                {
                        "name": "Laptop creado desde Spring Test",
                        "model": "Intelinmg",
                        "price": 2000.9,
                        "releaseDate": "2021-12-02"
                }
                """;

        //PREPARANDO LA PETICION

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/ejercicio5/laptop/findeAll", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();

        //assertEquals(0L,result.getId());
        //assertEquals("Libro creado desde Spring Test",result.getName()); //AQUI HE MODIFICADO el gettitle que habia por este.



    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}