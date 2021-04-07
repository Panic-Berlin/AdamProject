package ru.arsik.api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class UserClass {
    public List<User> getUsers() {
        return users;
    }
    @SerializedName("users")
    public List<User> users;
    class User {
        @SerializedName("id")
        private int id;
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getLastName() {
            return lastName;
        }
        @SerializedName("name")
        private String name;
        @SerializedName("lastName")
        private String lastName;
    }
}
