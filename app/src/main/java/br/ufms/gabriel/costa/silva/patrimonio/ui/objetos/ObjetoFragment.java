package br.ufms.gabriel.costa.silva.patrimonio.ui.objetos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import br.ufms.gabriel.costa.silva.patrimonio.backend.AppDatabase;
import br.ufms.gabriel.costa.silva.patrimonio.backend.dao.ObjetoTipoDao;
import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.ObjetoTipo;
import br.ufms.gabriel.costa.silva.patrimonio.databinding.FragmentObjetoBinding;

public class ObjetoFragment extends Fragment {

    private FragmentObjetoBinding binding;

    private ListView listView;

    private Intent intent;
    private ObjetoTipoDao objetoTipoDao;
    private Context applicationContext;

    public ObjetoFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ObjetosViewModel objetosViewModel =
                new ViewModelProvider(this).get(ObjetosViewModel.class);
        binding = FragmentObjetoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textDashboard;
        objetosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        applicationContext = getContext();
        AppDatabase db = AppDatabase.getDatabase(applicationContext);

        listView = binding.objetosList;
        Button novoObjeto = binding.novoObjeto;
        novoObjeto.setOnClickListener(view -> {
            Intent criaObjetoActivity = new Intent(applicationContext, CriaObjetoActivity.class);
            startActivity(criaObjetoActivity);
        });
        this.objetoTipoDao = db.objetoTipoDao();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        intent = new Intent(getActivity(), CriaObjetoActivity.class);
        preencheObjetos(applicationContext);
    }

    private void preencheObjetos(Context context) {
        List<ObjetoTipo> objetos = objetoTipoDao.getAllObjetoTipo();
        ArrayAdapter<ObjetoTipo> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, objetos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            ObjetoTipo objetoSelecionado = objetos.get(position);
            intent.putExtra("objeto_id", objetoSelecionado.getNumPatrimonio());
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
