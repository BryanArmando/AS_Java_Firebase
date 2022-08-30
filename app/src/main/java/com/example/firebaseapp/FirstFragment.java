package com.example.firebaseapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseapp.databinding.FragmentFirstBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayList<Product> listProductos=new ArrayList<Product>();
    private RecyclerView listaRecycler;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = binding.inputCode.getText().toString();
                String nombre = binding.inputProduct.getText().toString();
                String precio = binding.inputPrecio.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("datos");

                Product product = new Product(code,nombre,precio);
                myRef.setValue(product);

                String text = "Producto agregádo en la base de datos";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getActivity().getApplicationContext(), text, duration);
                toast.show();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("datos");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String codigo = dataSnapshot.child("code").getValue().toString();
                    String nombre = dataSnapshot.child("name").getValue().toString();
                    String precio = dataSnapshot.child("price").getValue().toString();

                    binding.valueCode.setText(codigo+"  ");
                    binding.valueName.setText(nombre+"  ");
                    binding.valuePrice.setText(precio+"  ");
                }



                //String codigo = dataSnapshot.child("code");

               // Toast toast = Toast.makeText(getActivity().getApplicationContext(), "cambio"+codigo, Toast.LENGTH_LONG);
               // toast.show();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* binding.recylcerViewLis.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        listProductos.add(new Product("1","coco","1.50"));

        listProductos.add(new Product("1","coco","1.50"));
        listProductos.add(new Product("1","coco","1.50"));
        listProductos.add(new Product("1","coco","1.50"));
        listProductos.add(new Product("2","coco","1.50"));

        AdaptadorDatos adatp= new AdaptadorDatos(listProductos);
        binding.recylcerViewLis.setAdapter(adatp);*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}