package duongptph28955.fpoly.kingshoes_duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DBHelper.DBHelper;
import duongptph28955.fpoly.kingshoes_duan1.dto.ThanhVien;


public class ThuThuDAO {
    private Context context;
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        this.context = context;
        DBHelper dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }

    public List<ThanhVien> getData(String sql, String... selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String maTT = c.getString(0);
            String hoTen = c.getString(1);
            String matKhau = c.getString(2);
            String soDT = c.getString(3);
            ThanhVien obj = new ThanhVien(maTT, hoTen, matKhau,soDT);
            list.add(obj);
            c.moveToNext();
        }
        return list;
    }

    public long insert(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("maThanhVien", obj.getMaTV());
        values.put("tenThanhVien", obj.getTenTV());
        values.put("matKhau", obj.getMatKhau());
        values.put("soDienThoaiTV", obj.getSoDT());

        return db.insert("ThuThu", null, values);
    }

    public int update(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("maThanhVien", obj.getMaTV());
        values.put("tenThanhVien", obj.getTenTV());
        values.put("matKhau", obj.getMatKhau());
        values.put("soDienThoaiTV", obj.getSoDT());

        return db.update("ThuThu", values, "maTT=?", new String[]{obj.getMaTV()});
    }

    public int updatePass(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("matKhau", obj.getMatKhau());

        return db.update("ThuThu", values, "maTT=?", new String[]{obj.getMaTV()});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThanhVien getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }

    public int checkLogin(String id, String pass) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThanhVien> list = getData(sql, id, pass);
        if(list.size() == 0) {
            return -1;
        }
        return 1;
    }

}
