package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.KhachHang;

public class KhachHangDao {
    SQLiteDatabase db;

    public KhachHangDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public long insertKhachHang(KhachHang obj){
        ContentValues values = new ContentValues();
        values.put("tenKhachHang",obj.tenKH);
        values.put("soDienThoaiKH",obj.soDTKH);
        values.put("diaChiKH",obj.diaChiKH);
        return db.insert("KHACHHANG",null,values);
    }

    public int updateKhachHang(KhachHang obj){
        ContentValues values = new ContentValues();
        values.put("tenKhachHang",obj.tenKH);
        values.put("soDienThoaiKH",obj.soDTKH);
        values.put("diaChiKH",obj.diaChiKH);
        return db.update("KHACHHANG",values,"maKhachHang=?",new String[]{String.valueOf(obj.maKH)});
    }

    public int deleteKhachHang(String id){
        return db.delete("KHACHHANG","maKhachHang=?",new String[]{id});
    }

    public List<KhachHang> getAll(){
        String sql = "SELECT *FROM KHACHHANG";
        return getData(sql);
    }
    @SuppressLint("Range")
    public List<KhachHang> getData(String sql, String ...SelectionArgs){
            List<KhachHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectionArgs);

        while (c.moveToNext()){
        KhachHang obj=new KhachHang();
        obj.setMaKH(Integer.parseInt(c.getString(c.getColumnIndex("maKhachHang"))));
        obj.setTenKH(c.getString(c.getColumnIndex("tenKhachHang")));
            obj.setSoDTKH(c.getString(c.getColumnIndex("soDienThoaiKH")));
            obj.setDiaChiKH(c.getString(c.getColumnIndex("diaChiKH")));
            list.add(obj);
        }
        return list;
    }
}
