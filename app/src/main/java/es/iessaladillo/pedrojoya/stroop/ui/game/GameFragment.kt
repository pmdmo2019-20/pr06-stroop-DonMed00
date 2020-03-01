package es.iessaladillo.pedrojoya.stroop.ui.game

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.UsersDatabase
import kotlinx.android.synthetic.main.game_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment(R.layout.game_fragment) {

    val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    private val viewmodel: GameViewModel by viewModels {
        GameViewModelFactory(
            UsersDatabase.getInstance
                (this.requireContext()).gameDao, requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupProgressBar()
        setupLabels()
        startGame()


    }

    private fun startGame() {
        viewmodel.changeCurrentWord()
        imgRight.setOnClickListener { viewmodel.checkRight() }
        imgWrong.setOnClickListener { viewmodel.checkWrong() }
    }

    private fun setupLabels() {
        var gameMode = settings.getString(getString(R.string.prefGameMode_key), "Time")
        viewmodel.run {
            wordsShown.observe(viewLifecycleOwner) {
                lblWords.text = getString(R.string.game_lblWordsLabel, it.toString())
            }
            wordsCorrects.observe(viewLifecycleOwner) {
                lblCorrects.text = getString(R.string.game_lblCorrectLabel, it.toString())
            }

            if (gameMode == "Time") {
                viewmodel.points.observe(viewLifecycleOwner) {
                    lblAttemptOrTime.text = getString(R.string.game_points, it.toString())
                }
            } else {
                viewmodel.attempt.observe(viewLifecycleOwner) {
                    lblAttemptOrTime.text = getString(R.string.game_attempts, it.toString())

                }
            }

            currentWordIndex.observe(viewLifecycleOwner){
                lblWordColors.text=words[it]
            }
            currentColorIndex.observe(viewLifecycleOwner){
                lblWordColors.setTextColor(colors[it])
            }
        }
        imgRight.setOnClickListener { viewmodel.changeCurrentWord() }


    }

    private fun setupProgressBar() {
        progressBarGame.max = 60000
        viewmodel.startGameThread(60000, 1000)
        viewmodel.progressBarTime.observe(viewLifecycleOwner) {
            progressBarGame.progress = it
        }
    }
}
