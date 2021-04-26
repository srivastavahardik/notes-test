package com.oddlyspaced.notes.api

import com.oddlyspaced.notes.modal.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // Folder Related
    @GET("/folder/all")
    fun fetchFoldersAll(): Call<List<Folder>>

    @GET("/folder/exists")
    fun checkFolderExists(): Call<MessageResponse>

    @GET("note/get")
    fun fetchNotes(@Query("folderId") folderId: Int = 1): Call<List<Note>>

    @GET("note/fetch")
    fun fetchNote(@Query("noteId") noteId: Int): Call<CompleteNote>

    @GET("/item/get")
    fun fetchItems(@Query("noteId") noteId: Int): Call<List<Item>>
}