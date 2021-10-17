package by.training.finaltask.bean.entities;

import java.io.Serializable;

public enum Role implements Serializable {
    ADMINISTRATOR(0),
    STUDENT(1),
    DEAN(2);


    private Integer value;

    Role(Integer value){this.value = value;}

    public Integer getValue(){
        return value;
    }

    public static Role getByCode(Integer role){
        for(Role g: Role.values()){
            if(g.value.equals(role)){
                return g;
            }
        }
        return null;
    }
}
