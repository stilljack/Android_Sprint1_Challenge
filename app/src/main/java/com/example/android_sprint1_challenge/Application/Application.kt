package com.example.android_sprint1_challenge.Application

import android.app.Application
import com.example.android_sprint1_challenge.Model.Movie

class sprintApplication: Application() {
    //I feel like this may not even work and if it does work it's probably a terrible way to do it.
    //hopefully we can keep track of the movies by index number
    //maybe i can avoid using this
     companion object{
        var index:Int = 0
    }

}