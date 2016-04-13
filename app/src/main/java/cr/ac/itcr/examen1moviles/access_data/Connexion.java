package cr.ac.itcr.examen1moviles.access_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Hellen Rojas Rojas on 30/03/2016.
 */
public class Connexion extends SQLiteOpenHelper {

    private static  final int VERSION_BDD = 1;
    private static  final String NAME_BDD = "ICTApp";

    public Connexion(Context context) {
        super(context, NAME_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            String sqlCreatePlace = "CREATE TABLE IF NOT EXISTS flor(name CHAR (100)  PRIMARY KEY ,nameC CHAR (100),color CHAR (100))";
            sql.append(sqlCreatePlace);
            db.execSQL(sql.toString());
        }
        catch (Exception error){
            Log.d("error", error.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            StringBuilder sql = new StringBuilder();
            for(int indiceVersion =oldVersion;indiceVersion<newVersion;indiceVersion++){
                int nextVersion = indiceVersion+1;
                switch (newVersion){
                    case 1:
                        String sqlDropPlace = "DROP flor IF EXISTS flor";
                        sql.append(sqlDropPlace);
                }
            }
            db.execSQL(sql.toString());
        }
        catch (Exception error){
            Log.d("error", error.getMessage());
        }
    }
}
