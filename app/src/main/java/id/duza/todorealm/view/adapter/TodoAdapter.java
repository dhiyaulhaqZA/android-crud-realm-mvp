package id.duza.todorealm.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.duza.todorealm.R;
import id.duza.todorealm.model.Todo;

/**
 * Created by dhiyaulhaqza on 3/26/17.
 */

public class TodoAdapter extends ArrayAdapter<Todo> {

    @BindView(R.id.tv_item_title) TextView tvTitle;
    @BindView(R.id.tv_item_description) TextView tvDescription;

    public TodoAdapter(@NonNull Context context, @NonNull List<Todo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        }

        ButterKnife.bind(this, convertView);

        Todo todo = getItem(position);
        if (todo != null) {
            tvTitle.setText(todo.getTitle());
            tvDescription.setText(todo.getDescription());
        }

        return convertView;
    }
}
