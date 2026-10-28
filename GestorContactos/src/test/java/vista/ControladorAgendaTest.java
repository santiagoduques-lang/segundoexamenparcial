/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author santi
 */
import controlador.ControladorAgenda;
import java.awt.event.ActionEvent;
import modelo.Agenda;
import modelo.Contacto;
import org.junit.jupiter.api.Test;
import vista.VentanaAgenda;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControladorAgendaTest {

    // Ventana falsa: no abre diálogos y deja controlar lo que "escribe" el usuario
    static class VentanaFalsa extends VentanaAgenda {

        String nombre = "";
        String telefono = "";
        String busqueda = "";
        String ultimoMensaje = null;
        String ultimoResultado = null;
        boolean formularioLimpio = false;
        boolean visible = false;

        @Override
        public String getNombre() {
            return nombre;
        }

        @Override
        public String getTelefono() {
            return telefono;
        }

        @Override
        public String getBusqueda() {
            return busqueda;
        }

        @Override
        public void mostrarMensaje(String mensaje) {
            ultimoMensaje = mensaje;
        }

        @Override
        public void mostrarResultado(String texto) {
            ultimoResultado = texto;
        }

        @Override
        public void limpiarFormulario() {
            formularioLimpio = true;
        }

        @Override
        public void setVisible(boolean b) {
            visible = b;
        }
    }

    // Simula un clic sobre un botón
    private void clic(ControladorAgenda controlador, Object boton) {
        controlador.actionPerformed(new ActionEvent(boton, ActionEvent.ACTION_PERFORMED, ""));
    }

    @Test
    public void testIniciar_HaceVisibleLaVentana() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        controlador.iniciar();
        // iniciar() llama a setVisible(true)

        assertTrue(vista.visible);
    }

    @Test
    public void testGuardar_NombreVacio() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "";
        vista.telefono = "3148973615";
        clic(controlador, vista.getBtnGuardar());
        // Falta el nombre: muestra el mensaje de error

        assertEquals("Debe ingresar nombre y teléfono.", vista.ultimoMensaje);
    }

    @Test
    public void testGuardar_TelefonoVacio() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Santiago";
        vista.telefono = "";
        clic(controlador, vista.getBtnGuardar());
        // Falta el teléfono: muestra el mensaje de error

        assertEquals("Debe ingresar nombre y teléfono.", vista.ultimoMensaje);
    }

    @Test
    public void testGuardar_CamposVaciosNoAgregaContacto() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "";
        vista.telefono = "";
        clic(controlador, vista.getBtnGuardar());
        // Con campos vacíos no se guarda nada: cantidad sigue en 0

        assertEquals(0, agenda.getCantidad());
    }

    @Test
    public void testGuardar_CamposVaciosNoLimpiaFormulario() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "";
        vista.telefono = "";
        clic(controlador, vista.getBtnGuardar());
        // Si hubo error, el formulario NO se limpia

        assertFalse(vista.formularioLimpio);
    }

    @Test
    public void testGuardar_DatosValidosMuestraMensaje() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Santiago";
        vista.telefono = "3148973615";
        clic(controlador, vista.getBtnGuardar());
        // Datos completos: muestra "Contacto guardado."

        assertEquals("Contacto guardado.", vista.ultimoMensaje);
    }

    @Test
    public void testGuardar_DatosValidosAgregaContacto() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Santiago";
        vista.telefono = "3148973615";
        clic(controlador, vista.getBtnGuardar());
        // El contacto queda guardado en la agenda: cantidad = 1

        assertEquals(1, agenda.getCantidad());
    }

    @Test
    public void testGuardar_DatosValidosGuardaTelefonoCorrecto() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Santiago";
        vista.telefono = "3148973615";
        clic(controlador, vista.getBtnGuardar());
        // Al buscar "Santiago" en la agenda aparece el teléfono ingresado

        assertEquals("3148973615", agenda.buscarTelefono("Santiago"));
    }

    @Test
    public void testGuardar_DatosValidosLimpiaFormulario() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Santiago";
        vista.telefono = "3148973615";
        clic(controlador, vista.getBtnGuardar());
        // Al guardar bien, se llama a limpiarFormulario()

        assertTrue(vista.formularioLimpio);
    }

    @Test
    public void testGuardar_AgendaLlena() {
        Agenda agenda = new Agenda();
        for (int i = 0; i < 150; i++) {
            agenda.agregarContacto(new Contacto("Contacto" + i, "300" + i));
        }
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Extra";
        vista.telefono = "3999999999";
        clic(controlador, vista.getBtnGuardar());
        // Con 150 contactos no cabe otro: muestra "La agenda está llena."

        assertEquals("La agenda está llena.", vista.ultimoMensaje);
    }

    @Test
    public void testGuardar_AgendaLlenaNoLimpiaFormulario() {
        Agenda agenda = new Agenda();
        for (int i = 0; i < 150; i++) {
            agenda.agregarContacto(new Contacto("Contacto" + i, "300" + i));
        }
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Extra";
        vista.telefono = "3999999999";
        clic(controlador, vista.getBtnGuardar());
        // Si no se pudo guardar, el usuario conserva lo que escribió

        assertFalse(vista.formularioLimpio);
    }

    @Test
    public void testBuscar_ContactoExistente() {
        Agenda agenda = new Agenda();
        agenda.agregarContacto(new Contacto("Laura", "3001234567"));
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.busqueda = "Laura";
        clic(controlador, vista.getBtnBuscar());
        // Encuentra a Laura: muestra "Teléfono: 3001234567"

        assertEquals("Teléfono: 3001234567", vista.ultimoResultado);
    }

    @Test
    public void testBuscar_ContactoNoExistente() {
        Agenda agenda = new Agenda();
        agenda.agregarContacto(new Contacto("Laura", "3001234567"));
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.busqueda = "Pedro";
        clic(controlador, vista.getBtnBuscar());
        // Pedro no está en la agenda: muestra "Contacto no encontrado."

        assertEquals("Contacto no encontrado.", vista.ultimoResultado);
    }

    @Test
    public void testBuscar_AgendaVacia() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.busqueda = "Laura";
        clic(controlador, vista.getBtnBuscar());
        // Agenda sin contactos: muestra "Contacto no encontrado."

        assertEquals("Contacto no encontrado.", vista.ultimoResultado);
    }

    @Test
    public void testBuscar_NoAgregaContactos() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Santiago";
        vista.telefono = "3148973615";
        vista.busqueda = "Santiago";
        clic(controlador, vista.getBtnBuscar());
        // El botón Buscar no debe ejecutar la acción de Guardar: cantidad = 0

        assertEquals(0, agenda.getCantidad());
    }

    @Test
    public void testActionPerformed_OrigenDesconocidoNoHaceNada() {
        Agenda agenda = new Agenda();
        VentanaFalsa vista = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, vista);
        vista.nombre = "Santiago";
        vista.telefono = "3148973615";
        clic(controlador, new Object());
        // Un evento que no viene de ningún botón conocido no guarda ni busca

        assertEquals(0, agenda.getCantidad());
    }
}
