package xyz.ecbn.moviemvvm.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_detail_image.*
import org.koin.android.ext.android.inject
import xyz.ecbn.moviemvvm.BuildConfig
import xyz.ecbn.moviemvvm.R
import xyz.ecbn.moviemvvm.data.model.Image

class DetailImageActivity : AppCompatActivity() {
    private val glide: RequestManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_image)

        val image = intent.getParcelableExtra<Image>("image")
        glide.load(BuildConfig.BASE_URL_IMAGE.plus("original") + image?.filePath)
            .into(myZoomageView)
    }
}
