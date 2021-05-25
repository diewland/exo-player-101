package com.example.exo101

import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import java.io.File

class MainActivity : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init player
        player = SimpleExoPlayer.Builder(this).build()

        // set media source
        val uri = Uri.fromFile(File("/sdcard/video1.mp4"))
        player?.apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
        }

        // prepare player view
        val playerView = PlayerView(this)
        playerView.useController = false
        //playerView.layoutParams = LinearLayout.LayoutParams(
        //    LinearLayout.LayoutParams.MATCH_PARENT,
        //    LinearLayout.LayoutParams.MATCH_PARENT
        //)

        // set player to player view
        playerView.player = player

        // add player view to screen
        val root = findViewById<LinearLayout>(R.id.ll_root)
        root.addView(playerView)
    }

    override fun onResume() {
        super.onResume()
        player?.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        player?.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}