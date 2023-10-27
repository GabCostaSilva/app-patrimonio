package br.ufms.gabriel.costa.silva.patrimonio.ui.objetos;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufms.gabriel.costa.silva.patrimonio.backend.entidades.Tipo;

public class TipoSpinnerAdapter extends ArrayAdapter<Tipo> {

    private final List<Tipo> tipos;

    public TipoSpinnerAdapter(Context context, int textViewResourceId, List<Tipo> tipos) {
        super(context, textViewResourceId, tipos);
        // Your sent context
        this.tipos = tipos;
    }

    @Override
    public int getCount() {
        return tipos.size();
    }

    @Override
    public Tipo getItem(int position) {
        return tipos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        Tipo tipo = tipos.get(position);
        label.setText(tipo.getTipo());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setEms(35);
        Tipo tipo = tipos.get(position);
        label.setText(tipo.getTipo());
        return label;
    }
}