package duongptph28955.fpoly.kingshoes_duan1.dto;

public class DoanhThu {
    private int tongTien;
    private int thang;

    public DoanhThu(int thang, int tongTien) {
        this.thang = thang;
        this.tongTien = tongTien;
    }


    public DoanhThu() {
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }
}
