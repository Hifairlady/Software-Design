package com.sysu.edgar.bach;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Edgar on 2016/7/14.
 */
public class SessionItems {

    public String[] dates;
    public String[] cinemaIds;
    public String[] urls;
    public String[] startTimes;
    public String[] movieIds;
    public String[] endTimes;
    public String[] languageAndEffects;
    public String[] playingRooms;
    public String[] prices;
    public int length = 0;
    public int count = 0;
    private JSONArray jsonArray;

    public SessionItems(final String urlPath) {
        System.out.println("Loading sessions...");
//        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jsonArray = JsonParse.getJsonArray(urlPath);
                    length = jsonArray.length();
                    dates = new String[length];
                    cinemaIds = new String[length];
                    urls = new String[length];
                    startTimes = new String[length];
                    movieIds = new String[length];
                    endTimes = new String[length];
                    languageAndEffects = new String[length];
                    playingRooms = new String[length];
                    prices = new String[length];

                    for (int i = 0; i < length; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        dates[i] = object.getString("date");
                        cinemaIds[i] = object.getString("cinemaId");
                        urls[i] = object.getString("id");
                        startTimes[i] = object.getString("startTime");
                        movieIds[i] = object.getString("movieId");
                        endTimes[i] = object.getString("endTime");
                        languageAndEffects[i] = object.getString("languageAndEffect");
                        playingRooms[i] = object.getString("playingRoom");
                        prices[i] = object.getString("price");
                        count++;
                    }
                    System.out.println("Load sessions complete!");
                } catch (SocketTimeoutException e1) {
                    e1.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                latch.countDown();
            }
        }).start();
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}