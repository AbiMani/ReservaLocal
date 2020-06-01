package ues.fia.eisi.reservalocalfia;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HorarioInsertarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdHorario;
    EditText editIdDia;
    Spinner spinnerDia;
    Spinner spinnerInicio;
    Spinner spinnerFin;
    String selecInicio;
    String selecFin;
    ArrayList<String> listaDias;
    ArrayList<Dia> diasList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_insertar);
        helper = new ControlReserveLocal(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editIdDia = (EditText) findViewById(R.id.editIdDia);
        spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
        spinnerInicio = (Spinner) findViewById(R.id.spinnerInicio);
        spinnerFin = (Spinner) findViewById(R.id.spinnerFin);


        //diasList = helper.consultaDia();
        //obtenerdias(diasList);
        //ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaDias);
        //spinnerDia.setAdapter(adaptador);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.inicio,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.fin,android.R.layout.simple_spinner_item);
        spinnerInicio.setAdapter(adapter);
        spinnerFin.setAdapter(adapter1);

        spinnerInicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecInicio = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerFin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecFin = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void obtenerdias(ArrayList<Dia> diasList) {
        listaDias= new ArrayList<String>();
        listaDias.add("seleccione:");
        for(int i=0;i<diasList.size();i++){
            listaDias.add(diasList.get(i).getIdDia()+" - "+diasList.get(i).getNomDia());
        }
    }


    public void insertarHorario(View v) {
        Integer idHorario= Integer.parseInt(editIdHorario.getText().toString());
        String idDia=editIdDia.getText().toString();
        String horaInicio=selecInicio;
        String horaFin=selecFin;

        String regInsertados;
        Horario horario=new Horario();
        horario.setidHorario(idHorario);
        horario.setidDia(idDia);
        horario.sethoraInicio(horaInicio);
        horario.sethoraFin(horaFin);

        helper.abrir();
        regInsertados=helper.insertar(horario);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdHorario.setText("");
        editIdDia.setText("");
    }
}
