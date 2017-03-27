package id.duza.todorealm.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.duza.todorealm.Presenter.DataTransaction;
import id.duza.todorealm.Presenter.DataTransactionImp;
import id.duza.todorealm.R;
import id.duza.todorealm.model.Todo;
import id.duza.todorealm.view.adapter.TodoAdapter;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_view_main) ListView listView;
    private TodoAdapter adapter;
    private List<Todo> todoList = new ArrayList<>();
    private RealmResults<Todo> todos;
    private DataTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        transaction = new DataTransactionImp(this);
        listenData();
        setupAdapter();
        fetchData(transaction.select());
        viewListener();
    }

    private void viewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                intent.putExtra("update", todoList.get(position).getId());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteAlertDialog(todoList.get(position));
                return true;
            }
        });
    }

    private void showDeleteAlertDialog(final Todo todo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(todo.getTitle())
                .setMessage("Are you sure to delete this item>?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        transaction.delete(todo);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void listenData() {
        todos = transaction.select();
        todos.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Todo>>() {
            @Override
            public void onChange(RealmResults<Todo> collection, OrderedCollectionChangeSet changeSet) {
                adapter.clear();
                adapter.addAll(collection);

            }
        });
    }

    private void fetchData(RealmResults<Todo> todoList) {
        adapter.clear();
        adapter.addAll(todoList);
    }

    private void setupAdapter() {
        adapter = new TodoAdapter(this, todoList);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.fab_main)
    public void fabInsertData(View view) {
        startActivity(new Intent(this, EditorActivity.class));
    }
}
