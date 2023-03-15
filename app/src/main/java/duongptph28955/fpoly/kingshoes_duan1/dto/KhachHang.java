package duongptph28955.fpoly.kingshoes_duan1.dto;

public class KhachHang {
    public int maKH;
    public String tenKH;
    public String soDTKH;
    public String diaChiKH;

    public KhachHang() {
    }

    public KhachHang(int maKH, String tenKH, String soDTKH, String diaChiKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soDTKH = soDTKH;
        this.diaChiKH = diaChiKH;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDTKH() {
        return soDTKH;
    }

    public void setSoDTKH(String soDTKH) {
        this.soDTKH = soDTKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }
}
