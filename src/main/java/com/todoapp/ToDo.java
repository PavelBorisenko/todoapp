package com.todoapp;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by pavlo.borysenko on 5/1/2016.
 */
public class ToDo {

    private String id;
    private String title;
    private boolean done;
    private Date createdOn = new Date();

    public ToDo(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.title = dbObject.getString("title");
        this.done = dbObject.getBoolean("done");
        this.createdOn = dbObject.getDate("createdOn");
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
}
