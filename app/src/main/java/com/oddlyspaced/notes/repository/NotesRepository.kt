package com.oddlyspaced.notes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.oddlyspaced.notes.api.ApiClient
import com.oddlyspaced.notes.api.ApiInterface
import com.oddlyspaced.notes.modal.MessageResponse
import com.oddlyspaced.notes.modal.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NotesRepository {

    private val gson = Gson()
    private val client = ApiClient.getApiClient()
    private val apiInterface = client.create(ApiInterface::class.java)

    fun fetchNotes(folderId: Int = 1): LiveData<List<Note>> {
        val notesResponse = MutableLiveData<List<Note>>()
        apiInterface.fetchNotesInFolder(folderId).enqueue(object : Callback<List<Note>> {
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

    fun updateNote(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            val add = apiInterface.updateNote(gson.toJson(note))
            withContext(Dispatchers.IO) {
                if (add.isSuccessful) {
                    Log.d("NotesRepository", "Note Updated successfully!")
                }
                else {
                    Log.e("NotesRepository", "Some error occurred in updating!")
                }
                Log.e("NotesRepository", add.body()?.message.toString())
            }
        }
    }

    fun deleteNote(noteId: Int) {
        apiInterface.deleteNote(noteId).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if (response.isSuccessful) {
                    Log.d("NotesRepository", response.body()?.message.toString())
                }
                else {
                    Log.e("NotesRepository", response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

}