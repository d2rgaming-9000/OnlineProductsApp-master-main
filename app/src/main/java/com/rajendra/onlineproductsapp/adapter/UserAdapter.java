package com.rajendra.onlineproductsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.onlineproductsapp.ItemView;
import com.rajendra.onlineproductsapp.ItemsActivity;
import com.rajendra.onlineproductsapp.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList usrid, usrf1, usrf2, usremail, usrpass;

    public UserAdapter(Activity activity, Context context, ArrayList usrid,
                         ArrayList usrf1, ArrayList usrf2,
                         ArrayList usremail, ArrayList usrpass){
        this.activity = activity;
        this.context = context;
        this.usrid = usrid;
        this.usrf1 = usrf1;
        this.usrf2 = usrf2;
        this.usremail = usremail;
        this.usrpass = usrpass;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.usr_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.usrid_txt.setText(String.valueOf(usrid.get(position)));
        holder.usrf1_txt.setText(String.valueOf(usrf1.get(position)));
        holder.usrf2_txt.setText(String.valueOf(usrf2.get(position)));
        holder.email_txt.setText(String.valueOf(usremail.get(position)));
        holder.pass_txt.setText(String.valueOf(usrpass.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemView.class);
                intent.putExtra("id", String.valueOf(usrid.get(position)));
                intent.putExtra("first name", String.valueOf(usrf1.get(position)));
                intent.putExtra("last name", String.valueOf(usrf2.get(position)));
                intent.putExtra("email", String.valueOf(usremail.get(position)));
                intent.putExtra("pass", String.valueOf(usrpass.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return usrid.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView usrid_txt, usrf1_txt, usrf2_txt, email_txt, pass_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View userView) {
            super(userView);
            usrid_txt = itemView.findViewById(R.id.usrid_txt);
            usrf1_txt = itemView.findViewById(R.id.usrf1_txt);
            usrf2_txt = itemView.findViewById(R.id.usrf2_txt);
            email_txt = itemView.findViewById(R.id.email_txt);
            pass_txt =  itemView.findViewById(R.id.pass_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            /* //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim); */
        }

    }

}
