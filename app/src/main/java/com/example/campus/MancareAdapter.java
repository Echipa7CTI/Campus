package com.example.campus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class MancareAdapter extends RecyclerView.Adapter<MancareAdapter.MancareAdapterViewHolder> {

    Context context;
    List<Mancare> arrayListMancare;

    public MancareAdapter(Context context, List<Mancare> arrayListMancare){
        this.context=context;
        this.arrayListMancare = arrayListMancare;
    }

    @NonNull
    @Override
    public MancareAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mancare_item,parent, false);
        return new MancareAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MancareAdapterViewHolder holder, int position) {
        Mancare mancare= arrayListMancare.get(position);
        holder.numeTxt.setText(mancare.getNumeMancare());
        holder.tipTxt.setText(mancare.getTipMancare());
        holder.pretTxt.setText(mancare.getPretMancare()+" RON");

    }

    @Override
    public int getItemCount() {
        return arrayListMancare.size();
    }


    public class MancareAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView numeTxt, tipTxt, pretTxt;

        public MancareAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            numeTxt = itemView.findViewById(R.id.numeMancareItem);
            tipTxt = itemView.findViewById(R.id.tipMancareItem);
            pretTxt = itemView.findViewById(R.id.pretMancareItem);
            //itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CantinaAdmActivity)context).openMancareActivity(arrayListMancare,getAdapterPosition());
                }
            });

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
