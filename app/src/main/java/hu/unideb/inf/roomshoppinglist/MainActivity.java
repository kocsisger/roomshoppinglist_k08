package hu.unideb.inf.roomshoppinglist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import hu.unideb.inf.roomshoppinglist.model.ShoppingListDatabase;
import hu.unideb.inf.roomshoppinglist.model.ShoppingListItem;

public class MainActivity extends AppCompatActivity {

    TextView shoppingListTextView;
    EditText newItemNameEditText;

    private ShoppingListDatabase shoppingListDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        shoppingListTextView = findViewById(R.id.shoppingListTextView);
        newItemNameEditText = findViewById(R.id.newItemNameEditText);

        shoppingListDatabase = Room.databaseBuilder(this, ShoppingListDatabase.class, "shoppinglist_db")
                .fallbackToDestructiveMigration(true)
                .build();
    }

    public void addItem(View view) {
        new Thread(() -> {
            ShoppingListItem sli = new ShoppingListItem();
            sli.setName(newItemNameEditText.getText().toString());
            shoppingListDatabase.shoppingListDAO().insertListItem(sli);

            Log.d("CheckDB", shoppingListDatabase.shoppingListDAO().getAllItems().toString());
            String listText = shoppingListDatabase.shoppingListDAO().getAllItems().toString();
            runOnUiThread(() -> shoppingListTextView.setText(listText));
        }).start();
    }
}