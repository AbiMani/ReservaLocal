package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoInsertarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdEncargadoLocal;
    EditText editNomEncargadoLocal;
    EditText editApeEncargadoLocal;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_insertar);
        helper = new  ControlReserveLocal (this);
        editIdEncargadoLocal = (EditText) findViewById(R.id.editIdEncargadoinsertar);
        editNomEncargadoLocal = (EditText) findViewById(R.id.editNomEncargadoinsertar);
        editApeEncargadoLocal = (EditText) findViewById(R.id.editApeEncargadoinsertar);

    }
    public void insertarEncargado(View v) {
        String IdEncargadoLocal=editIdEncargadoLocal.getText().toString();
        String nomEncargadoLocal=editNomEncargadoLocal.getText().toString();
        String apeEncargadoLocal=editApeEncargadoLocal.getText().toString();

        Encargado encargado=new Encargado();
        encargado.setIdEncargadoLocal(IdEncargadoLocal);
        encargado.setNomEncargadoLocal(nomEncargadoLocal);
        encargado.setApeEncargadoLocal(apeEncargadoLocal);
        String regInsertados;
        helper.abrir();
        regInsertados=helper.insertar(encargado);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdEncargadoLocal.setText("");

        editNomEncargadoLocal.setText("");
        editApeEncargadoLocal.setText("");

    }}


