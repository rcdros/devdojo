package br.com.devdojo;

import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ricardors on 08/08/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//fará uma série de confgurações para utilizar o endpoint
@AutoConfigureMockMvc
public class StudentEndPointTest {

    @Autowired //TestRestTemplate é uma versão mais leve do RestTemplate
    private TestRestTemplate restRestTemplate;

    @LocalServerPort //para saber qual a porta está sendo utilizada...
    private int port;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    /*@Before
    public void start() {
        restRestTemplate = restRestTemplate.withBasicAuth("Ricardo", "123456");
    }*/

    @TestConfiguration //
    static class Config {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder().basicAuthorization("Ricardo", "123456");
        }
    }

    @Test //usando REST Template
    public void listStudentWhenUsernameAndPasswordError401() {
        System.out.println("Port Nnumber: " + port);
        restRestTemplate = restRestTemplate.withBasicAuth("1", "1");

        ResponseEntity<String> responseForEntity = restRestTemplate.getForEntity("/v1/protected/student/", String.class);
        Assertions.assertThat(responseForEntity.getStatusCodeValue()).isEqualTo(401);
    }


    @Test //usando MockMVC
    public void listStudentWhenUsernameAndPasswordHTTP200WithMock() {
        List<Student> list = Arrays.asList(new Student("Ricardo", "a@a.com.br"));
        BDDMockito.when(studentRepository.findAll()).thenReturn(list);

        System.out.println("Port Nnumber: " + port);

        ResponseEntity<String> responseForEntity = restRestTemplate.getForEntity("/v1/protected/students/", String.class);
        Assertions.assertThat(responseForEntity.getStatusCodeValue()).isEqualTo(200);
    }


    @Test //usando MockMVc
    @WithMockUser(username = "admin", password = "123456", roles = {"USER", "ADMIN"})
    public void listStudentWhenStudentNoDelete() throws Exception {
        System.out.println("Port Nnumber: " + port);
        BDDMockito.doNothing().when(studentRepository).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/admin/students/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


}
