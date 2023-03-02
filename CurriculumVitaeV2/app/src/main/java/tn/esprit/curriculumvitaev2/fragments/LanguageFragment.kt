package tn.esprit.curriculumvitaev2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import tn.esprit.curriculumvitaev2.R

private const val AR_LANG_ARG = "ar"
private const val EN_LANG_ARG = "en"
private const val FR_LANG_ARG = "fr"

class LanguageFragment : Fragment() {

    private lateinit var arCx: CheckBox
    private lateinit var enCx: CheckBox
    private lateinit var frCx: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_language, container, false)

        arCx = v.findViewById(R.id.arCx)
        enCx = v.findViewById(R.id.enCx)
        frCx = v.findViewById(R.id.frCx)

        arguments?.apply {
            arCx.isChecked = getBoolean(AR_LANG_ARG)
            enCx.isChecked = getBoolean(EN_LANG_ARG)
            frCx.isChecked = getBoolean(FR_LANG_ARG)
        }

        return v
    }

    companion object {

        @JvmStatic
        fun new(isArabic: Boolean, isEnglish: Boolean, isFrench: Boolean) =
            LanguageFragment().apply {
                arguments = Bundle().apply {

                    putBoolean(AR_LANG_ARG, isArabic)
                    putBoolean(EN_LANG_ARG, isEnglish)
                    putBoolean(FR_LANG_ARG, isFrench)
                }
            }
    }
}