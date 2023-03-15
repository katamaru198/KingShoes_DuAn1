package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import duongptph28955.fpoly.kingshoes_duan1.DAO.ThanhVienDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.ThanhVien;

public class themTaiKhoan_Fragment extends Fragment {
    TextInputLayout edUser, edTenTV, edSDT, edPass, edRepass;
    ThanhVienDAO dao;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edUser = view.findViewById(R.id.edUsername);
        edTenTV = view.findViewById(R.id.edTenThanhVien);
        edSDT = view.findViewById(R.id.edSDTTV);
        edPass = view.findViewById(R.id.edPassword);
        edRepass = view.findViewById(R.id.edRepass);
        view.findViewById(R.id.btn_SaveTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.maTV = edUser.getEditText().getText().toString();
                thanhVien.tenTV = edTenTV.getEditText().getText().toString();
                thanhVien.soDT = edSDT.getEditText().getText().toString();
                thanhVien.matKhau = edPass.getEditText().getText().toString();
                dao = new ThanhVienDAO(getContext());
                if (validate()>0){
                    if (dao.insertThanhVien(thanhVien)>0){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public int validate(){
        int check = 1;
        if (edUser.getEditText().getText().toString().length() == 0 || edTenTV.getEditText().getText().toString().length() == 0 || edSDT.getEditText().getText().toString().length() == 0 ||edPass.getEditText().getText().toString().length() == 0|| edRepass.getEditText().getText().toString().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (!edPass.getEditText().getText().toString().equals(edRepass.getEditText().getText().toString())){
            Toast.makeText(getContext(), "Password nhập lại không đúng", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_themtv, null);
    }
}
