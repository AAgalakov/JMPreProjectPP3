package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

//    public User(String name, int age, String role) {
//        this.name = name;
//        this.age = age;
//        this.role = role;
//    }

    public User(String name, String password, int age, String role) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

//    public User(long id, String name, int age, String role) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.role = role;
//    }

    public User(long id, String name, String password, int age, String role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getAge() == user.getAge() &&
                getName().equals(user.getName())
                &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getRole());
    }
}
