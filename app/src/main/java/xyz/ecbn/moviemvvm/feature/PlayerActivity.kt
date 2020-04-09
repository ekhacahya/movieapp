package xyz.ecbn.moviemvvm.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_player.*
import xyz.ecbn.moviemvvm.R


class PlayerActivity : AppCompatActivity() {
    private val TAG = PlayerActivity::class.java.name

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        url = intent.getStringExtra("url")
        lifecycle.addObserver(youtubePlayer)
        youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(url.toString(), 0F)
            }
        })

    }

}
