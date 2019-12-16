package com.example.uberapp.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.uberapp.History;
import com.example.uberapp.MainActivity;
import com.example.uberapp.databases.bbdd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Repository {

    private Context context;
    public static FirebaseAuth mAuth;

    //singleton
    private static Repository srepository;
    public static SQLiteDatabase db; // Variable para consultar siempre a la base de datos


    private Repository(Context context){

        this.context = context;
        bbdd bbdd = new bbdd(context);
        db = bbdd.getWritableDatabase();
        mAuth = FirebaseAuth.getInstance();
    }

    public static Repository get(Context context){

        if(srepository == null){
            srepository = new Repository(context);
        }
        return srepository;
    }

    public static void close_repository(){
        db.close();
    }

    // Metodos (necesitaré utilizar el contexto!!)


    public static void registerUserInFirebase(Activity activity ,String username, String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Firebase", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(activity.getBaseContext(),MainActivity.class);
                            activity.startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Firebase", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public static void loginDriverInFirebase(Activity activity,String username,String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("Firebase","loginDriver:success");
                            Intent i = new Intent(activity.getBaseContext(),MainActivity.class);
                            activity.startActivity(i);
                        } else {
                            Log.w("Firebase","loginDriver:failure");
                        }
                    }
                });
    }

    public static boolean checkUserForUsers(String user,String passwd){

        // Valor que devolverá el select
        String[] columns = {"IDUsername"};

        //Columnas que queremos buscar
        String select = "Username = ? and Password = ? and UserType = ?";

        //Información que pasaremos al where
        String [] selectArgs = {user,passwd,"User"};

        Cursor c = db.query("Users",
                columns,
                select,
                selectArgs,
                null,
                null,
                null);

        int count = c.getCount();
        c.close();
        //db.close();

        if(count > 0){
            return true;
        } else {
            return false;
        }

    }

    public static boolean checkLoginForDrivers(String user,String passwd){

        // Valor que devolverá el select
        String [] columns = {"IDUsername"};

        // Columnas que queremos buscar
        String select = "Username = ? and Password = ? and UserType = ?";

        //Información que se pasará al WHERE
        String [] selectArgs = {user,passwd,"Driver"};

        Cursor c = db.query("Users",
                columns,
                select,
                selectArgs,
                null,
                null,
                null);

        int count = c.getCount();
        c.close();
        //db.close();

        if (count > 0){
            return true;
        } else {
            return false;
        }

    }

    public static List<History> getHistories(){
        List<History> histories = new ArrayList<>();

        histories.add(new History("Calle San sebastián","La Maquinista"));
        histories.add(new History("Glories","Diagonal Mar"));
        histories.add(new History("Calle Gran de Sant Andreu","Heron City"));
        histories.add(new History("asdasdasdasdsa","asdadad"));
        histories.add(new History("fdgjfkdgjdfgdfl","sdlskjgskdj"));
        histories.add(new History("dfkdjfkjsdf","hgfdjhgjdfhghfjd"));

        return histories;
    }
}