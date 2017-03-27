package id.duza.todorealm.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.duza.todorealm.Presenter.DataTransactionImp;
import id.duza.todorealm.Presenter.GenerateId;
import id.duza.todorealm.Presenter.StoreData;
import id.duza.todorealm.Presenter.TodoValidator;
import id.duza.todorealm.R;
import id.duza.todorealm.model.Todo;
import io.realm.Realm;
import io.realm.RealmQuery;

public class EditorActivity extends AppCompatActivity {

    @BindView(R.id.et_editor_title) EditText etTitle;
    @BindView(R.id.et_editor_description) EditText etDescription;
    private StoreData storeData;
    private int updatedId;
    private GenerateId generateId;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        updatedId = getIntent().getIntExtra("update", -1);

        initializeObjects();

        updateViewIfExists();
    }

    private void initializeObjects() {
        storeData = new StoreData(this);
        generateId = new GenerateId(this);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
    }

    private void updateViewIfExists() {
        if (updatedId != -1)    {
            Todo todo = realm.where(Todo.class).equalTo("id", updatedId).findFirst();
            etTitle.setText(todo.getTitle());
            etDescription.setText(todo.getDescription());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save)   {
            storeData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void storeData() {
        if (storeData.insertOrUpdate(collectData(), updatedId)) {
            finish();
        }
    }

    private Todo collectData() {
        int nextID = generateId.getUniqueId(updatedId);
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        return new Todo(nextID, title, description);
    }
}
