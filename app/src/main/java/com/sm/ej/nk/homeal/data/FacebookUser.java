package com.sm.ej.nk.homeal.data;

import java.io.Serializable;

/**
 * Created by kimnamgil on 2016. 9. 11..
 */
public class FacebookUser implements Serializable {
    private String name;
    private String id;
    private String email;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
