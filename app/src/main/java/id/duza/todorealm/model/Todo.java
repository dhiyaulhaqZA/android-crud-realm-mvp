package id.duza.todorealm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dhiyaulhaqza on 3/26/17.
 */

public class Todo extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private String description;

    public Todo()   {}

    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
