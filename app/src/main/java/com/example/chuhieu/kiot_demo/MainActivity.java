package com.example.chuhieu.kiot_demo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.chuhieu.kiot_demo.BanHang.fragment.Fragment_List_SP;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        Navigation();
    }

    private void Navigation() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Lựa chọn bán hàng");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        default Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_List_SP()).commit();
        navigationView.setCheckedItem(R.id.nav_banhang);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_banhang) {
            toolbar.setTitle("Bán Hàng");
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Fragment_List_SP()).commit();
            }
            // Handle the camera action
        } else if (id == R.id.nav_hoadontam) {
            toolbar.setTitle("Hóa đơn tạm");


        } else if (id == R.id.nav_lichsubanhang) {
            toolbar.setTitle("Lịch Sử bán hàng");

        } else if (id == R.id.nav_trahang) {
            toolbar.setTitle("Trả Hàng");

        } else if (id == R.id.nav_lapphieuthu) {
            toolbar.setTitle("Lập Phiếu Thu");

        } else if (id == R.id.nav_baocaohangnay) {
            toolbar.setTitle("Báo Cáo Hàng Ngày");

        } else if (id == R.id.nav_quanly) {
            toolbar.setTitle("Quản Lý");

        } else if (id == R.id.nav_dangxuat) {

        } else if (id == R.id.nav_caidat) {
            toolbar.setTitle("Cài Đặt");

        } else if (id == R.id.nav_chatkiot) {
            toolbar.setTitle("Chat Kio");

        } else if (id == R.id.nav_dieukhoan) {
            toolbar.setTitle("Điều Khoản");

        } else if (id == R.id.nav_goi) {
            toolbar.setTitle("Liên Hệ");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
