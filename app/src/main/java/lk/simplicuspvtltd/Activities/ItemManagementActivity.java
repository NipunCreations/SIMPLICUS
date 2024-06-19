package lk.simplicuspvtltd.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import lk.simplicuspvtltd.Api.ApiService;
import lk.simplicuspvtltd.Models.Item;
import lk.simplicuspvtltd.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemManagementActivity extends AppCompatActivity {

    private EditText editTextItemName;
    private Button buttonAdd, buttonDelete, buttonUpdate;
    private ListView listViewItems;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> itemsList;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_management);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        editTextItemName = findViewById(R.id.editTextItemName);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        listViewItems = findViewById(R.id.listViewItems);
        itemsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        listViewItems.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") // Use 10.0.2.2 for localhost on the emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        fetchItems();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItem();
            }
        });

        listViewItems.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item selection if needed
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click and go to the main activity
            finish(); // This will close the activity and return to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchItems() {
        Call<List<Item>> call = apiService.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Item> items = response.body();
                    itemsList.clear();
                    for (Item item : items) {
                        itemsList.add(item.getName());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ItemManagementActivity.this, "Failed to retrieve items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(ItemManagementActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addItem() {
        String itemName = editTextItemName.getText().toString().trim();
        if (itemName.isEmpty()) {
            Toast.makeText(this, "Please enter an item name", Toast.LENGTH_SHORT).show();
            return;
        }

        Item newItem = new Item();
        newItem.setName(itemName);
        Call<Item> call = apiService.addItem(newItem);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemsList.add(response.body().getName());
                    adapter.notifyDataSetChanged();
                    editTextItemName.setText("");
                } else {
                    Toast.makeText(ItemManagementActivity.this, "Failed to add item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(ItemManagementActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteItem() {
        // Logic to delete item based on selected item in listViewItems
        int selectedItemPosition = listViewItems.getCheckedItemPosition();
        if (selectedItemPosition != ListView.INVALID_POSITION) {
            String itemName = itemsList.get(selectedItemPosition);
            Call<Void> call = apiService.deleteItem(itemName);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        itemsList.remove(selectedItemPosition);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ItemManagementActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ItemManagementActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please select an item to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateItem() {
        // Logic to update item based on selected item in listViewItems
        int selectedItemPosition = listViewItems.getCheckedItemPosition();
        if (selectedItemPosition != ListView.INVALID_POSITION) {
            String itemName = itemsList.get(selectedItemPosition);
            String updatedItemName = editTextItemName.getText().toString().trim();
            if (!updatedItemName.isEmpty()) {
                Item updatedItem = new Item();
                updatedItem.setName(updatedItemName);
                Call<Item> call = apiService.updateItem(itemName, updatedItem);
                call.enqueue(new Callback<Item>() {
                    @Override
                    public void onResponse(Call<Item> call, Response<Item> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            itemsList.set(selectedItemPosition, response.body().getName());
                            adapter.notifyDataSetChanged();
                            editTextItemName.setText("");
                        } else {
                            Toast.makeText(ItemManagementActivity.this, "Failed to update item", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Item> call, Throwable t) {
                        Toast.makeText(ItemManagementActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Please enter an updated item name", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please select an item to update", Toast.LENGTH_SHORT).show();
        }
    }
}
