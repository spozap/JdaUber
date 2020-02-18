package com.example.uberapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.app.Activity.RESULT_OK;


public class profileFragment extends Fragment {

    private profileViewModel profileViewModel;

    private TextView usernameTxt,passwordTxt;
    private ImageView userProfile;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        profileViewModel = ViewModelProviders.of(this).get(profileViewModel.class);

        usernameTxt = root.findViewById(R.id.usernameTxt);
        passwordTxt = root.findViewById(R.id.passwordTxt);
        userProfile = root.findViewById(R.id.userProfileImg);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,20);
            }
        });
        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 20 && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = null;
            Uri uri;
            uri = data.getData();
            if (uri != null){
                //userProfile.setImageBitmap(Bitmap.createScaledBitmap(bitmap,599 ,599,false));
                profileViewModel.uploadImage(uri);
            }
        }
    }
}
