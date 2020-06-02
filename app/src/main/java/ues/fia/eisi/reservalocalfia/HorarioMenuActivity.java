package ues.fia.eisi.reservalocalfia;



import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class HorarioMenuActivity extends AppCompatActivity {
    String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro", "Actualizar Registro"};
    String[] activities={"HorarioInsertarActivity","HorarioEliminarActivity","HorarioConsultarActivity", "HorarioActualizarActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_menu);
        //ListView listView = getListView();
        //listView.setBackgroundColor(Color.rgb(174, 182, 191));
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        //setListAdapter(adapter);
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnInsertar:
                miIntent=new Intent(HorarioMenuActivity.this,HorarioInsertarActivity.class);
                break;
            case R.id.btnEliminar:
                miIntent=new Intent(HorarioMenuActivity.this,HorarioEliminarActivity.class);
                break;
            case R.id.btnConsultar:
                miIntent=new Intent(HorarioMenuActivity.this,HorarioConsultarActivity.class);
                break;
            case R.id.btnActualizar:
                miIntent=new Intent(HorarioMenuActivity.this,HorarioActualizarActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }
/**
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        l.getChildAt(position).setBackgroundColor(Color.rgb(133, 149, 158));
        try{
            Class<?> clase=Class.forName("ues.fia.eisi.reservalocalfia."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }**/
}
