package tn.esprit.curriculumvitaev2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import tn.esprit.curriculumvitaev2.R

private const val ANDROID_SKILL_ARG = "android_skill"
private const val FLUTTER_SKILL_ARG = "flutter_skill"
private const val IOS_SKILL_ARG = "ios_skill"

class SkillsFragment : Fragment() {

    private lateinit var androidSk: SeekBar
    private lateinit var flutterSk: SeekBar
    private lateinit var iosSk: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_skills, container, false)

        androidSk = v.findViewById(R.id.androidSk)
        flutterSk = v.findViewById(R.id.flutterSk)
        iosSk = v.findViewById(R.id.iosSk)

        androidSk.isEnabled = false
        flutterSk.isEnabled = false
        iosSk.isEnabled = false

        arguments?.apply {

            androidSk.progress = getInt(ANDROID_SKILL_ARG)
            flutterSk.progress = getInt(FLUTTER_SKILL_ARG)
            iosSk.progress = getInt(IOS_SKILL_ARG)
        }

        return v
    }

    companion object {

        @JvmStatic
        fun new(androidP: Int, flutterP: Int, iosP: Int) =
            SkillsFragment().apply {
                arguments = Bundle().apply {

                    putInt(ANDROID_SKILL_ARG, androidP)
                    putInt(FLUTTER_SKILL_ARG, flutterP)
                    putInt(IOS_SKILL_ARG, iosP)
                }
            }
    }
}