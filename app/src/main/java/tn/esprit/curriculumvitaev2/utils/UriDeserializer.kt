package tn.esprit.curriculumvitaev2.utils

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type

object UriTypeAdapter : JsonDeserializer<Uri>, JsonSerializer<Uri> {

    private const val PROPERTY_NAME = "uri"

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Uri {
        println("jsonObject: ${json?.asJsonObject}")
        return Uri.parse(json?.asJsonObject?.get(PROPERTY_NAME)?.asString)
    }

    override fun serialize(src: Uri?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonObject().apply {
            addProperty(PROPERTY_NAME, src.toString())
        }
    }
}