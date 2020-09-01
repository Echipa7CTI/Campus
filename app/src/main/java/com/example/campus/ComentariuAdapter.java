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

public class ComentariuAdapter extends RecyclerView.Adapter<ComentariuAdapter.ComentariuAdapterViewHolder> {

    Context context;
    List<Comentariu> arrayListComentariu;

    public ComentariuAdapter(Context context, List<Comentariu> arrayListComentariu){
        this.context=context;
        this.arrayListComentariu = arrayListComentariu;
    }

    @NonNull
    @Override
    public ComentariuAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comentariu_item,parent, false);
        return new ComentariuAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentariuAdapterViewHolder holder, int position) {
        Comentariu comentariu= arrayListComentariu.get(position);
        holder.idComentariuTxt.setText(comentariu.getMailComentariu()+": ");
        holder.textComentariuTxt.setText(comentariu.getTextComentariu());

    }

    @Override
    public int getItemCount() {
        return arrayListComentariu.size();
    }


    public class ComentariuAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView idComentariuTxt, textComentariuTxt;

        public ComentariuAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            idComentariuTxt = itemView.findViewById(R.id.idUser);
            textComentariuTxt = itemView.findViewById(R.id.textComentariu);
            //itemView.setOnLongClickListener(this);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { ((AnunturiAdmActivity)context).openMancareActivity(arrayListAnunt,getAdapterPosition());
                }
            });*/

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
