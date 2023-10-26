package br.ufms.gabriel.costa.silva.patrimonio.ui.tipos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.ufms.gabriel.costa.silva.patrimonio.R;
import br.ufms.gabriel.costa.silva.patrimonio.backend.AppDatabase;
import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.TipoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Tipo;
import br.ufms.gabriel.costa.silva.patrimonio.ui.objetos.ObjetosControler;

public class CriaTipoActivity extends AppCompatActivity implements ObjetosControler {

    private AppDatabase db;
    private EditText editTipo, editDescricao;
    private Button btnExcluir;
    private int tipoId;
    private TipoDao tipoDao;
    private Tipo tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_novo_tipo);
        db = AppDatabase.getDatabase(getApplicationContext());
        tipoDao = db.tipoDao();
        editTipo = findViewById(R.id.tipoName);
        editDescricao = findViewById(R.id.descricaoTipo);
        btnExcluir = findViewById(R.id.btnExcluir);

        tipoId = getIntent().getIntExtra("tipo_id", -1);
    }

    @Override
    public void excluir(View view) {

    }

    @Override
    public void salvar(View view) {
        String descricao = editDescricao.getText().toString();
        String tipoName = editTipo.getText().toString();

        if (tipoName.equals("") || descricao.equals("")) {
            Toast.makeText(CriaTipoActivity.this,
                    "Preencha todos os campos",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Tipo tipoParaCriacao = new Tipo(tipoName, descricao);

        if (Objects.nonNull(this.tipo)) {
            tipoParaCriacao.setId(this.tipo.getId());
            tipoDao.update(tipoParaCriacao);
            Toast.makeText(this, "Atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            tipoDao.insertAll(tipoParaCriacao);
            Toast.makeText(this, "Objeto cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltar(View view) {
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (tipoId >= 0)
            getTipo();
        else
            btnExcluir.setVisibility(View.GONE);
    }

    private void getTipo() {
        tipo = tipoDao.getUser(tipoId);
        editTipo.setText(tipo.getTipo());
        editDescricao.setText(tipo.getDescricao());
    }
}
