package ru.arsik.api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseYelp {
    @SerializedName("alias")
    public String alias ;

    @SerializedName("title")
    public String title ;

    @SerializedName("parents")
    public List<String> parents ;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getParents() {
        return parents;
    }

    public void setParents(List<String> parents) {
        this.parents = parents;
    }

    public List<String> getCountryWhitelist() {
        return countryWhitelist;
    }

    public void setCountryWhitelist(List<String> countryWhitelist) {
        this.countryWhitelist = countryWhitelist;
    }

    @SerializedName("country_whitelist")
    public List<String> countryWhitelist ;
}
