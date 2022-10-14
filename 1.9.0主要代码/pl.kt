package com.uuuuu.uwzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.uuuuu.uwzz.databinding.ActivityPlBinding

class pl : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityPlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        val url_list = "[a-f0-9]{32}".toRegex().findAll(Uwc.a).toList()
        if (url_list.isNotEmpty()) {
            for (url in url_list) {
                binding.pl.append("https://p.ananas.chaoxing.com/star3/origin/${url.value}.jpg\n" +
                        "")
            }
            Toast.makeText(this,"仅供签到使用，严谨用于非法用途！",Toast.LENGTH_LONG).show()
        }
    }
}