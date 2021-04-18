package com.oddlyspaced.notes.api

import com.oddlyspaced.notes.modal.Note
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("note/get")
    fun fetchNotes(@Query("folderId") folderId: Int = 1): Call<List<Note>>
}