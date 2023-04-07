package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;

public class DoanhThuDao {
    SQLiteDatabase db;

    public DoanhThuDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

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
