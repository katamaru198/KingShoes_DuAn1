package duongptph28955.fpoly.kingshoes_duan1.dto;

public class HoaDon {
    public int maHD;
    public int maSP;
    public int maKH;
    public int maMau;
    public int maSize;
    public int trangThai;
    public int giaXuat;
    public String ngayXuat;
    public int soLuongXuat;


    public HoaDon() {
    }

    public HoaDon(int maHD, int maSP, int maKH, int maMau, int maSize, int trangThai, int giaXuat, String ngayXuat, int soLuongXuat) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.maKH = maKH;
        this.maMau = maMau;
        this.maSize = maSize;
        this.trangThai = trangThai;
        this.giaXuat = giaXuat;
        this.ngayXuat = ngayXuat;
        this.soLuongXuat = soLuongXuat;
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

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
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
