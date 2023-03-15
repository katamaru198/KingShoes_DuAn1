package duongptph28955.fpoly.kingshoes_duan1.dto;

public class ThanhVien {
    public String maTV;
    public String tenTV;
    public String matKhau;
    public String soDT;

    public ThanhVien() {
    }

    public ThanhVien(String maTV, String tenTV, String matKhau, String soDT) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.matKhau = matKhau;
        this.soDT = soDT;
    }

    public String getMaTV() {
        return maTV;
    }

    public void setMaTV(String maTV) {
        this.maTV = maTV;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }
}
