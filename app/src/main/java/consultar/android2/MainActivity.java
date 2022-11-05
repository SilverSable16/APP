package consultar.android2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {
    EditText consulta;
    ImageButton busqueda;
    TextView id;
    TextView nombre;
    TextView marca;
    TextView descripcion;
    TextView fn_ingreso;
    TextView pres_costo;
    TextView pres_venta;
    TextView existencia;
    TextView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consulta=(EditText) findViewById(R.id.txtnombre);
        id=(TextView) findViewById(R.id.lblidproducto);
        nombre=(TextView) findViewById(R.id.lblnombre);
        marca=(TextView) findViewById(R.id.lblmarca);
        descripcion=(TextView) findViewById(R.id.lbldescripcion);
        fn_ingreso=(TextView) findViewById(R.id.lblfechaingreso);
        pres_costo=(TextView) findViewById(R.id.lblpreciocosto);
        pres_venta=(TextView) findViewById(R.id.lblprecioventa);
        existencia=(TextView) findViewById(R.id.lblexistencias);
        imagen=(TextView) findViewById(R.id.lblimagen);
        busqueda = (ImageButton) findViewById(R.id.btnbuscar);
        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultaproducto();
            }
        });
    }
    public Connection conexionBD() {
        Connection cn = null;
        try {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.5; database=new_schema;user=root;password=neeko154");

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return cn;
    }
    public void consultaproducto(){
        try{
            Statement stm= conexionBD().createStatement();
            ResultSet rs=stm.executeQuery("SELECT*FROM productos WHERE producto= '" + consulta.getText()+"'");
            if(rs.next()){
            }
            id.setText(rs.getString(1));
            nombre.setText(rs.getString(2));
            marca.setText(rs.getString(3));
            descripcion.setText(rs.getString(4));
            fn_ingreso.setText(rs.getString(5));
            pres_costo.setText(rs.getString(6));
            pres_venta.setText(rs.getString(7));
            existencia.setText(rs.getString(8));
            imagen.setText(rs.getString(9));
            consulta.setText("");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




}
