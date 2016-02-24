package ws.temp.jadestern

import android.app.Application
import android.test.ApplicationTestCase
import android.util.Log

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
class ApplicationTest : ApplicationTestCase<Application>(Application::class.java) {
    init {
        Log.d(TAG, "ApplicationTest: ")
    }

    companion object {
        private val TAG = ApplicationTest::class.java.simpleName
    }
}