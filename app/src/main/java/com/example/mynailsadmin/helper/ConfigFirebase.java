package com.example.mynailsadmin.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

/*
    // retorna a referencia do database
    public static DatabaseReference getFirebase() {
        if ( refecenciaFirebase == null) {
            refecenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return  refecenciaFirebase;
    }


    // retorna a instacia do FirebaseAuth
    public static FirebaseAuth getReferenciaAutenticacao() {
        if( referenciaAutenticacao == null) {
            referenciaAutenticacao = FirebaseAuth.getInstance();
        }
        return referenciaAutenticacao;
    }*/

    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;


    //retorna a instancia do FirebaseDatabase
    public static DatabaseReference getFirebase(){
        if ( firebase == null ){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }

    //Retorna a instancia do FirebaseAuth
    public static FirebaseAuth getReferenciaAutenticacao(){
        if( autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;

    }
}
