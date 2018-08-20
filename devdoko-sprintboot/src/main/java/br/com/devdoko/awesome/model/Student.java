package br.com.devdoko.awesome.model;

/**
 * Created by ricardors on 05/08/2018.
 */
public class Student {
    public String name;

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
