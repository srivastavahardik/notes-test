package com.oddlyspaced.notes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oddlyspaced.notes.api.ApiClient
import com.oddlyspaced.notes.api.ApiInterface
import com.oddlyspaced.notes.modal.Note
import com.oddlyspaced.notes.modal.CompleteNote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NotesRepository {

    private val client = ApiClient.getApiClient()
    private val apiInterface = client.create(ApiInterface::class.java)

    fun fetchNotes(folderId: Int = 1): LiveData<List<Note>> {
        val notesResponse = MutableLiveData<List<Note>>()
        apiInterface.fetchNotes(folderId).enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                if (response.isSuccessful) {
                    notesResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                Log.e("NotesRepository", "ERROR")
                t.printStackTrace()
            }

        })
        return notesResponse
    }

}