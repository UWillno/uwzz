package com.uuuuu.uwzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.uuuuu.uwzz.databinding.ActivityTjtBinding
import com.uuuuu.uwzz.databinding.ActivityUwcBinding
import com.uuuuu.uwzz.databinding.ActivityUwzzPlusBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class UwzzPlus : AppCompatActivity() {
    val uname = "17671056113"
    val password = "zhj775825"
    var cookies: Map<String, String>? = null
    var uid = ""
    var realname = ""
    var coursename = listOf<String>()
    lateinit var binding: ActivityUwzzPlusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUwzzPlusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        val handler = Handler(mainLooper) {
            when (it.what) {
                1 -> {
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, coursename)
                    binding.clist.adapter = adapter
                    Log.d("uw",coursename.toString())
                }
            }
            false
        }
        init(handler)




    }
    @OptIn(DelicateCoroutinesApi::class)
    fun init(handler: Handler){
        GlobalScope.launch{
            val loginurl =
                "https://passport2-api.chaoxing.com/v11/loginregister?cx_xxt_passport=json&uname=$uname&code=$password"
            val cookies = Jsoup.connect(loginurl).timeout(30000).execute().cookies()
            Log.d("uw",cookies.toString())
            Log.d("uw",loginurl)
            val info = Jsoup.connect("https://sso.chaoxing.com/apis/login/userLogin4Uname.do").cookies(cookies).get().toString()
//    print(info)
//    print("\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()[0].value)

            for (i in "\"puid\":[0-9|.]+".toRegex().findAll(info).toList()) {
                uid = ((i.value.split(":")[1].toString()))
            }
            for (i in "\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()) {
                realname = (i.value.split(":")[1].replace("\"", ""))
            }
            val coursedata =
                Jsoup.connect("https://mooc1-2.chaoxing.com/course/phone/courselistdata?courseFolderId=0&isFiled=0&query=")
                    .cookies(cookies).get().toString()
            coursename =
                Jsoup.parse(coursedata).getElementsByTag("dt").text().split(" ").toList()
            handler.sendEmptyMessage(1)


        }


    }
}