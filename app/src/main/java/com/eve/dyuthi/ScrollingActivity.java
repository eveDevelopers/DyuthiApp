package com.eve.dyuthi;


import android.app.DownloadManager;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    CardView day2, day3, day4;
    ImageView dayImage2, dayImage3, dayImage4;
    TabLayout tabLayout;
    FloatingActionButton register;
    String event_url = "https://dyuthi.live/get_events/";
    private List<EventlistItem> generalitemList, musicitemList, danceitemList, literatureitemList, artitemList, otheritemList;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        dayImage2 = findViewById(R.id.dayImg2);
        dayImage3 = findViewById(R.id.dayImg3);
        dayImage4 = findViewById(R.id.dayImg4);
        progressBar = findViewById(R.id.progress_circular);
        register  = findViewById(R.id.register);
        day2.setOnClickListener(this);
        day3.setOnClickListener(this);
        day4.setOnClickListener(this);
        register.setOnClickListener(this);
        SharedPreferences i_value = getSharedPreferences("i_value", MODE_PRIVATE);
        SharedPreferences.Editor editor = i_value.edit();
        editor.putInt("i_value", 0);
        editor.commit();
        SharedPreferences sharedPreferences = getSharedPreferences("lists", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.clear();
        editor1.commit();
        generalitemList = new ArrayList<>();
        musicitemList = new ArrayList<>();
        danceitemList = new ArrayList<>();
        literatureitemList = new ArrayList<>();
        artitemList = new ArrayList<>();
        otheritemList = new ArrayList<>();
        tabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.container);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //mViewPager.setOffscreenPageLimit(0);
        Glide.with(this).load(R.drawable.gow).into(dayImage2);
        Glide.with(this).load(R.drawable.agam).into(dayImage3);
        Glide.with(this).load(R.drawable.nucleya).into(dayImage4);
        CollectEvent();
    }

    public void refresh() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        //tabLayout.getTabAt(0).setIcon(R.drawable.dance);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        Toast.makeText(getApplicationContext(),"Refreshed",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day2:
                Intent intent = new Intent(ScrollingActivity.this, EventActivity.class);
                intent.putExtra("image_id", R.drawable.gow);
                intent.putExtra("name", "GOWRY LAKSHMI");
                intent.putExtra("coordinator_name", "Contact");
                intent.putExtra("coordinator_no", "8089521307");
                intent.putExtra("date", "15 March");
                intent.putExtra("venue", "Main Ground,Government Engineering College, Thrissur");
                intent.putExtra("description", getString(R.string.gowry));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        dayImage2,
                        "simple_activity_transition");

                startActivity(intent, options.toBundle());
                break;
            case R.id.day3:
                Intent intent2 = new Intent(ScrollingActivity.this, EventActivity.class);
                intent2.putExtra("image_id", R.drawable.agam);
                intent2.putExtra("name", "AGAM NITE");
                intent2.putExtra("coordinator_name", "Contact");
                intent2.putExtra("coordinator_no", "9562466182");
                intent2.putExtra("date", "16 March");
                intent2.putExtra("venue", "Main Ground,Government Engineering College, Thrissur");
                intent2.putExtra("description", getString(R.string.agam1));
                ActivityOptionsCompat options2 = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        dayImage3,
                        "simple_activity_transition");

                startActivity(intent2, options2.toBundle());
                break;

            case R.id.day4:

                Intent intent3 = new Intent(ScrollingActivity.this, EventActivity.class);
                intent3.putExtra("image_id", R.drawable.nucleya);
                intent3.putExtra("name", "NUCLEYA");
                intent3.putExtra("coordinator_name", "Contact");
                intent3.putExtra("coordinator_no", "9562466182");
                intent3.putExtra("date", "17 March");
                intent3.putExtra("venue", "Main Ground,Government Engineering College, Thrissur");
                intent3.putExtra("description", getString(R.string.nucleya));
                ActivityOptionsCompat options3 = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        dayImage4,
                        "simple_activity_transition");

                startActivity(intent3, options3.toBundle());

                break;
            case R.id.register:
                open_tab("https://dyuthi.live/register/");
                break;
        }
    }

    public void CollectEvent() {
        generalitemList.clear();
        musicitemList.clear();
        danceitemList.clear();
        literatureitemList.clear();
        artitemList.clear();
        otheritemList.clear();
        tabLayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, event_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tabLayout.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                try {
                    Log.e("response", response);
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("event_list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);

                        JSONArray jsonArray1 = data.getJSONArray("events");
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            JSONObject data1 = jsonArray1.getJSONObject(j);
                            String event_name = data1.getString("event_name");
                            String event_desc = data1.getString("event_desc");
                            String coordinator_name = data1.getString("coordinator_name");
                            String coordinator_phone = data1.getString("coordinator_phone");
                            String event_fees = data1.getString("event_fees");
                            String category = data1.getString("category");
                            int prize;
                            try {
                                prize = data1.getInt("prize");
                            } catch (Exception e) {
                                prize = 0;
                            }
                            String img_url = data1.getString("poster");
                            img_url = "https://dyuthi.live/static/" + img_url;
                            JSONArray jsonArray2 = data1.getJSONArray("schedule");
                            List<Schedule> scheduleList = new ArrayList<>();
                            //scheduleList.clear();
                            for (int k = 0; k < jsonArray2.length(); k++) {
                                JSONObject data2 = jsonArray2.getJSONObject(k);
                                String round_name = data2.getString("round_name");
                                String round_time = data2.getString("round_time");
                                String round_date = data2.getString("round_date");
                                String round_venue = data2.getString("round_venue");
                                // Log.e("round date",round_date+round_time);
                                Schedule schedule = new Schedule(round_name, round_time, round_date, round_venue);
                                scheduleList.add(schedule);
                            }
                            EventlistItem eventlistItem = new EventlistItem(event_name, event_desc, coordinator_name, coordinator_phone, category, event_fees, prize, scheduleList, img_url);
                            category = category.toLowerCase();
                            Log.e("Cat", category);
                            if (category.equals("general")) {
                                generalitemList.add(eventlistItem);
                            } else if (category.equals("music")) {
                                musicitemList.add(eventlistItem);
                            } else if (category.equals("dance")) {
                                danceitemList.add(eventlistItem);
                            } else if (category.equals("literature")) {
                                literatureitemList.add(eventlistItem);
                            } else if (category.equals("art")) {
                                artitemList.add(eventlistItem);
                            } else if (category.equals("talk/stage/theatre")) {
                                otheritemList.add(eventlistItem);
                            }

                        }
                    }
                    Log.e("length_mus", String.valueOf(musicitemList.size()));
                    SharedPreferences sharedPreferences = getSharedPreferences("lists", MODE_PRIVATE);
                    SharedPreferences.Editor edit_list = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String object_gen = gson.toJson(generalitemList);
                    String object_mus = gson.toJson(musicitemList);
                    String object_dance = gson.toJson(danceitemList);
                    String object_lit = gson.toJson(literatureitemList);
                    String object_art = gson.toJson(artitemList);
                    String object_other = gson.toJson(otheritemList);
                    edit_list.putString("gen_list", object_gen);
                    edit_list.putString("mus_list", object_mus);
                    edit_list.putString("dance_list", object_dance);
                    edit_list.putString("lit_list", object_lit);
                    edit_list.putString("art_list", object_art);
                    edit_list.putString("other_list", object_other);
                    edit_list.commit();
                    refresh();
                    //Log.e("event_namre",itemList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("jsonerror", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tabLayout.setVisibility(View.VISIBLE);
                mViewPager.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                error.printStackTrace();
                //Log.e("error", error.toString());
                try {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        requestQueue.add(stringRequest);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        //        private List<EventlistItem> generalitemList, musicitemList, danceitemList, literatureitemList, artitemList, otheritemList;
        private List<Schedule> schedules;
        String event_url = "https://dyuthi.live/get_events/";
        private RecyclerView.Adapter adapter;
        private RecyclerView recyclerView;
        private NestedScrollView sponsors;
        private ProgressBar progressBar;
        int i;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            SharedPreferences i_value = getContext().getSharedPreferences("i_value", MODE_PRIVATE);
            i = i_value.getInt("i_value", 0);
            recyclerView = rootView.findViewById(R.id.view_list);
            progressBar = rootView.findViewById(R.id.progress_circular);
            sponsors = rootView.findViewById(R.id.sponsors);
            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            generalitemList = new ArrayList<>();
//            musicitemList = new ArrayList<>();
//            danceitemList = new ArrayList<>();
//            literatureitemList = new ArrayList<>();
//            artitemList = new ArrayList<>();
//            otheritemList = new ArrayList<>();
            schedules = new ArrayList<>();
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("lists", MODE_PRIVATE);

            Log.e("first", "working");
            Gson gson = new Gson();
            Type type = new TypeToken<List<EventlistItem>>() {
            }.getType();
            String obj_str;
            try {
                switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                    case 1:
                        recyclerView.setVisibility(View.VISIBLE);
                        sponsors.setVisibility(View.GONE);
                        obj_str = sharedPreferences.getString("gen_list", "");
                        List<EventlistItem> generalitemList = gson.fromJson(obj_str, type);
                        Log.e("jsong", generalitemList.toString());
                        adapter = new EventAdapter(getContext(), generalitemList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 2:
                        recyclerView.setVisibility(View.VISIBLE);
                        sponsors.setVisibility(View.GONE);
                        obj_str = sharedPreferences.getString("mus_list", "");
                        List<EventlistItem> musicitemList = gson.fromJson(obj_str, type);
                        Log.e("jsong", musicitemList.toString());
                        adapter = new EventAdapter(getContext(), musicitemList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 3:
                        recyclerView.setVisibility(View.VISIBLE);
                        sponsors.setVisibility(View.GONE);
                        obj_str = sharedPreferences.getString("dance_list", "");
                        List<EventlistItem> danceitemList = gson.fromJson(obj_str, type);
                        Log.e("jsong", danceitemList.toString());
                        adapter = new EventAdapter(getContext(), danceitemList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 4:
                        recyclerView.setVisibility(View.VISIBLE);
                        sponsors.setVisibility(View.GONE);
                        obj_str = sharedPreferences.getString("lit_list", "");
                        List<EventlistItem> literatureitemList = gson.fromJson(obj_str, type);
                        Log.e("jsong", literatureitemList.toString());
                        adapter = new EventAdapter(getContext(), literatureitemList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 5:
                        recyclerView.setVisibility(View.VISIBLE);
                        sponsors.setVisibility(View.GONE);
                        obj_str = sharedPreferences.getString("art_list", "");
                        List<EventlistItem> artitemList = gson.fromJson(obj_str, type);
                        Log.e("jsong", artitemList.toString());
                        adapter = new EventAdapter(getContext(), artitemList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 6:
                        recyclerView.setVisibility(View.VISIBLE);
                        sponsors.setVisibility(View.GONE);
                        obj_str = sharedPreferences.getString("other_list", "");
                        List<EventlistItem> otheritemList = gson.fromJson(obj_str, type);
                        Log.e("jsong", otheritemList.toString());
                        adapter = new EventAdapter(getContext(), otheritemList);
                        recyclerView.setAdapter(adapter);
                        break;
                    case 7:
                        recyclerView.setVisibility(View.GONE);
                        sponsors.setVisibility(View.VISIBLE);
                        break;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //textView.setText("Section: "+String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }


    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 7;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(ScrollingActivity.this, About.class));
            return true;
        } else if (id == R.id.refresh) {
            CollectEvent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void open_tab(String link) {
        Uri uri = Uri.parse(link);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setShowTitle(true);
        intentBuilder.setToolbarColor(ContextCompat.getColor(ScrollingActivity.this, R.color.normal_white));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(ScrollingActivity.this, R.color.normal_white));
        intentBuilder.setStartAnimations(ScrollingActivity.this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        intentBuilder.setExitAnimations(ScrollingActivity.this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        CustomTabsIntent customTabsIntent = intentBuilder.build();
        customTabsIntent.launchUrl(ScrollingActivity.this, uri);
    }


}
