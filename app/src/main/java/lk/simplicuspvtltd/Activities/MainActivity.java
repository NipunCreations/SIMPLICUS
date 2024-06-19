package lk.simplicuspvtltd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import lk.simplicuspvtltd.R;

public class MainActivity extends AppCompatActivity {

    private View buttonManageItems, buttonViewItems;
    private BottomNavigationView bottomNavigationView;
    private ImageView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtonManageItems();
        initButtonViewItems();
        initLoginButton();
    }

    private void initButtonManageItems() {
        buttonManageItems = findViewById(R.id.buttonManageItems);
        buttonManageItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemManagementActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButtonViewItems() {
        buttonViewItems = findViewById(R.id.buttonViewItems);
        buttonViewItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewItemsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLoginButton() {
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
