package duongptph28955.fpoly.kingshoes_duan1.dto;

public class SanPham {
    public int maSP;
    public String maLoai;
    public String tenSP;
    public int giaNhap ;
    public int soLuong;
    public String ngayNhap;
    public String hinhAnh;

    public SanPham() {
    }

    public SanPham(int maSP, String maLoai, String tenSP, int giaNhap, int soLuong, String ngayNhap, String hinhAnh) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.hinhAnh = hinhAnh;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
