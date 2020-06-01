package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TipoEventoActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editidTipoEvento;
    EditText editnombreEvento;
    Spinner spinnerTipoEvento;
    String[] items;
    ArrayList<TipoEvento> list;
    TipoEvento tipoEvento=new TipoEvento();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_actualizar);
        helper = new ControlReserveLocal(this);

        list = helper.ListTipoEventos();
        items = new String[list.size()];
        for (int i=0;i<list.size();i++){
            tipoEvento = list.get(i);
            items[i] = String.valueOf(tipoEvento.getIdTipoEvento());
        }
        spinnerTipoEvento=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerTipoEvento.setAdapter(adapter);
        //editidTipoEvento=(EditText) findViewById(R.id.editidTipoEvento);
        editnombreEvento = (EditText) findViewById(R.id.editNombreEvento);
    }

    public void actualizarTipoEvento(View v) {
        TipoEvento tipoEvento=new TipoEvento();

        String tipoEvento_id=spinnerTipoEvento.getSelectedItem().toString();
        tipoEvento.setIdTipoEvento(tipoEvento_id);
        tipoEvento.setNomTipoEvento(editnombreEvento.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(tipoEvento);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        editnombreEvento.setText("");
        //editidTipoEvento.setText("");
    }

}
