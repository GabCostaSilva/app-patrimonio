package br.ufms.gabriel.costa.silva.patrimonio.ui.objetos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import br.ufms.gabriel.costa.silva.patrimonio.R;
import br.ufms.gabriel.costa.silva.patrimonio.backend.AppDatabase;
import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.ObjetoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.TipoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Objeto;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Tipo;

public class CriaObjetoActivity extends AppCompatActivity implements ObjetosControler {

    private AppDatabase db;
    private View edtUsuario;
    private EditText editNomeFuncionario;

    private Spinner editTipo;
    private Button btnSalvar, btnExcluir;
    private int numPatrimonio;
    private ObjetoDao objetoDao;
    private Objeto objeto;

    private Tipo tipoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_novo_objeto);
        db = AppDatabase.getDatabase(getApplicationContext());
        objetoDao = db.objetoDao();
        TipoDao tipoDao = db.tipoDao();
        editTipo = findViewById(R.id.tipoId);
        editNomeFuncionario = findViewById(R.id.nomeFuncionario);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);

        List<Tipo> tipos = tipoDao.getAll();

        SpinnerAdapter adapter = new TipoSpinnerAdapter(this, android.R.layout.simple_spinner_item, tipos.toArray(new Tipo[0]));
        editTipo.setAdapter(adapter);
        editTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Tipo tipo = (Tipo) adapter.getItem(position);
                assert tipo != null;
                tipoSelecionado = tipo;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });
        numPatrimonio = getIntent().getIntExtra("objeto_id", -1);
    }

    @Override
    public void excluir(View view) {
        Objeto objetoParaExcluir = objetoDao.getObjeto(numPatrimonio);
        objetoDao.delete(objetoParaExcluir);
    }

    @Override
    public void salvar(View view) {
        String funcionarioName = editNomeFuncionario.getText().toString();

        if (Objects.isNull(tipoSelecionado) || funcionarioName.equals("")) {
            Toast.makeText(CriaObjetoActivity.this,
                    "Preencha todos os campos",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Objeto objetoParaCriacao = new Objeto(LocalDate.now().toString(), tipoSelecionado.getId(), funcionarioName);

        if (Objects.nonNull(objeto)) {
            objetoParaCriacao.setNumPatrimonio(objeto.getNumPatrimonio());
            objetoDao.update(objetoParaCriacao);
            Toast.makeText(this, "Atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            objetoDao.insertAll(objetoParaCriacao);
            Toast.makeText(this, "Objeto cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltar(View view) {
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (numPatrimonio >= 0)
            getObjeto();
        else
            btnExcluir.setVisibility(View.GONE);
    }

    private void getObjeto() {
        objeto = objetoDao.getObjeto(numPatrimonio);
        editTipo.setSelection(objeto.getTipoId());
        editNomeFuncionario.setText(objeto.getNomeFuncionario());
    }
}
