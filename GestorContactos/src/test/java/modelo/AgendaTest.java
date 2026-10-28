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

}