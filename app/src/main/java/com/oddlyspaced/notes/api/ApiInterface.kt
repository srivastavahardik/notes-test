package com.oddlyspaced.notes.api

import com.oddlyspaced.notes.modal.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    // Folder Related
    @GET("/folder/all")
    fun fetchFoldersAll(): Call<List<Folder>>

    @GET("/folder/notes")
    fun fetchNotesInFolder(
        @Query("folderId") folderId: Int = 1
    ): Call<List<Note>>
}