package com.rbk.coroutineex

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var counter = 0
        var btn: Button = findViewById(R.id.btnAdd)
        var textView: TextView = findViewById(R.id.text_view)


        btn.setOnClickListener() {
            counter++
            textView.text = counter.toString()

        }

        //understanding how to switch between scopes inside coroutine withContext
         val mainscope=CoroutineScope(Dispatchers.Main)
        val backgroundScope=CoroutineScope(Dispatchers.IO)

        mainscope.launch {
            //coroutineContext variable accessible from coroutine scope or suspending function
            Log.d("TAG","Using the ${coroutineContext}")

            //switching to IO Dispatchers using withCOntext
            withContext(Dispatchers.IO){
                Log.d("TAG","Using the ${coroutineContext}")
                Log.d("TAG","You are in Background Thread")
                delay(1000)
            }

            //switching to main dispatchers again
            Log.d("TAG","Using the ${coroutineContext}")

        }




/*       scope.launch {

           downloadBigFile()
       }*/
    }

    private fun downloadBigFile() {
        for (i in 1..100000){
            Log.d("TAG","Downloading: $i")
        }
    }
}