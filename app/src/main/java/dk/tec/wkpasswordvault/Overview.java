package dk.tec.wkpasswordvault;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_overview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Create a list to display in the Spinner
        List<String> mList = Arrays.asList("Delhi", "Mumbai", "Chennai", "Kolkata", "Bengaluru");

        // Create an adapter as shown below
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_xml, mList);
        mArrayAdapter.setDropDownViewResource(R.layout.spinner_xml);
        // Set the adapter to the Spinner
        Spinner mSpinner = findViewById(R.id.passwords);

        mSpinner.setAdapter(mArrayAdapter);
    }


}