package dk.tec.wkpasswordvault;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    EditText username, password, repeatPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeatPassword);
        register = findViewById(R.id.register);

        register.setOnClickListener(v -> register());
    }

    public void register() {
        if (!password.getText().toString().equals(repeatPassword.getText().toString())) {
            return;
        }
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String url = "http://10.131.209.16:8888/user/register";

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
                if (response.code() == 200) loginReturn();
            }

            public void onFailure(Call call, IOException e) {
                return;
            }
        });
    }

    public void loginReturn() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}