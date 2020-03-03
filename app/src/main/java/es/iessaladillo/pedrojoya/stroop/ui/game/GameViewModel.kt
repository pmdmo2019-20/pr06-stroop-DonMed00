package es.iessaladillo.pedrojoya.stroop.ui.game

import android.app.Application
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.data.GameDao
import es.iessaladillo.pedrojoya.stroop.data.entity.Game
import kotlin.concurrent.thread
import kotlin.random.Random


class GameViewModel(
    private val gameDao: GameDao,
    private val application: Application
) : ViewModel() {


    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }


    @Volatile
     var isGameFinished: Boolean = false
    @Volatile
    private var currentWordMillis: Int = 0
    @Volatile
    private var millisUntilFinished: Int = 0
    private val handler: Handler = Handler()


    private val _progressBarTime: MutableLiveData<Int> = MutableLiveData(millisUntilFinished)
    val progressBarTime: LiveData<Int>
        get() = _progressBarTime

    private val _wordsShown: MutableLiveData<Int> = MutableLiveData(0)
    val wordsShown: LiveData<Int>
        get() = _wordsShown

    private val _wordsCorrects: MutableLiveData<Int> = MutableLiveData(0)
    val wordsCorrects: LiveData<Int>
        get() = _wordsCorrects


    private val _points: MutableLiveData<Int> = MutableLiveData(0)
    val points: LiveData<Int>
        get() = _points


    private val _attempt: MutableLiveData<Int> = MutableLiveData()
    val attempt: LiveData<Int>
        get() = _attempt


    val words: List<String> = listOf("Blue", "Red", "Yellow", "Green")

    val colors: List<Int> = listOf(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN)

    private val _currentWordIndex: MutableLiveData<Int> = MutableLiveData(0)
    val currentWordIndex: LiveData<Int>
        get() = _currentWordIndex

    private val _currentColorIndex: MutableLiveData<Int> = MutableLiveData(0)
    val currentColorIndex: LiveData<Int>
        get() = _currentColorIndex

    private val _gameFinish: MutableLiveData<Game> = MutableLiveData()
    val gameFinish: LiveData<Game>
        get() = _gameFinish


    init {
        setupSettings()
    }
   private fun setupSettings(){
       _attempt.value=settings.getString("prefAttempts","5")!!.toInt()


    }


    private fun onGameTimeTick(millisUntilFinished: Int) {
        _progressBarTime.value = millisUntilFinished
    }


    private fun onGameTimeFinish() {

        gameDao.insertGame(Game(0,settings.getString("prefGameMode", "Time")!!,settings.getString("prefGameTime", "60000")!!.toInt()/60000,_wordsShown.value!!,_wordsCorrects.value!!,_points.value!!))
        _gameFinish.value= Game(0,settings.getString("prefGameMode", "Time")!!,settings.getString("prefGameTime", "60000")!!.toInt()/60000,_wordsShown.value!!,_wordsCorrects.value!!,_points.value!!)
        isGameFinished = true

    }

    private fun nextWord() {
        incrementWordsShown()
        changeCurrentWord()


    }

    fun checkRight() {
        currentWordMillis = 0
        if(currentColorIndex.value==currentWordIndex.value){
            incrementWordsCorrects()
            incrementPoints()
        }else{
            if(settings.getString("prefGameMode", "Time")!!.toLowerCase()=="attempts"){
                decrementAttempts()
            }
        }
        nextWord()
    }

    fun checkWrong() {
        currentWordMillis = 0
        if(currentColorIndex.value!=currentWordIndex.value){
            incrementWordsCorrects()
            incrementPoints()
        }else{
            if(settings.getString("prefGameMode", "Time")!!.toLowerCase()=="attempts"){
                decrementAttempts()
            }
        }
        nextWord()
    }

    fun changeCurrentWord() {
        _currentWordIndex.value = words.indexOf(words[Random.nextInt(4)])
        _currentColorIndex.value = colors.indexOf(colors[Random.nextInt(4)])
    }

    private fun incrementWordsShown() {
        _wordsShown.value = _wordsShown.value!!.plus(1)
    }

    private fun incrementWordsCorrects() {
        _wordsCorrects.value = _wordsCorrects.value!!.plus(1)
    }
    private fun incrementPoints(){
        _points.value=_points.value!!.plus(10)
    }

    private fun decrementAttempts(){
        _attempt.value=_attempt.value!!.minus(1)
    }

    fun startGameThread(gameTime: Int, wordTime: Int) {
        millisUntilFinished = gameTime
        currentWordMillis = 0
        isGameFinished = false
        val checkTimeMillis: Int = wordTime / 2
        thread {
            try {
                while (!isGameFinished) {
                    Thread.sleep(checkTimeMillis.toLong())
                    // Check and publish on main thread.
                    handler.post {
                        if (!isGameFinished) {
                            if (currentWordMillis >= wordTime) {
                                currentWordMillis = 0
                                nextWord()
                            }
                            currentWordMillis += checkTimeMillis
                            millisUntilFinished -= checkTimeMillis
                            onGameTimeTick(millisUntilFinished)
                            if (millisUntilFinished <= 0 || _attempt.value!! <=0) {
                                onGameTimeFinish()
                            }
                        }
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        isGameFinished = true
    }

}