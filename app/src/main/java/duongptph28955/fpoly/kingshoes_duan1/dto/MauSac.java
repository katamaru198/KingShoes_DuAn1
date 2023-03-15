package duongptph28955.fpoly.kingshoes_duan1.dto;

public class MauSac {
    public int maMau;
    public String tenMau;
    public int maSP;

    public MauSac() {
    }

    public MauSac(int maMau, String tenMau, int maSP) {
        this.maMau = maMau;
        this.tenMau = tenMau;
        this.maSP = maSP;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
}
