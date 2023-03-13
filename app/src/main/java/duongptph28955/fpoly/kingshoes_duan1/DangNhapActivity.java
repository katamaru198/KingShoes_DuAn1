package duongptph28955.fpoly.kingshoes_duan1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;


public class DangNhapActivity extends AppCompatActivity {

    TextInputLayout edtTaiKhoan, edtMatKhau;
    Button btnDangNhap;
    CheckBox chkLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        chkLuu = findViewById(R.id.chkLuu);

    }
}