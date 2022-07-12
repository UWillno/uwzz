package com.uuuuu.uwzz

import android.content.Context
import android.view.View
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XC_MethodHook
import kotlin.Throws
import de.robv.android.xposed.XC_MethodReplacement

class Uwzz : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName == "com.example.currilculumdesign") {
            val clazz = XposedHelpers.findClass("com.example.currilculumdesign.util.ParseEduUtil", lpparam.classLoader)
            XposedHelpers.findAndHookMethod(clazz, "check_dex", Context::class.java, object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    param.result = true
                }
            })
            val clazz1 = XposedHelpers.findClass("com.example.currilculumdesign.MainActivity", lpparam.classLoader)
            XposedHelpers.findAndHookMethod(clazz1, "showPopupMenu", View::class.java, object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any? {
                    return null
                }
            })
            XposedHelpers.findAndHookMethod(clazz1, "checkUpdate", object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam): Any? {
                    return null
                }
            })
            //https://www.lanzouw.com/b02ckh7nc
            val clazz4 = XposedHelpers.findClass(
                "com.example.currilculumdesign.adapter.AttendDetailAdapter",
                lpparam.classLoader
            )
            XposedHelpers.findAndHookConstructor(
                clazz4,
                Context::class.java,
                MutableList::class.java,
                Int::class.javaPrimitiveType,
                String::class.java,
                object : XC_MethodHook() {
                    @Throws(Throwable::class)
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        super.beforeHookedMethod(param)
                        param.args[3] = "1"
                    }

                    @Throws(Throwable::class)
                    override fun afterHookedMethod(param: MethodHookParam) {
                        super.afterHookedMethod(param)
                        param.args[3] = "1"
                    }
                })
            val clazz3 =
                XposedHelpers.findClass("com.example.currilculumdesign.util.HttpGetToCheck", lpparam.classLoader)
            XposedHelpers.findAndHookMethod(clazz3, "isUpdated", Context::class.java, object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    param.result = true
                }
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: MethodHookParam) {
                    super.afterHookedMethod(param)
                    param.result = true
                }
            })
        }
    }
}