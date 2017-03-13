
package com.fanrt.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.net.Uri;

public class UninstallOtherAppPlugin extends CordovaPlugin {
    public static final String UNINSTALL_ACTION = "uninstall";
    public static final String CHECKAPPVERSION_ACTION = "checkAppVersion";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(UNINSTALL_ACTION)) {
            String appPackage = args.getString(0);
            appUninstall(appPackage, callbackContext);
            return true;
        } else if (action.equals(CHECKAPPVERSION_ACTION)) {
            String appPackage = args.getString(0);
            String appVersion = args.getString(1);
            checkAppVersion(appPackage, appVersion, callbackContext);
        }

        return true;
    }

    // Thanks to http://floresosvaldo.com/android-cordova-plugin-checking-if-an-app-exists
    private void appUninstall(String appPackage, CallbackContext callbackContext) {
        Context ctx = this.cordova.getActivity().getApplicationContext();
        final PackageManager pm = ctx.getPackageManager();
        try {
            pm.getPackageInfo(appPackage, PackageManager.GET_ACTIVITIES);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:" + appPackage));
            cordova.getActivity().startActivity(intent);
            callbackContext.success("uninstallSuccess");
        }
        catch(PackageManager.NameNotFoundException e) {
            callbackContext.error("appNotFound");
        }
    }

    private void checkAppVersion(String appPackage, String appVersion, CallbackContext callbackContext) {
        Context ctx = this.cordova.getActivity().getApplicationContext();
        final PackageManager pm = ctx.getPackageManager();
        try {
            pm.getPackageInfo(appPackage, PackageManager.GET_ACTIVITIES);
            if (!appVersion.equals(pm.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionName)) {
                callbackContext.success("correctApp");
            } else {
                callbackContext.error("versionError");
            }
        }
        catch(PackageManager.NameNotFoundException e) {
            callbackContext.error("appNotFound");
        }
    }
}  
