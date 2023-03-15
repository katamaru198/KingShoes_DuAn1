package duongptph28955.fpoly.kingshoes_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import duongptph28955.fpoly.kingshoes_duan1.fragment.fragmentLoaiGiay;
import duongptph28955.fpoly.kingshoes_duan1.fragment.themTaiKhoan_Fragment;

public class MainActivity extends AppCompatActivity {
    NavigationView nav;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.tool_bar);
        drawerLayout = findViewById(R.id.drawerLayout);
        nav = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.action_LoaiGiay:
                        fragment = new fragmentLoaiGiay();
                        break;
                    case R.id.action_Giay:

                        break;
                    case R.id.action_mauSac:

                        break;
                    case R.id.action_size:

                        break;
                    case R.id.action_khachHang:

                        break;
                    case R.id.action_HoaDon:

                        break;
                    case R.id.action_top10:

                        break;
                    case R.id.action_doanhThu:

                        break;
                    case R.id.action_themTaiKhoan:
                        fragment = new themTaiKhoan_Fragment();
                        break;
                    case R.id.action_doiPass:

                        break;
                    case R.id.action_dangXuat:

                        break;
                    default:
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.linerLayout,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}