package com.seccion8.seccion_08.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.seccion8.seccion_08.Fragments.AlertsFragment;
import com.seccion8.seccion_08.Fragments.EmailFragment;
import com.seccion8.seccion_08.Fragments.InfoFragment;
import com.seccion8.seccion_08.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        setFragmentByDefault();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // Se recibe el elemento al que se le hace click
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_mail:
                        fragment = new EmailFragment();
                        // boolean para indicar que se va a abrir un fragment
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_alert:
                        fragment = new AlertsFragment();
                        // boolean para indicar que se va a abrir un fragment
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        // boolean para indicar que se va a abrir un fragment
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_opcion_1:
                        Toast.makeText(getApplicationContext(), "Has clickeado en la opcion 1", Toast.LENGTH_LONG).show();
                        break;
                }

                if (fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    // para resaltar el elemento del menu en el que estamos
                    item.setChecked(true);
                    // poner nuevo titulo en action bar
                    getSupportActionBar().setTitle(item.getTitle());
                    // cerrar el menu
                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        // Habilitar el icono alado de la barra y mostrarlo
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void  setFragmentByDefault() {
        // Metodo para establecer fragment por default
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new EmailFragment()).commit();
        // Marcar elemento email como seleccionado desde el inicio
        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);
        // Añadir titulo a action bar
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // abrir el menu nav_options lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
