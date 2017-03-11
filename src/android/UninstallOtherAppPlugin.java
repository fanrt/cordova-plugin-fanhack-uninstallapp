
package com.fanrt.plugin;  
  
import org.apache.cordova.CallbackContext;  
import org.apache.cordova.CordovaPlugin;  
import org.apache.cordova.PluginResult;  
import org.json.JSONArray;  
import org.json.JSONException;  
  
import android.app.Activity;  
import android.content.ComponentName;  
import android.content.Intent;  
import android.os.Bundle;  
import android.util.Log;  
import android.content.Intent;
import android.net.Uri;
  
public class UninstallOtherAppPlugin extends CordovaPlugin {  
	public static final String ACTION = "uninstall";  
	  
	@Override  
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {  
		if (action.equals(ACTION)) {
			String appPackage = args.getString(0);
			String appVersion = args.getString(1);
			checkApp(appPackage, appVersion);
			return true;
		}  
  
		return true;  
	}

	// Thanks to http://floresosvaldo.com/android-cordova-plugin-checking-if-an-app-exists
	public boolean appInstalled(String appPackage, String appVersion) {
		Context ctx = this.cordova.getActivity().getApplicationContext();
		final PackageManager pm = ctx.getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo(appPackage, PackageManager.GET_ACTIVITIES);
			ApplicationInfo app = packageManager.getApplicationInfo(appPackage, 0);
			if (appVersion.equals(app.versionCode)) {
				app_installed = true;
			} else {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_DELETE);
				intent.setData(Uri.parse("package:" + appPackage));
				cordova.getActivity().startActivity(intent);
				app_installed = false;
			}
		}
		catch(PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	private void checkApp(String appPackage, String appVersion, CallbackContext callbackContext) {
		if(appUninstall(appPackage, appVersion)) {
			callbackContext.success("1");
		}
		else {
			callbackContext.error("0");
		}
	}
}  
