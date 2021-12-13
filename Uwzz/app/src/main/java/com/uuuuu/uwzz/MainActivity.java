package com.uuuuu.uwzz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import java.io.*;

public class MainActivity extends AppCompatActivity {
    ImageButton ib;
    String shell = "cd /sdcard/zzzz/\n" + "a=$(ls -l /data/data/ | grep com.example.currilculumdesign)\n" +
            "b=${a:14:25}\n" +
            "c=${b% u*}\n" +
            "cp -r -f com.example.currilculumdesign_preferences.xml com.example.currilculumdesign/shared_prefs/\n" +
            "cp -r com.example.currilculumdesign /data/data/\n" +
            "chgrp $c /data/data/com.example.currilculumdesign/shared_prefs\n" +
            "chown $c /data/data/com.example.currilculumdesign/shared_prefs\n" +
            "chmod 0755 /data/data/com.example.currilculumdesign/shared_prefs\n" +
            "chgrp $c /data/data/com.example.currilculumdesign/databases\n" +
            "chown $c /data/data/com.example.currilculumdesign/databases\n" +
            "chmod 0755 /data/data/com.example.currilculumdesign/databases\n" +
            "chgrp $c /data/data/com.example.currilculumdesign/databases/zzs.db\n" +
            "chown $c /data/data/com.example.currilculumdesign/databases/zzs.db\n" +
            "chmod 0660 /data/data/com.example.currilculumdesign/databases/zzs.db\n" +
            "chgrp $c /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml\n" +
            "chown $c /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml\n" +
            "chmod 0660 /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ib = findViewById(R.id.ycccbbbb);
        ib.setOnLongClickListener(view -> {
            startActivity(new Intent(this, uwc.class));
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void RootCmd(String cmd) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception ignored) {

        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                assert process != null;
                process.destroy();
            } catch (Exception ignored) {
            }
        }
    }


    @SuppressLint({"NonConstantResourceId", "SdCardPath"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gitee: {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText("http://gitee.com/uwillno/uwzz");
                Toast.makeText(this, "已复制项目链接到剪切板！", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.qqq: {
                ClipboardManager cm1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm1.setText("https://jq.qq.com/?_wv=1027&k=0ml338o9");
                Toast.makeText(this, "已复制加群链接到剪切板！", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.http1: {
                ClipboardManager cm2 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm2.setText("https://www.lanzoux.com/b02ckh7nc?w");
                Toast.makeText(this, "已复制蓝奏云链接到剪切板！", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.uwzz: {
                startActivity(new Intent(this, log.class));
                break;
            }
            case R.id.skip: {
                RootCmd(shell);
                Toast.makeText(getApplicationContext(), "不生效就手动执行.sh，我也不会了", Toast.LENGTH_LONG).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean copyFileFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }


    private static final String[] PERMISSIONS_STORAGE = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //            android.Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_PERMISSION_CODE = 3;

    public void pms(Activity obj) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            for (String value : PERMISSIONS_STORAGE) {
                if (ActivityCompat.checkSelfPermission(obj,
                        value) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(obj, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
                }
            }
        }
    }

}