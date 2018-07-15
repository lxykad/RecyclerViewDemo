package com.lxy.recyclerview.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lxy.recyclerview.MainActivity
import com.lxy.recyclerview.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var intent = Intent(this, SecondActivity::class.java)
        mBtSecond.setOnClickListener { startActivity(intent) }

        var main = Intent(this, MainActivity::class.java)
        mBtMain.setOnClickListener { startActivity(main) }

        var timeline = Intent(this, HTimeLineActivity::class.java);
        mBtTimeLine.setOnClickListener { startActivity(timeline) }

        var vh = Intent(this, ScrollRecyclerViewActivity::class.java);
        mbtVH.setOnClickListener { startActivity(vh) }
    }
}
