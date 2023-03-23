package duongptph28955.fpoly.kingshoes_duan1.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String name = "kingshoes";
    public static final int version = 2;
    public DBHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbLoaiGiay = "Create table LOAIGIAY(maLoai integer primary key autoincrement," +
                "tenLoai text not null)";
        db.execSQL(tbLoaiGiay);

        String tbMauSac = "Create table MAUSAC(maMau integer primary key autoincrement," +
                "tenMau text not null)";
        db.execSQL(tbMauSac);

        String tbSize = "Create table SIZE(maSize integer primary key autoincrement," +
                "size text not null)";
        db.execSQL(tbSize);

        String tbSanPham = "Create table SANPHAM(maSanPham integer primary key autoincrement," +
                "maLoai integer references LOAIGIAY(maLoai)," +
                "maMau integer references MAUSAC(maMau)," +
                "maSize integer references SIZE(maSize)," +
                "tenSanPham text not null," +
                "giaNhap integer not null," +
                "soLuong integer not null," +
                "ngayNhap text not null," +
                "hinhAnh blob not null)";
        db.execSQL(tbSanPham);

        String tbKhachHang = "Create table KHACHHANG(maKhachHang integer primary key autoincrement," +
                "tenKhachHang text not null," +
                "soDienThoaiKH text not null," +
                "diaChiKH text not null)";
        db.execSQL(tbKhachHang);

        String tbHoaDon = "Create table HOADON(maHoaDon integer primary key autoincrement," +
                "maKhachHang references KHACHHANG(maKhachHang)," +
                "maSanPham references SANPHAM(maSanPham)," +
                "maMau references MAUSAC(maMau)," +
                "maSize references SIZE(maSize)," +
                "trangThai integer not null," +
                "giaXuat integer not null," +
                "ngayXuat text not null," +
                "soLuongXuat integer not null)";
        db.execSQL(tbHoaDon);

        String tbThanhVien = "Create table THANHVIEN(maThanhVien text primary key," +
                "tenThanhVien text not null," +
                "matKhau text not null," +
                "soDienThoaiTV text not null)";
        db.execSQL(tbThanhVien);

        db.execSQL("Insert into THANHVIEN values ('admin', 'Phan Trọng Dương', 'admin', '0946898528'), ('tv1', 'Bùi Quang Vũ', 'tv1', '0123456789')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
