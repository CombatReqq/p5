package ru.btpit.p2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.btpit.p2.databinding.ActivityMainBinding
import viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val viewModel:  PostViewModel by viewModels()
        viewModel.data.observe(this){post ->
            with(binding){
                textView.text = post.author
                textView2.text = post.published
                textView3.text = post.content
                LikeCount.text = formatNumber(post.likeCount)
                ShareCount.text = formatNumber(post.shareCount)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.like_red
                else R.drawable.like1
                )
            }
        }
binding.like.setOnClickListener{
    viewModel.like()
}
        binding.share.setOnClickListener{
            viewModel.share()
        }
    }
    private fun formatNumber(number: Int): String {
        return when {
            number >= 1000000 -> {
                val value = number / 1000000
                val remainder = number % 1000000
                if (remainder > 0) {
                    if (remainder >= 100000) {
                        String.format("%.1f M", value + remainder / 1000000.0)
                    } else {
                        String.format("%d.%d M", value, remainder / 100000)
                    }
                } else {
                    "$value M"
                }
            }
            number in 1000..9999 -> {
                String.format("%.1fK", number / 1000.0)
            }
            number >= 10000 -> {
                String.format("%dK", number / 1000)
            }
            else -> number.toString()
        }
    }


}