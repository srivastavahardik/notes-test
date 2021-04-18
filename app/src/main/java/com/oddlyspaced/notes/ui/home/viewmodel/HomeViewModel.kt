package com.oddlyspaced.notes.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oddlyspaced.notes.modal.Note

class HomeViewModel: ViewModel() {

    private val _notes = MutableLiveData<ArrayList<Note>>()
    val notes: LiveData<ArrayList<Note>>
        get() = _notes

    init {
        _notes.value = ArrayList()
        _notes.value?.add(Note(1, "First"))
        _notes.value?.add(Note(2, "First"))
        _notes.value?.add(Note(3, "First"))
        _notes.value?.add(Note(4, "First"))
        _notes.value?.add(Note(5, "First"))
        _notes.value?.add(Note(6, "First"))
        // TODO : Check for possible replacement of plus
        // to notify observers
        _notes.value?.plus(Note(3, "First"))
    }

}

/*
class GameViewModel: ViewModel() {
    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val timer: CountDownTimer

    init {
        Log.d("GameViewModel", "Created!")

        _word.value = ""
        _score.value = 0

        timer = object:CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(p0: Long) {
                _currentTime.value = p0/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

        }
        timer.start()

        resetList()
        nextWord()
    }

    companion object {
        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
        else {
            resetList()
//            onGameFinish()
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
        onGameFinishComplete()
    }

    private fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel", "Destroyed!")
        timer.cancel()
    }

}*/