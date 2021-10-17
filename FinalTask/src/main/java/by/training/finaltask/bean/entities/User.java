package by.training.finaltask.bean.entities;

import by.training.finaltask.bean.Entity;

import java.io.Serializable;
import java.util.Objects;

public class User extends Entity {
    private String login;
    private Role role;
    private String pass;

    public User(Integer id,String login, String pass, Role role){
        super(id);
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    public User(Integer id, String login, Role role){
        this.role = role;
        super.setId(id);
        this.login = login;
    }

    public User(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && role.equals(user.role) && pass.equals(user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, role, pass);
    }

    @Override
    public String toString() {
        return "User{" + super.getId() +
                "login='" + login + '\'' +
                ", role=" + role +
                ", pass='" + pass + '\'' +
                '}';
    }
}
