package com.example.exo101

import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import java.io.File

class MainActivity : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init player
        player = SimpleExoPlayer.Builder(this).build()

        // repeat infinite loop
        player!!.repeatMode = Player.REPEAT_MODE_ALL

        // set media source
        val playlist = arrayListOf(
            //"/sdcard/video1.mp4",
            //"/sdcard/video2.mp4",
            //"/sdcard/video3.mp4",
            R.raw.coke,
            R.raw.pepsi,
        )
        playlist.forEach {
            //val item = MediaItem.fromUri(Uri.fromFile(File(it)))
            val rawDataSource = RawResourceDataSource(this@MainActivity)
            rawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(it)))
            val item = MediaItem.fromUri(rawDataSource.uri!!)
            player!!.addMediaItem(item)
        }
        player!!.prepare()

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