package com.example.guguchatty

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.guguchatty.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity3 : AppCompatActivity() {

    lateinit var binding3:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding3 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding3.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val database2 = Firebase.database //база данных
        val myRef = database2.getReference("message3") // база данных

        val button: Button = findViewById(R.id.bSend)
        button.setOnClickListener {
            myRef.setValue(binding3.edName.text.toString()) //отправка сообщений
            myRef.setValue(binding3.edMessage.text.toString()) //отправка сообщений
        }
        onChangeListener(myRef)

    }
    fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{ // получение сообщуний
            override fun onDataChange(snapshot: DataSnapshot) {
                binding3.apply {
                    rcView.append("\n") // с новой строки
                    rcView.append(" ${snapshot.value.toString()}") //дополнение сообщуний (ник)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}