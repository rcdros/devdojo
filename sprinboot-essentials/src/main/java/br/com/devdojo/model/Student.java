package br.com.devdojo.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;

/**
 * Created by ricardors on 05/08/2018.
 */

@Entity
public class Student extends AbstractEntity {

    public Student() {

    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
