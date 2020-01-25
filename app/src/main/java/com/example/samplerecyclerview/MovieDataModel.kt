package com.example.samplerecyclerview

import com.example.samplerecyclerview.ui.main.getYear
import java.util.*
import kotlin.collections.ArrayList

data class MovieDataModel (
        val title: String?,
        val url : String?,
        val date:Date,
        val dataType : MovieDataType,
        val id:Int
)


{
    companion object {

        private fun createDate(year:Int, month:Int, date:Int):Date{
            val calendar = Calendar.getInstance()
            calendar.set(year,month,date)
            return calendar.time

        }



        fun generateNewList(array:ArrayList<MovieDataModel>):ArrayList<MovieDataModel>{

             val newArray  = ArrayList<MovieDataModel>()

            array.forEach {
                val currentItemYear = getYear(it.date)
                val nextItemYear = getYear(array.getOrNull(it.id+1)?.date)

                if (nextItemYear!= null && currentItemYear == nextItemYear ){
                    newArray.add(MovieDataModel("header","header",it.date,MovieDataType.HEADER_TYPE,2300))
                }
            }

            array.addAll(newArray)

            array.sortBy {
                it.date
            }

            return array
        }


        fun createList ():ArrayList<MovieDataModel>{

            return arrayListOf(MovieDataModel("Shawshank Redemption", "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg", createDate(1999,Calendar.MARCH,19),MovieDataType.MOVIE_TYPE,0),
                    MovieDataModel(" The Godfather","https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,704,1000_AL_.jpg", createDate(2000,Calendar.MARCH,24),MovieDataType.MOVIE_TYPE,1),
                    MovieDataModel("the Dark Knight","https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", createDate(2001,Calendar.JULY,18),MovieDataType.MOVIE_TYPE,2 ),
                    MovieDataModel(" The Godfather: Part II","https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,701,1000_AL_.jpg", createDate(2001,Calendar.DECEMBER,20),MovieDataType.MOVIE_TYPE,3),
                    MovieDataModel("The Lord of the Rings: The Return of the King", "https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,675,1000_AL_.jpg", createDate(2002,Calendar.APRIL,29),MovieDataType.MOVIE_TYPE,4),
                    MovieDataModel("Pulp Fiction", "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,686,1000_AL_.jpg", createDate(2002,Calendar.OCTOBER,24),MovieDataType.MOVIE_TYPE,5),
                    MovieDataModel("Schindler's List","https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SY1000_CR0,0,666,1000_AL_.jpg", createDate(2003,Calendar.FEBRUARY,4),MovieDataType.MOVIE_TYPE,6),
                    MovieDataModel("12 Angry Men","https://m.media-amazon.com/images/M/MV5BMWU4N2FjNzYtNTVkNC00NzQ0LTg0MjAtYTJlMjFhNGUxZDFmXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SY1000_CR0,0,649,1000_AL_.jpg", createDate(2004,Calendar.APRIL,4),MovieDataType.MOVIE_TYPE,7),
                    MovieDataModel("Inception","https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", createDate(2003,Calendar.OCTOBER,23),MovieDataType.MOVIE_TYPE,8),
                    MovieDataModel("Fight Club","https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,666,1000_AL_.jpg", createDate(2003,Calendar.DECEMBER,11),MovieDataType.MOVIE_TYPE,9)
            )
        }
    }
}

enum class MovieDataType {
    HEADER_TYPE,
    MOVIE_TYPE
}
