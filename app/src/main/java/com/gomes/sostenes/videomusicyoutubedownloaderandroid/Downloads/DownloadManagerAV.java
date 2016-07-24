package com.gomes.sostenes.videomusicyoutubedownloaderandroid.Downloads;

import com.gomes.sostenes.videomusicyoutubedownloaderandroid.MediaAV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sostenes on 24/07/2016.
 */
public class DownloadManagerAV {

    private List<DownloadAV> downloadAVList;

    public DownloadManagerAV() {
        this.downloadAVList = new ArrayList<>();
    }

    public void newDownload(MediaAV mediaAV){
        int id = downloadAVList.size();
        this.downloadAVList.add(new DownloadAV(mediaAV));
    }

    public void pauseDownload(int id){
        DownloadAV downloadAV = this.downloadAVList.get(id);
        if( downloadAV != null){
            downloadAV.setStatus(DownloadAV.STATUS_PAUSED);
        }
    }

    public void continueDownload(int id){
        DownloadAV downloadAV = this.downloadAVList.get(id);
        if( downloadAV != null){
            downloadAV.setStatus(DownloadAV.STATUS_DOWNLOADING);
        }
    }

    public void stopDownload(int id){
        DownloadAV downloadAV = this.downloadAVList.get(id);
        if( downloadAV != null){
            downloadAV.setStatus(DownloadAV.STATUS_STOPPED);
        }
    }

    public void removeDownload(int id){
        DownloadAV downloadAV = this.downloadAVList.get(id);
        if( downloadAV != null){
            downloadAV.setStatus(DownloadAV.STATUS_STOPPED);
            this.downloadAVList.remove(id);
        }
    }

    public void pauseAllDownloads(){
        for (DownloadAV downloadAV : this.downloadAVList) {
            if( downloadAV != null){
                downloadAV.setStatus(DownloadAV.STATUS_PAUSED);
            }
        }
    }

    public void contiueAllDownloads(){
        for (DownloadAV downloadAV : this.downloadAVList) {
            if( downloadAV != null){
                downloadAV.setStatus(DownloadAV.STATUS_DOWNLOADING);
            }
        }
    }

    public void stopAllDownloads(){
        for (DownloadAV downloadAV : this.downloadAVList) {
            if( downloadAV != null){
                downloadAV.setStatus(DownloadAV.STATUS_STOPPED);
            }
        }
    }

    public void removeAllDownloads(){
        this.downloadAVList.removeAll(this.downloadAVList);
    }
}
