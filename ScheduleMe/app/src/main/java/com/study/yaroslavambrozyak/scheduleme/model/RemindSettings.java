package com.study.yaroslavambrozyak.scheduleme.model;


import io.realm.RealmObject;

public class RemindSettings extends RealmObject {

    private long id;
    private boolean musicEnable;
    private boolean vibrationEnable;

    public RemindSettings() {
    }

    public RemindSettings(long id, boolean musicEnable, boolean vibrationEnable) {
        this.id = id;
        this.musicEnable = musicEnable;
        this.vibrationEnable = vibrationEnable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isMusicEnable() {
        return musicEnable;
    }

    public void setMusicEnable(boolean musicEnable) {
        this.musicEnable = musicEnable;
    }

    public boolean isVibrationEnable() {
        return vibrationEnable;
    }

    public void setVibrationEnable(boolean vibrationEnable) {
        this.vibrationEnable = vibrationEnable;
    }
}
