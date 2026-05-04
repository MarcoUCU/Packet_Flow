package com.example;

public enum EstadoPaquete {
    PENDIENTE,
    EN_TRANSITO,
    RECIBIDO,
    PERDIDO;

    public static EstadoPaquete desde(String s) {
        if (s == null) {
            return PENDIENTE;
        }
        String norm = s.trim().toUpperCase().replace(' ', '_');
        if (norm.isEmpty()) {
            return PENDIENTE;
        }
        return EstadoPaquete.valueOf(norm);
    }
}
