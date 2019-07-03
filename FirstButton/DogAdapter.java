package com.elanco.elancoapps.FirstButton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elanco.elancoapps.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.viewHolder> {

ArrayList<Dog>alldogs=new ArrayList<>();
Context context;
OnDogClickListener onDogClickListener;

    public DogAdapter(ArrayList<Dog> alldogs, Context context,OnDogClickListener onDogClickListener) {
        this.alldogs = alldogs;
        this.context = context;
        this.onDogClickListener=onDogClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doglistitem,parent,false);
        return new viewHolder(view,onDogClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Dog current =alldogs.get(position);
        holder.dogname.setText(current.getDog_name());
        // image is empty
        if (current.getDog_imagepath().equals("NULL")){
           holder.dogimage.setVisibility(View.GONE);
        }
        else{
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.cardbackground));
            Picasso.with(context).load(current.getDog_imagepath())
                    .fit()
                    .into(holder.dogimage);
        }
        // if not added any dog
        if (current.dog_name.equals(context.getResources().getString(R.string.unregistred))){
            holder.plusimage.setVisibility(View.VISIBLE);
        }
        else{
            holder.plusimage.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        if (alldogs.isEmpty())
            return 0;
        else
            return alldogs.size();
    }

    public class  viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dogname;
        ImageView plusimage,dogimage;
        OnDogClickListener onDogClickListener;
        CardView cardView;

        public viewHolder(@NonNull View itemView,OnDogClickListener onDogClickListener) {
            super(itemView);
            dogname=itemView.findViewById(R.id.dogname);
            plusimage=itemView.findViewById(R.id.plusbutton);
            dogimage=itemView.findViewById(R.id.dogimageview);
            this.onDogClickListener=onDogClickListener;
            itemView.setOnClickListener(this);
            cardView=itemView.findViewById(R.id.cardoflist);

        }

        @Override
        public void onClick(View view) {
            onDogClickListener.OnDogClick(getAdapterPosition());
        }
    }

    public  interface OnDogClickListener{
        void OnDogClick(int position);
    }


}
