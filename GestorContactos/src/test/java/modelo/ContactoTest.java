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

public class ContactoTest {

    @Test
    public void testConstructor_GuardaNombre() {
        Contacto c = new Contacto("Santiago", "3148973615");

        assertEquals("Santiago", c.getNombre());
    }

    @Test
    public void testConstructor_GuardaTelefono() {
        Contacto c = new Contacto("Santiago", "3148973615");

        assertEquals("3148973615", c.getTelefono());
    }
}