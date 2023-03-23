package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;

public class SanPhamDAO {
    DBHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<SanPham> getDSSanPham(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sp.maSanPham, lg.maLoai, lg.tenLoai, sp.tenSanPham, sp.giaNhap, sp.soLuong, sp.ngayNhap, ms.maMau, sz.maSize, sp.hinhAnh FROM SANPHAM sp, LOAIGIAY lg, MAUSAC ms, SIZE sz WHERE sp.maLoai = lg.maLoai and sp.maMau = ms.maMau and sp.maSize = sz.maSize", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6),cursor.getInt(7), cursor.getInt(8), cursor.getBlob(9)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themGiayMoi(String tensp, int maloai, int gianhap, int soluong, String ngaynhap,int mamau, int masize, byte[] hinh){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSanPham", tensp);
        contentValues.put("maLoai", maloai);
        contentValues.put("giaNhap", gianhap);
        contentValues.put("soLuong", soluong);
        contentValues.put("ngayNhap", ngaynhap);
        contentValues.put("maMau", mamau);
        contentValues.put("maSize", masize);
        contentValues.put("hinhAnh", hinh);
        long check = sqLiteDatabase.insert("SANPHAM", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatSanPham(SanPham sanPham){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSanPham", sanPham.getTenSP());
        contentValues.put("maLoai", sanPham.getMaLoai());
        contentValues.put("giaNhap", sanPham.getGiaNhap());
        contentValues.put("soLuong", sanPham.getSoLuong());
        contentValues.put("ngayNhap", sanPham.getNgayNhap());
        contentValues.put("hinhAnh", sanPham.getHinhAnh());
        long check = sqLiteDatabase.update("SANPHAM", contentValues, "maSanPham = ?", new String[]{String.valueOf(sanPham.getMaSP())});
        if (check == -1)
            return false;
        return true;
    }

    @SuppressLint("Range")
    private List< SanPham> getData(String sql, String...selectionArgs){
        List<SanPham> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor c = database.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            SanPham obj = new SanPham();
            obj.maSP = Integer.parseInt(c.getString( c.getColumnIndex("maSanPham")));
            obj.tenSP = c.getString( c.getColumnIndex("tenSanPham"));
            list.add(obj);
        }
        return list;
    }


    public SanPham getID(String id){
        String sql = "select * from SANPHAM where maSanPham=?";
        List<SanPham> list = getData(sql, id);
        return list.get(0);
    }
//    @SuppressLint("Range")
//    public SanPham getId(String...id){
//        String sql = "select * from SANPHAM where maSanPham = ?";
//        List<SanPham>  list = new ArrayList<>();
//        Cursor c = dbHelper.getWritableDatabase().rawQuery(sql,id);
//        while (c.moveToNext()){
//            SanPham obj = new SanPham();
//            obj.maSP = Integer.parseInt(c.getString(c.getColumnIndex("maSP")));
//            obj.tenSP = c.getString(c.getColumnIndex("tenSP"));
//            list.add(obj);
//        }
//        return list.get(0);
//    }
    // boolean true: xóa thành công; false: thất bại
    //int 1: xóa thành công ; 0: thất bại ; -1: tìm thấy sản phẩm có trong hóa đơn
//    public int xoaSanPham(int masp){
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM HOADON WHERE maSanPham = ?", new String[]{String.valueOf(masp)});
//        if (cursor.getCount() !=0){
//            return -1;
//        }
//        long check = sqLiteDatabase.delete("SANPHAM", "maSanPham = ?", new String[]{String.valueOf(masp)});
//        if (check == -1)
//            return 0;
//        return 1;
//    }
}
