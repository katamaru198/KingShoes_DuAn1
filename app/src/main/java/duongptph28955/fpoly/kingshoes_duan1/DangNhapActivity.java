package duongptph28955.fpoly.kingshoes_duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import duongptph28955.fpoly.kingshoes_duan1.DAO.ThanhVienDAO;


public class DangNhapActivity extends AppCompatActivity {

    TextInputLayout edTaiKhoan, edMatKhau;
    Button btnDangNhap;
    CheckBox chkLuu;
    ThanhVienDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edMatKhau = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        chkLuu = findViewById(R.id.chkLuu);

        dao = new ThanhVienDAO(this);

        SharedPreferences pref = getSharedPreferences("GHINHO_FILE", MODE_PRIVATE);
        edTaiKhoan.getEditText().setText(pref.getString("TAIKHOAN",""));
        edMatKhau.getEditText().setText(pref.getString("MATKHAU",""));
        chkLuu.setChecked(pref.getBoolean("NHO",false));


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });


    }

    public void checkLogin(){
        String User = edTaiKhoan.getEditText().getText().toString().trim();
        String matkhau = edMatKhau.getEditText().getText().toString().trim();
        if (User.isEmpty() ){
            edTaiKhoan.setError("tài khoản không được trống");
            edMatKhau.setError(null);
        }else if(matkhau.isEmpty()){
            edTaiKhoan.setError(null);
            edMatKhau.setError("mật khẩu không được trống");
        }else{
            edTaiKhoan.setError(null);
            edMatKhau.setError(null);
            if (dao.checkLogin(User,matkhau)>0){
                Toast.makeText(getApplicationContext(),"login thành công",Toast.LENGTH_SHORT).show();
                remember(User,matkhau,chkLuu.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",User);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"tài khoản va mật khẩu không đúng ",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void remember(String u,String m  ,boolean status){
        SharedPreferences preferences= getSharedPreferences("GHINHO_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit =preferences.edit();

        if (!status){
            edit.clear();

        }else {

            edit.putString("TAIKHOAN",u);
            edit.putString("MATKHAU",m);
            edit.putBoolean("NHO",status);

        }
        edit.commit();

    }
}