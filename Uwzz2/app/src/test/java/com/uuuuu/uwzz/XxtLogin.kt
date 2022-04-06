package com.uuuuu.uwzz

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.junit.Test
import java.nio.charset.Charset
import java.util.*



class XxtLogin {
    companion object{
        fun base64Encode(token: String): String {
            val encodedBytes = Base64.getEncoder().encode(token.toByteArray())
            return String(encodedBytes, Charset.forName("UTF-8"))
        }
        fun base64Decode(token: String): String {
            val decodedBytes = Base64.getDecoder().decode(token.toByteArray())
            return String(decodedBytes, Charset.forName("UTF-8"))
        }

    }
    @Test
    fun a(){
        val pwd="zhj775825"
        val uname="17671056113"
        val connection= Jsoup.connect("http://passport2.chaoxing.com/fanyalogin")
        val response=connection.data("uname",uname).data("password",base64Encode(pwd)).data("refer","http%3A%2F%2Fi.chaoxing.com").data("t","true")
            .ignoreContentType(true).followRedirects(true).method(Connection.Method.POST).timeout(60000).execute()
        print(response.cookies())

    }
}