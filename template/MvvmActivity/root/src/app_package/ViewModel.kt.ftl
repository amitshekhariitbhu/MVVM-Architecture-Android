package ${escapeKotlinIdentifiers(packageName)}.${folderName}.viewmodel;

import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.data.repository.MainRepository
import io.reactivex.disposables.CompositeDisposable

class ${className}ViewModel(private val repository: MainRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}