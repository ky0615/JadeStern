package ws.temp.jadestern.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ws.temp.jadestern.R

class PrefaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preface)
    }

    companion object {
        private val TAG = PrefaceActivity::class.java.simpleName
    }
}
