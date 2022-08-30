package com.example.firebaseapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos> {
    private ArrayList<Product> listProductos;
    public  AdaptadorDatos(ArrayList<Product> listProductos){
        this.listProductos=listProductos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatos(listProductos.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView code;
        TextView name;
        TextView price;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            code=(TextView) itemView.findViewById(R.id.valueCode);
            name=(TextView) itemView.findViewById(R.id.valueName);
            price=(TextView) itemView.findViewById(R.id.valuePrice);

        }

        public void asignarDatos(Product product) {

            code.setText("olaa");

        }
    }
}
