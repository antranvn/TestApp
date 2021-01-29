package com.example.testlandscapelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.ViewHolder> {

    public MainListAdapter mainListAdapter;
    public ItemModel itemModel;

    public Context context;
    public LayoutInflater inflater;

    public ValueAdapter(MainListAdapter mainListAdapter, ItemModel itemModel, Context context, LayoutInflater inflater) {
        this.mainListAdapter = mainListAdapter;
        this.itemModel = itemModel;
        this.context = context;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.list_value_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtValue.setText(mainListAdapter.valueGetters[position].GetValue(itemModel));
    }

    @Override
    public int getItemCount() {
        if (mainListAdapter.valueGetters == null)
            return 0;
        return mainListAdapter.valueGetters.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtValue = (TextView)itemView;
        }
    }
}
