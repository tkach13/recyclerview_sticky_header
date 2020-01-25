package com.example.samplerecyclerview.ui.main

import com.example.samplerecyclerview.MovieDataModel

fun ArrayList<MovieDataModel>.getNext(id:Int):MovieDataModel?{
    while (id<size){
        return get(id+1)
    }

    return null

}