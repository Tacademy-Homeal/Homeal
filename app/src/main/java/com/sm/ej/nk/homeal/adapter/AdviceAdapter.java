package com.sm.ej.nk.homeal.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.sm.ej.nk.homeal.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class AdviceAdapter extends BaseExpandableListAdapter {
    private Activity context;
    private Map<String, List<String>> adviceCollections;
    private List<String> advices;

    public AdviceAdapter(Activity context, List<String> advices,
                                 Map<String, List<String>> adviceCollections) {
        this.context = context;
        this.adviceCollections = adviceCollections;
        this.advices = advices;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return adviceCollections.get(advices.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String advice = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_advice_child, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.advice_answer);

        item.setText(advice);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return adviceCollections.get(advices.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return advices.get(groupPosition);
    }

    public int getGroupCount() {
        return advices.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String adviceName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.view_advice_parent,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.advice_question);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(adviceName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}