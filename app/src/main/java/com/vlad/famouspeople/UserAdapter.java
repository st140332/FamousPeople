package com.vlad.famouspeople;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.createDeviceProtectedStorageContext;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Vlad on 20.03.2018.
 */

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<User> users;
    private  Context context;
    private UserAdapterListener mListener;

    public interface UserAdapterListener {
        void onClickAtOKButton(int position,boolean PlusPoint);
    }

    public UserAdapter(List<User> users, UserAdapterListener mListener) {
        this.users = users;
        this.mListener = mListener;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserAdapter.ViewHolder holder, final int position) {
        holder.firstName.setText(users.get(position).getFirstName());
        holder.lastName.setText(users.get(position).getLastName());
        holder.email.setText(users.get(position).getEmail());
        holder.points.setText(Integer.toString(users.get(position).getPoints()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateUser.class);
                intent.putExtra("FName", users.get(position).getFirstName());
                intent.putExtra("LName", users.get(position).getLastName());
                intent.putExtra("Email", users.get(position).getEmail());
                intent.putExtra("Id",users.get(position).getId());
                context.startActivity(intent);
            }
        });

        final AppDatabase db = App.getInstance().getDatabase();
        holder.plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mListener.onClickAtOKButton(position,true);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickAtOKButton(position,false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;
        public TextView lastName;
        public TextView email;
        public TextView points;
        public Button plus;
        public Button minus;

        public RelativeLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            firstName = (TextView) itemView.findViewById(R.id.first_name);
            lastName = (TextView) itemView.findViewById(R.id.last_name);
            email = (TextView) itemView.findViewById(R.id.email);
            points = (TextView) itemView.findViewById(R.id.points);
            linearLayout = (RelativeLayout) itemView.findViewById(R.id.linearLayout);
            plus = (Button)itemView.findViewById(R.id.plus);
            minus = (Button)itemView.findViewById(R.id.minus);
        }
    }

}
