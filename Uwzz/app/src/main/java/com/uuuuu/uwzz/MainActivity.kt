package com.uuuuu.uwzz

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.jaredrummler.android.shell.Shell
import com.uuuuu.uwzz.ca.ss
import com.uuuuu.uwzz.databinding.ActivityMainBinding

import java.io.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ycccbbbb.setOnLongClickListener(OnLongClickListener {
            startActivity(Intent(this, Uwc::class.java))
            true
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    @SuppressLint("NonConstantResourceId", "SdCardPath")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val cm = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        when (item.itemId) {
            R.id.gitee -> {
                cm.setPrimaryClip(ClipData.newPlainText(null, "http://gitee.com/uwillno/uwzz"))
                Toast.makeText(this, "已复制项目链接到剪切板！", Toast.LENGTH_SHORT).show()
            }
            R.id.qqq -> {
                cm.setPrimaryClip(ClipData.newPlainText(null, "https://jq.qq.com/?_wv=1027&k=0ml338o9"))
                Toast.makeText(this, "已复制加群链接到剪切板！", Toast.LENGTH_SHORT).show()
            }
            R.id.http1 -> {
                cm.setPrimaryClip(ClipData.newPlainText(null, "https://www.lanzoux.com/b02ckh7nc?w"))
                Toast.makeText(this, "已复制蓝奏云链接到剪切板！", Toast.LENGTH_SHORT).show()
            }
            R.id.skip -> {
                val op = getExternalFilesDir(null).toString()
                val path=getExternalFilesDir("zzzz")
//                Log.d("uw",path.toString())
                val shell = "cd $path/\n" +
                        "cp -r -f com.example.currilculumdesign_preferences.xml com.example.currilculumdesign/shared_prefs/\n" +
                        "cp -r -f com.example.currilculumdesign /data/data/\n" +
                        "chmod -R 0777 /data/data/com.example.currilculumdesign/"
                copyfile("U", op)
                Shell.SU.run(shell)
                val re = sh(shell)
                when (re) {
                    "??" -> Toast.makeText(this, "没权限或给了依旧这样，就还是自己手动移动文件吧，我搞不来了！", Toast.LENGTH_LONG).show()
                    else -> Toast.makeText(this, "不生效就去手动执行sh吧，已放到sd根目录和$re ", Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun sh(shell: String): String {
        pms(this)
        try {
            val file = File(getExternalFilesDir("UWillno"), "跳过教务登录.sh")
            val file1 = File("/sdcard/", "跳过教务登录.sh")
            val ot1 = FileOutputStream(file1)
            val ot = FileOutputStream(file)
            ot1.write(shell.toByteArray())
            ot.write(shell.toByteArray())
            ot.close()
            ot1.close()
            return getExternalFilesDir("UWillno").toString()
        } catch (t: Throwable) {
            t.printStackTrace()
            return "??"
        }
    }

    fun copyfile(ap: String, np: String) {
        try {
            val fileNames = assets.list(ap)
            if (fileNames!!.size > 0) {
                for (fileName in fileNames) {
                    var newAssetsPath: String
                    newAssetsPath = if ("" == ap || "/" == ap) {
                        fileName
                    } else {
                        if (ap.endsWith("/")) {
                            ap + fileName
                        } else {
                            "$ap/$fileName"
                        }
                    }
                    copyfile(newAssetsPath, "$np/$fileName")
                }
            } else {
                val file = File(np)
                file.parentFile.mkdirs()
                val `is` = assets.open(ap)
                val fos = FileOutputStream(np)
                val buffer = ByteArray(1024)
                var byteCount: Int
                while (`is`.read(buffer).also { byteCount = it } != -1) {
                    fos.write(buffer, 0, byteCount)
                }
                fos.flush()
                fos.close()
                `is`.close()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }


    fun pms(obj: Activity?) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager() == false -> {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                val uri: Uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.R -> {
                for (value in PERMISSIONS_STORAGE) {
                    if (ActivityCompat.checkSelfPermission(
                            obj!!,
                            value
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(obj, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE)
                    }
                }
            }
        }
    }

    companion object {
        private val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )
        private const val REQUEST_PERMISSION_CODE = 3
    }
}
