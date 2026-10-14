/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author santi
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Agenda;
import modelo.Contacto;
import vista.VentanaAgenda;

public class ControladorAgenda implements ActionListener {

    private Agenda agenda;
    private VentanaAgenda vista;

    public ControladorAgenda(Agenda agenda, VentanaAgenda vista) {
        this.agenda = agenda;
        this.vista = vista;
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnBuscar().addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            guardarContacto();
        } else if (e.getSource() == vista.getBtnBuscar()) {
            buscarContacto();
        }
    }

    private void guardarContacto() {
        String nombre = vista.getNombre();
        String telefono = vista.getTelefono();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            vista.mostrarMensaje("Debe ingresar nombre y teléfono.");
        } else {
            Contacto nuevo = new Contacto(nombre, telefono);
            if (agenda.agregarContacto(nuevo)) {
                vista.mostrarMensaje("Contacto guardado.");
                vista.limpiarFormulario();
            } else {
                vista.mostrarMensaje("La agenda está llena.");
            }
        }
    }

    private void buscarContacto() {
        String telefono = agenda.buscarTelefono(vista.getBusqueda());

        if (telefono != null) {
            vista.mostrarResultado("Teléfono: " + telefono);
        } else {
            vista.mostrarResultado("Contacto no encontrado.");
        }
    }
}
