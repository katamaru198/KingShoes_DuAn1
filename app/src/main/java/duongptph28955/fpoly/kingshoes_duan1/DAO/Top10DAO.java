package duongptph28955.fpoly.kingshoes_duan1.DAO;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;

public class Top10DAO {
    DBHelper dbHelper;

    public Top10DAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<SanPham> getTop10(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT hd.maSanPham, sp.tenSanPham, SUM(hd.soLuongXuat), sp.hinhAnh FROM HOADON hd, SANPHAM sp WHERE hd.maSanPham = sp.maSanPham GROUP by hd.maSanPham, sp.tenSanPham HAVING SUM(hd.soLuongXuat) ORDER BY SUM(sp.soLuong) DESC LIMIT 10", null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getBlob(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
