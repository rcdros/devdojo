package br.com.devdojo.javaclient;

import br.com.devdojo.model.PageableResponsible;
import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ricardors on 08/08/2018.
 */
public class JavaSprintClient {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthorization("Ricardo", "123456")
                .build();
/*
        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
        System.out.println(student);
        Student[] students = restTemplate.getForObject("/", Student[].class);
        System.out.println(Arrays.toString(students));
*/

        ResponseEntity<Student> studentEntity = restTemplate.getForEntity("/{id}", Student.class, 1);
        System.out.println(studentEntity.getBody());


        ResponseEntity<PageableResponsible<Student>> listStudent1 = restTemplate.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponsible<Student>>() {
        });
        System.out.println(listStudent1.getBody());

        ResponseEntity<PageableResponsible<Student>> listStudent2 = restTemplate.exchange("/?sort=id,desc&sort=name,asc", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponsible<Student>>() {
        });
        System.out.println(listStudent2.getBody());
    }
}
