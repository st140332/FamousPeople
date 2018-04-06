package com.vlad.famouspeople;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vlad on 06.04.2018.
 */

 class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> {
    List<Point> points;
    private Context context;
     public PointAdapter(List<Point> points)
     {
         this.points=points;
     }

    @Override
    public PointAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.point_row,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PointAdapter.ViewHolder holder, int position) {
        holder.amount.setText(Integer.toString(points.get(position).getCount()));
        holder.comment.setText(points.get(position).getComment());
        holder.date.setText(points.get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView amount;
        public TextView comment;
        public TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            amount = (TextView) itemView.findViewById(R.id.amount);
            comment = (TextView) itemView.findViewById(R.id.comment);
            date = (TextView) itemView.findViewById(R.id.date);

        }
    }
}
