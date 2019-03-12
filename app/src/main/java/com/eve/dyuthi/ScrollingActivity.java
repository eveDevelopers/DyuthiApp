package com.eve.dyuthi;


import android.app.DownloadManager;

import android.app.Activity;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;

import com.bumptech.glide.Glide;

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
    CardView day2,day3,day4;

    String event_url="http://192.168.43.183:8000/events/";
    private List<EventlistItem> itemList;
    private List<Schedule> schedules;
    //private List<Schedule> send_schedule;

    ImageView dayImage2,dayImage3,dayImage4;
    String url1 = "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwi4p-rytfzgAhVWOSsKHaTIBDcQjRx6BAgBEAU&url=https%3A%2F%2Fwww.justdial.com%2Fentertainment%2Fartist%2FGowry-Lekshmi%2FA839744&psig=AOvVaw3BRchgYeyVzfCZBefmzMly&ust=1552473809853704";
    String url2 = "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwiIwdbTtfzgAhWPfX0KHWHPDnEQjRx6BAgBEAU&url=https%3A%2F%2Fwww.oklisten.com%2Falbum%2Fsaagara_shayana_vibho&psig=AOvVaw0PGXOhvjev17FTAVyX6_az&ust=1552473763489338";
    String url3 = "https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjBwNWDtvzgAhXEe30KHYnGBaAQjRx6BAgBEAU&url=https%3A%2F%2Freacho.in%2Fpune%2Fevent%2Fkingfisher-buzz-presents-nucleya-raja-baja-album-launch&psig=AOvVaw02Se9ysrhha9_eyLaVsxPn&ust=1552473869064267";


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
        day2.setOnClickListener(this);
        day3.setOnClickListener(this);
        day4.setOnClickListener(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Glide.with(this).load(R.drawable.day2).into(dayImage2);
        Glide.with(this).load(R.drawable.agam).into(dayImage3);
        Glide.with(this).load(R.drawable.nucleya).into(dayImage4);
        TabLayout tabLayout = findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        //tabLayout.getTabAt(0).setIcon(R.drawable.dance);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.day2:
                Intent intent = new Intent(ScrollingActivity.this  , EventActivity.class);
                intent.putExtra("image_id",R.drawable.day2);
                intent.putExtra("name","Live Concert");
                intent.putExtra("coordinator_name","Philip Paul");
                intent.putExtra("coordinator_no","8281742377");
                intent.putExtra("date","15/03/19");
                intent.putExtra("venue","Main Ground,Government Engineering College, Thrissur");
                intent.putExtra("description",getString(R.string.large_text));
                ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                         dayImage2,
                        "simple_activity_transition");

                startActivity(intent , options.toBundle());
                break;
            case R.id.day3:
                Intent intent2 = new Intent(ScrollingActivity.this  , EventActivity.class);
                intent2.putExtra("image_id",R.drawable.agam);
                intent2.putExtra("name","Agam");
                intent2.putExtra("coordinator_name","Philip Paul");
                intent2.putExtra("coordinator_no","8281742377");
                intent2.putExtra("date","16/03/19");
                intent2.putExtra("venue","Main Ground,Government Engineering College, Thrissur");
                intent2.putExtra("description",getString(R.string.large_text));
                ActivityOptionsCompat options2 =ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        dayImage3,
                        "simple_activity_transition");

                startActivity(intent2 , options2.toBundle());
                break;

            case R.id.day4:

                startActivity(new Intent(getApplicationContext(),EventActivity.class));
                CollectEvent();

                Intent intent3 = new Intent(ScrollingActivity.this  , EventActivity.class);
                intent3.putExtra("image_id",R.drawable.nucleya);
                intent3.putExtra("name","Nucleya");
                intent3.putExtra("coordinator_name","Philip Paul");
                intent3.putExtra("coordinator_no","8281742377");
                intent3.putExtra("date","17/03/19");
                intent3.putExtra("venue","Main Ground,Government Engineering College, Thrissur");
                intent3.putExtra("description",getString(R.string.large_text));
                ActivityOptionsCompat options3 =ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        dayImage4,
                        "simple_activity_transition");

                startActivity(intent3 , options3.toBundle());

                break;
        }
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
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("Section: "+String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
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
            return 6;
        }
    }

    public void CollectEvent(){
        itemList= new ArrayList<>();
        schedules = new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, event_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("event_list");
                    for(int i =0;i<jsonArray.length();i++)
                    {
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.e("response",data.get("category").toString());
                        JSONArray jsonArray1 = data.getJSONArray("events");
                        for(int j=0;j<jsonArray1.length();j++){
                        JSONObject data1 =  jsonArray1.getJSONObject(j);
                        String event_name = data1.getString("event_name");
                        String event_desc = data1.getString("event_desc");
                        String coordinator_name = data1.getString("coordinator_name");
                        String coordinator_phone =  data1.getString("coordinator_phone");
                        int event_fees = data1.getInt("event_fees");
                        String category = data1.getString("category");
                        int prize = data1.getInt("prize");
                        String img_url = data1.getString("ing_url");
                        JSONArray jsonArray2 = data1.getJSONArray("schedules");
                        for(int k=0;k<jsonArray2.length();k++)
                        {
                            JSONObject data2 = jsonArray2.getJSONObject(k);
                            String round_name = data2.getString("round_name");
                            String round_time = data2.getString("round_time");
                            String round_date = data2.getString("round_date");
                            String round_venue = data2.getString("round_venue");
                            Schedule schedule = new Schedule(round_name,round_time,round_date,round_venue);
                            schedules.add(schedule);
                        }
                        EventlistItem eventlistItem = new EventlistItem(event_name,event_desc,coordinator_name,coordinator_phone,category,event_fees,prize,schedules,img_url);
                        itemList.add(eventlistItem);

                        }
                    }
                    Log.e("event_namre",itemList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("jsonerror",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("error",error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }
}
