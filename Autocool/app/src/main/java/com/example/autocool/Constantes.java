package com.example.autocool;

public class Constantes {
    private static final String adresseAPI = "http://192.168.1.34/Autocool-php/controleurAndroid/";


    public static String getAPI(String page){
        return adresseAPI + page;
    }

}
