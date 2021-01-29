package com.example.testlandscapelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainNameListAdapter extends RecyclerView.Adapter<MainNameListAdapter.ViewHolder> {

    public List<ItemModel> itemModelList;
    private Context context;
    private LayoutInflater inflater;

    public MainNameListAdapter(List<ItemModel> itemModelList, Context context) {
        this.itemModelList = itemModelList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.list_main_name_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtItemName.setText(itemModelList.get(position).itemName);
    }

    @Override
    public int getItemCount() {
        if (itemModelList == null)
            return 0;
        return itemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_item_name);
        }
    }
}
