package duongptph28955.fpoly.kingshoes_duan1.dto;

public class SanPham {
    public int maSP;
    public int maLoai;
    private String tenLoai;
    public String tenSP;
    private int giaNhap ;
    private int soLuong;
    private String ngayNhap;
    private byte[] hinhAnh;

    public SanPham(int maSP, int maLoai, String tenLoai, String tenSP, int giaNhap, int soLuong, String ngayNhap, byte[] hinhAnh) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.tenSP = tenSP;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.hinhAnh = hinhAnh;
    }

    public SanPham(int maSP, int maLoai, String tenSP, int giaNhap, int soLuong, String ngayNhap, byte[] hinhAnh) {
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

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
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

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
