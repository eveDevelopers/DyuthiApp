 package com.eve.dyuthi;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

 public class About extends AppCompatActivity {


    ImageView logo;
    TextView visit_web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        logo = findViewById(R.id.logo);
        Glide.with(About.this).load(R.drawable.dyuthi_logo).into(logo);
        visit_web = findViewById(R.id.visit_website);
        visit_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://techfest.ktu.edu.in/");
                CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
                intentBuilder.setShowTitle(true);
                intentBuilder.setToolbarColor(ContextCompat.getColor(About.this, R.color.normal_white));
                intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(About.this, R.color.normal_white));
                intentBuilder.setStartAnimations(About.this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                intentBuilder.setExitAnimations(About.this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                CustomTabsIntent customTabsIntent = intentBuilder.build();
                customTabsIntent.launchUrl(About.this, uri);
            }
        });
    }
}
