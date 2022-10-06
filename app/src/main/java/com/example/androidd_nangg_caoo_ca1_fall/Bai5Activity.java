package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.androidd_nangg_caoo_ca1_fall.dto.NewAdapter;
import com.example.androidd_nangg_caoo_ca1_fall.dto.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bai5Activity extends AppCompatActivity {
    private ListView lvNews;
    private List<News> news = new ArrayList<>();
    private URL feedURL;
    private NewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai5);
        adapter = new NewAdapter(getBaseContext(), news);
        lvNews = findViewById(R.id.lvNews);
        lvNews.setAdapter(adapter);
        String urlSite = ("https://vnexpress.net/rss/tam-su.rss");
        new FeedService().execute(urlSite);
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentItem = news.get(position);
                if(currentItem != null) {
                    Intent intent = new Intent(Bai5Activity.this, WebViewActivity.class);
                    intent.putExtra("link", currentItem.getLink());
                    System.out.println(currentItem.getLink());
                    startActivity(intent);
                }

            }
        });

    }
    public List<News> parseFeed(URL url) throws IOException, XmlPullParserException {
        ArrayList<News> list = new ArrayList<>();
        InputStream stream = url.openConnection().getInputStream();
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(stream, "UTF-8");
        News item = new News();
        int eventType = parser.getEventType();
        String text = "";
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if(name.equalsIgnoreCase("item")) {
                        item = new News();
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    if(name.equalsIgnoreCase("item")) {
                        list.add(item);
                    }
                    if(name.equalsIgnoreCase("title")) {
                        if(text !=null) {
                            item.setTitle(text);
                        }

                    } else if(name.equalsIgnoreCase("description")) {
                        if(text !=null) {
                            item.setDescription( text.replaceAll("^<.*./br>",""));
                        }

                    }else if(name.equalsIgnoreCase("link")) {
                        if(text !=null) {
                            item.setLink(text);
                        }

                    }
                    break;
            }
            eventType = parser.next();
        }
        return list;
    }

    class FeedService extends AsyncTask<String,  Integer, List<News>> {

        @Override
        protected List<News> doInBackground(String... strings) {
            List<News> test = new ArrayList<>();
            try {
                feedURL = new URL(strings[0]);
                test = parseFeed(new URL("https://vnexpress.net/rss/tam-su.rss"));
                Log.i("TEST", news.size()+"");
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }

            return test;
        }

        @Override
        protected void onPostExecute(List<News> results) {
            for (News item:results
                 ) {
                System.out.println(item);
            }
            news= results;
            super.onPostExecute(results);
            adapter.forceUpdate(results);
        }
    }
}
