package com.urban.mvvmshowcase.utility;

import android.app.Activity;
import android.os.Bundle;

import org.robolectric.android.controller.ActivityController;

public final class ActivityUtil {
    private ActivityUtil() {
    }

    public static <T extends Activity> ActivityController<T> freshActivityStart(
            ActivityController<T> activityController) {
        return activityController.create().start().resume().visible();
    }

    public static <T extends Activity> ActivityController<T> restoredActivityStart(
            ActivityController<T> activityController) {
        return activityController.create().start().restoreInstanceState(new Bundle())
                .resume().visible();
    }
}
