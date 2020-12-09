package com.example.mynailsadmin.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static DatabaseReference refecenciaFirebase;
    private static FirebaseAuth referenciaAutenticacao;


    // retorna a referencia do database
    public static DatabaseReference getFirebase() {
        if ( refecenciaFirebase == null) {
            refecenciaFirebase = FirebaseDatabase.getInstance().getReference("");
        }
        return  refecenciaFirebase;
    }


    // retorna a instacia do FirebaseAuth
    public static FirebaseAuth getReferenciaAutenticacao() {
        if( referenciaAutenticacao == null) {
            referenciaAutenticacao = FirebaseAuth.getInstance();
        }
        return referenciaAutenticacao;
    }
}
