/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author santi
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AgendaTest {

    @Test
    public void testAgendaNuevaEstaVacia() {
        Agenda agenda = new Agenda();
        // Una agenda recién creada no tiene contactos: cantidad = 0

        assertEquals(0, agenda.getCantidad());
    }

    @Test
    public void testAgendaNuevaTieneCapacidad150() {
        Agenda agenda = new Agenda();
        // El arreglo se crea con 150 posiciones

        assertEquals(150, agenda.getContactos().length);
    }
    
     @Test
    public void testAgregarContacto_Exitoso() {
        Agenda agenda = new Agenda();
        Contacto c = new Contacto("Santiago", "3148973615");
        // Hay espacio disponible: devuelve true

        assertTrue(agenda.agregarContacto(c));
    }

    @Test
    public void testAgregarContacto_AumentaCantidad() {
        Agenda agenda = new Agenda();
        agenda.agregarContacto(new Contacto("Santiago", "3148973615"));
        agenda.agregarContacto(new Contacto("Laura", "3001234567"));
        // Se agregaron 2 contactos: cantidad = 2

        assertEquals(2, agenda.getCantidad());
    }
    
    @Test
    public void testAgregarContacto_SeGuardaEnPrimeraPosicion() {
        Agenda agenda = new Agenda();
        Contacto c = new Contacto("Santiago", "3148973615");
        agenda.agregarContacto(c);
        // El primer contacto queda en la posición 0 del arreglo

        assertSame(c, agenda.getContactos()[0]);
    }

    @Test
    public void testAgregarContacto_AgendaLlena() {
        Agenda agenda = new Agenda();
        for (int i = 0; i < 150; i++) {
            agenda.agregarContacto(new Contacto("Contacto" + i, "300" + i));
        }
        // Con 150 contactos la agenda está llena: el contacto 151 devuelve false

        assertFalse(agenda.agregarContacto(new Contacto("Extra", "3999999999")));
    }
    
    @Test
    public void testBuscarTelefono_ContactoExistente() {
        Agenda agenda = new Agenda();
        agenda.agregarContacto(new Contacto("Santiago", "3148973615"));
        agenda.agregarContacto(new Contacto("Laura", "3001234567"));
        // Buscar "Laura" devuelve su teléfono

        assertEquals("3001234567", agenda.buscarTelefono("Laura"));
    }

    @Test
    public void testBuscarTelefono_IgnoraMayusculas() {
        Agenda agenda = new Agenda();
        agenda.agregarContacto(new Contacto("Santiago", "3148973615"));
        // Usa equalsIgnoreCase: "SANTIAGO" encuentra a "Santiago"

        assertEquals("3148973615", agenda.buscarTelefono("SANTIAGO"));
    }

    @Test
    public void testBuscarTelefono_ContactoNoExistente() {
        Agenda agenda = new Agenda();
        agenda.agregarContacto(new Contacto("Santiago", "3148973615"));
        // "Pedro" no está en la agenda: devuelve null

        assertNull(agenda.buscarTelefono("Pedro"));
    }

    @Test
    public void testBuscarTelefono_AgendaVacia() {
        Agenda agenda = new Agenda();
        // No hay contactos que recorrer: devuelve null

        assertNull(agenda.buscarTelefono("Santiago"));
    }

    @Test
    public void testBuscarTelefono_NombreRepetidoDevuelvePrimero() {
        Agenda agenda = new Agenda();
        agenda.agregarContacto(new Contacto("Laura", "3001111111"));
        agenda.agregarContacto(new Contacto("Laura", "3002222222"));
        // Con nombres repetidos devuelve el primero que encuentra

        assertEquals("3001111111", agenda.buscarTelefono("Laura"));
    }
    
    
    
    

}