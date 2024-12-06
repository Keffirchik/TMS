package com.example.tms.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyAPI {

    @GET("/character/{objectId}")
    suspend fun getOneCharacter(@Path(value = "objectId") objectId: Int): DisneyObjects

    @GET("/character")
    suspend fun getAllCharacters(): DisneyObjects

    @GET("/character?{queryParams}")
    suspend fun filterCharacter(@Path(value = "queryParams") queryParams: String): DisneyObjects

    @Parcelize
    data class DisneyObjects(
        @SerializedName("_id") val _id: Int,
        @SerializedName("url") val url: String,
        @SerializedName("name") val name: String,
        @SerializedName("sourceUrl") val sourceUrl: String,
        @SerializedName("films") val films: ArrayList<String>,
        @SerializedName("shortFilms") val shortFilms: ArrayList<String>,
        @SerializedName("tvShows") val tvShows: ArrayList<String>,
        @SerializedName("videoGames") val videoGames: ArrayList<String>,
        @SerializedName("alignment") val alignment: String,
        @SerializedName("parkAttractions") val parkAttractions: ArrayList<String>,
        @SerializedName("allies") val allies: ArrayList<String>,
        @SerializedName("enemies") val enemies: ArrayList<String>
    ) : Parcelable

}