package com.oddlyspaced.notes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oddlyspaced.notes.api.ApiClient
import com.oddlyspaced.notes.api.ApiInterface
import com.oddlyspaced.notes.modal.Note
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesRepository {

    private val client = ApiClient.getApiClient()
    private val apiInterface = client.create(ApiInterface::class.java)

    private val _notesResponse = MutableLiveData<List<Note>>()
    val notesResponse: LiveData<List<Note>>
        get() = _notesResponse

    fun fetchNotes() {
        apiInterface.fetchNotes().enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                if (response.isSuccessful) {
                    _notesResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                Log.e("NotesRepository", "ERROR")
                t.printStackTrace()
            }
        })
    }

}