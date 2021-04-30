package com.example.mynailsadmin.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfigFirebase {

    private static StorageReference referenciaStorage;
    private static FirebaseAuth referenciaAutenticacao;
    private static DatabaseReference referenciaFirebase;

    public static String getIdUsuario()
    {
        FirebaseAuth autenticacao = getReferenciaAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }

    //retorna a instancia do FirebaseDatabase
    public static DatabaseReference getFirebase()
    {
        if ( referenciaFirebase == null )
        {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    //Retorna a instancia do FirebaseAuth
    public static FirebaseAuth getReferenciaAutenticacao()
    {
        if( referenciaAutenticacao == null)
        {
            referenciaAutenticacao = FirebaseAuth.getInstance();
        }
        return referenciaAutenticacao;
    }

    //retorna a instancia do Storage
    public static StorageReference getReferenciaStorage()
    {
        if(referenciaStorage == null)
        {
            referenciaStorage = FirebaseStorage.getInstance().getReference();
        }
        return referenciaStorage;
    }
}
