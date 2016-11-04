            package com.example.ekasilab.reportcard;

            import android.content.ContentValues;
            import android.content.Context;
            import android.database.Cursor;
            import android.database.sqlite.SQLiteDatabase;
            import android.database.sqlite.SQLiteOpenHelper;

            /**
             * Created by eKasilab on 17/10/2016.
             */

            public class databaseHelper extends SQLiteOpenHelper {
                //declaring variables that we going to assign the database in
                public static final String DATABASE_NAME = "student.db"; //database name
                public static final String TABLE_NAME = "student_table";//database table
                SQLiteDatabase db;
                //table contains with the following columns

                public static final String col_1 = "ID";
                public static final String col_2 = "NAME";
                public static final String col_3 = "SURNAME";
                public static final String col_4 = "MARKS";
                public static final String col_5 = "USERNAME";
                public static final String col_6 = "PASSWORD";




                // function for creating the database
                public databaseHelper(Context context) {
                    super(context, DATABASE_NAME, null, 3);

                }

                @Override
                public void onCreate(SQLiteDatabase db) {
                    //executing the query
                    db.execSQL("create table " + TABLE_NAME + "(ID INTEGER  PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, SURNAME TEXT NOT NULL, MARKS INTEGER NOT NULL, USERNAME TEXT NOT NULL, PASSWORD TEXT NOT NULL )");

                }

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                    onCreate(db);
                }
                public String searchPass (String username)
                {


                    db = this.getReadableDatabase();
                    String query = " Select username,password from " +TABLE_NAME;
                    Cursor cursor = db.rawQuery(query, null);

                    String a, b;
                    b = "password word Not found";
                    if (cursor.moveToFirst())
                    {
                        do{
                            a = cursor.getString(0);


                            if (a.equals(username))
                            {
                                b=cursor.getString(1);
                                break;
                            }
                        }
                        while (cursor.moveToNext());
                    }
                    return b;
                }



                //inserting the data in the database
                public boolean insertData(String name, String surname, String marks, String username, String password) {
                    SQLiteDatabase db = this.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(col_2, name);
                    contentValues.put(col_3, surname);
                    contentValues.put(col_4, marks);
                    contentValues.put(col_5, username);
                    contentValues.put(col_6, password);
                    //this will insert data in the database
                    db.insert(TABLE_NAME, null, contentValues);





                    long results = db.insert(TABLE_NAME, null, contentValues);
                    if (results == -1)
                        return false;
                    else
                        return true;
                }





                public Cursor getAlldata() {
                    SQLiteDatabase db = this.getWritableDatabase();
                    //instance of cursor
                    Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
                    return res;
                }



                //update data
                public boolean updateData(String id, String name, String surname, String marks, String username, String password ) {
                    SQLiteDatabase db = this.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(col_1, id);
                    contentValues.put(col_2, name);
                    contentValues.put(col_3, surname);
                    contentValues.put(col_4, marks);
                   contentValues.put(col_5, username);
                    contentValues.put(col_6, password);
            //calling update
                    db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
                    return true;
                }


                //deleting data from the database
                public int deleteData(String id) {
                    SQLiteDatabase db = this.getWritableDatabase();
                    //deleting the data
                    return db.delete(TABLE_NAME, "ID =?", new String[]{id});


                }


            }
