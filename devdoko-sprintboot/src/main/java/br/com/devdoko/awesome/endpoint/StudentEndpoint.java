package br.com.devdoko.awesome.endpoint;

import br.com.devdoko.awesome.model.Student;
import br.com.devdoko.awesome.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by ricardors on 05/08/2018.
 */
@RestController
@RequestMapping("student")
public class StudentEndpoint {

    @Autowired
    private DateUtil dateUtil;

    @RequestMapping(method = RequestMethod.GET, path="/list")
    public List<Student> listAll() {
        System.out.println("GET listAll: "+dateUtil.formatLocalDateTimeDataBaseStyle(LocalDateTime.now()));
        return asList(new Student("Ricardo"), new Student("Santos"));
    }
}
