package base.pb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBH extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "/mnt/sdcard/pb.db";    // Database Name
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "teks";   // Table Name
    private static final String ID="t_id";     // Column I (Primary Key)
    private static final String KID="k_id";     // Column III
    private static final String PASAL="pasal";     // Column IV
    private static final String AYAT = "ayat";    //Column II
    private static final String TABLE_NAME2 = "buku";   // Table2 Name
    private static final String BID="b_id";     // Column I (Primary Key)
    private static final String BUKU = "buku";    //Column II
    private static final String JUMLAH = "jumlah";    //Column III
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KID+" INTEGER, "+PASAL+" INTEGER, "+AYAT+" TEXT);";
    private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME2+
            " ("+BID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+BUKU+" TEXT, "+JUMLAH+" INTEGER);";
    
    public DBH(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }
    
    public Cursor getBuku(String kitab) {
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor cursor = db.rawQuery("SELECT * FROM buku WHERE buku like '%" + kitab + "%' ", null);
    	return cursor; 
    }
    
    public Cursor getTeks(int buku, int index ) {
    	SQLiteDatabase db = getReadableDatabase();
    	Cursor cursor = db.rawQuery("SELECT * FROM teks WHERE k_id = " + buku + " AND pasal = " + index, null);
    	return cursor; 
    }
    
}
