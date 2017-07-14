package com.crest.recyclerviewclick;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by brittany on 7/8/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<Integer> integerList;
    List<String> stringsList;
    MainActivity.myInterface myIntFace;
    int selected;

    public CustomAdapter(MainActivity mainActivity, List<Integer> imgList, List<String> stringList, MainActivity.myInterface myInterface) {
        context = mainActivity;
        integerList = imgList;
        stringsList = stringList;
        myIntFace = myInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycle_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.imgView.setImageResource(integerList.get(position));
        holder.textView.setText(stringsList.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myIntFace.count(position);
                selected = position;
                notifyDataSetChanged();
            }
        });
        if (selected == position)
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
        else
            holder.layout.setBackgroundColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return integerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView textView;
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
            textView = (TextView) itemView.findViewById(R.id.txtPosition);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
        }
    }
}
