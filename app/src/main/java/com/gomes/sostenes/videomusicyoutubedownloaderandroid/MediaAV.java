package com.gomes.sostenes.videomusicyoutubedownloaderandroid;

/**
 * Created by Sostenes on 24/07/2016.
 */
public class MediaAV {
    private String url;
    private String fileName;
    private int duration;

    public MediaAV() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
