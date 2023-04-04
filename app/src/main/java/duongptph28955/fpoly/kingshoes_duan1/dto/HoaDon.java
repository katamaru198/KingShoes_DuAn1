package duongptph28955.fpoly.kingshoes_duan1.dto;

public class HoaDon {
    public int maHD;
    public int maSP;
    public int maKH;
    public String tenMau;
    public String size;
    public int trangThai;
    public int giaXuat;
    public String ngayXuat;
    public int soLuongXuat;

    public HoaDon(int maHD, int maSP, int maKH, String tenMau, String size, int trangThai, int giaXuat, String ngayXuat, int soLuongXuat) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.maKH = maKH;
        this.tenMau = tenMau;
        this.size = size;
        this.trangThai = trangThai;
        this.giaXuat = giaXuat;
        this.ngayXuat = ngayXuat;
        this.soLuongXuat = soLuongXuat;
    }

    public HoaDon() {
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getGiaXuat() {
        return giaXuat;
    }

    public void setGiaXuat(int giaXuat) {
        this.giaXuat = giaXuat;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public int getSoLuongXuat() {
        return soLuongXuat;
    }

    public void setSoLuongXuat(int soLuongXuat) {
        this.soLuongXuat = soLuongXuat;
    }
}
