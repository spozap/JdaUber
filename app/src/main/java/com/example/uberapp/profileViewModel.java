package com.example.uberapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class profileViewModel extends ViewModel {

    private FirebaseAuth mAuth;

    public void uploadImage(Uri uri){

        mAuth = FirebaseAuth.getInstance();

        if (uri != null){
            //Creating a child with the username's UID
            StorageReference mImageRef = FirebaseStorage.getInstance().getReference().child(mAuth.getUid());
            mImageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("Storage","Img subida");
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Storage","Error al subir la imagen");
                }
            });

        }

    }

}
