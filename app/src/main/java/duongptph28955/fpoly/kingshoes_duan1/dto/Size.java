package duongptph28955.fpoly.kingshoes_duan1.dto;

public class Size {
    public int maSize;
    public String size;
    public int maSP;

    public Size() {
    }

    public Size(int maSize, String size, int maSP) {
        this.maSize = maSize;
        this.size = size;
        this.maSP = maSP;
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
}
