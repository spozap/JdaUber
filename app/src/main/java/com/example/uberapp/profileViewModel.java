package com.example.uberapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class profileViewModel extends ViewModel {

    private MutableLiveData<Uri> profileImage;

    public profileViewModel(){
        profileImage = new MutableLiveData<>();
    }

    public MutableLiveData<Uri> getProfileImage(){ return profileImage; }


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


    public void downloadProfileImage(){

        mAuth = FirebaseAuth.getInstance();
        StorageReference mImageRef = FirebaseStorage.getInstance().getReference().child(mAuth.getUid());

        mImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                profileImage.postValue(uri);
            }
        })

        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Storage","Error al descargar la imagen");
            }
        });

    }

}
