package com.example.devaraa_kaaduu.data.remote
import android.content.Context
import android.net.Uri

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.io.File

import okhttp3.MultipartBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.devaraa_kaaduu.data.remote.PlantNetApi
import com.example.devaraa_kaaduu.data.model.PlantResponse

fun uploadToPlantNet(
    uri: Uri,
    context: Context,
    onResult: (String) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {

        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val tempFile = File(context.cacheDir, "upload.jpg")

            tempFile.outputStream().use { output ->
                inputStream?.copyTo(output)
            }

            val requestFile =
                tempFile.asRequestBody("image/*".toMediaTypeOrNull())

            val body = MultipartBody.Part.createFormData(
                "images",
                tempFile.name,
                requestFile
            )

            val organ =
                "leaf".toRequestBody("text/plain".toMediaTypeOrNull())

            val response = RetrofitInstance.api.identifyPlant(
                body,
                organ,
                "2b10iLpEpPsMTagN38rh0B02u"
            )

            val result = response.results.firstOrNull()
                ?.species?.scientificNameWithoutAuthor
                ?: "No match found"

            withContext(Dispatchers.Main) {
                onResult(result)
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onResult("Error: ${e.message}")
            }
        }
    }
}