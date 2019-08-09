package com.example.android_sprint1_challenge.Controller

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_sprint1_challenge.Model.Movie
import com.example.android_sprint1_challenge.R
import kotlinx.android.synthetic.main.activity_edit.*
import com.example.android_sprint1_challenge.Application.sprintApplication.Companion.index

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        //set up save listener
        btn_save_movie.setOnClickListener {
            var intentSaveMovie = Intent()
            intentSaveMovie.putExtra("movie", createMovie().title)
            intentSaveMovie.putExtra("movie", createMovie().watch)
            intentSaveMovie.putExtra("movie", createMovie().index)
            setResult(RESULT_OK, intentSaveMovie)
            finish()
        }
       //set up delete listener
        btn_delete_movie.setOnClickListener {
        }

    }
    fun createMovie(): Movie {
        var check:Boolean= false
        if (checkBox.isChecked) {
            check= true
        }
        var newMovie = Movie(et_movie_title.text.toString(),check,index)
        return newMovie
    }
}
