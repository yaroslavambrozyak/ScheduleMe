package com.study.yaroslavambrozyak.scheduleme.model;


public class Remind {

    private long id;
    private String title;
    private String descripton;

    public Remind() {
    }

    public Remind(long id, String title, String descripton) {
        this.id = id;
        this.title = title;
        this.descripton = descripton;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
}
