package com.example.testlandscapelist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {

    public List<ItemModel> list;
    public ItemModelValueGetter[] valueGetters;

    public Context context;
    public LayoutInflater inflater;
    public List<RecyclerView> itemRecycleViews;
    private int scrollX = 0;

    public MainListAdapter(List<ItemModel> list, ItemModelValueGetter[] valueGetters, Context context) {
        this.list = list;
        this.valueGetters = valueGetters;
        this.context = context;
        inflater = LayoutInflater.from(context);
        itemRecycleViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.list_main_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel itemModel = list.get(position);
        holder.valueAdapter.itemModel = itemModel;
        holder.valueAdapter.notifyDataSetChanged();
        holder.viewValueList.invalidate();
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView viewValueList;
        public ValueAdapter valueAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewValueList = itemView.findViewById(R.id.view_value_list);
            valueAdapter = new ValueAdapter(MainListAdapter.this, null, context, inflater);
            GridLayoutManager itemLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false);
            viewValueList.setLayoutManager(itemLayoutManager);
            viewValueList.setAdapter(valueAdapter);
            viewValueList.setNestedScrollingEnabled(false);
        }
    }
}
