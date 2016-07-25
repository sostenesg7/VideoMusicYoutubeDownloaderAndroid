package com.gomes.sostenes.videomusicyoutubedownloaderandroid.Downloads;

import com.gomes.sostenes.videomusicyoutubedownloaderandroid.MediaAV;
import com.gomes.sostenes.videomusicyoutubedownloaderandroid.NotificationAV;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.Scanner;

/**
 * Created by Sostenes on 25/07/2016.
 */
public class DonwloadInfoAV {

	public static int FORMAT_MP3 = 0;
	public static int FORMAT_MP4 = 1;

    public DonwloadInfoAV(){}

    public static MediaAV getDownloadMedia(String videoUrl, int format){

		String requestUrl = "";
        MediaAV downloadMedia = null;
        HttpURLConnection connection = null;
		
		if(format == DonwloadInfoAV.FORMAT_MP3){
			requestUrl = "http://www.youtubeinmp3.com/download/?video=" + videoUrl + "&autostart=1";
		}else if(format == DonwloadInfoAV.FORMAT_MP4){
			//requestUrl = "http://www.youtubeinmp3.com/download/?video=" + videoUrl + "&autostart=1";
		}else{
			return null;
		}

        try {
            connection = (HttpURLConnection) new URL(requestUrl).openConnection();
            connection.connect();
            if(connection.getResponseMessage().equalsIgnoreCase("OK")){

                System.out.println("RESPOSTA OK");

                String downloadUrl = "";
                StringBuffer bufferBody = new StringBuffer();
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNext()){
                    bufferBody.append(scanner.next());
                }

				if(format == DonwloadInfoAV.FORMAT_MP3){
					downloadUrl = getMp3DownloadUrl(bufferBody.toString());
				}
				
                System.out.println(downloadUrl);

                downloadMedia = getMedia(downloadUrl);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                connection.disconnect();
            }catch (Exception ex){}

        }
        return downloadMedia;
    }


	private static String getMp3DownloadUrl(String body){

        String url = null;

        try {
            int start = body.toString().indexOf("/download/get/?i=");
            String link = body.toString().substring(start, start + 200);
            url = "http://www.youtubeinmp3.com" + link.substring(0, link.indexOf("\"><i"));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return url;
	}

    private static MediaAV getMedia(String url){
        HttpURLConnection connection = null;
        String fileName = "";

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();

            NotificationAV.getInstance().newNotification(2, "Convertendo", "Aguarde");

            while (connection.getContentType().equals("text/html")) {
                System.out.println(">>> TEXTO");
                connection.disconnect();
                connection = (HttpURLConnection) new URL(url).openConnection();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {}
            }

            fileName = connection.getHeaderField("Content-Disposition");
            fileName = fileName.substring(fileName.indexOf("\"")+1, fileName.lastIndexOf("\""));

            fileName = Normalizer
                    .normalize(fileName, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            try {
                connection.disconnect();
            }catch (Exception ex){}

        }
        return new MediaAV(url, fileName);
    }

}
