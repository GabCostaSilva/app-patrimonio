package br.ufms.gabriel.costa.silva.patrimonio.ui.tipos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import br.ufms.gabriel.costa.silva.patrimonio.backend.AppDatabase;
import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.TipoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Tipo;
import br.ufms.gabriel.costa.silva.patrimonio.databinding.FragmentTiposBinding;

public class TiposFragment extends Fragment {

    private FragmentTiposBinding binding;

    private ListView listView;

    private Intent intent;
    private TipoDao tipoDao;
    private Context applicationContext;

    public TiposFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTiposBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        applicationContext = getContext();
        AppDatabase db = AppDatabase.getDatabase(applicationContext);

        listView = binding.tiposList;
        Button novoTipo = binding.novoTipo;
        novoTipo.setOnClickListener(view -> {
            Intent criaObjetoActivity = new Intent(applicationContext, CriaTipoActivity.class);
            startActivity(criaObjetoActivity);
        });
        this.tipoDao = db.tipoDao();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        intent = new Intent(getActivity(), CriaTipoActivity.class);
        preencheTipos(applicationContext);
    }

    private void preencheTipos(Context context) {
        List<Tipo> tipos = tipoDao.getAll();
        ArrayAdapter<Tipo> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, tipos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Tipo tipo = tipos.get(position);
            intent.putExtra("tipo_id", tipo.getId());
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
