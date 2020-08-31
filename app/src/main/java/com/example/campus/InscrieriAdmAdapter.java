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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InscrieriAdmAdapter extends RecyclerView.Adapter<InscrieriAdmAdapter.InscrieriAdmAdapterViewHolder> {

    Context context;
    List<Cerere> arrayListCerere;

    public InscrieriAdmAdapter(Context context, List<Cerere> arrayListCerere){
        this.context=context;
        this.arrayListCerere = arrayListCerere;
    }

    @NonNull
    @Override
    public InscrieriAdmAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cerere_item,parent, false);
        return new InscrieriAdmAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InscrieriAdmAdapterViewHolder holder, int position) {
        Cerere cerere= arrayListCerere.get(position);
        holder.userId.setText(cerere.getIdUser());

    }

    @Override
    public int getItemCount() {
        return arrayListCerere.size();
    }

    public void filterList(ArrayList<Cerere> filteredList) {
        arrayListCerere = filteredList;
        notifyDataSetChanged();
    }


    public class InscrieriAdmAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView userId;

        public InscrieriAdmAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            //itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { ((InscrieriAdmActivity)context).openMancareActivity(arrayListCerere,getAdapterPosition());
                }
            });

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
