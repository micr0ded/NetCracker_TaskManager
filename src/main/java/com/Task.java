package com;

public class Task {
    private int ID;
    private String description;
    private long time;

    public Task(int taskID, String desc, long time){
        ID = taskID;
        description = desc;
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public long getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String toString(){
        String str = new String(ID + " " + description + " " + time);
        return str;
    }
}
