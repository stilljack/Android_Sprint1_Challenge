package com.example.android_sprint1_challenge.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        const val REQUEST_CODE_EDIT_MOVIE = 2
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

    //so tried mostly rewriting this code from last nights intent After hours, but I'm def screwing it up -- working on trying to just ge
    // anything working now
    fun refreshBookList(){
        ll_movie_list.removeAllViews()
        for((i,movie) in movieArray.withIndex()){
            ll_movie_list.addView(createTextView(movie))
        }
    }

    override fun onPostResume() {
        refreshBookList()
        super.onPostResume()
    }


    fun createTextView(movie:Movie): TextView {
        var newMovieView = TextView(this)
        newMovieView.textSize = 24f
        newMovieView.id = movie.index
        var watched ="init"
        when {
            movie.watch -> watched ="Watched"
            else -> watched ="Not Watched"
        }
        newMovieView.text = "${movie.title} -- $watched -- ${movie.index}"

        newMovieView.setOnClickListener {
            var tvIntent = Intent(this, EditActivity::class.java)
            tvIntent.putExtra("tvMovie", movie)
            startActivityForResult(tvIntent, REQUEST_CODE_EDIT_MOVIE)
            movieArray.removeAt(movie.index)

        }
        return newMovieView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //when we get it back from edit activity...
        if (requestCode == REQUEST_CODE_ADD_MOVIE && resultCode == Activity.RESULT_OK) {
            val newMovie = data!!.getSerializableExtra("movie") as Movie
                movieArray.add(newMovie)

        } else if (requestCode ==  REQUEST_CODE_EDIT_MOVIE && resultCode == Activity.RESULT_OK) {
            val editMovie = data!!.getSerializableExtra("movie") as Movie
            movieArray.add(editMovie)
        }
        //so i believe this will trigger if we get delete, shouldn't have to do much
        else if (resultCode == Activity.RESULT_CANCELED) {
            val deleteMovie = data!!.getSerializableExtra("delete") as Movie
        }
    }

}
