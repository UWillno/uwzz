package com.uuuuu.uwzz

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.widget.Button
import com.uuuuu.uwzz.R
import com.uuuuu.uwzz.Uwc
import com.alibaba.fastjson.JSON
import android.widget.Toast
import com.alibaba.fastjson.JSONObject
import java.lang.Exception

class ca : AppCompatActivity() {
    lateinit var pj: EditText
//   val js: Button?

    //    static String[] ss = {"su", "sh", "-c", "/storage/emulated/0/Android/data/com.uuuuu.uwzz/files/UWillno/跳过教务登录.sh"};
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ca)
        pj = findViewById(R.id.pj)
        var data = pj.getText().toString()
        pj.setText("")
        try {
            data = Uwc.a
            val parseArray = JSON.parseArray(
                JSON.parseObject(JSON.parseObject(data).getString("data")).getString("questionList")
            )
            var types: String
            val it: Iterator<Any> = parseArray.iterator()
            var i = 0
            while (it.hasNext()) {
                val jSONObject = it.next() as JSONObject
                i++
                if (jSONObject.getString("type") == "4") {
                    for (value in JSON.parseArray(jSONObject.getString("memberList"))) {
                        val jSONObject2 = value as JSONObject
                        val parseObject =
                            JSONObject.parseObject(jSONObject2.getString("answerJson"))
                        if (parseObject.size != 0) {
                            if (parseObject.getString("content") != null) {
                                types = parseObject.getString("content")
                                pj.append(
                                    """
    $types
    
    """.trimIndent()
                                )
                            } else {
                                for (o in JSON.parseArray(parseObject.getString("recs"))) {
                                    pj.append(
                                        """
    https://p.ananas.chaoxing.com/star3/origin/${(o as JSONObject).getString("objectid")}.jpg
    
    """.trimIndent()
                                    )
                                }
                            }
                        }
                        types =
                            if (i > 1) jSONObject2.getString("name") + " -" + i + ".简答题" else jSONObject2.getString(
                                "name"
                            )
                        pj.append(
                            """
    $types
    
    """.trimIndent()
                        )
                    }
                } else {
                    pj.append(
                        """
    ${jSONObject.getString("rightAnswer")}
    
    """.trimIndent()
                    )
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "页面异常，无法解析！！", Toast.LENGTH_LONG).show()
        }
    }
}