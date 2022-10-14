package com.uuuuu.uwzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uuuuu.uwzz.Uwc.Companion.a
import com.uuuuu.uwzz.databinding.ActivityJftjBinding



class jftj : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityJftjBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        val nlist = ArrayList<String>()//姓名
        val scorel = ArrayList<Double>()//总分
        val qdscorel = ArrayList<Double>() //抢答
        val xrscorel = ArrayList<Double>()//选人
        val stlxl = ArrayList<Double>()//随堂练习
        val wjl = ArrayList<Double>() //问卷
        for (i in "\"name\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(a).toList())
            nlist.add(i.value.split(":")[1].replace("\"", ""))
        for (i in "\"answerScore\":[0-9|.]+".toRegex().findAll(a).toList())
            qdscorel.add((i.value.split(":")[1].toDouble()))
        for (i in "\"pickScore\":[0-9|.]+".toRegex().findAll(a).toList())
            xrscorel.add((i.value.split(":")[1].toDouble()))
        for (i in "\"quizScore\":[0-9|.]+".toRegex().findAll(a).toList())
            stlxl.add((i.value.split(":")[1].toDouble()))
        for (i in "\"score\":[0-9|.]+".toRegex().findAll(a).toList())
            scorel.add((i.value.split(":")[1].toDouble()))
        for (i in "\"voteScore\":[0-9|.]+".toRegex().findAll(a).toList())
            wjl.add((i.value.split(":")[1].toDouble()))
        val m = HashMap<String, Double>()
        for (i in stlxl.indices) {
            m["姓名：${nlist[i]}\t总积分：${scorel[i]}\t随堂练习：${stlxl[i]}\t" +
                    "抢答：${qdscorel[i]}\t选人${xrscorel[i]}\t问卷${wjl[i]}\n"] = scorel[i]
        }
        val ms = m.entries.sortedBy { it.value }.associateBy({ it.key }, { it.value })
        for (i in ms.keys.reversed())
            binding.ttjjj.append(i)
    }
}