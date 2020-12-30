package com.jintin.viewmodelsample

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.jintin.viewmodelsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edit.addTextChangedListener {
            viewModel.setText(it.toString())
        }
        showKeyboard(binding.edit)
        viewModel.textLiveData.observe(this) {
            binding.text.text = "Text: $it"
        }
        viewModel.textLengthLiveData.observe(this) {
            binding.length.text = "Length: $it"
        }
    }

    private fun showKeyboard(edit: EditText) {
        edit.postDelayed({
            edit.requestFocus()
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT)
        }, 500)
    }
}