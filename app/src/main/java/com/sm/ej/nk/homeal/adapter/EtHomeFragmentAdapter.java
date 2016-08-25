package com.sm.ej.nk.homeal.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sm.ej.nk.homeal.InfoCkDetailActivity;
import com.sm.ej.nk.homeal.R;
import com.sm.ej.nk.homeal.viewholder.EtHomeFragmentViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class EtHomeFragmentAdapter extends RecyclerView.Adapter<EtHomeFragmentViewHolder> {

    Context context;
    Intent intent;
    List<Drawable> items = new ArrayList<>();

    public void add(Drawable foodview){
        items.add(foodview);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EtHomeFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_et_home_fragment, parent, false);
        context = parent.getContext();
        intent= new Intent(parent.getContext(), InfoCkDetailActivity.class);
        return new EtHomeFragmentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(EtHomeFragmentViewHolder holder, int position) {
        holder.setFoodView(items.get(position));

        holder.getFoodView().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.getStartView().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"You choose heart ",Toast.LENGTH_LONG).show();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.getCommentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"You choose comment ",Toast.LENGTH_LONG).show();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.getHartView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"You choose this item",Toast.LENGTH_LONG).show();
            }
        });
    }



}
