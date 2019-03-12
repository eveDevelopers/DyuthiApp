package com.eve.dyuthi;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day2.setOnClickListener(this);
        day3.setOnClickListener(this);
        day4.setOnClickListener(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


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
            case R.id.day3:
            case R.id.day4:
                startActivity(new Intent(getApplicationContext(),EventActivity.class));
                CollectEvent();
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
