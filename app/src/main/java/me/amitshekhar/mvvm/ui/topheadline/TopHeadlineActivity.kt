package me.amitshekhar.mvvm.ui.topheadline

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import me.amitshekhar.mvvm.MVVMApplication
import me.amitshekhar.mvvm.R
import me.amitshekhar.mvvm.data.model.Article
import me.amitshekhar.mvvm.databinding.ActivityTopHeadlineBinding
import me.amitshekhar.mvvm.di.component.DaggerActivityComponent
import me.amitshekhar.mvvm.di.module.ActivityModule
import me.amitshekhar.mvvm.ui.base.UiState
import javax.inject.Inject

class TopHeadlineActivity : AppCompatActivity() {

    @Inject
    lateinit var topHeadlineViewModel: TopHeadlineViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        setThemeFromPreferences()
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MVVMArchitectureAndroid)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()



    }

    private fun setThemeFromPreferences() {
        val currentTheme = AppCompatDelegate.getDefaultNightMode()
        val preferredTheme = getPreferredTheme()
        if (preferredTheme != currentTheme) {
            AppCompatDelegate.setDefaultNightMode(preferredTheme)
            recreate()
        }
    }

    private fun getPreferredTheme(): Int {
        // Implement logic to retrieve user's theme preference (e.g., from SharedPreferences)
        // Return AppCompatDelegate.MODE_NIGHT_YES, AppCompatDelegate.MODE_NIGHT_NO, or AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        // For simplicity, return AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM as default
        return AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@TopHeadlineActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true

        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }



}
