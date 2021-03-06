package com.dins.test.api.response;

public class ContactResponse {

    private long id;
    private String name;
    private String number;

    public ContactResponse(long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public ContactResponse() {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
