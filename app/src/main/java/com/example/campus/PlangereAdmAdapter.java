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

public class PlangereAdmAdapter extends RecyclerView.Adapter<PlangereAdmAdapter.PlangereAdmAdapterViewHolder> {

    Context context;
    List<Plangere> arrayListPlangere;

    public PlangereAdmAdapter(Context context, List<Plangere> arrayListPlangere){
        this.context=context;
        this.arrayListPlangere = arrayListPlangere;
    }

    @NonNull
    @Override
    public PlangereAdmAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plangere_item,parent, false);
        return new PlangereAdmAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlangereAdmAdapterViewHolder holder, int position) {
        Plangere plangere= arrayListPlangere.get(position);
        holder.subiectPlangereTxt.setText(plangere.getTitlu());
    }

    @Override
    public int getItemCount() {
        return arrayListPlangere.size();
    }

    public void filterList(ArrayList<Plangere> filteredList) {
        arrayListPlangere = filteredList;
        notifyDataSetChanged();
    }


    public class PlangereAdmAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView subiectPlangereTxt;

        public PlangereAdmAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            subiectPlangereTxt = itemView.findViewById(R.id.subiectPlangere);

            //itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PlangeriAdmActivity)context).openPlangereActivity(arrayListPlangere,getAdapterPosition());
                }
            });
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
