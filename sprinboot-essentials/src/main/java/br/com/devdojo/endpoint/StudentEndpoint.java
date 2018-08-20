package br.com.devdojo.endpoint;

import br.com.devdojo.error.CustomErroType;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentPagingRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ricardors on 05/08/2018.
 */
@RestController
@RequestMapping("v1")
public class StudentEndpoint {

    private final StudentPagingRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentPagingRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping(path="protected/students")
    @ApiOperation(value = "Return the all list of students", response = Student[].class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="Authorization", value="Bearer Token",
//                    required = true, dataType = "string", paramType = "header")
//    })
    public ResponseEntity<?> listAll(Pageable pageable, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {

        Student student = studentDAO.findOne(id);
        if (student == null) {
            throw new ResourceNotFoundException("Student not found");
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/findbyname/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable("name") String name) {

        try {
            List<Student> students = studentDAO.findByNameIgnoreCaseContaining(name);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new CustomErroType("Student not found"), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(path="admin/students")
    @Transactional()
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
        Student novo = studentDAO.save(student);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {

        try {
            studentDAO.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new CustomErroType("Student wasn't deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path="admin/students")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {

        try {
            studentDAO.save(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new CustomErroType("Student wasn't updated"), HttpStatus.NOT_FOUND);
        }
    }
}
