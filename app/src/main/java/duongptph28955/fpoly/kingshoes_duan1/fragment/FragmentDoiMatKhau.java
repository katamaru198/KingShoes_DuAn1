package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import duongptph28955.fpoly.kingshoes_duan1.DAO.ThanhVienDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.ThuThuDAO;
import duongptph28955.fpoly.kingshoes_duan1.MainActivity;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.ThanhVien;

public class FragmentDoiMatKhau extends Fragment {
    MainActivity mMainActivity;
    EditText edOldPass, edNewPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edOldPass = view.findViewById(R.id.fmChangePass_edPassOld);
        edNewPass = view.findViewById(R.id.fmChangePass_edPassNew);
        edRePass = view.findViewById(R.id.fmChangePass_edRePass);
        btnSave = view.findViewById(R.id.fmChangePass_btnSave);
        btnCancel = view.findViewById(R.id.fmChangePass_btnCancel);

        mMainActivity = (MainActivity) getActivity();

        dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(v -> {
            edOldPass.setText("");
            edNewPass.setText("");
            edRePass.setText("");
        });

        btnSave.setOnClickListener(v -> {
            //Lấy id từ MainActivty
            String id = mMainActivity.getUserName();
            if (Validate() > 0) {
                ThanhVien thanhVien = dao.getID(id);
                thanhVien.setMatKhau(edNewPass.getText().toString().trim());
                if (dao.updatePass(thanhVien) > 0) {
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    edOldPass.setText("");
                    edNewPass.setText("");
                    edRePass.setText("");
                } else {
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public int Validate() {
        int check = 1;
        if (edOldPass.getText().length() == 0 || edNewPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getActivity(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = 0;
        } else {
            //Lấy mật khẩu cũ
            String id = mMainActivity.getUserName();
            ThanhVien thuThu = dao.getID(id);
            String passOld = thuThu.getMatKhau();

            String passNew = edNewPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if (!passOld.equals(edOldPass.getText().toString())) {
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = 0;
            }
            if (!passNew.equals(rePass)) {
                Toast.makeText(getActivity(), "Nhập lại mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = 0;
            }
        }
        return check;
    }
}
