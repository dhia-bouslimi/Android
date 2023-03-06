package tn.esprit.curriculumvitaev2.utils

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CvObject(
    var fullName: String? = null,
    var email: String? = null,
    var age: Int? = null,
    var gender: String? = null,
    var skillsScore: HashMap<String, Int>? = null,
    var languages: HashMap<String, Boolean>? = null,
    var hobbies: HashMap<String, Boolean>? = null,
    var imgURI: Uri? = null,
) : Parcelable