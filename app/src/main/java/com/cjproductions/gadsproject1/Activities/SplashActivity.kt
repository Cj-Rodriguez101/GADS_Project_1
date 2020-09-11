package com.cjproductions.gadsproject1.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.cjproductions.gadsproject1.R


class SplashActivity : BaseActivity() {

    private lateinit var handler: Handler
    private lateinit var splashImage: ImageView
    override fun displayProgressBar(bool: Boolean) {
        //ignore since no progress shows in splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashImage = findViewById(R.id.splash_image)
        val performAnimation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        performAnimation?.start()
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, LeaderBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}