package com.eve.dyuthi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;

import java.util.List;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView toolbar_image;
    CollapsingToolbarLayout ctb;
    int image_id;
    TextView coordinator_name,venue,description,date_time;
    String phone;
    LinearLayout coordinator_name_layout,cash;
    EventlistItem eventlistItem;
    TextView fee,prize;
    View view_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctb = findViewById(R.id.collapsingToolbar);
        toolbar_image = findViewById(R.id.toolbarImage);
        coordinator_name = findViewById(R.id.coordinator_name);
        coordinator_name_layout = findViewById(R.id.coordinator_name_layout);
        venue = findViewById(R.id.venue);
        description = findViewById(R.id.description);
        date_time = findViewById(R.id.date_time);
        cash = findViewById(R.id.cash);
        fee = findViewById(R.id.fee);
        prize = findViewById(R.id.prize);
        view_bg = findViewById(R.id.view);
        coordinator_name_layout.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setColor();
        setData();


    }

    private void setData() {
        image_id = getIntent().getIntExtra("image_id",-1);
        if(image_id!=-1){
            Glide.with(this).load(image_id).into(toolbar_image);
            getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
            coordinator_name.setText(getIntent().getStringExtra("coordinator_name"));
            date_time.setText(getIntent().getStringExtra("date"));
            venue.setText(getIntent().getStringExtra("venue"));
            description.setText(getIntent().getStringExtra("description"));
            phone = getIntent().getStringExtra("coordinator_no");
            cash.setVisibility(View.GONE);
        }else {
            Gson gson = new Gson();
            String obj = getIntent().getStringExtra("object");
            eventlistItem = gson.fromJson(obj,EventlistItem.class);
           // Glide.with(this).load(eventlistItem.getImg_url()).into(toolbar_image);
            setColor(eventlistItem.getImg_url());
            coordinator_name.setText(eventlistItem.getCoordinator_name());
            String date_t = getDateTimeString(eventlistItem);
            date_time.setText(date_t);
            String venue_t = getVenueString(eventlistItem);
            venue.setText(venue_t);
            getSupportActionBar().setTitle(eventlistItem.getEvent_name());
            phone = eventlistItem.getCoordinator_phone();
            description.setText(Html.fromHtml(eventlistItem.getEvent_desc()).toString().replaceAll("\"",""));
            fee.setText("Registration fees: "+eventlistItem.getEvent_fees());
            prize.setText("Prizes Worth: "+String.valueOf(eventlistItem.getPrize()));
        }

    }

    private String getDateTimeString(EventlistItem eventlistItem) {
        String rounds = "";
        List<Schedule> scheduleList = eventlistItem.getSchedule();
        for (int i=0;i<scheduleList.size();i++) {
            Schedule schedule = scheduleList.get(i);
            rounds+=schedule.getRound_name()+" : "+schedule.getRound_date().substring(0,schedule.getRound_date().length()-6)+","+schedule.getRound_time();
            rounds+="\n";
        }
        return rounds.trim();
    }
    private String getVenueString(EventlistItem eventlistItem) {
        String rounds = "";
        List<Schedule> scheduleList = eventlistItem.getSchedule();
        for (int i=0;i<scheduleList.size();i++) {
            Schedule schedule = scheduleList.get(i);
            rounds+=schedule.getRound_name()+" : "+schedule.getRound_venue();
            rounds+="\n";
        }
        return rounds.trim();
    }

    void callCoordinator(String phone){
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + phone.trim());
            intent.setData(uri);
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void setColor(String img_url){
        Glide.with(this).asBitmap().
                load(img_url).
                into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        toolbar_image.setImageBitmap(resource);
                        toolbar_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Palette.from(resource)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(@Nullable Palette palette) {
                                        Palette.Swatch textSwatch = palette.getVibrantSwatch();
                                        GradientDrawable gd;
                                        if (textSwatch == null) {
                                            gd = new GradientDrawable(
                                                    GradientDrawable.Orientation.BOTTOM_TOP,
                                                    new int[]{0xff000000, 0x00000000});
                                        }else {
                                            gd = new GradientDrawable(
                                                    GradientDrawable.Orientation.BOTTOM_TOP,
                                                    new int[]{textSwatch.getRgb(), 0x00000000}

                                            );
                                        }
                                        view_bg.setBackground(gd);
                                        Window window = getWindow();
//
                                        // clear FLAG_TRANSLUCENT_STATUS flag:
                                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                                        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
                                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

                                        // finally change the color
                                        if (textSwatch!= null) {
                                            window.setStatusBarColor(textSwatch.getRgb());
                                            ctb.setStatusBarScrimColor(textSwatch.getRgb());
                                            ctb.setContentScrimColor(textSwatch.getRgb());
                                        }else{
                                            int black = Color.rgb(0,0,0);
                                            window.setStatusBarColor(black);
                                            ctb.setStatusBarScrimColor(black);
                                            ctb.setContentScrimColor(black);
                                        }

                                    }
                                });
                    }
                });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.coordinator_name_layout:
                    callCoordinator(phone);
                    break;
            }
    }
}
