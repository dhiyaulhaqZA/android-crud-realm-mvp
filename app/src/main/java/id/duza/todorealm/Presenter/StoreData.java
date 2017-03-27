package id.duza.todorealm.Presenter;

import android.content.Context;
import android.text.TextUtils;

import id.duza.todorealm.model.Todo;

/**
 * Created by dhiyaulhaqza on 3/27/17.
 */

public class StoreData {

    private TodoValidator validator;
    private DataTransaction transaction;
    private Context context;

    public StoreData(Context context)  {
        this.context = context;
        this.validator = new TodoValidator();
        this.transaction = new DataTransactionImp(context);
    }
    public boolean insertOrUpdate(Todo todo, int updatedId)    {
        boolean isValid = validator.isTodoValid(todo);
        if (isValid) {
            if (updatedId == -1) {
                transaction.insert(todo);
            } else {
                transaction.update(todo);
            }
        }

        return isValid;
    }
}
