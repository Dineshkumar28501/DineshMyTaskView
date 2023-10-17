package com.sgs.mytaskview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.sgs.mytaskview.database.LoginDataBase
import com.sgs.mytaskview.database.LoginFactory
import com.sgs.mytaskview.database.LoginRepo
import com.sgs.mytaskview.database.LoginViewModel
import com.sgs.mytaskview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, LoginFactory(LoginRepo(LoginDataBase.getDatabase(this@MainActivity))))[LoginViewModel::class.java]

        val callback = this.onBackPressedDispatcher.addCallback(this) {
            finish()
          }

        val textWatchers = listOf(
            binding.name, binding.password,
        ).map { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    updateSubmitButtonBackground()
                }
              })
           }

        updateSubmitButtonBackground()


        binding.save.setOnClickListener {

            when{
                binding.name.text.isNullOrEmpty()->{
                    Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
                }
                binding.name.text.isNullOrEmpty()->{
                    Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
                }
                else->{
                    val username = binding.name.text.toString()
                    val password = binding.password.text.toString()
                    viewModel.login(username, password)
                }
            }
        }

        viewModel.loginResultLiveData.observe(this) { isLoginSuccessful ->
            when (isLoginSuccessful) {
                true -> {

                    binding.name.setText("")
                    binding.password.setText("")

                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    intent = Intent(this@MainActivity,DetailsView::class.java)
                    startActivity(intent)
                }
                false -> {

                    binding.name.setText("")
                    binding.password.setText("")

                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    intent = Intent(this@MainActivity,Register::class.java)
                    startActivity(intent)
                }
                null -> {
                    Toast.makeText(this, "Please Check", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateSubmitButtonBackground() {
        val allFieldsFilled =
            listOf(binding.name, binding.password).all { editText ->
                editText.text.toString().isNotEmpty()
            }
        if (allFieldsFilled) {
            binding.save.setBackgroundResource(R.drawable.background2)
        } else {
            binding.save.setBackgroundResource(R.drawable.background1)
            }
        }

}