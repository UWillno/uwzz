package com.uuuuu.uwzz

//import com.uuuuu.uwzz.MainActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.gyf.immersionbar.ImmersionBar
import com.uuuuu.uwzz.databinding.ActivityUwcBinding
import com.uuuuu.uwzz.tj.Companion.getworkAnswerId
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.net.URLEncoder
import java.util.*
import java.util.regex.Pattern


//import es.dmoral.toasty.Toasty;
class Uwc : AppCompatActivity(), KeyEvent.Callback {
    //    String waid = "0";
//    lateinit var webView: WebView
    var url = "https://mooc1-2.chaoxing.com/visit/courses"
    var wal: List<String>? = null
    var tempurl: String? = null
    var flag = 0
    lateinit var binding: ActivityUwcBinding

    //    lateinit var qh: Button
//    lateinit var sx: Button
//    lateinit var qd: Button
//    lateinit var ex: Button
//    lateinit var to: Button
    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUwcBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val mi=ImmersionBar.with(this)
//        mi.statusBarDarkFont(true).statusBarColor(R.color.purple_200).transparentNavigationBar().fitsSystemWindows(true).init()

        // 开启JavaScript支持
        val settings = binding.webview.settings
        settings.javaScriptEnabled = true
        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
        settings.setSupportZoom(true)
        // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        settings.builtInZoomControls = true
        // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        settings.domStorageEnabled = true
        // 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        binding.webview.requestFocus()
        binding.webview.setInitialScale(150)

        // 设置此属性,可任意比例缩放,设置webview推荐使用的窗口
        settings.useWideViewPort = true
        settings.displayZoomControls = false

        // 设置webview加载的页面的模式,缩放至屏幕的大小
        settings.loadWithOverviewMode = true
        binding.webview.setWebViewClient(WebViewClient())
        binding.webview.loadUrl(url)
        binding.webview.addJavascriptInterface(JavaObjectJsInterface(), "java_obj")
        binding.ky.setOnClickListener { binding.webview.loadUrl("https://mooc1-2.chaoxing.com/visit/courses") }

//        binding.ky.setOnClickListener { binding.webview.loadUrl("https://mooc1-2.chaoxing.com/visit/courses") }
        //        Button qd=findViewById(R.id.qd);
//        registerForContextMenu(qd);

        binding.qd.setOnClickListener { showPopupMenu(binding.qd) }
        binding.sssssxxxxx.setOnClickListener { binding.webview.reload() }
        binding.ex.setOnClickListener { showexPopupMenu(binding.ex) }
        binding.qqqqhhhh.visibility = View.GONE
        binding.qqqqhhhh.setOnClickListener {
            if (flag < wal!!.size - 1) {
                flag++
            } else flag = 0
            var url1 = tempurl
            url1 = url1!!.replace("workAnswerId=[0-9]+".toRegex(), "workAnswerId=" + wal!![flag])
            url1 = url1.replace("mooc=[0-9]".toRegex(), "mooc=0")
            binding.webview.loadUrl(url1)
        }
    }

    @SuppressLint("NonConstantResourceId")
    private fun showexPopupMenu(view: View?) {
        // View当前PopupMenu显示的相对View的位置
        val popupMenu = PopupMenu(this, view)
        // menu布局
        popupMenu.menuInflater.inflate(R.menu.exmenu, popupMenu.menu)
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.zyda -> {

//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("选择功能").setIcon(R.drawable.mm)
//                        .setNegativeButton("gid", ((dialogInterface, i) -> {
//                            waid = gwaid(webView.getUrl());
//                            if (!waid.equals("0"))
//                                Toast.makeText(this, "可能获取成功了？去需要查看作业查看答案？", Toast.LENGTH_LONG).show();
//                            else
//                                Toast.makeText(this, "你能不能先找个作业进去啊！焯！", Toast.LENGTH_LONG).show();
//
//                        }));
//                builder.setPositiveButton("查看答案", (dialog, a) -> {
//                    if (!waid.equals("0")) {
//                        String url=webView.getUrl().replaceAll("workAnswerId=[0-9]+", "workAnswerId=" + waid);
//                        url=url.replaceAll("mooc=[0-9]","mooc=0");
//                        webView.loadUrl(url);
//                        Toast.makeText(getApplicationContext(), "可能查看成功了？自己记下来答案返回重进再填写，失败就换gid", Toast.LENGTH_LONG).show();
//                    } else
//                        Toast.makeText(this, "我不懂，但我大受震撼！", Toast.LENGTH_LONG).show();
//                });
//                builder.show();
                    tempurl = binding.webview.url
                    binding.webview.webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView, url: String) {
                            super.onPageFinished(view, url)
                            binding.webview.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('body')[0].innerHTML);")
                        }
                    }
                    val backForwardList = binding.webview.copyBackForwardList()
                    binding.webview.loadUrl(backForwardList.getItemAtIndex(backForwardList.currentIndex - 1).url)
                    //                webView.reload();
                    showToast(this, "等3s我怕你网速太慢...")
                    val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
                        override fun onTick(l: Long) {}
                        override fun onFinish() {
                            wal = getworkAnswerId(a)
                            if (wal!!.size != 0) {
                                showToast(applicationContext, "有" + wal!!.size + "个可能是答案的界面")
                                binding.qqqqhhhh.visibility = View.VISIBLE
                                binding.sssssxxxxx.visibility = View.GONE
                                binding.qd.visibility = View.GONE
                                binding.ex.visibility = View.GONE
                                binding.ky.visibility = View.GONE
                            }
                        }
                    }
                    countDownTimer.start()
                }
                R.id.cyda -> {
                    if (gid(binding.webview.url) != null) {
                        val au =
                            "https://mobilelearn.chaoxing.com/v2/apis/quiz/getStatisticsData?activeId=" + gid(
                                binding.webview.url
                            )
                        binding.webview.webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView, url: String) {
                                super.onPageFinished(view, url)
                                binding.webview.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('pre')[0].innerHTML);")
                            }
                        }
                        binding.webview.loadUrl(au)
                        showToast(this, "等3s我怕你网速太慢...")
                        val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
                            override fun onTick(l: Long) {}
                            override fun onFinish() {
                                startActivity(Intent(applicationContext, ca::class.java))
                            }
                        }
                        countDownTimer.start()
                    }
                }
                R.id.cktp->{

//                    val connect =
//                        Jsoup.connect("https://mobilelearn.chaoxing.com/widget/sign/pcTeaSignController/getAttendList?activeId=$str&appType=15&classId=$str2&fid=0")
//                    this.connection = connect
//                    val execute =
//                        connect.ignoreContentType(true).followRedirects(true).cookies(map).method(Connection.Method.GET)
//                            .timeout(60000).execute()
//                    this.response = execute
//                    val parseObject = JSON.parseObject(JSON.parseObject(execute.body()).getString("data"))
//                    val parseArray: JSONArray = JSON.parseArray(parseObject.getString("yiqianList"))
//                    val parseArray2: JSONArray = JSON.parseArray(parseObject.getString("weiqianList"))
//                    val arrayList = ArrayList<Any>()
//                    val arrayList2 = ArrayList<Any>()
//                    val it: Iterator<Any> = parseArray.iterator()
//                    while (it.hasNext()) {
//                        val jSONObject = it.next() as JSONObject
//                        arrayList.add(
//                            AttendListEntity(
//                                jSONObject.getString("name"),
//                                "https://p.ananas.chaoxing.com/star3/origin/" + jSONObject.getString("title") + ".jpg"
//                            )
//                        )
//                    }
//                    val it2: Iterator<Any> = parseArray2.iterator()
//                    while (it2.hasNext()) {
//                        arrayList2.add(AttendListEntity((it2.next() as JSONObject).getString("name"), ""))
//                    }
//                    val arrayList3 = ArrayList<Any>()
//                    arrayList3.add(arrayList)
//                    arrayList3.add(arrayList2)
//                    return arrayList3
                    showToast(this, "可能侵权，暂不实现")
                    }
                R.id.hwtj -> {
                    binding.webview.webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView, url: String) {
                            super.onPageFinished(view, url)
                            binding.webview.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('pre')[0].innerHTML);")
                            //                        Log.d("uw",a);
                        }
                    }
                    if (gcid(binding.webview.url) != "0" && gcourseid(
                            binding.webview.url
                        ) != "0"
                    ) {
                        binding.webview.loadUrl(
                            "https://stat2-ans.chaoxing.com/work-stastics/student-works?clazzid=" + gcid(
                                binding.webview.url
                            ) + "&courseid=" + gcourseid(binding.webview.url) + "&page=1&pageSize=200"
                        )
                    }
                    showToast(this, "等3s我怕你网速太慢...")
                    val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
                        override fun onTick(l: Long) {}
                        override fun onFinish() {
                            startActivity(Intent(applicationContext, tj::class.java))
                        }
                    }
                    countDownTimer.start()
                }
                R.id.jftj -> {
                    binding.webview.webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView, url: String) {
                            super.onPageFinished(view, url)
                            binding.webview.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('pre')[0].innerHTML);")
                            //                        Log.d("uw",a);
                        }
                    }
                    if (gcid(binding.webview.url) != "0" && gcourseid(
                            binding.webview.url
                        ) != "0"
                    ) {
                        binding.webview.loadUrl(
                            "https://mobilelearn.chaoxing.com/v2/apis/integral/getIntegralList?DB_STRATEGY=COURSEID&STRATEGY_PARA=courseId&pageSize=200&page=1&classId=" + gcid(
                                binding.webview.url
                            ) + "&courseId=" + gcourseid(binding.webview.url)
                        )
                    }
                    showToast(this, "等3s我怕你网速太慢...")
                    val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
                        override fun onTick(l: Long) {}
                        override fun onFinish() {
                            startActivity(Intent(applicationContext, jftj::class.java))
                        }
                    }
                    countDownTimer.start()
                }
            }
            true
        }
        popupMenu.show()
    }


    @SuppressLint("NonConstantResourceId")
    private fun showPopupMenu(view: View?) {
        // View当前PopupMenu显示的相对View的位置
        val popupMenu = PopupMenu(this, view)
        // menu布局
        popupMenu.menuInflater.inflate(R.menu.smenu, popupMenu.menu)
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.ssqd -> {
                    if (gid(binding.webview.url) != null) {
                        binding.webview.loadUrl(
                            "https://mobilelearn.chaoxing.com/v2/apis/sign/signIn?activeId=" + gid(
                                binding.webview.url
                            )
                        )
                        showToast(applicationContext, "可能签到成功？自己返回刷新页面..我懒得写了")
                    }
                }
                R.id.pzqd->{
//                    showToast(this, "若闪退可能没有指定位置或页面不正确，开学习通随便签")
                    try {
                        val aid: String?
                        val cookieManager: CookieManager = CookieManager.getInstance()
                        val cookie = cookieManager.getCookie(binding.webview.url)
//                    Log.d("uw",cookie)
                        val cookiemap = cookieToMap(cookie)
                        var info = ""
                        Thread {
                            info = Jsoup.connect("https://sso.chaoxing.com/apis/login/userLogin4Uname.do")
                                .cookies(cookiemap)
                                .get().toString()
                        }.start()
                        Log.d("uw", info)
//    print("\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()[0].value)
                        var uid = ""
                        var realname = ""
                        for (i in "\"puid\":[0-9|.]+".toRegex().findAll(info).toList()) {
                            uid = ((i.value.split(":")[1].toString()))
                        }
                        for (i in "\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()) {
                            realname = (i.value.split(":")[1].replace("\"", ""))
                        }
                        val activeId = "activeId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                            .toList()[0].value.trim()

                        val courseId = "courseId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                            .toList()[0].value.trim()
                        val classId = "classId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                            .toList()[0].value.trim()
                        val paid = "[0-9]+".toRegex().findAll(activeId).toList()[0].value.trim()
                        val url0 =
                            "https://mobilelearn.chaoxing.com/newsign/preSign?$courseId&$classId&activePrimaryId=$paid&general=1&sys=1&ls=1&appType=15&&tid=&uid=$uid&ut=s"

                        Log.d("uw", url0)
                        if (gid(binding.webview.url) != null) {
                            Thread {
                                val con0 = Jsoup.connect(url0)
                                val con = Jsoup.connect("https://mobilelearn.chaoxing.com/pptSign/stuSignajax?$activeId")
//                                val con = Jsoup.connect("https://mobilelearn.chaoxing.com/pptSign/stuSignajax?$activeId&objectId=")
                                con0.ignoreContentType(true).followRedirects(true).cookies(cookiemap)
                                    .method(
                                        Connection.Method.GET
                                    ).timeout(60000).execute()
                                con.ignoreContentType(true).followRedirects(true).cookies(cookiemap)
                                    .method(
                                        Connection.Method.GET
                                    ).timeout(60000).execute()
                            }.start()
                            binding.webview.reload()
                            showToast(this, "我猜签到成功了？")
                        }
                    } catch (_: Exception) {
                    }
//                    val connect = Jsoup.connect(
//                        "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?activeId=$str" + "&objectId="
//                        )
//                    )
//                    connect.ignoreContentType(true).followRedirects(true).cookies(map).method(Connection.Method.GET)
//                            .timeout(60000).execute()
                }
                R.id.loqd -> {
                    showToast(this, "若闪退可能没有指定位置或页面不正确，开学习通随便签")
                    try {
                        val aid: String?
                        val cookieManager: CookieManager = CookieManager.getInstance()
                        val cookie = cookieManager.getCookie(binding.webview.url)
//                    Log.d("uw",cookie)
                        val cookiemap = cookieToMap(cookie)
                        var info = ""
                        Thread {
                            info = Jsoup.connect("https://sso.chaoxing.com/apis/login/userLogin4Uname.do")
                                .cookies(cookiemap)
                                .get().toString()
                        }.start()
                        Log.d("uw", info)
//    print("\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()[0].value)
                        var uid = ""
                        var realname = ""
                        for (i in "\"puid\":[0-9|.]+".toRegex().findAll(info).toList()) {
                            uid = ((i.value.split(":")[1].toString()))
                        }
                        for (i in "\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()) {
                            realname = (i.value.split(":")[1].replace("\"", ""))
                        }
                        val activeId = "activeId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                            .toList()[0].value.trim()

                        val courseId = "courseId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                            .toList()[0].value.trim()
                        val classId = "classId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                            .toList()[0].value.trim()
                        val paid = "[0-9]+".toRegex().findAll(activeId).toList()[0].value.trim()
                        val url0 =
                            "https://mobilelearn.chaoxing.com/newsign/preSign?$courseId&$classId&activePrimaryId=$paid&general=1&sys=1&ls=1&appType=15&&tid=&uid=$uid&ut=s"

                        Log.d("uw", url0)
                        if (gid(binding.webview.url) != null) {
                            aid = gid(binding.webview.url)
                            val connect = Jsoup.connect(
                                "https://mobilelearn.chaoxing.com/v2/apis/active/getPPTActiveInfo?activeId=" + aid

                            )
                            Thread {
                                var response =
                                    connect.ignoreContentType(true).followRedirects(true)
                                        .cookies(cookiemap)
                                        .method(
                                            Connection.Method.GET
                                        ).timeout(60000).execute()
                                val parseObject: JSONObject =
                                    JSON.parseObject(
                                        JSON.parseObject(response.body()).getString("data")
                                    )
                                val hashMap = HashMap<Any, Any>()
//                            hashMap["signCode"] = parseObject.getString("signCode")
//                            hashMap["ifphoto"] = parseObject.getString("ifphoto")
                                hashMap["locationText"] = parseObject.getString("locationText")
                                hashMap["locationLatitude"] = parseObject.getString("locationLatitude")
                                hashMap["locationLongitude"] =
                                    parseObject.getString("locationLongitude")
//                            Log.d("uw",hashMap.toString())
//                            Log.d("uw","https://mobilelearn.chaoxing.com/pptSign/stuSignajax?address=" + hashMap.get("place"))
                                val con0 = Jsoup.connect(url0)
                                val con = Jsoup.connect(
                                    "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?address=" + hashMap.get(
                                        "locationText"
                                    )
                                        .toString() + "&activeId=" + aid + "&latitude=" + hashMap.get(
                                        "locationLatitude"
                                    ).toString() + "&longitude=" + hashMap.get("locationLongitude")
                                        .toString() + "&fid=0&appType=15&ifTiJiao=1"
                                )
                                con0.ignoreContentType(true).followRedirects(true).cookies(cookiemap)
                                    .method(
                                        Connection.Method.GET
                                    ).timeout(60000).execute()
                                con.ignoreContentType(true).followRedirects(true).cookies(cookiemap)
                                    .method(
                                        Connection.Method.GET
                                    ).timeout(60000).execute()
                            }.start()
                            binding.webview.reload()
                            showToast(this, "我猜签到成功了？")
                        }
                    } catch (_: Exception) {
                    }
                }
                R.id.qrqd -> {
                    val cookieManager: CookieManager = CookieManager.getInstance()
                    val cookie = cookieManager.getCookie(binding.webview.url)
//                    Log.d("uw",cookie)
                    val cookiemap = cookieToMap(cookie)
                    var info = ""
                    Thread {
                        info = Jsoup.connect("https://sso.chaoxing.com/apis/login/userLogin4Uname.do")
                            .cookies(cookiemap)
                            .get().toString()
                    }.start()
                    Log.d("uw", info)
//    print("\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()[0].value)
                    var uid = ""
                    var realname = ""
                    for (i in "\"puid\":[0-9|.]+".toRegex().findAll(info).toList()) {
                        uid = ((i.value.split(":")[1].toString()))
                    }
                    for (i in "\"realname\":\"[\\u4E00-\\u9FA5]+\"".toRegex().findAll(info).toList()) {
                        realname = (i.value.split(":")[1].replace("\"", ""))
                    }
                    val activeId = "activeId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                        .toList()[0].value.trim()

                    val courseId = "courseId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                        .toList()[0].value.trim()
                    val classId = "classId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                        .toList()[0].value.trim()
                    val paid = "[0-9]+".toRegex().findAll(activeId).toList()[0].value.trim()
                    val url0 =
                        "https://mobilelearn.chaoxing.com/newsign/preSign?$courseId&$classId&activePrimaryId=$paid&general=1&sys=1&ls=1&appType=15&&tid=&uid=$uid&ut=s"

                    Log.d("uw", url0)

                    val editText = EditText(this)
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("输入enc").setIcon(R.drawable.mm).setView(editText)
                        .setNegativeButton("取消", null)
                    builder.setPositiveButton("确认") { dialog: DialogInterface?, a: Int ->
                        if (gid(
                                binding.webview.url
                            ) != null
                        ) {
                            try {
//                                val uid = editText.text.toString().split('#')[0].trim()
                                val name = URLEncoder.encode(realname, "utf8").trim()
                                val enc = editText.text.toString().trim()
                                val activeId = "activeId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                                    .toList()[0].value.trim()
                                val fid = "fid=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                                    .toList()[0].value.trim()
                                val courseId = "courseId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                                    .toList()[0].value.trim()
                                val classId = "classId=[0-9]+".toRegex().findAll(binding.webview.url.toString())
                                    .toList()[0].value.trim()
                                val paid = "[0-9]+".toRegex().findAll(activeId).toList()[0].value.trim()
//                                val url0 =
//                                    "https://mobilelearn.chaoxing.com/newsign/preSign?$courseId&$classId&activePrimaryId=$paid&general=1&sys=1&ls=1&appType=15&&tid=&uid=$uid&ut=s"
//                                binding.webview.loadUrl(url0);
                                Log.d("uw", url0)
                                val url =
                                    "https://mobilelearn.chaoxing.com/pptSign/stuSignajax?enc=$enc&name=$name&$activeId&uid=$uid&clientip=&useragent=&latitude=-1&longitude=-1&$fid&appType=15".trim()
                                Thread{
                                    val con0=Jsoup.connect(url0)
                                    val con=Jsoup.connect(url)
                                    con0.ignoreContentType(true).followRedirects(true).cookies(cookiemap)
                                        .method(
                                            Connection.Method.GET
                                        ).timeout(60000).execute()
                                    con.ignoreContentType(true).followRedirects(true).cookies(cookiemap)
                                        .method(
                                            Connection.Method.GET
                                        ).timeout(60000).execute()
                                }.start()
//                                Log.d("uw", url)
                                binding.webview.reload()

                            } catch (_: java.lang.Exception) {

                            }


////
                            showToast(applicationContext, "可能签到成功？自己刷新页面..我懒得写了")
                        }
                    }
                    builder.show()
                }
            }
            true
        }
        popupMenu.show()
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.qdmenu, menu)
//        return true
//    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (binding.webview.canGoBack()) {
                binding.webview.goBack()
                binding.qqqqhhhh.visibility = View.GONE
                binding.sssssxxxxx.visibility = View.VISIBLE
                binding.qd.visibility = View.VISIBLE
                binding.ex.visibility = View.VISIBLE
                binding.ky.visibility = View.VISIBLE
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    class JavaObjectJsInterface {
        @JavascriptInterface // 要加这个注解，不然调用不到
        fun onHtml(html: String) {
            a = html
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    @SuppressLint("NonConstantResourceId", "SdCardPath")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val cm = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val intent = Intent(Intent.ACTION_VIEW)

        when (item.itemId) {
            R.id.gitee -> {

                intent.data = Uri.parse("http://gitee.com/uwillno/uwzz")
//                cm.setPrimaryClip(ClipData.newPlainText(null, "http://gitee.com/uwillno/uwzz"))
                showToast(this, "写得很烂，大佬轻喷！")
            }
            R.id.qqq -> {

                intent.data = Uri.parse("https://jq.qq.com/?_wv=1027&k=0ml338o9")
//                cm.setPrimaryClip(ClipData.newPlainText(null, "https://jq.qq.com/?_wv=1027&k=0ml338o9"))
                showToast(this, "欢迎入伙！")
            }
            R.id.http1 -> {
                intent.data = Uri.parse("https://www.lanzoux.com/b02ckh7nc?w")
//                cm.setPrimaryClip(ClipData.newPlainText(null, "https://www.lanzoux.com/b02ckh7nc?w"))
                showToast(this, "密码可能是1234！")
            }
//            R.id.skip -> {
//                val op = getExternalFilesDir(null).toString()
//                val path=getExternalFilesDir("zzzz")
////                Log.d("uw",path.toString())
//                val shell = "cd $path/\n" +
//                        "cp -r -f com.example.currilculumdesign_preferences.xml com.example.currilculumdesign/shared_prefs/\n" +
//                        "cp -r -f com.example.currilculumdesign /data/data/\n" +
//                        "chmod -R 0777 /data/data/com.example.currilculumdesign/"
//                copyfile("U", op)
//                Shell.SU.run(shell)
//                val re = sh(shell)
//                when (re) {
//                    "??" -> Toast.makeText(this, "没权限或给了依旧这样，就还是自己手动移动文件吧，我搞不来了！", Toast.LENGTH_LONG).show()
//                    else -> Toast.makeText(this, "不生效就去手动执行sh吧，已放到sd根目录和$re ", Toast.LENGTH_LONG).show()
//                }
//            }
        }
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }


    companion object {
        @JvmField
        var a = "10086"

        //    static public String gwaid(String url) {
        //        Matcher m = Pattern.compile("workAnswerId=[0-9]+").matcher(url);
        //        if (m.find()) {
        //            m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)));
        //            if (m.find())
        //                return m.group(0);
        //        }
        //        return "0";
        //    }
        fun gcourseid(url: String?): String? {
            var m = Pattern.compile("courseId=[0-9]+").matcher(url)
            if (m.find()) {
                m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)))
                if (m.find()) return m.group(0)
            }
            return "0"
        }

        fun gcid(url: String?): String? {
            var m = Pattern.compile("clazzid=[0-9]+").matcher(url)
            if (m.find()) {
                m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)))
                if (m.find()) {
                    return m.group(0)
                }
            }
            return "0"
        }

        fun showToast(context: Context?, text: String?) {
            val toast = Toast(context)

            //设置Tosat的属性，如显示时间
            toast.duration = Toast.LENGTH_LONG

            //创建线性水平布局管理器,设置内容为垂直居中
            val ly = LinearLayout(context)
            ly.gravity = Gravity.CENTER_VERTICAL

            //创建ImageView
            val iv = ImageView(context)
            iv.setImageResource(R.mipmap.ic_launcher_foreground)

            //创建TextView
            val tv = TextView(context)
            tv.text = text

            //将图标和提示内容add进布局管理器
            ly.addView(tv)
            ly.addView(iv)

            //将布局管理器添加进Toast
            toast.view = ly

            //显示提示
            toast.show()
            //
//        ImageView imageView=new ImageView(context);
//        Toast toast=new Toast(context);
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        toast.setView(imageView);
//        toast.setText(text);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.show();
        }

        fun cookieToMap(value: String): Map<String, String>? {
            var value = value
            val map: MutableMap<String, String> = HashMap()
            value = value.replace(" ", "")
            if (value.contains(";")) {
                val values = value.split(";".toRegex()).toTypedArray()
                for (`val` in values) {
                    val vals = `val`.split("=".toRegex()).toTypedArray()
                    map[vals[0]] = vals[1]
                }
            } else {
                val values = value.split("=".toRegex()).toTypedArray()
                map[values[0]] = values[1]
            }
            return map
        }

        fun gid(url: String?): String? {
            var m = Pattern.compile("activeId=[0-9]+").matcher(url)
            if (m.find()) {
                m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)))
                if (m.find()) return m.group(0)
            }
            return null
        }
    }

//    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
//        val clazz4 = XposedHelpers.findClass(
//            "",
//            lpparam.classLoader)
//        Log.d("uw", clazz4.declaredFields.toString())
////        return true
//    }


}
