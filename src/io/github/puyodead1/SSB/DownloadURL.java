package io.github.puyodead1.SSB;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;

public class DownloadURL {
	public static boolean Download(String url, String output) {
        try {
            downloadUsingStream(url, output);
            return true;
        } catch (UnknownHostException e) {
        	SSB.Log("[ERROR]: Unknown Host, please make sure you are connected to the internet and try again.");
            return false;
        } catch(IOException e) {
        	SSB.Log("[ERROR]: Unknown Error Occured!");
        	return false;
        }
	}

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}
