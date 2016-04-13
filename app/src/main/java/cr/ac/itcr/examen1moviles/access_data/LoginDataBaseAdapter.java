package cr.ac.itcr.examen1moviles.access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    //Sentencia SQL para crear una nueva base de datos.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME text,PASSWORD text,TYPE text); ";
    //Variable que  contiene la instancia de base de datos
    public SQLiteDatabase db;
    //Contexto de la aplicación que utiliza la base de datos .
    private final Context context;
    //Base de datos abrir / actualizar
    private DataBaseHelper dbHelper;
    public LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String password, String tipo)
    {
        ContentValues newValues = new ContentValues();
       // Asignar valores para cada fila .
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        newValues.put("TYPE", tipo);

        // Introducir la fila en la tabla
        db.insert("LOGIN", null, newValues);
    }

    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

}