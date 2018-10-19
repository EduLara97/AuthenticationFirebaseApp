package com.example.itlab.authenticationfirebaseapp.utils

import android.graphics.Bitmap
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions

class FaceDetectionFirebase(private val mGraphicOverlay: GraphicOverlay) {


    /** Data de la libreria de conocimiento facial de Firebase */
    fun runFaceRecognition(bitmap: Bitmap,callback:(String)->Unit){

        val options = FirebaseVisionFaceDetectorOptions.Builder()
                .setModeType(FirebaseVisionFaceDetectorOptions.ACCURATE_MODE)
                .setLandmarkType(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                .setMinFaceSize(0.15f)
                .setTrackingEnabled(true)
                .build()

        val image = FirebaseVisionImage.fromBitmap(bitmap)

        val detector = FirebaseVision.getInstance().getVisionFaceDetector(options)

        detector.detectInImage(image)
                .addOnSuccessListener{ mensaje ->
                    processFaceRecognitionResult(mensaje){
                        callback(it) /** Retornar el mensaje de la funcion */
                        detector.close()
                    }
                }
                .addOnFailureListener{
                    callback(it.toString()) /** Retornar el error de Firebase */
                    detector.close()
                }
    }

    private fun processFaceRecognitionResult(firebaseVisionList: List<FirebaseVisionFace>, callback:(String)->Unit){

        /** Limpiar la pantalla de lo anteriormente dibujado */
        mGraphicOverlay.clear()

        /** Comprobar que hay caras en la imagen */
        if (firebaseVisionList.isEmpty()) {
            callback("No se han detectado caras") /** Retornar que no se encontro caras */
            return
        }

        for(i in firebaseVisionList){
            val textGraphic = FaceGraphic(mGraphicOverlay,true)
            textGraphic.updateFace(i,0)
            mGraphicOverlay.add(textGraphic)
        }

        callback("true") /** Retornar que fue exitoso */
    }

}