package com.example.android_sprint1_challenge.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_sprint1_challenge.R
import android.widget.TextView
import com.example.android_sprint1_challenge.Controller.EditActivity
import com.example.android_sprint1_challenge.Model.Movie
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    //create an array to store our movies
    var movieArray = mutableListOf<Movie>()

    companion object {
        //i honestly don't understand how the const work -- i.e. i can't articulate it but i know it works so... whatever?
        const val REQUEST_CODE_ADD_MOVIE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        //on botton click, send add movie to edit activity
        btn_add_movie.setOnClickListener {
            var addMovieIntent = Intent(this, EditActivity::class.java)
            startActivityForResult(addMovieIntent, REQUEST_CODE_ADD_MOVIE)
        }
    }

    fun createTextView(movie:Movie, index: Int): TextView {
        var newMovieView = TextView(this)
        newMovieView.textSize = 24f
        newMovieView.id = index
        newMovieView.text = movie.title

        newMovieView.setOnClickListener {
         // finish this later
        }
        return newMovieView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //when we get it back from edit activity...
        if (requestCode == REQUEST_CODE_ADD_MOVIE && resultCode == Activity.RESULT_OK) {
            val newMovie = data!!.getSerializableExtra("movie") as Movie
            movieArray.add(newMovie)
        }
    }

}
