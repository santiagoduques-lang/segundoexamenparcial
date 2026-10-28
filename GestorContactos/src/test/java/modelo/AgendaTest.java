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
    
    
    

}