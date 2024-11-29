package com.example.warehousemanagement.data.util

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val firebaseStorage: FirebaseStorage = Firebase.storage
val storageReference = firebaseStorage.reference
fun uploadImageToFirebase(
    uri: Uri,
    onSuccess: (String) -> Unit,
    onFailure: (Exception) -> Unit,
    coroutineScope: CoroutineScope,
) {
    // Tạo một tham chiếu đến vị trí lưu trữ ảnh
    val imageReference = storageReference.child("imagesWare/${System.currentTimeMillis()}.jpg")

    // Upload ảnh từ URI
    val uploadTask = imageReference.putFile(uri)
        .addOnSuccessListener {
            // Lấy URL công khai sau khi upload thành công
            imageReference.downloadUrl.addOnSuccessListener { downloadUri ->
                onSuccess(downloadUri.toString())
                print("Iris cheo ${downloadUri.toString()}")
                // Trả về URL của ảnh
            }.addOnFailureListener { exception ->
                print("Iris URL ${exception.message}")

                onFailure(exception) // Trả về lỗi nếu lấy URL thất bại
            }
        }.addOnFailureListener { exception ->
            print("Iris upload ${exception.message}")

            onFailure(exception) // Trả về lỗi nếu upload thất bại
        }
}

