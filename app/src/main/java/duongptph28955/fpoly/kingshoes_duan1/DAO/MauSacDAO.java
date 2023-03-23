package duongptph28955.fpoly.kingshoes_duan1.DAO;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.MauSac;

public class MauSacDAO {
    SQLiteDatabase db;

    public MauSacDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertMauSac(MauSac obj){
        ContentValues values = new ContentValues();

        values.put("tenMau", obj.getTenMau());
        return db.insert("MAUSAC", null, values);
    }
    public int updateMauSac( MauSac obj){
        ContentValues values = new ContentValues();

        values.put("tenMau", obj.getTenMau());
        return db.update("MAUSAC", values, "maMau=?", new String[]{ obj.getMaMau()+ ""});
    }
    public int deleteMauSac( MauSac obj){
        return db.delete("MAUSAC", "maMau=?", new String[]{ obj.getMaMau()+ ""});
    }

    @SuppressLint("Range")
    private List< MauSac> getData(String sql, String...selectionArgs){
        List<MauSac> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            MauSac obj = new MauSac();
            obj.maMau = Integer.parseInt(c.getString( c.getColumnIndex("maMau")));
            obj.tenMau = c.getString( c.getColumnIndex("tenMau"));
            list.add(obj);
        }
        return list;
    }

    public List<MauSac> getAll(){
        String sql = "select * from MAUSAC";
        return getData(sql);
    }

    public MauSac getID(String id){
        String sql = "select * from MAUSAC where maMau=?";
        List<MauSac> list = getData(sql, id);
        return list.get(0);
    }
}
