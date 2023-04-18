package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.HoaDon;

public class DoanhThuDao {
    SQLiteDatabase db;

    public DoanhThuDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String...selectionArgs){
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
    public List<HoaDon> GetHoaDonTheoNgay(String tuNgay , String denNgay){
        String sql = "select * from HOADON WHERE ngayXuat BETWEEN ? AND ?";
        return getData(sql,tuNgay,denNgay);
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay , String denNgay){
        String sqlDoanhThu = "SELECT  SUM(giaXuat) as doanhthu FROM HOADON WHERE ngayXuat BETWEEN ? AND ? ";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
            }catch (Exception e){
                e.printStackTrace();
                list.add(0);
            }
        }

        return list.get(0);
    }

}
