package com.eve.dyuthi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private List<EventlistItem> eventlistItemList;

    public EventAdapter (Context context, List<EventlistItem> eventlistItemList){
        this.context = context;
        this.eventlistItemList=eventlistItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event,parent,false);
        return new ViewHolder(v,context,eventlistItemList);
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder,final int i) {

        final EventlistItem eventlistItem = eventlistItemList.get(i);

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
        private TextView textView;
        public ViewHolder(View itemView , Context context, List<EventlistItem> eventlistItems) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
        }

        private void bind(EventlistItem eventlistItem){
            textView.setText(eventlistItem.getEvent_name());

        }
    }
}
