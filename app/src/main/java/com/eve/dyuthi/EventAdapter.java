package com.eve.dyuthi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    Context context;
    private List<EventlistItem> eventlistItemList;

    public EventAdapter (Context context, List<EventlistItem> eventlistItemList){
        this.context = context;
        this.eventlistItemList = eventlistItemList;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EventAdapter.ViewHolder holder, final int i) {
        final EventlistItem ev = eventlistItemList.get(i);
        Glide.with(context).load(ev.getImg_url()).into(holder.imageView);
        holder.Name.setText(ev.getEvent_name());
        String date_time = ev.getSchedule().get(0).getRound_time()+","+ev.getSchedule().get(0).getRound_date();
        holder.date.setText(date_time);
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context ,EventActivity.class);

                //passing contents
                Gson gson = new Gson();
                intent.putExtra("object" , gson.toJson(ev));

                ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context,
                        holder.imageView ,
                        "simple_activity_transition");

                context.startActivity(intent , options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventlistItemList.size();
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public int getItemViewType(int position){
        return position;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView Name,date;
        private ImageView imageView;
        private CardView cardItem;
        public ViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.event_bg);
            cardItem = itemView.findViewById(R.id.card_item);
        }

    }
}
