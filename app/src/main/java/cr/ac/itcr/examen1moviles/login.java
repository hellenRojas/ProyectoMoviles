package cr.ac.itcr.examen1moviles;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.itcr.examen1moviles.access_data.LoginDataBaseAdapter;

public class login extends Activity
{
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    // Se crea la instancia de la base de datos SQLite
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        TextView btnSignUp=(TextView)findViewById(R.id.textRegistro);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSignUP=new Intent(getApplicationContext(),singUp.class);
                startActivity(intentSignUP);
            }
        });


        final EditText editTextUserName=(EditText)findViewById(R.id.txtEmail);
        final EditText editTextPassword=(EditText)findViewById(R.id.txtPass);

        Button btnSignIn=(Button)findViewById(R.id.btnLogin);

        // Se le da funcionalidad el boton de logeo
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Se obtiene el nombre de usuario y la contraseña
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // Se recupera la constraseña de la base de datos (de su respectivo usuario)
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                // Se coamprueba que la contraseña conincida con el usuario
                if(password.equals(storedPassword))
                {
                    Toast.makeText(login.this, "Se logeo correctamente", Toast.LENGTH_LONG).show();
                    Intent intentDash=new Intent(getApplicationContext(),DashboardActivity.class);
                    startActivity(intentDash);

                }
                else
                {
                    Toast.makeText(login.this, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }
}
