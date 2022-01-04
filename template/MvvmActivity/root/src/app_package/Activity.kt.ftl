package ${escapeKotlinIdentifiers(packageName)}.${folderName}.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.data.api.ApiHelper
import com.mindorks.framework.mvvm.data.api.ApiServiceImpl
import com.mindorks.framework.mvvm.ui.base.ViewModelFactory


class ${className}Activity : AppCompatActivity() {

    private lateinit var viewModel: ${className}ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${activityLayout})
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {

    }

    private fun setupObserver() {

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(${className}ViewModel::class.java)
    }
}
