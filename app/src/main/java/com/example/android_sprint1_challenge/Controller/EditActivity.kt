package com.example.android_sprint1_challenge.Controller

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_sprint1_challenge.Model.Movie
import com.example.android_sprint1_challenge.R
import kotlinx.android.synthetic.main.activity_edit.*
import com.example.android_sprint1_challenge.Application.sprintApplication.Companion.index
import kotlinx.android.synthetic.main.activity_edit.view.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        //set up save listener
        btn_save_movie.setOnClickListener {
            var intentSaveMovie = Intent()
            intentSaveMovie.putExtra("movie", createMovie())
            setResult(RESULT_OK, intentSaveMovie)
            finish()
        }
        //set up delete listener -- this more or less works but there
        btn_delete_movie.setOnClickListener {
            var intentSaveMovie = Intent()
            setResult(RESULT_CANCELED, intentSaveMovie)
            finish()
        }


        //more shameless theft/implementation from the AH project last night
        var bundle: Bundle? = intent.extras
        if (bundle != null) {
            loadMovie(bundle.getSerializable("tvMovie") as Movie)
        }

    }

    fun loadMovie(movie: Movie) {
        et_movie_title.setText(movie.title)
        if (movie.watch) {
            checkBox.isChecked = true
        }
    }


    fun createMovie(): Movie {
        var check: Boolean = false
        if (checkBox.isChecked) {
            check = true
        }
        val newMovie = Movie(et_movie_title.text.toString(), check, index)
        index++
        return newMovie
    }
}
