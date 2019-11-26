package com.example.uberapp.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uberapp.History;
import com.example.uberapp.databases.bbdd;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private Context context;

    //singleton
    private static Repository srepository;
    public static SQLiteDatabase db; // Variable para consultar siempre a la base de datos


    private Repository(Context context){

        this.context = context;
        bbdd bbdd = new bbdd(context);
        db = bbdd.getWritableDatabase();
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


    public static void registerUser(String user, String passwd){



    }

    public static void registerDriver(String user, String passwd){



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
        db.close();

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
        db.close();

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