package com.example.myapplication.ui.screen

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class MainActivity : ComponentActivity() {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fullscreen immersive mode
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        // Initialize ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build().apply {
            val mediaItem = MediaItem.fromUri("http://10.0.2.2:8080/background-HD.mp4")
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }

        // Set up PlayerView
        playerView = PlayerView(this).apply {
            player = exoPlayer
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        setContentView(playerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}