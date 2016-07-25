package com.gomes.sostenes.videomusicyoutubedownloaderandroid.Downloads;

import android.content.Context;
import android.os.Environment;

import com.aspsine.multithreaddownload.CallBack;
import com.aspsine.multithreaddownload.DownloadConfiguration;
import com.aspsine.multithreaddownload.DownloadException;
import com.aspsine.multithreaddownload.DownloadInfo;
import com.aspsine.multithreaddownload.DownloadManager;
import com.aspsine.multithreaddownload.DownloadRequest;
import com.gomes.sostenes.videomusicyoutubedownloaderandroid.MediaAV;
import com.gomes.sostenes.videomusicyoutubedownloaderandroid.Downloads.DownloadAV;

import java.io.File;

/**
 * Created by Sostenes on 24/07/2016.
 */
public class DownloadManagerAV{

    //private List<DownloadAV> downloadAVList;
    private DownloadManager downloadManager;
    private File defaultDownloadFolder;//MUDAR PELAS CONFIGURAÇÕES

    public DownloadManagerAV(Context context) {
        //this.downloadAVList = new ArrayList<>();

        downloadManager = DownloadManager.getInstance();
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        this.downloadManager.init(context, configuration);
    }

    public void newDownload(String videoUrl){
        //this.downloadAVList.add(new DownloadAV(mediaAV));

        final MediaAV mediaAV = DonwloadInfoAV.getDownloadMedia(videoUrl, DonwloadInfoAV.FORMAT_MP3);//

        this.defaultDownloadFolder = new File(Environment.getExternalStorageDirectory() + "/Download/");

        if(mediaAV != null) {

            DownloadRequest downloadRequest = new DownloadRequest.Builder()
                    .setTitle(mediaAV.getFileName())
                    .setUri(mediaAV.getUrl())
                    .setFolder(this.defaultDownloadFolder)
                    .build();

            this.downloadManager.getInstance().download(downloadRequest, mediaAV.getUrl(), new CallBack() {

                int notificationID = mediaAV.getUrl().hashCode();//downloadAVList.size();//CRIAR NOTIFICACAO

                @Override
                public void onStarted() {
                    //NOTIFICACAO DE INICIO
                    System.out.println(">>> DOWNLOAD INICIADO");

                }

                @Override
                public void onConnecting() {

                }

                @Override
                public void onConnected(long total, boolean isRangeSupport) {

                }

                @Override
                public void onProgress(long finished, long total, int progress) {
                    //NOTIFICACAO DE PROGRESSO
                    System.out.println(">>> BAIXANDO " + mediaAV.getFileName() + " :: " +  progress + " %");

                }

                @Override
                public void onCompleted() {
                    //NOTIFICACAO DE COMPLETUDE

                    System.out.println(">>> COMPLETO");

                }

                @Override
                public void onDownloadPaused() {
                    //NOTIFICACAO DE ERRO
                }

                @Override
                public void onDownloadCanceled() {

                }

                @Override
                public void onFailed(DownloadException e) {
                    System.out.println(">>> ERRO " + e.getErrorMessage());
                }
            });
        }
    }

    public void pauseDownload(String tag){
        DownloadManager.getInstance().pause(tag);
    }

    public void continueDownload(int id){
    }


    public void cancelDownload(String tag){
        DownloadManager.getInstance().cancel(tag);
    }

    public void pauseAllDownloads(){
        DownloadManager.getInstance().pauseAll();
    }

    public void contiueAllDownloads(){

    }

    public void cancelAllDownloads(){
        DownloadManager.getInstance().cancelAll();
    }
}
