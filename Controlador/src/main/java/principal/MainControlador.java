package principal;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class MainControlador {
    
    public static void main(String[] args) throws IOException { 
        BaseDatos datos = new BaseDatos();
        
        Semaphore mutex = new Semaphore(1);
        
        //recibe los aviones recien creados en vuelos
        ServidorVuelos server = new ServidorVuelos(datos);
        
        //recibe desde ventana Control cuando se selecciona una pista
        ServidorVControl serverVentanaCont = new ServidorVControl(datos);
        
        //recibe desde ventana Control cuando se selecciona una compuerta
        ServidorVControl2 serverVentanaCont2 = new ServidorVControl2(datos);
        
        //hilo que revisa aterrizando para ver si alguno ya aterrizó
        hiloAterrizando hilo4 = new hiloAterrizando(datos,mutex);
        hilo4.start();
        
        //hilo que revisa avionesTaxi para ver si alguno ya llego a compuerta
        hiloTaxi hilo5 = new hiloTaxi(datos,mutex);
        hilo5.start();
    }
}
