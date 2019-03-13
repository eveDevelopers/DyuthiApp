package com.eve.dyuthi;

import android.os.Build;
import android.text.Html;

import java.util.List;

public class EventlistItem {

    private String event_name,event_desc,coordinator_name,coordinator_phone,category,event_fees;
    private  int prize;
    private List<Schedule> schedule;
    private String img_url;


    public EventlistItem(String event_name, String event_desc, String coordinator_name, String coordinator_phone, String category, String event_fees, int prize, List<Schedule> schedule, String img_url) {
        this.event_name = event_name;
        this.event_desc = event_desc;
        this.coordinator_name = coordinator_name;
        this.coordinator_phone = coordinator_phone;
        this.category = category;
        this.event_fees = event_fees;
        this.prize = prize;
        this.schedule = schedule;
        this.img_url=img_url;
    }


    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public String getCoordinator_name() {
        return coordinator_name;
    }

    public String getCoordinator_phone() {
        return coordinator_phone;
    }

    public String getCategory() {
        return category;
    }

    public String getEvent_fees() {
        return event_fees;
    }

    public int getPrize() {
        return prize;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public String getImg_url() {
        return img_url;
    }
    public String getRenderedDescription() {
        if ( this.event_desc.startsWith("\"")){
            this.event_desc = this.event_desc.substring(1);
        }
        if ( this.event_desc.endsWith("\\")){
            this.event_desc = this.event_desc.substring(0, this.event_desc.length()-1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(this.event_desc, Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            return Html.fromHtml(this.event_desc).toString();
        }
    }
}
