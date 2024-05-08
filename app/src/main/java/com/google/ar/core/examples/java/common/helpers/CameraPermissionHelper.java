/*
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.ar.core.examples.java.common.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/** Helper to ask camera permission. */
public final class CameraPermissionHelper {
  private static final int CAMERA_PERMISSION_CODE = 0;
  private static final String[] CAMERA_PERMISSION = new String[] {
          Manifest.permission.CAMERA,
          Manifest.permission.BLUETOOTH,
          Manifest.permission.BLUETOOTH_ADMIN,
          Manifest.permission.BLUETOOTH_SCAN,
          Manifest.permission.BLUETOOTH_ADVERTISE,
          Manifest.permission.BLUETOOTH_CONNECT,
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.ACCESS_WIFI_STATE,
          Manifest.permission.CHANGE_WIFI_STATE,

          // permission required by UWB API
          Manifest.permission.UWB_RANGING,
          Manifest.permission.NEARBY_WIFI_DEVICES
  };

  /** Check to see we have the necessary permissions for this app. */
  public static boolean hasCameraPermission(Activity activity) {
    for (String permission : CAMERA_PERMISSION) {
      if (!(ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED)) {
        return false;
      }
    }
    return true;
  }

  /** Check to see we have the necessary permissions for this app, and ask for them if we don't. */
  public static void requestCameraPermission(Activity activity) {
    ActivityCompat.requestPermissions(
        activity, CAMERA_PERMISSION, CAMERA_PERMISSION_CODE);
  }

  /** Check to see if we need to show the rationale for this permission. */
  public static boolean shouldShowRequestPermissionRationale(Activity activity) {
    for (String permission : CAMERA_PERMISSION) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
        return true;
      }
    }
    return false;
  }

  /** Launch Application Setting to grant permission. */
  public static void launchPermissionSettings(Activity activity) {
    Intent intent = new Intent();
    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
    activity.startActivity(intent);
  }
}
