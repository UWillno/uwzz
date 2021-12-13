package com.uuuuu.uwzz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

import static com.uuuuu.uwzz.uwc.a;


public class ca extends AppCompatActivity {
    private EditText pj;
    private Button js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca);
        pj = findViewById(R.id.pj);

            String data = pj.getText().toString();
            pj.setText("");
            try {
                if (a != null) {
                    data = a;
                }
                JSONArray parseArray = JSON.parseArray(JSON.parseObject(JSON.parseObject(data).getString("data")).getString("questionList"));
                String types;
                Iterator<Object> it = parseArray.iterator();
                int i = 0;
                while (it.hasNext()) {
                    JSONObject jSONObject = (JSONObject) it.next();
                    i++;
                    if (jSONObject.getString("type").equals("4")) {
                        for (Object value : JSON.parseArray(jSONObject.getString("memberList"))) {
                            JSONObject jSONObject2 = (JSONObject) value;
                            JSONObject parseObject = JSONObject.parseObject(jSONObject2.getString("answerJson"));
                            if (parseObject.size() != 0) {
                                if (parseObject.getString("content") != null) {
                                    types = parseObject.getString("content");
                                    pj.append(types + "\n");
                                } else {
                                    for (Object o : JSON.parseArray(parseObject.getString("recs"))) {
                                        pj.append("https://p.ananas.chaoxing.com/star3/origin/" + ((JSONObject) o).getString("objectid") + ".jpg\n");
                                    }
                                }
                            }
                            types = i > 1 ? jSONObject2.getString("name") + " -" + i + ".简答题" : jSONObject2.getString("name");
                            pj.append(types + "\n");
                        }

                    } else {
                        pj.append(jSONObject.getString("rightAnswer") + "\n");
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "页面异常，无法解析！！", Toast.LENGTH_LONG).show();
            }
    }
}