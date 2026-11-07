//Lloyd Musau, BBIT, 227326, 8th Nov 2026

package com.musau.sufeeds;
//parent class
public class Academics {
    protected String courseName;
    protected String topic;

    public Academics(String courseName, String topic) {
        this.courseName = courseName;
        this.topic = topic;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getTopic() {
        return topic;
    }
}