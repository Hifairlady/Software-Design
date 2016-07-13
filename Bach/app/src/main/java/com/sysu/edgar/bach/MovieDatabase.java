package com.sysu.edgar.bach;

import android.graphics.Bitmap;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by B402 on 2016/7/13.
 */
public class MovieDatabase {

    public String[] titles;
    public String[] scores;
    public String[] types;
    public String[] time_languages;
    public String[] on_times;
    public String[] actors;
    public Bitmap[] images;
    public String[] descriptions;
    public String[] ids;
    public String[] imgUrls;
    private JSONArray jsonArray;

    public MovieDatabase(MovieDatabase other) {
        this.titles = other.titles;
        this.scores = other.scores;
        this.types = other.types;
        this.time_languages = other.time_languages;
        this.on_times = other.on_times;
        this.actors = other.actors;
        this.descriptions = other.descriptions;
        this.ids = other.ids;
        this.imgUrls = other.imgUrls;
        this.images = other.images;
    }

    public MovieDatabase() {}

    public MovieDatabase(final String urlPath) {
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jsonArray = JsonParse.getJsonArray(urlPath);
                    titles = new String[jsonArray.length()];
                    scores = new String[jsonArray.length()];
                    types = new String[jsonArray.length()];
                    time_languages = new String[jsonArray.length()];
                    on_times = new String[jsonArray.length()];
                    actors = new String[jsonArray.length()];
                    descriptions = new String[jsonArray.length()];
                    ids = new String[jsonArray.length()];
                    imgUrls = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        titles[i] = object.getString("name");
                        scores[i] = object.getString("score");
                        types[i] = object.getString("type");
                        time_languages[i] = object.getString("timeAndLanguage");
                        on_times[i] = object.getString("onTime");
                        actors[i] = object.getString("actors");
                        ids[i] = object.getString("id");
                        descriptions[i] = object.getString("description");
                        imgUrls[i] = object.getString("img");
//                        System.out.println(object.getString("img"));
//                        Bitmap bitmap = ImageService.getBitmapFromUrl(object.getString("img"));
//                        images[i] = bitmap;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTitles(String[] s) {
        titles = s;
    }

    public void setScores(String[] s) {
        scores = s;
    }

    public void setTypes(String[] s) {
        types = s;
    }

    public void setTime_languages(String[] s) {
        time_languages = s;
    }

    public void setOn_times(String[] s) {
        on_times = s;
    }

    public void setActors(String[] s) {
        actors = s;
    }

    public void setDescriptions(String[] s) {
        descriptions = s;
    }

    public void setIds(String[] s) {
        ids = s;
    }

    public void setImages(ArrayList<Bitmap> b) {
        images = new Bitmap[b.size()];
        for (int i = 0; i < b.size(); i++) {
            images[i] = b.get(i);
        }
    }

    public void setImgUrls(String[] s) {
        imgUrls = s;
    }

}