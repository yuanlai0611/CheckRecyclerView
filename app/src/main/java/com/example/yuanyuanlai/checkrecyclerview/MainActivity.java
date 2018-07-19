package com.example.yuanyuanlai.checkrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView firstRecyclerView;
    private FirstAdapter firstAdapter;
    private List<FirstItem> firstItems = new ArrayList<>();
    private CheckBox checkAllCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAllCheckBox = (CheckBox)findViewById(R.id.checkAll);
        firstRecyclerView = (RecyclerView)findViewById(R.id.first_recyclerView);

        for(int i=0; i<30; i++){
            firstItems.add(new FirstItem("test"+i, false));
        }

        checkAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //firstItems.clear();
                if (isChecked){
                    firstAdapter.setCheckAll(true, false);
                }else{
                    firstAdapter.setCheckAll(false, false);
                }
                firstAdapter.notifyDataSetChanged();
            }
        });

        firstAdapter = new FirstAdapter(this, firstItems);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        firstRecyclerView.setLayoutManager(linearLayoutManager);
        firstRecyclerView.setAdapter(firstAdapter);

        firstAdapter.setOnCheckListener(new FirstAdapter.OnCheckListener() {
            @Override
            public void onCheck(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }
}
