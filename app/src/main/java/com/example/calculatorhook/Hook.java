package com.example.calculatorhook;

import java.math.BigDecimal;
import java.math.BigInteger;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if(lpparam.packageName.equals("com.example.calculator")){
            Class<?> mainCls= XposedHelpers.findClass("com.example.calculator.MyListener",lpparam.classLoader);
            XposedHelpers.findAndHookMethod(mainCls, "calculateFour",new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    BigDecimal error = new BigDecimal(BigInteger.ZERO);
                    param.setResult(error);
                }
            });
        }
    }
}
