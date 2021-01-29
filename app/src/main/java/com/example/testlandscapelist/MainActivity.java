package com.example.testlandscapelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView viewList;
    MainListAdapter mainListAdapter;
    RecyclerView viewNameList;
    MainNameListAdapter mainNameListAdapter;

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            for (RecyclerView view : recyclerViews) {
                if (view != recyclerView) {
                    view.removeOnScrollListener(this);
                    view.scrollBy(0, dy);
                    view.addOnScrollListener(this);
                }
            }
        }
    };
    private RecyclerView[] recyclerViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewNameList = findViewById(R.id.view_list_name);
        viewList = findViewById(R.id.view_list);

        // Create Adapter

        mainNameListAdapter = new MainNameListAdapter(null, this);
        viewNameList.setLayoutManager(new LinearLayoutManager(this));
        viewNameList.setAdapter(mainNameListAdapter);

        mainListAdapter = new MainListAdapter(null, null, this);
        viewList.setLayoutManager(new LinearLayoutManager(this));
        viewList.setAdapter(mainListAdapter);

        recyclerViews = new RecyclerView[2];
        recyclerViews[0] = viewNameList;
        recyclerViews[1] = viewList;

        viewNameList.addOnScrollListener(scrollListener);
        viewList.addOnScrollListener(scrollListener);

        new AsyncTask<Void, Void, ItemModelValueGetter[]>() {
            public ItemModelValueGetter[] valueGetters;
            public List<ItemModel> itemModelList;

            @Override
            protected ItemModelValueGetter[] doInBackground(Void... voids) {
                valueGetters = new ItemModelValueGetter[20];
                for (int i=0; i<20; i++) {
                    ItemModelValueGetter valueGetter = new ItemModelValueGetter();
                    String fieldName = String.format("value%02d", i);
                    valueGetter.SetFieldName(fieldName);
                    valueGetters[i] = valueGetter;
                }
                // Create the models
                itemModelList = new ArrayList<>();
                for (int i=0; i<100; i++) {
                    ItemModel item = new ItemModel();
                    item.itemName = String.format("Item%02d", i);
                    item.value00 = String.format("v_%02d_%02d", i, 0);
                    item.value01 = String.format("v_%02d_%02d", i, 1);
                    item.value02 = String.format("v_%02d_%02d", i, 2);
                    item.value03 = String.format("v_%02d_%02d", i, 3);
                    item.value04 = String.format("v_%02d_%02d", i, 4);
                    item.value05 = String.format("v_%02d_%02d", i, 5);
                    item.value06 = String.format("v_%02d_%02d", i, 6);
                    item.value07 = String.format("v_%02d_%02d", i, 7);
                    item.value08 = String.format("v_%02d_%02d", i, 8);
                    item.value09 = String.format("v_%02d_%02d", i, 9);
                    item.value10 = String.format("v_%02d_%02d", i, 10);
                    item.value11 = String.format("v_%02d_%02d", i, 11);
                    item.value12 = String.format("v_%02d_%02d", i, 12);
                    item.value13 = String.format("v_%02d_%02d", i, 13);
                    item.value14 = String.format("v_%02d_%02d", i, 14);
                    item.value15 = String.format("v_%02d_%02d", i, 15);
                    item.value16 = String.format("v_%02d_%02d", i, 16);
                    item.value17 = String.format("v_%02d_%02d", i, 17);
                    item.value18 = String.format("v_%02d_%02d", i, 18);
                    item.value19 = String.format("v_%02d_%02d", i, 19);

                    itemModelList.add(item);
                }

                return valueGetters;
            }

            @Override
            protected void onPostExecute(ItemModelValueGetter[] aVoid) {
                super.onPostExecute(aVoid);

                Log.d("Green", "Green valueGetters size: " + aVoid.length);
                for (ItemModelValueGetter getter : aVoid) {
                    Log.d("Green", "Green valueGetter " + getter.fieldName);
                }
                mainNameListAdapter.itemModelList = itemModelList;
                mainNameListAdapter.notifyDataSetChanged();
                mainListAdapter.valueGetters = aVoid;
                mainListAdapter.list = itemModelList;
                mainListAdapter.notifyItemRangeChanged(0, valueGetters.length);
            }
        }.execute();
    }
}