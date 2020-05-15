package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HorarioActualizarActivity extends Activity {
    ControlReserveLocal helper;
    EditText editIdHorario;
    EditText editIdDia;
    EditText editHoraInicio;
    EditText editHoraFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_actualizar);
        helper = new ControlReserveLocal(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editIdDia = (EditText) findViewById(R.id.editIdDia);
        editHoraInicio = (EditText) findViewById(R.id.editHoraInicio);
        editHoraFin = (EditText) findViewById(R.id.editHoraFin);
    }

    public void actualizarHorario(View v){
        Horario horario = new Horario();
        horario.setidHorario(Integer.parseInt(editIdHorario.getText().toString()));
        horario.setidDia(editIdDia.getText().toString());
        horario.sethoraInicio(editHoraInicio.getText().toString());
        horario.sethoraFin(editHoraFin.getText().toString());
        helper.abrir();
        String estado =helper.actualizar(horario);
        helper.cerrar();
        Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();

    }
    public void  limpiarTexto(View v){
        editIdHorario.setText("");
        editIdDia.setText("");
        editHoraInicio.setText("");
        editHoraFin.setText("");
    }

}
