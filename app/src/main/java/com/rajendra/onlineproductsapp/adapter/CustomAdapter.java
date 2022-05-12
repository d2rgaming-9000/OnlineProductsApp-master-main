package com.rajendra.onlineproductsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.onlineproductsapp.ItemView;
import com.rajendra.onlineproductsapp.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList product_id, product_type, product_specifier, product_range, prod_qty, prod_img;

    public CustomAdapter(Activity activity, Context context, ArrayList product_id,
                         ArrayList product_type, ArrayList product_specifier,
                         ArrayList product_range, ArrayList prod_qty){
        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.product_type = product_type;
        this.product_specifier = product_specifier;
        this.product_range = product_range;
        this.prod_qty = prod_qty;
        //this.prod_img = prod_img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.product_type_txt.setText(String.valueOf(product_type.get(position)));
        holder.product_specifier_txt.setText(String.valueOf(product_specifier.get(position)));
        holder.product_range_txt.setText(String.valueOf(product_range.get(position)));
        holder.product_qty_txt.setText(String.valueOf(prod_qty.get(position)));
        //holder.product_img_src.setImageResource((Integer) prod_img.get(position));


        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemView.class);
                intent.putExtra("id", String.valueOf(product_id.get(position)));
                intent.putExtra("type", String.valueOf(product_type.get(position)));
                intent.putExtra("specifier", String.valueOf(product_specifier.get(position)));
                intent.putExtra("range", String.valueOf(product_range.get(position)));
                intent.putExtra("prod_qty", String.valueOf(prod_qty.get(position)));
          //      intent.putExtra("prod_img", String.valueOf(prod_img.get(position)));


                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView product_img_src;
        TextView product_id_txt, product_type_txt, product_specifier_txt, product_range_txt, product_qty_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_type_txt = itemView.findViewById(R.id.product_type_txt);
            product_specifier_txt = itemView.findViewById(R.id.product_specifier_txt);
            product_range_txt = itemView.findViewById(R.id.product_ranges_txt);
            product_qty_txt = itemView.findViewById(R.id.product_qty_txt);
            //product_img_src = itemView.findViewById(R.id.product_img_src);


            mainLayout = itemView.findViewById(R.id.mainLayout);
            /* //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim); */
        }

    }

}
