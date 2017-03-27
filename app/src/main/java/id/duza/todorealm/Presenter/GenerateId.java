package id.duza.todorealm.Presenter;

import android.content.Context;

import id.duza.todorealm.model.Todo;
import io.realm.Realm;

/**
 * Created by dhiyaulhaqza on 3/27/17.
 */

public class GenerateId {
    private DataTransaction transaction;
    private Context context;
    private Realm realm;

    public GenerateId(Context context) {
        this.context = context;
        this.transaction = new DataTransactionImp(context);
        realm = Realm.getDefaultInstance();
    }

    public int getUniqueId(int updatedId)    {
        int uniqueId = 0;
        if (!(transaction.select().size() == 0))   {
            uniqueId = realm.where(Todo.class).max("id").intValue() + 1;
            if (updatedId != -1) {
                uniqueId = updatedId;
            }
        }
        return uniqueId;
    }
}
