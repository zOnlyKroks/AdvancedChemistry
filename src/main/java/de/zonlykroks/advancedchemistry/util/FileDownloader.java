package de.zonlykroks.advancedchemistry.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class FileDownloader {

    private static boolean isRedirected( Map<String, List<String>> header ) {
        for( String hv : header.get( null )) {
            if(   hv.contains( " 301 " )
                    || hv.contains( " 302 " )) return true;
        }
        return false;
    }
    public static void downloadAndWrite(String link, File file) throws IOException {
        file.createNewFile();
        URL url  = new URL( link );
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        Map< String, List< String >> header = http.getHeaderFields();
        while( isRedirected( header )) {
            link = header.get( "Location" ).get( 0 );
            url    = new URL( link );
            http   = (HttpURLConnection)url.openConnection();
            header = http.getHeaderFields();
        }
        InputStream input  = http.getInputStream();
        byte[]       buffer = new byte[4096];
        int          n      = -1;
        OutputStream output = new FileOutputStream(file);
        while ((n = input.read(buffer)) != -1) {
            output.write( buffer, 0, n );
        }
        output.close();
    }

}
