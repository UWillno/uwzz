package com.uuuuu.uwzz;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class uwc extends AppCompatActivity implements KeyEvent.Callback {
    private Button ga, to, ejs, sx;
    int ori;
    String waid = "0";
    int flag = 0;
    CountDownTimer cdt;
    CookieManager cookieManager;
    WebView webView;
    String url = "https://www.chaoxing.com";

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uwc);
        webView = findViewById(R.id.webview);
        // 开启JavaScript支持
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        webView.getSettings().setBuiltInZoomControls(true);
        // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
//        webView.getSettings().setDomStorageEnabled(true);
        // 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置此属性,可任意比例缩放,设置webview推荐使用的窗口
        webView.getSettings().setUseWideViewPort(true);
        // 设置webview加载的页面的模式,缩放至屏幕的大小
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        webView.addJavascriptInterface(new JavaObjectJsInterface(), "java_obj");
//        ht = findViewById(R.id.back);
//        ht.setOnClickListener(view -> webView.goBack());
//        ga = findViewById(R.id.getanswer);
//        ga.setOnClickListener(view ->
//        {
//            String ai = gid(webView.getUrl());
//            if (ai != null) {
//                String au = "https://mobilelearn.chaoxing.com/v2/apis/quiz/getStatisticsData?activeId=" + ai;
//                webView.setWebViewClient(new WebViewClient() {
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        super.onPageFinished(view, url);
//                        webView.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('pre')[0].innerHTML);");
//                    }
//                });
//                webView.loadUrl(au);
//            }
//        });

        to = findViewById(R.id.toold);
        to.setOnClickListener(view -> webView.loadUrl("https://mooc1-2.chaoxing.com/visit/courses"));
//        ejs = findViewById(R.id.ejs);
//        ejs.setOnClickListener(view -> startActivity(new Intent(this, ca.class)));
        sx = findViewById(R.id.sssssxxxxx);
        sx.setOnClickListener(view -> webView.reload());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.qdmenu, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    static String a="10086";

    public class JavaObjectJsInterface {
        @JavascriptInterface // 要加这个注解，不然调用不到
        public void onHtml(String html) {
            a = html;
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ssqd: {
                if (gid(webView.getUrl()) != null) {
                    webView.loadUrl("https://mobilelearn.chaoxing.com/v2/apis/sign/signIn?activeId=" + gid(webView.getUrl()));
                    Toast.makeText(this, "可能签到成功？自己返回刷新页面..我懒得写了", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.qrqd: {
                EditText editText = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("输入enc部分内容").setIcon(R.drawable.mm).setView(editText)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确认", (dialog, a) -> {
                    if (gid(webView.getUrl()) != null) {
                        webView.loadUrl("https://mobilelearn.chaoxing.com/pptSign/stuSignajax?activeId=" + gid(webView.getUrl()) + "&enc=" + editText.getText().toString() + "&fid=0");
                        Toast.makeText(this, "可能签到成功？自己返回刷新页面..我懒得写了", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
                break;
            }
            case R.id.zyda: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("选择功能").setIcon(R.drawable.mm)
                        .setNegativeButton("gid", ((dialogInterface, i) -> {
                            waid = gwaid(webView.getUrl());
                            if (!waid.equals("0"))
                                Toast.makeText(this, "可能获取成功了？去需要查看作业查看答案？", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(this, "你能不能先找个作业进去啊！焯！", Toast.LENGTH_LONG).show();

                        }));
                builder.setPositiveButton("查看答案", (dialog, a) -> {
                    if (!waid.equals("0")) {
                        webView.loadUrl(webView.getUrl().replaceAll("workAnswerId=[0-9]+", "workAnswerId=" + waid));
                        Toast.makeText(getApplicationContext(), "可能查看成功了？自己记下来答案返回重进再填写，失败就换gid", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(this, "我不懂，但我大受震撼！", Toast.LENGTH_LONG).show();
                });
                builder.show();
                break;
            }
            case R.id.cyda: {
                if (gid(webView.getUrl()) != null) {
                    String au = "https://mobilelearn.chaoxing.com/v2/apis/quiz/getStatisticsData?activeId=" + gid(webView.getUrl());
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
                            webView.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('pre')[0].innerHTML);");
                        }
                    });
                    webView.loadUrl(au);
                    Toast.makeText(this, "等3s我怕你网速太慢...", Toast.LENGTH_LONG).show();
                    CountDownTimer countDownTimer = new CountDownTimer(3000, 3000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            startActivity(new Intent(getApplicationContext(), ca.class));
                        }
                    };
                    countDownTimer.start();
                }
                break;
            }
            case R.id.hwtj: {
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        webView.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('pre')[0].innerHTML);");
//                        Log.d("uw",a);
                    }
                });
                if (!gcid(webView.getUrl()).equals("0") && !gcourseid(webView.getUrl()).equals("0")) {
                    webView.loadUrl("https://stat2-ans.chaoxing.com/work-stastics/student-works?clazzid=" + gcid(webView.getUrl()) + "&courseid=" + gcourseid(webView.getUrl()) + "&page=1&pageSize=200");
                }
                Toast.makeText(this, "等3s我怕你网速太慢...", Toast.LENGTH_LONG).show();
                CountDownTimer countDownTimer = new CountDownTimer(3000, 3000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(getApplicationContext(), tj.class));
                    }
                };
                countDownTimer.start();
                break;
            }
            case R.id.jftj: {
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        webView.loadUrl("javascript:window.java_obj.onHtml(document.getElementsByTagName('pre')[0].innerHTML);");
//                        Log.d("uw",a);
                    }
                });
                if (!gcid(webView.getUrl()).equals("0") && !gcourseid(webView.getUrl()).equals("0")) {
                    webView.loadUrl("https://mobilelearn.chaoxing.com/v2/apis/integral/getIntegralList?DB_STRATEGY=COURSEID&STRATEGY_PARA=courseId&pageSize=200&page=1&classId=" + gcid(webView.getUrl()) + "&courseId=" + gcourseid(webView.getUrl()));

                }
                Toast.makeText(this, "等3s我怕你网速太慢...", Toast.LENGTH_LONG).show();
                CountDownTimer countDownTimer = new CountDownTimer(3000, 3000) {
                    @Override
                    public void onTick(long l) {

                    }
                    @Override
                    public void onFinish() {
                        startActivity(new Intent(getApplicationContext(), jftj.class));
                    }
                };
                countDownTimer.start();
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    static public String gwaid(String url) {
        Matcher m = Pattern.compile("workAnswerId=[0-9]+").matcher(url);
        if (m.find()) {
            m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)));
            if (m.find())
                return m.group(0);
        }
        return "0";
    }

    static public String gcourseid(String url) {
        Matcher m = Pattern.compile("courseId=[0-9]+").matcher(url);
        if (m.find()) {
            m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)));
            if (m.find())
                return m.group(0);
        }
        return "0";
    }

    static public String gcid(String url) {
        Matcher m = Pattern.compile("clazzid=[0-9]+").matcher(url);
        if (m.find()) {
            m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)));
            if (m.find()) {
                return m.group(0);
            }
        }
        return "0";
    }

    static public String gid(String url) {
        Matcher m = Pattern.compile("activeId=[0-9]+").matcher(url);
        if (m.find()) {
            m = Pattern.compile("[0-9]+").matcher(Objects.requireNonNull(m.group(0)));
            if (m.find())
                return m.group(0);
        }
        return null;
    }
}