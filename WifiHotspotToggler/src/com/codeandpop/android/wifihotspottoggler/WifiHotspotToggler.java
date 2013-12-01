/*
 * Copyright 2013 Jussi Jousimo, jvj@iki.fi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeandpop.android.wifihotspottoggler;

import com.whitebyte.wifihotspotutils.WIFI_AP_STATE;
import com.whitebyte.wifihotspotutils.WifiApManager;

import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;



public class WifiHotspotToggler extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WifiApManager wifiApManager = new WifiApManager(this);	
		WIFI_AP_STATE state = wifiApManager.getWifiApState();
		String message = "";
		
		if (state == WIFI_AP_STATE.WIFI_AP_STATE_DISABLING || state == WIFI_AP_STATE.WIFI_AP_STATE_DISABLED)
		{			
			boolean success = wifiApManager.setWifiApEnabled(null, true);
			if (success) message = "Enabling WiFi hiotspot..."; 
			else message = "Enabling WiFi hotspot failed.";
		}
		else if (state == WIFI_AP_STATE.WIFI_AP_STATE_ENABLING || state == WIFI_AP_STATE.WIFI_AP_STATE_ENABLED)
		{
			boolean success = wifiApManager.setWifiApEnabled(null, false);
			if (success) message = "Disabling WiFi hotspot..."; 
			else message = "Disabling WiFi hotspot failed.";
		}
		else if (state == WIFI_AP_STATE.WIFI_AP_STATE_FAILED)
		{
			message = "Unable to toggle WiFi hotspot. Check the system configuration or WiFi hotspot is unsupported by the device.";
		}
		
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		
		finish();
	}
}
