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

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Register extends AppCompatActivity {

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
        String url = "http://10.131.209.16:8888/register";

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

//        try (Response response = client.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            if (response.headers().toString().equals("HTTP/1.1 200 OK")) {
//                loginReturn();
//            }
//        } catch (SocketTimeoutException e) {
//            System.out.println("Timeout");
//        } catch (MalformedURLException e) {
//            System.out.println("Malformed URL");
//        } catch (IOException e) {
//            System.out.println("IO Exception");
//        }
    }

    public void loginReturn() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}