package com.tecnologiasmoviles.dbafitnessapp;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPrincipiosweider extends RecyclerView.Adapter<AdaptadorPrincipiosweider.ViewHolderPrincipiosWeider> {

    ArrayList<PrincipiosweiderVo> listaPrincipiosweider;

    public AdaptadorPrincipiosweider(ArrayList<PrincipiosweiderVo> listaPrincipiosweider) {
        this.listaPrincipiosweider = listaPrincipiosweider;
    }

    @Override
    public ViewHolderPrincipiosWeider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_principiosweider, null, false);
        return new ViewHolderPrincipiosWeider(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPrincipiosWeider Miholder, int position) {

        Miholder.etiNombre.setText(listaPrincipiosweider.get(position).getNombre());
        Miholder.etiInformacion.setText(listaPrincipiosweider.get(position).getInfo());
        Miholder.foto.setImageResource(listaPrincipiosweider.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaPrincipiosweider.size();
    }

    public class ViewHolderPrincipiosWeider extends RecyclerView.ViewHolder {

        TextView etiNombre;
        TextView etiInformacion;
        ImageView foto;

        public ViewHolderPrincipiosWeider(View itemView) {
            super(itemView);
            etiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            etiInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
