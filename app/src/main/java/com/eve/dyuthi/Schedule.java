package com.eve.dyuthi;

public class Schedule {

    private String round_name,round_time,round_date,round_venue;


    public Schedule(String round_name, String round_time, String round_date, String round_venue) {
        this.round_name = round_name;
        this.round_time = round_time;
        this.round_date = round_date;
        this.round_venue = round_venue;
    }


    public String getRound_name() {
        return round_name;
    }

    public String getRound_time() {
        return round_time;
    }

    public String getRound_date() {
        return round_date;
    }

    public String getRound_venue() {
        return round_venue;
    }
}
