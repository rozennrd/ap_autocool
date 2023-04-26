package com.example.autocool;

public class Constantes {
    private static final String adresseAPI = "http://192.168.43.92/Autocool-php/controleurAndroid/";


    public static String getAPI(String page){
        return adresseAPI + page;
    }

}
