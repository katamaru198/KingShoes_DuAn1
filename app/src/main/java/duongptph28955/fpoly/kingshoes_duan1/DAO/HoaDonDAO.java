package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.HoaDon;
import duongptph28955.fpoly.kingshoes_duan1.dto.MauSac;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;

public class HoaDonDAO {
    SQLiteDatabase db;

    public HoaDonDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertHoaDon(HoaDon obj){
        ContentValues values = new ContentValues();
        values.put("maKhachHang", obj.maKH);
        values.put("maSanPham", obj.maSP);
        values.put("tenMau", obj.tenMau);
        values.put("tenSize", obj.size);
        values.put("trangThai", obj.trangThai);
        values.put("giaXuat", obj.giaXuat);
        values.put("ngayXuat", obj.ngayXuat);
        return db.insert("HOADON", null, values);
    }

    public int deleteHoaDon(HoaDon obj){
        return db.delete("HOADON","maHoaDon=?",new String[]{obj.getMaHD() + ""});
    }

    public int updateHoaDon(HoaDon obj){
        ContentValues values = new ContentValues();
        values.put("maKhachHang", obj.maKH);
        values.put("maSanPham", obj.maSP);
        values.put("tenMau", obj.tenMau);
        values.put("tenSize", obj.size);
        values.put("trangThai", obj.trangThai);
        values.put("giaXuat", obj.giaXuat);
        values.put("ngayXuat", obj.ngayXuat);
        return db.update("HOADON", values,"maHoaDon=?",new String[]{obj.getMaHD() + ""});
    }

    @SuppressLint("Range")
    private List< HoaDon> getData(String sql, String...selectionArgs){
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            HoaDon obj = new HoaDon();
            obj.maHD = Integer.parseInt(c.getString( c.getColumnIndex("maHoaDon")));
            obj.maSP = Integer.parseInt(c.getString( c.getColumnIndex("maSanPham")));
            obj.maKH = Integer.parseInt(c.getString( c.getColumnIndex("maKhachHang")));
            obj.tenMau = c.getString( c.getColumnIndex("tenMau"));
            obj.size = c.getString( c.getColumnIndex("tenSize"));
            obj.trangThai = Integer.parseInt(c.getString(c.getColumnIndex("trangThai")));
            obj.giaXuat = Integer.parseInt(c.getString(c.getColumnIndex("giaXuat")));
            obj.ngayXuat = c.getString(c.getColumnIndex("ngayXuat"));
            list.add(obj);
        }
        return list;
    }
    public List<HoaDon> getAll(){
        String sql = "select * from HOADON";
        return getData(sql);
    }

    public HoaDon getID(String id){
        String sql = "select * from HOADON where maHoaDon=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }
}
