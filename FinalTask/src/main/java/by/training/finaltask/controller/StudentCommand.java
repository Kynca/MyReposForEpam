package by.training.finaltask.controller;

import by.training.finaltask.bean.entities.Role;

import java.util.HashSet;
import java.util.Set;

public interface StudentCommand extends Command {
    default Set<Role> getRoleSet() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.STUDENT);
        return roleSet;
    }
}
