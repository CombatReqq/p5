package Repository

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен ->  http://netology.gy/fyb",
        published =  "21 мая в 18:36",
        likedByMe = false,
        likeCount =  999,
        shareCount = 11999
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like(){
        post = post.copy(likedByMe = !post.likedByMe)
        if(post.likedByMe)
        {
            post = post.copy(likeCount = post.likeCount + 1)
        }
        else {
            post = post.copy(likeCount = post.likeCount - 1)
        }
        data.value = post

    }

    override fun share(){
        post = post.copy(shareCount = post.shareCount + 1)
        data.value = post
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

   private fun updateCountText(count: Int, textView: TextView) {
        val text = formatNumber(count)
        textView.text = text
    }


}
