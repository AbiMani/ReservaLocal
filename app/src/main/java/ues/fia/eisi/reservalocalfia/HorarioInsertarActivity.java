package ues.fia.eisi.reservalocalfia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioInsertarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdHorario;
    EditText editIdDia;
    EditText editHoraInicio;
    EditText editHoraFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_insertar);
        helper = new ControlReserveLocal(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editIdDia = (EditText) findViewById(R.id.editIdDia);
        editHoraInicio = (EditText) findViewById(R.id.editHoraInicio);
        editHoraFin = (EditText) findViewById(R.id.editHoraFin);
    }
    public void insertarHorario(View v) {
        Integer idHorario= Integer.valueOf(editIdHorario.getText().toString());
        String idDia=editIdDia.getText().toString();
        String horaInicio=editHoraInicio.getText().toString();
        String horaFin=editHoraFin.getText().toString();

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
        editHoraInicio.setText("");
        editHoraFin.setText("");
    }
}
