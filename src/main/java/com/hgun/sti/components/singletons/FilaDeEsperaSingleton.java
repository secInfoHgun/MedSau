package com.hgun.sti.components.singletons;

import com.hgun.sti.models.FilaDeEspera;

import java.util.ArrayList;

public class FilaDeEsperaSingleton {
    private static FilaDeEsperaSingleton filaDeEsperaSingleton;
    public ArrayList<FilaDeEspera> filaDeEspera;

    private FilaDeEsperaSingleton() {
        this.filaDeEspera = new ArrayList<FilaDeEspera>();
    }

    public static FilaDeEsperaSingleton getInstance() {
        if (filaDeEsperaSingleton == null)
            filaDeEsperaSingleton = new FilaDeEsperaSingleton();
        return filaDeEsperaSingleton;
    }
}
