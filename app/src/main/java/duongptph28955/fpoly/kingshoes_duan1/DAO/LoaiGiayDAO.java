package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.LoaiGiay;


public class LoaiGiayDAO {
    DBHelper dbHelper;

    public LoaiGiayDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<LoaiGiay> getDSLoaiGiay(){  // Lấy toàn bộ danh sách của loại giầy
        ArrayList<LoaiGiay> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase(); // đọc dữ liệu của data
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAIGIAY", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst(); // có dữ liệu di chuyển con trỏ lên vị trí đầu tiên
            do {
                list.add(new LoaiGiay(cursor.getInt(0), cursor.getString(1))); // Đổ dữ liệu của bảng vào danh sách - constructor có tham số DTO
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoaiGiay(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase(); //ghi dữ liệu vào data
        ContentValues values = new ContentValues();
        values.put("tenLoai", tenLoai);
        long check = sqLiteDatabase.insert("LOAIGIAY", null, values);
        if (check == -1)
            return false;
        return true;
    }

    public boolean thaydoiLoaiGiay(int maLoai, String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase(); //ghi dữ liệu vào data
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoai);
        long check = sqLiteDatabase.update("LOAIGIAY", contentValues, "maLoai = ?", new String[]{String.valueOf(maLoai)});
        if (check == -1)
            return false;
        return true;
    }

    //Xoa loại giầy
    //1:xóa thành công, 0:xóa thất bại, -1:có giầy tồn tại trong loại giầy
//    public int xoaLoaiGiay(int id){
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM WHERE maLoai = ?", new String[]{String.valueOf(id)});
//        if (cursor.getCount() !=0){
//            return -1;
//        }
//        long check = sqLiteDatabase.delete("LOAIGIAY", "maLoai = ?", new String[]{String.valueOf(id)});
//        if (check == -1){
//            return 0;
//        }
//        return 1;
//    }
}
