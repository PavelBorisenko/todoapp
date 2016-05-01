package com.todoapp;

import com.google.gson.Gson;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pavlo.borysenko on 5/1/2016.
 */
public class ToDoService {

    private final DB db;
    private final DBCollection collection;

    public ToDoService(DB db) {
        this.db = db;
        this.collection = db.getCollection("todos");
    }

    public List<ToDo> findAll() {
        List<ToDo> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new ToDo((BasicDBObject) dbObject));
        }
        return todos;
    }

    public void createNewTodo(String body) {
        ToDo todo = new Gson().fromJson(body, ToDo.class);
        collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
    }

    public ToDo find(String id) {
        return new ToDo((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    public ToDo update(String todoId, String body) {
        ToDo todo = new Gson().fromJson(body, ToDo.class);
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
        return this.find(todoId);
    }
}
