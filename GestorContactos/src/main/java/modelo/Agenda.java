/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author santi
 */
public class Agenda {

    private Contacto[] contactos;
    private int cantidad;

    public Agenda() {
        contactos = new Contacto[150];
        cantidad = 0;
    }

    public boolean agregarContacto(Contacto c) {
        if (cantidad < contactos.length) {
            contactos[cantidad] = c;
            cantidad++;
            return true;
        }
        return false;
    }

    public String buscarTelefono(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                return contactos[i].getTelefono();
            }
        }
        return null;
    }
    public Contacto[] getContactos() {
        return contactos;
    }

    public int getCantidad() {
        return cantidad;
    }
}
