package duongptph28955.fpoly.kingshoes_duan1.dto;

public class LoaiGiay {
    public int maLoai;
    public String tenLoai;

    public LoaiGiay() {
    }

    public LoaiGiay(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
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
}
