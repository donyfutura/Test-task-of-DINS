package com.dins.test.api.request;


import javax.validation.constraints.NotBlank;

public class UserEditRequest {

    @NotBlank
    private String name;

    public UserEditRequest() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
