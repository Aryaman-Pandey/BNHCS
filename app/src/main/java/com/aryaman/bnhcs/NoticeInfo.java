package com.aryaman.bnhcs;

public class NoticeInfo {

    public String heading;

    public String note;

    public NoticeInfo() {
    }

    public NoticeInfo(String heading, String note) {

        this.heading = heading;
        this.note= note;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHeading() {
        return this.heading;
    }

    public String getNote(){ return this.note; }

}
