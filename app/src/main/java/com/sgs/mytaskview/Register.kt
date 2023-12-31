package com.sgs.mytaskview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sgs.mytaskview.database.*
import com.sgs.mytaskview.databinding.ActivityRegisterBinding
import kotlinx.coroutines.delay

class Register : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = LoginRepo(LoginDataBase.getDatabase(this@Register))
        val factory = LoginFactory(repo)
        viewModel = ViewModelProvider(this,factory)[LoginViewModel::class.java]


        listOf(
            binding.userName, binding.password,binding.mobile,binding.lastName,binding.firstName
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

        binding.register.setOnClickListener {
            when{
                binding.userName.text.isNullOrEmpty()->{
                    Toast.makeText(this@Register, "Enter User Name", Toast.LENGTH_SHORT).show()
                }
                binding.password.text.isNullOrEmpty()->{
                    Toast.makeText(this@Register, "Enter Password", Toast.LENGTH_SHORT).show()
                }
                binding.mobile.text.isNullOrEmpty()->{
                    Toast.makeText(this@Register, "Enter Mobile number", Toast.LENGTH_SHORT).show()
                }
                binding.firstName.text.isNullOrEmpty()->{
                    Toast.makeText(this@Register, "Enter First Name", Toast.LENGTH_SHORT).show()
                }
                binding.lastName.text.isNullOrEmpty()->{
                    Toast.makeText(this@Register, "Enter Last Name", Toast.LENGTH_SHORT).show()
                }

                else->{
                        addItems()
                }
            }

        }

    }
    private fun addItems(){
        lifecycleScope.launchWhenStarted {
            viewModel.insert(
                LoginModel(
                    binding.userName.text.toString(),
                    binding.password.text.toString(),
                    binding.mobile.text.toString(),
                    binding.firstName.text.toString(),
                    binding.lastName.text.toString(),
                    0

                )
            )
            delay(1000)
            clear()
        }
    }

    private fun clear(){
        binding.userName.setText("")
        binding.password.setText("")
        binding.mobile.setText("")
        binding.firstName.setText("")
        binding.lastName.setText("")
        Toast.makeText(this@Register, "Save Successfully", Toast.LENGTH_SHORT).show()
        intent = Intent(this@Register,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun updateSubmitButtonBackground() {
        val allFieldsFilled =
            listOf( binding.userName, binding.password,binding.mobile,binding.lastName,binding.firstName).all { editText ->
                editText.text.toString().isNotEmpty()
            }
        if (allFieldsFilled) {
            binding.register.setBackgroundResource(R.drawable.background2)
        } else {
            binding.register.setBackgroundResource(R.drawable.background1)
        }
    }

}