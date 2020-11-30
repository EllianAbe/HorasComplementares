package com.uniso.lpdm.horascomplementares;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class AtividadeComplementarAdapter extends ArrayAdapter<AtividadeComplementar> {
    List<AtividadeComplementar> atividadeComplementarArrayList;
    private int resource;
    Context c;

    public AtividadeComplementarAdapter(Context c, int resource, List<AtividadeComplementar> list) {
        super(c, resource, list);

        atividadeComplementarArrayList = list;
        this.resource = resource;
        this.c = c;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return atividadeComplementarArrayList.size();
    }

    @Override
    public AtividadeComplementar getItem(int position) {
        // TODO Auto-generated method stub
        return atividadeComplementarArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout atividadeView;
        final AtividadeComplementar atividadeComplementar = getItem(position);

        if (convertView == null) {
            atividadeView = new ConstraintLayout(getContext());

            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            layoutInflater.inflate(resource, atividadeView, true);
        } else {
            atividadeView = (ConstraintLayout) convertView;
        }

        TextView categoria = (TextView) atividadeView.findViewById(R.id.txtCategoria);
        categoria.setText(atividadeComplementar.getTipo());

        TextView descricao = (TextView) atividadeView.findViewById(R.id.txtDescricao);
        descricao.setText(atividadeComplementar.getNome());

        TextView hora = (TextView) atividadeView.findViewById(R.id.txtHoras);
        hora.setText(Integer.toString(atividadeComplementar.getNumHoras()) + " Horas");

        TextView status = (TextView) atividadeView.findViewById(R.id.txtStatus);
        status.setText(atividadeComplementar.getStatusDesc());

        return atividadeView;
    }

    public int getAtividadeId(int pos){
        return getItem(pos).getId();
    }
}