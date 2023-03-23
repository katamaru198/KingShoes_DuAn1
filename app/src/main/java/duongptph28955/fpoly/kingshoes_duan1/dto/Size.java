package duongptph28955.fpoly.kingshoes_duan1.dto;

public class Size {
    public int maSize;
    public String size;
    public int maSP;
    public int soLuong;
    public int maMau;
    public Size() {
    }

    public Size(int maSize, String size, int maSP, int soLuong, int maMau) {
        this.maSize = maSize;
        this.size = size;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.maMau = maMau;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }
}
