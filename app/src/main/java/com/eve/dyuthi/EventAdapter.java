package com.eve.dyuthi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    Context context;
    private List<EventlistItem> eventlistItemList;

    public EventAdapter (Context context, List<EventlistItem> eventlistItemList){
        this.context = context;
        this.eventlistItemList = eventlistItemList;
//        Log.e("item_count",String.valueOf(this.eventlistItemList.size()));
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EventAdapter.ViewHolder holder, final int i) {
        final EventlistItem ev = eventlistItemList.get(i);
        //Glide.with(context).load(ev.getImg_url()).into(holder.imageView);
        holder.Name.setText(ev.getEvent_name());
        String date_time;
        try {
            date_time = ev.getSchedule().get(0).getRound_time()+", "+ev.getSchedule().get(0).getRound_date();
            Log.e("date_time",date_time);
        }catch (Exception e){
            date_time = "";
        }

        setDominantColor(holder,-1,ev.getImg_url());
        holder.date.setText(date_time.substring(0,date_time.length()-6));
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

    public void setDominantColor(final EventAdapter.ViewHolder holder, final int dir, String img_url){
        Glide.with(context).asBitmap()
                .load(img_url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bmp, @Nullable Transition<? super Bitmap> transition) {
                        holder.imageView.setImageBitmap(bmp);
                        Palette.from(bmp)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(@Nullable Palette palette) {
                                        Palette.Swatch textSwatch = palette.getVibrantSwatch();
                                        if (textSwatch == null) {
//                                            Toast.makeText(mContext, "Null swatch :(", Toast.LENGTH_SHORT).show();
                                            Log.e("null swatch", "swatch is null");
                                            return;
                                        }
                                        GradientDrawable gd;
                                        if(dir == 1) {
                                            gd = new GradientDrawable(
                                                    GradientDrawable.Orientation.RIGHT_LEFT,
                                                    new int[]{textSwatch.getRgb(), 0x00000000}

                                            );
                                            holder.row_sec_l.setBackground(gd);
                                        }else{
                                            gd = new GradientDrawable(
                                                    GradientDrawable.Orientation.BOTTOM_TOP,
                                                    new int[]{textSwatch.getRgb(),0x00000000, 0x00000000}

                                            );
                                            holder.row_sec_l.setBackground(gd);
                                        }

                                    }
                                });
                    }
                });
//        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.events);
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
        private LinearLayout row_sec_l;
        public ViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.event_bg);
            cardItem = itemView.findViewById(R.id.card_item);
            row_sec_l = itemView.findViewById(R.id.row_sec_l);
        }

    }
}
