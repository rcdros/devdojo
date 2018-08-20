package br.com.devdojo;

import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.DockerComposeContainer;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.util.List;

/**
 * Created by ricardors on 08/08/2018.
 */


@RunWith(SpringRunner.class)
@DataJpaTest ///fará variaas configurações para facilitar a criação dos testes...
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Rule //indica quais exceções estaremos esperando..
    public ExpectedException thrown = ExpectedException.none();


//    @ClassRule
//    public static DockerComposeRule docker = DockerComposeRule.builder()
//            .file("docker/docker-compose.yaml")
//            .waitingForService("devdojo", HealthChecks.toHaveAllPortsOpen()) .build();


    @Test
    public void createShouldPersistentData() {
        Student student = new Student("Ricardo", "a@a.com.br");

        this.studentRepository.save(student);
        Assertions.assertThat(student.getId()).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo("Ricardo");
        Assertions.assertThat(student.getEmail()).isEqualTo("a@a.com.br");
    }

    @Test
    public void findUserByName() {
        Student student = new Student("Ricardo", "a@a.com.br");
        this.studentRepository.save(student);

        List<Student> students = this.studentRepository.findByNameIgnoreCaseContaining("ric");
        Assertions.assertThat(students.size()).isGreaterThan(0);
    }

    @Test
    public void constraintViolationException () {
        thrown.expect(ConstraintViolationException.class);

        this.studentRepository.save(new Student());
    }

}
