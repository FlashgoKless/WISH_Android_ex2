package com.example.wish_android_ex2

import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ToastMatcher : TypeSafeMatcher<Root>() {
    override fun describeTo(description: Description) {
        description.appendText("is a Toast")
    }

    override fun matchesSafely(root: Root): Boolean {
        val type = root.windowLayoutParams?.get()?.type
        if (type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY) {
            val windowToken: IBinder? = root.decorView.windowToken
            val appToken: IBinder? = root.decorView.applicationWindowToken
            return windowToken === appToken
        }
        return false
    }
}