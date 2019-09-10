package com.example.demo.document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.HashMap;

public class Images {

    @Id
    private ObjectId Id;
    private String imagename;
    private String username;
    private String background;
    private String before;
    private String output;
    private HashMap<String,Long> time;

    public Images(String username, String before, String output, HashMap<String,Long> time) {
        this.username = username;
        this.before = before;
        this.output = output;
        this.time = time;
    }

    public HashMap<String, Long> getTime() {
        return time;
    }

    public void setTime(HashMap<String, Long> time) {
        this.time = time;
    }

    public ObjectId getId() {
        return Id;
    }

    public void setId(ObjectId id) {
        Id = id;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
