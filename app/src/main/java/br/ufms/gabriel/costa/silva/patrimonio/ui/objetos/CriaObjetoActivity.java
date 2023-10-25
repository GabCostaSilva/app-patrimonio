package br.ufms.gabriel.costa.silva.patrimonio.ui.objetos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.ufms.gabriel.costa.silva.patrimonio.R;
import br.ufms.gabriel.costa.silva.patrimonio.backend.AppDatabase;
import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.ObjetoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Objeto;

public class CriaObjetoActivity extends AppCompatActivity implements ObjetosControler {

    private AppDatabase db;
    private View edtUsuario;
    private EditText editTipo, editNomeFuncionario;

    private Button btnSalvar, btnExcluir;
    private int numPatrimonio;
    private ObjetoDao objetoDao;
    private Objeto objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_novo_objeto);
        db = AppDatabase.getDatabase(getApplicationContext());
        objetoDao = db.objetoDao();
        editTipo = findViewById(R.id.tipoId);
        editNomeFuncionario = findViewById(R.id.nomeFuncionario);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);

        numPatrimonio = getIntent().getIntExtra("objeto_id", -1);
    }

    @Override
    public void excluirObjeto(View view) {

    }

    @Override
    public void salvarObjeto(View view) {
        String tipo = editTipo.getText().toString();
        String funcionarioName = editNomeFuncionario.getText().toString();

        if (tipo.equals("") || funcionarioName.equals("")) {
            Toast.makeText(CriaObjetoActivity.this,
                    "Preencha todos os campos",
                    Toast.LENGTH_LONG).show();
        }

        Objeto objetoParaCriacao = new Objeto(Integer.parseInt(tipo), funcionarioName);

        if(Objects.nonNull(objeto)){
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
        editTipo.setText(String.valueOf(objeto.getTipoId()));
        editNomeFuncionario.setText(objeto.getNomeFuncionario());
    }
}
