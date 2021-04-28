package com.dins.test.api.response;

public class UserResponse {

    private long id;

    private String name;

    public UserResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserResponse() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
