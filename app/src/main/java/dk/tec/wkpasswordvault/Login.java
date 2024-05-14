package dk.tec.wkpasswordvault;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.io.IOException;

import dk.tec.wkpasswordvault.models.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();

    EditText username, password;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.regiser);

        login.setOnClickListener(v -> login());
        register.setOnClickListener(v -> register());
    }

    public void login() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String url = "http://10.131.209.16:8888/user/login";

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
                if (response.code() == 200) {
                    SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    Gson gson = new Gson();
                    int userId = gson.fromJson(response.body().string(), User.class).getId();

                    editor.putInt("userId", userId).apply();

                    loggedIn();
                }

                return;
            }

            public void onFailure(Call call, IOException e) {
                return;
            }
        });
    }

    public void loggedIn() {
        Intent intent = new Intent(this, Overview.class);
        startActivity(intent);
    }

    public void register() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}