package com.example.android.guesstheword

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _currentTime=MutableLiveData<Long>()
    val currentTime:LiveData<Long>
    get()=_currentTime

    /*private val _currentTimeString=MutableLiveData<String>()
    val currentTimeString:LiveData<String>
        get()=_currentTimeString*/
    val currentTimeString:LiveData<String>


    private val timer: CountDownTimer

    private val _eventGameFinish=MutableLiveData<Boolean>()
    val eventGameFinish:LiveData<Boolean>
    get() = _eventGameFinish


    private var _word = MutableLiveData<String>()
    val word:LiveData<String>
    get()=_word

    // The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get()=_score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        _word.value=""
        _score.value=0
        resetList()
        nextWord()

        Log.i("GameViewModel", "GameViewModel created!")

        timer=object:CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                _currentTime.value =DONE
                onGameFinish()
            }

            override fun onTick(p0: Long) {
                _currentTime.value=p0/ ONE_SECOND
            }
        }
        timer.start()
        currentTimeString=Transformations.map(currentTime){ time->
            DateUtils.formatElapsedTime(time)
        }
    }

    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            _word.value =  wordList.removeAt(0)
        }
        else{
            resetList()
        }
    }

    private fun resetList() {
        Log.i("GameViewModel", "wordList initialized")
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

    /** Methods for buttons presses **/
    fun onSkip() {
        _score.value= _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value= _score.value?.plus(1)
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }
    fun onGameFinish(){
        _eventGameFinish.value=true
    }
    fun onGameFinishComplite(){
        _eventGameFinish.value=false
    }
    companion object{
        private val DONE=0L
        private val ONE_SECOND=1000L
        private val COUNTDOWN_TIME=60000L
    }
}