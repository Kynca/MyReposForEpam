package by.training.finaltask.controller;

import by.training.finaltask.bean.Result;
import by.training.finaltask.bean.entities.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public interface Command {
    Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException;

    default Set<Role> getRoleSet(){
        Set<Role> roleSet = new HashSet<>();
        return roleSet;
    }
}
