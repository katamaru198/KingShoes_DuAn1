package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.Size;

public class SizeGiayDAO {
    SQLiteDatabase db;

    public SizeGiayDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertSize(Size obj){
        ContentValues values = new ContentValues();
        values.put("size", obj.getSize());
        values.put("maSanPham", obj.getMaSP());
        return db.insert("SIZE", null, values);
    }
    public int updateSize( Size obj){
        ContentValues values = new ContentValues();
        values.put("size", obj.getSize());
        values.put("maSanPham", obj.getMaSP());
        return db.update("SIZE", values, "maSize=?", new String[]{ obj.getMaSP()+ ""});
    }
    public int deleteSize( Size obj){
        return db.delete("SIZE", "maSize=?", new String[]{ obj.getMaSP()+ ""});
    }

    @SuppressLint("Range")
    private List< Size> getData(String sql, String...selectionArgs){
        List<Size> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            Size obj = new Size();
            obj.maSize = Integer.parseInt(c.getString( c.getColumnIndex("maSize")));
            obj.size = c.getString( c.getColumnIndex("size"));
            obj.maSP = Integer.parseInt(c.getString( c.getColumnIndex("maSanPham")));
            list.add(obj);
        }
        return list;
    }

    public List<Size> getAll(){
        String sql = "select * from SIZE";
        return getData(sql);
    }

    public Size getID(String id){
        String sql = "select * from SIZE where maSize=?";
        List<Size> list = getData(sql, id);
        return list.get(0);
    }
}
