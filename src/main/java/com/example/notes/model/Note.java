package com.example.notes.model;

public class Note {
    private Long id;
    private String title;
    private String body;

    public Note() {}

    public Note(Long id, String title, String body) {
        this.id = id; this.title = title; this.body = body;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setBody(String body) { this.body = body; }
}
