package id.duza.todorealm.Presenter;

import android.content.Context;

import java.util.List;

import id.duza.todorealm.model.Todo;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dhiyaulhaqza on 3/26/17.
 */

public class DataTransactionImp implements DataTransaction{

    private Realm realm;
    private RealmResults<Todo> todos;

    public DataTransactionImp(Context context)  {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void insert(Todo todo)   {
        realm.beginTransaction();
        realm.copyToRealm(todo);
        realm.commitTransaction();
    }

    @Override
    public RealmResults<Todo> select()    {
        todos = realm.where(Todo.class).findAll();
        return todos;
    }

    @Override
    public void update(Todo todo)  {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(todo);
        realm.commitTransaction();
    }

    @Override
    public void delete(Todo todo)    {
        realm.beginTransaction();
        todo.deleteFromRealm();
        realm.commitTransaction();
    }
}
