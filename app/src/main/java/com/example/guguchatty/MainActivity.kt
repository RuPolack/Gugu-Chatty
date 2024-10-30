package com.example.guguchatty

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

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val database = Firebase.database //база данных
        val myRef = database.getReference("message1") // база данных

        val button: Button = findViewById(R.id.bSend)
        button.setOnClickListener {
            myRef.setValue(binding.edName.text.toString()) //отправка сообщений
            myRef.setValue(binding.edMessage.text.toString()) //отправка сообщений
        }
onChangeListener(myRef)

    }
fun onChangeListener(dRef: DatabaseReference){
    dRef.addValueEventListener(object : ValueEventListener{ // получение сообщуний
        override fun onDataChange(snapshot: DataSnapshot) {
            binding.apply {
            rcView.append("\n") // с новой строки
            rcView.append(" ${snapshot.value.toString()}") //дополнение сообщуний (ник)
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    })
}
}

