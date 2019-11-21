package com.example.uberapp.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bbdd extends SQLiteOpenHelper {

    // Info base de datos

    private static final String DATABASE_NAME = "uberapp.db";
    private static final int DATABASE_VERSION = 2;


    // Tablas

    private static final String TABLE_USERS = "Users";
    private static final String TABLE_HISTORY = "History";

    //

    //Columnas tabla users
    private static final String TABLE_USERS_USERNAME = "Username";
    private static final String TABLE_USERS_PK = "IDUsername";
    private static final String TABLE_USERS_PASS = "Password";
    private static final String TABLE_USERS_TYPE = "UserType";

    //

    //Columnas tabla history
    private static final String TABLE_HISTORY_PK = "IDHistory";
    private static final String TABLE_HISTORY_ACTUBI = "ActualUbication";
    private static final String TABLE_HISTORY_DESTUBI = "DestinationUbication";
    private static final String TABLE_HISTORY_ACTDATE = "ActualDate";

    //

    SQLiteDatabase db;

    public bbdd(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("+
                TABLE_USERS_PK + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                TABLE_USERS_USERNAME + " VARCHAR(20) NOT NULL," +
                TABLE_USERS_PASS + " VARCHAR(20) NOT NULL," +
                TABLE_USERS_TYPE + " CHECK( "+TABLE_USERS_TYPE+" IN ('User','Driver')))";

        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("+
                TABLE_HISTORY_PK + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                TABLE_HISTORY_ACTUBI + " TEXT NOT NULL," +
                TABLE_HISTORY_DESTUBI + " TEXT NOT NULL," +
                TABLE_HISTORY_ACTDATE + " VARCHAR(15) NOT NULL," +
                TABLE_USERS_PK + " INTEGER NOT NULL,"+
                "FOREIGN KEY ("+TABLE_USERS_PK+")REFERENCES "+TABLE_USERS+")";

        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
        sqLiteDatabase.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override // Metodo para actualizar la base de datos
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAntigua, int nuevaVersion) {
        if (versionAntigua != nuevaVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_HISTORY);
            onCreate(sqLiteDatabase);
        }
    }
}