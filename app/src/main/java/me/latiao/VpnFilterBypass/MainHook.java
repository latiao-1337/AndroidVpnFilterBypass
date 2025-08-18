package me.latiao.VpnFilterBypass;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod(
                "android.net.VpnService$Builder",
                lpparam.classLoader,
                "addDisallowedApplication",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        param.setResult(param.thisObject);
                    }
                }
        );

        // Hook VpnService.Builder.addAllowedApplication
        XposedHelpers.findAndHookMethod(
                "android.net.VpnService$Builder",
                lpparam.classLoader,
                "addAllowedApplication",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        param.setResult(param.thisObject);
                    }
                }
        );
    }
}
