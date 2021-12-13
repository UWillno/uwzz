package com.uuuuu.uwzz;

import android.content.Context;
import android.view.View;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.util.List;

public class uwzz implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {


        if (lpparam.packageName.equals("com.example.currilculumdesign")) {
            Class clazz = XposedHelpers.findClass("com.example.currilculumdesign.util.ParseEduUtil", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(clazz, "check_dex", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(true);
                }
            });
            Class clazz1 = XposedHelpers.findClass("com.example.currilculumdesign.MainActivity", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(clazz1, "showPopupMenu", View.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return null;
                }
            });
            XposedHelpers.findAndHookMethod(clazz1, "checkUpdate", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return null;
                }
            });
            //https://www.lanzouw.com/b02ckh7nc
            Class clazz4 = XposedHelpers.findClass("com.example.currilculumdesign.adapter.AttendDetailAdapter", lpparam.classLoader);

            XposedHelpers.findAndHookConstructor(clazz4, Context.class, List.class, int.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[3] = "1";
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.args[3] = "1";
                }
            });

            Class clazz3 = XposedHelpers.findClass("com.example.currilculumdesign.util.HttpGetToCheck", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(clazz3, "isUpdated", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.setResult(true);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(true);
                }
            });
        }
    }
}
