package com.gomes.sostenes.videomusicyoutubedownloaderandroid.Downloads;

import com.gomes.sostenes.videomusicyoutubedownloaderandroid.MediaAV;

/**
 * Created by Sostenes on 24/07/2016.
 */
public class DownloadAV {

    public static int STATUS_STOPPED     = 0;
    public static int STATUS_DOWNLOADING = 1;
    public static int STATUS_PAUSED      = 2;

    private int fileTotalSize;
    private int fileDownloadedSize;
    private int progress;
    private int status;
    private MediaAV mediaAV;

    public DownloadAV(MediaAV mediaAV) {
        this.status = this.STATUS_STOPPED;
        this.progress = 0;
        this.mediaAV = mediaAV;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFileTotalSize() {
        return fileTotalSize;
    }

    public void setFileTotalSize(int fileTotalSize) {
        this.fileTotalSize = fileTotalSize;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public MediaAV getMediaAV() {
        return mediaAV;
    }

    public int getFileDownloadedSize() {
        return fileDownloadedSize;
    }

    public void setFileDownloadedSize(int fileDownloadedSize) {
        this.fileDownloadedSize = fileDownloadedSize;
    }

    public void setMediaAV(MediaAV mediaAV) {
        this.mediaAV = mediaAV;
    }
}
