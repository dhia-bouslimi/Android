package tn.esprit.curriculumvitaev2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import tn.esprit.curriculumvitaev2.R

private const val HB_MUSIC_ARG = "music"
private const val HB_GAMES_ARG = "games"
private const val HB_SPORT_ARG = "sport"

class HobbiesFragment : Fragment() {

    private lateinit var musicCx: CheckBox
    private lateinit var sportCx: CheckBox
    private lateinit var gamesCx: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_hobbies, container, false)

        musicCx = v.findViewById(R.id.musicCx)
        sportCx = v.findViewById(R.id.sportCx)
        gamesCx = v.findViewById(R.id.gamesCx)

        arguments?.apply {
            musicCx.isChecked = getBoolean(HB_MUSIC_ARG)
            sportCx.isChecked = getBoolean(HB_SPORT_ARG)
            gamesCx.isChecked = getBoolean(HB_GAMES_ARG)
        }

        return v
    }

    companion object {

        @JvmStatic
        fun new(isMusic: Boolean, isGames: Boolean, isSport: Boolean) =
            HobbiesFragment().apply {
                arguments = Bundle().apply {

                    putBoolean(HB_MUSIC_ARG, isMusic)
                    putBoolean(HB_GAMES_ARG, isGames)
                    putBoolean(HB_SPORT_ARG, isSport)
                }
            }
    }
}