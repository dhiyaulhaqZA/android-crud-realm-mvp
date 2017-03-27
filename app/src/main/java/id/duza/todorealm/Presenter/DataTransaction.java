package id.duza.todorealm.Presenter;

import id.duza.todorealm.model.Todo;
import io.realm.RealmResults;

/**
 * Created by dhiyaulhaqza on 3/26/17.
 */

public interface DataTransaction {

    RealmResults<Todo> select();
    void insert(Todo todo);
    void update(Todo todo);
    void delete(Todo todo);
}
