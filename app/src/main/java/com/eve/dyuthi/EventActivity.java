package com.eve.dyuthi;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class EventActivity extends AppCompatActivity {


    ImageView toolbar_image;
    CollapsingToolbarLayout ctb;
    int image_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ctb = findViewById(R.id.collapsingToolbar);
        toolbar_image = findViewById(R.id.toolbarImage);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setColor();
        setImagePro();

    }

    private void setImagePro() {
        image_id = getIntent().getIntExtra("image_id",0);
        if(image_id!=0){
            Glide.with(this).load(image_id).into(toolbar_image);
            getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        }

    }

    public void setColor(){
        Glide.with(this).asBitmap().
                load(R.drawable.agam).
                into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        toolbar_image.setImageBitmap(resource);
                        toolbar_image.setScaleType(ImageView.ScaleType.CENTER);
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
                                        toolbar_image.setBackground(gd);
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
}
