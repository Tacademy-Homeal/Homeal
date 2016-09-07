package com.sm.ej.nk.homeal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.sm.ej.nk.homeal.adapter.AdviceAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdviceActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_ck_toolbar)
    Toolbar toolbar;
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> adviceCollection;
    ExpandableListView expListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.FAQ));

        createGroupList();
        createCollection();

        expListView = (ExpandableListView) findViewById(R.id.advice_list);
        final ExpandableListAdapter expListAdapter = new AdviceAdapter(
                this, groupList, adviceCollection);
        expListView.setAdapter(expListAdapter);

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem=-1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    expListView.collapseGroup(previousItem);
                previousItem=groupPosition;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add(getResources().getString(R.string.advice1));
        groupList.add(getResources().getString(R.string.advice2));
        groupList.add(getResources().getString(R.string.advice3));
        groupList.add(getResources().getString(R.string.advice4));
        groupList.add(getResources().getString(R.string.advice5));
        groupList.add(getResources().getString(R.string.advice6));
        groupList.add(getResources().getString(R.string.advice7));
        groupList.add(getResources().getString(R.string.advice8));
        groupList.add(getResources().getString(R.string.advice9));
        groupList.add(getResources().getString(R.string.advice10));
        groupList.add(getResources().getString(R.string.advice11));
        groupList.add(getResources().getString(R.string.advice12));
        groupList.add(getResources().getString(R.string.advice13));
        groupList.add(getResources().getString(R.string.advice14));
        groupList.add(getResources().getString(R.string.advice15));
        groupList.add(getResources().getString(R.string.advice16));
        groupList.add(getResources().getString(R.string.advice17));
        groupList.add(getResources().getString(R.string.advice18));
        groupList.add(getResources().getString(R.string.advice19));
        groupList.add(getResources().getString(R.string.advice20));
        groupList.add(getResources().getString(R.string.advice21));
        groupList.add(getResources().getString(R.string.advice22));
        groupList.add(getResources().getString(R.string.advice23));
        groupList.add(getResources().getString(R.string.advice24));
        groupList.add(getResources().getString(R.string.advice25));
    }

    private void createCollection() {
        String[] advice1Models = {getResources().getString(R.string.advice1_1)};
        String[] advice2Models = {getResources().getString(R.string.advice2_1)};
        String[] advice3Models = {getResources().getString(R.string.advice3_1)};
        String[] advice4Models = { getResources().getString(R.string.advice4_1)};
        String[] advice5Models = {getResources().getString(R.string.advice5_1)};
        String[] advice6Models = {getResources().getString(R.string.advice6_1)};
        String[] advice7Models = {getResources().getString(R.string.advice7_1)};
        String[] advice8Models = {getResources().getString(R.string.advice8_1)};
        String[] advice9Models = {getResources().getString(R.string.advice9_1)};
        String[] advice10Models = {getResources().getString(R.string.advice10_1)};
        String[] advice11Models = {getResources().getString(R.string.advice11_1)};
        String[] advice12Models = {getResources().getString(R.string.advice12_1)};
        String[] advice13Models = {getResources().getString(R.string.advice13_1)};
        String[] advice14Models = {getResources().getString(R.string.advice14_1)};
        String[] advice15Models = {getResources().getString(R.string.advice15_1)};
        String[] advice16Models = {getResources().getString(R.string.advice16_1)};
        String[] advice17Models = {getResources().getString(R.string.advice17_1)};
        String[] advice18Models = {getResources().getString(R.string.advice18_1)};
        String[] advice19Models = {getResources().getString(R.string.advice19_1)};
        String[] advice20Models = {getResources().getString(R.string.advice20_1)};
        String[] advice21Models = {getResources().getString(R.string.advice21_1)};
        String[] advice22Models = {getResources().getString(R.string.advice22_1)};
        String[] advice23Models = {getResources().getString(R.string.advice23_1)};
        String[] advice24Models = {getResources().getString(R.string.advice24_1)};
        String[] advice25Models = {getResources().getString(R.string.advice25_1)};

        adviceCollection = new LinkedHashMap<String, List<String>>();

        for (String advice : groupList) {
            if (advice.equals(getResources().getString(R.string.advice1))) {
                loadChild(advice1Models);
            } else if (advice.equals(getResources().getString(R.string.advice2)))
                loadChild(advice2Models);
            else if (advice.equals(getResources().getString(R.string.advice3)))
                loadChild(advice3Models);
            else if (advice.equals(getResources().getString(R.string.advice4)))
                loadChild(advice4Models);
            else if (advice.equals(getResources().getString(R.string.advice5)))
                loadChild(advice5Models);
            else if (advice.equals(getResources().getString(R.string.advice6)))
                loadChild(advice6Models);
            else if (advice.equals(getResources().getString(R.string.advice7)))
                loadChild(advice7Models);
            else if (advice.equals(getResources().getString(R.string.advice8)))
                loadChild(advice8Models);
            else if (advice.equals(getResources().getString(R.string.advice9)))
                loadChild(advice9Models);
            else if (advice.equals(getResources().getString(R.string.advice10)))
                loadChild(advice10Models);
            else if (advice.equals(getResources().getString(R.string.advice11)))
                loadChild(advice11Models);
            else if (advice.equals(getResources().getString(R.string.advice12)))
                loadChild(advice12Models);
            else if (advice.equals(getResources().getString(R.string.advice13)))
                loadChild(advice13Models);
            else if (advice.equals(getResources().getString(R.string.advice14)))
                loadChild(advice14Models);
            else if (advice.equals(getResources().getString(R.string.advice15)))
                loadChild(advice15Models);
            else if (advice.equals(getResources().getString(R.string.advice16)))
                loadChild(advice16Models);
            else if (advice.equals(getResources().getString(R.string.advice17)))
                loadChild(advice17Models);
            else if (advice.equals(getResources().getString(R.string.advice18)))
                loadChild(advice18Models);
            else if (advice.equals(getResources().getString(R.string.advice19)))
                loadChild(advice19Models);
            else if (advice.equals(getResources().getString(R.string.advice20)))
                loadChild(advice20Models);
            else if (advice.equals(getResources().getString(R.string.advice21)))
                loadChild(advice21Models);
            else if (advice.equals(getResources().getString(R.string.advice22)))
                loadChild(advice22Models);
            else if (advice.equals(getResources().getString(R.string.advice23)))
                loadChild(advice23Models);
            else if (advice.equals(getResources().getString(R.string.advice24)))
                loadChild(advice24Models);
            else
                loadChild(advice25Models);

            adviceCollection.put(advice, childList);
        }
    }

    private void loadChild(String[] adviceModels) {
        childList = new ArrayList<String>();
        for (String model : adviceModels)
            childList.add(model);
    }

}
