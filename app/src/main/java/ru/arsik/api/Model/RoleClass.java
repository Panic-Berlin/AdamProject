package ru.arsik.api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoleClass {

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    // список всех юзеров
    @SerializedName("Roles")
    private List<Role> roles;


}

class Role {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
}



