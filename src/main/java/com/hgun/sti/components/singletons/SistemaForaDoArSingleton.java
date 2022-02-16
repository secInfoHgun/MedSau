package com.hgun.sti.components.singletons;

public class SistemaForaDoArSingleton {
    private static SistemaForaDoArSingleton sistemaForaDoArSingleton;
    public boolean sistemaForaDoAr;

    private SistemaForaDoArSingleton() {
        this.sistemaForaDoAr = false;
    }

    public static SistemaForaDoArSingleton getInstance() {
        if (sistemaForaDoArSingleton == null)
            sistemaForaDoArSingleton = new SistemaForaDoArSingleton();
        return sistemaForaDoArSingleton;
    }

    public void alterarSistemaForaDoAr(){
        this.sistemaForaDoAr = !this.sistemaForaDoAr;
    }
}
