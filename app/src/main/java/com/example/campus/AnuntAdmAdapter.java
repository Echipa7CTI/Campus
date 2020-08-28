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

public class AnuntAdmAdapter extends RecyclerView.Adapter<AnuntAdmAdapter.AnuntAdmAdapterViewHolder> {

    Context context;
    List<Anunt> arrayListAnunt;

    public AnuntAdmAdapter(Context context, List<Anunt> arrayListAnunt){
        this.context=context;
        this.arrayListAnunt = arrayListAnunt;
    }

    @NonNull
    @Override
    public AnuntAdmAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.anunt_item,parent, false);
        return new AnuntAdmAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnuntAdmAdapterViewHolder holder, int position) {
        Anunt anunt= arrayListAnunt.get(position);
        holder.textAnuntTxt.setText(anunt.getTextAnunt());
        holder.titluAnuntTxt.setText(anunt.getTitluAnunt());

    }

    @Override
    public int getItemCount() {
        return arrayListAnunt.size();
    }


    public class AnuntAdmAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView textAnuntTxt, titluAnuntTxt;

        public AnuntAdmAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textAnuntTxt = itemView.findViewById(R.id.textAnuntItem);
            titluAnuntTxt = itemView.findViewById(R.id.titluAnuntItem);
            //itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { ((AnunturiAdmActivity)context).openMancareActivity(arrayListAnunt,getAdapterPosition());
                }
            });

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
