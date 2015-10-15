package com.ysj.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ysj.log.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            foo();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void foo() throws JSONException {
        bar();
    }

    private void bar() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "123456");

        JSONObject child = new JSONObject();
        child.put("name", "Yu Shaojian");

        jsonObject.put("child", child);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject);

        ArrayList<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");

        JSONObject longJSON = new JSONObject(TestData.json);

        // quite simple
        L.d("hello");

        // specify a special tag
        L.d("LOG_LOG", "hello");

        // sometimes we just want to see which method was called, without any messages.
        L.d();

        // JSON is printed pretty
        L.d(longJSON);
        L.d(jsonArray);

        L.d(list);

        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        L.d(map);
    }
}
