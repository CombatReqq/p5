package viewmodel

import Repository.PostRepository
import Repository.PostRepositoryInMemoryImpl
import androidx.lifecycle.ViewModel


class  PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}