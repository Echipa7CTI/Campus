package com.example.campus;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class AnuntUserAdapter extends RecyclerView.Adapter<AnuntUserAdapter.AnuntUserAdapterViewHolder> {

    AnunturiFragment cast;
    Context context;
    List<Anunt> arrayListAnunt;

    public AnuntUserAdapter(Context context, List<Anunt> arrayListAnunt, AnunturiFragment cast){
        this.context=context;
        this.arrayListAnunt = arrayListAnunt;
        this.cast = cast;
    }

    @NonNull
    @Override
    public AnuntUserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.anunt_item,parent, false);
        return new AnuntUserAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AnuntUserAdapterViewHolder holder, int position) {
        final Anunt anunt= arrayListAnunt.get(position);
        holder.textAnuntTxt.setText(anunt.getTextAnunt());
        holder.titluAnuntTxt.setText(anunt.getTitluAnunt());
        holder.dataAnuntTxt.setText(anunt.getData());
    }

    @Override
    public int getItemCount() {
        return arrayListAnunt.size();
    }


    public class AnuntUserAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView textAnuntTxt, titluAnuntTxt, dataAnuntTxt;
        CardView card;

        public AnuntUserAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textAnuntTxt = itemView.findViewById(R.id.textAnuntItem);
            titluAnuntTxt = itemView.findViewById(R.id.titluAnuntItem);
            dataAnuntTxt = itemView.findViewById(R.id.dataAnuntItem);
            card = itemView.findViewById(R.id.cardView);
            //itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    (cast).openMancareActivity(arrayListAnunt,getAdapterPosition());
                }
            });

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
