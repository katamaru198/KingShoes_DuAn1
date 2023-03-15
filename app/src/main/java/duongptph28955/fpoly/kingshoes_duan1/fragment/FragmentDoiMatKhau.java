package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import duongptph28955.fpoly.kingshoes_duan1.DAO.ThanhVienDAO;
import duongptph28955.fpoly.kingshoes_duan1.MainActivity;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.ThanhVien;

public class FragmentDoiMatKhau extends Fragment {
    ThanhVienDAO dao;
    EditText ed_OldPass, ed_NewPass, ed_RePass;
    Button btn_Cancel, btn_Save;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = new ThanhVienDAO(getActivity());
        ed_NewPass = view.findViewById(R.id.edNewPass);
        ed_OldPass = view.findViewById(R.id.edOldPass);
        ed_RePass = view.findViewById(R.id.edRenewPass);
        btn_Cancel = view.findViewById(R.id.btnCancelRePass);
        btn_Save = view.findViewById(R.id.btnSaveRePass);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_OldPass.setText("");
                ed_NewPass.setText("");
                ed_RePass.setText("");
            }
        });
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("GHINHO_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("TAIKHOAN", "");
                if (validate()>0){
                    ThanhVien thuThu = dao.getID(user);
                    thuThu.matKhau = ed_NewPass.getText().toString();
                    dao.updateThanhVien(thuThu);
                    if (dao.updatePass(thuThu)>0){
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        ed_OldPass.setText("");
                        ed_RePass.setText("");
                        ed_NewPass.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thay đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public int validate(){
        int check = 1;
        if (ed_OldPass.getText().length() == 0 || ed_NewPass.getText().length() == 0|| ed_RePass.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("GHINHO_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("MATKHAU", "");
            String pass = ed_NewPass.getText().toString();
            String RePass = ed_RePass.getText().toString();
            if (!passOld.equals(ed_OldPass.getText().toString())){
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(RePass)){
                Toast.makeText(getActivity(), "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }
        return check;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doimatkhau_fragment, container, false);
    }
}
