package ues.fia.eisi.reservalocalfia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TipoEventoMenuActivity extends AppCompatActivity {

    String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro", "Actualizar Registro"};
    String[] activities={"TipoEventoInsertarActivity","TipoEventoEliminarActivity","TipoEventoConsultarActivity", "TipoEventoActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setListAdapter(new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, menu));
        setContentView(R.layout.activity_tipo_evento_menu);
        //BDhelper=new ControlBDReservaLocales(this);
    }
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnInsertar:
                miIntent=new Intent(TipoEventoMenuActivity.this,TipoEventoInsertarActivity.class);
                break;
            case R.id.btnEliminar:
                miIntent=new Intent(TipoEventoMenuActivity.this,TipoEventoEliminarActivity.class);
                break;
            case R.id.btnConsultar:
                miIntent=new Intent(TipoEventoMenuActivity.this,TipoEventoConsultarActivity.class);
                break;
            case R.id.btnActualizar:
                miIntent=new Intent(TipoEventoMenuActivity.this,TipoEventoActualizarActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }

}
