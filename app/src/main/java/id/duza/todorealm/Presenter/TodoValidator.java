package id.duza.todorealm.Presenter;

import android.text.TextUtils;

import id.duza.todorealm.model.Todo;

/**
 * Created by dhiyaulhaqza on 3/26/17.
 */

public class TodoValidator  {

    public boolean isTodoValid(Todo todo) {
        return  (!(TextUtils.isEmpty(todo.getTitle())
                && TextUtils.isEmpty(todo.getDescription())));
    }
}
