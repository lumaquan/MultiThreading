package com.lumaquan.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    private static List<String> urls = new ArrayList<>();

    static {
        urls.add("http://dummy.restapiexample.com/api/v1/employees");
        urls.add("https://dummyimage.com/300");
    }

    public static void main(String[] args) {

        try {

            URL url = new URL(urls.get(1));
            URLConnection connection = url.openConnection();

            //connection.connect();
            printURLConnection(connection);

            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            printHttpURLConnection(httpURLConnection);

            printInputStream(connection.getInputStream(), 2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printHttpURLConnection(HttpURLConnection httpURLConnection) throws IOException {
        System.out.println("printHttpURLConnection____________________________________________________");
        System.out.println("response code " + httpURLConnection.getResponseCode());
        System.out.println("request method " + httpURLConnection.getRequestMethod());
        System.out.println("response message  " + httpURLConnection.getResponseMessage());
    }

    private static void printInputStream(InputStream inputStream, int type) throws IOException {
        if (inputStream == null) return;
        System.out.println("available = " + inputStream.available());
        switch (type) {
            case 1:
                int val = -1;
                while ((val = inputStream.read()) != -1) {
                    System.out.println((byte) val);
                }
                break;
            case 2:
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                break;
        }
    }

    private static void printURLConnection(URLConnection urlConnection) throws IOException {
        if (urlConnection == null) return;

        System.out.println("printURLConnection___________________________________________");
        System.out.println("content type " + urlConnection.getContentType());
        System.out.println("content encoding " + urlConnection.getContentEncoding());
        System.out.println("allow user interaction " + urlConnection.getAllowUserInteraction());
        System.out.println("connect timeout " + urlConnection.getConnectTimeout());
        System.out.println("content lenght " + urlConnection.getContentLength());
        System.out.println("read timeout " + urlConnection.getReadTimeout());
        System.out.println("date " + urlConnection.getDate());
        System.out.println("expiration " + urlConnection.getExpiration());
        System.out.println("IfModifiedSince " + urlConnection.getIfModifiedSince());
        System.out.println("do input " + urlConnection.getDoInput());

        System.out.println("HEADERS________________________________________________________");
        Set<String> headers = new TreeSet();
        for (String key : urlConnection.getHeaderFields().keySet()) {
            if (key != null) headers.add(key);
        }
        for (String header : headers) {
            System.out.println("key = " + header + " value = " + urlConnection.getHeaderField(header));
        }
    }
}
