package tn.esprit.curriculumvitaev2.utils

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.GsonBuilder

const val ANDROID_KEY = "Android"
const val FLUTTER_KEY = "Flutter"
const val iOS_KEY = "iOS"

const val EN = "English"
const val AR = "Arabic"
const val FR = "French"

const val GAMES = "Games"
const val SPORT = "Sport"
const val MUSIC = "Music"

const val CV_DETAILS = "CV_DETAILS"

const val PREFS_NAME = "RESUME_KEY"

const val IS_REMEMBERED = "IS_REMEMBERED"

const val PICK_IMAGE_CODE = 100

const val PERMS_REQUEST_CODE = 101

val GSON_VAR: Gson = GsonBuilder().registerTypeAdapter(Uri::class.java, UriTypeAdapter).create()

const val INTENT_VALUE_NAME = "CvObject"


const val IS_GRANTED_READ_IMAGES = "IS_GRANTED_READ_IMAGES"
