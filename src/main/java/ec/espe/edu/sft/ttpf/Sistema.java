package ec.espe.edu.sft.ttpf;

import ec.espe.edu.sft.ttpf.Util.Credenciales;
import ec.espe.edu.sft.ttpf.Util.MensajesSistema;
import ec.espe.edu.sft.ttpf.Util.TituloVentanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class Sistema {

    private JFrame ventanaAdministrador;
    private JFrame ventanaIniciarSesion;
    private JFrame ventanaGestionEmpleado;
    private JFrame ventanaGestionCliente;
    private JFrame ventanaGestionReservas;

    private JTextField campoTextoUsuario;
    private JPasswordField campoTextoContrasenia;

    private JTable tablaReservas;

    private int intentosFallidos = 0;
    private boolean sistemaBloqueado = false;
    private int tiempoRestanteSegundos = 180;
    private JButton botonInicarSesion;

    // --- Variables para Gestión de Colaboradores ---
    private JTextField txtNombreColaborador;
    private JTextField txtCedulaColaborador;
    private JTextField txtUsuarioColaborador;
    private JPasswordField txtContraseniaColaborador;
    private JTable tablaColaboradores;
    private DefaultTableModel modeloTablaColaboradores;

    private java.util.ArrayList<ec.espe.edu.sft.ttpf.model.Empleado> listaColaboradores = new java.util.ArrayList<>();

    //Ventana Loggin
    public void iniciar() {
        ventanaIniciarSesion = new JFrame("Inciar Sesion");
        ventanaIniciarSesion.setSize(385, 350);
        ventanaIniciarSesion.setLayout(new GridLayout(4, 1));

        Color colorVentana = new Color(230, 240, 255);
        ventanaIniciarSesion.getContentPane().setBackground(colorVentana);

        JPanel panelBienvenido = new JPanel(new GridLayout(1, 1));
        JPanel panelUsuario = new JPanel(new GridLayout(1, 2));
        JPanel panelContrasenia = new JPanel(new GridLayout(1, 2));
        JPanel panelSesion = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panelBienvenido.setBackground(colorVentana);
        panelUsuario.setBackground(colorVentana);
        panelContrasenia.setBackground(colorVentana);
        panelSesion.setBackground(colorVentana);

        JLabel etiquetaBienvenida = new JLabel("Bienvenido", JLabel.CENTER);
        JLabel etiquetaUsuario = new JLabel("Usuario", JLabel.CENTER);
        JLabel etiquetaContrasenia = new JLabel("Contraseña", JLabel.CENTER);

        campoTextoUsuario = new JTextField();
        campoTextoUsuario.setPreferredSize(new Dimension(150, 25));
        campoTextoUsuario.setHorizontalAlignment(JLabel.CENTER);

        campoTextoContrasenia = new JPasswordField();
        campoTextoContrasenia.setPreferredSize(new Dimension(150, 25));
        campoTextoContrasenia.setHorizontalAlignment(JLabel.CENTER);

        botonInicarSesion = new JButton("Iniciar Sesion");
        botonInicarSesion.setPreferredSize(new Dimension(160, 65));
        botonInicarSesion.setHorizontalAlignment(JLabel.CENTER);

        botonInicarSesion.addActionListener(e -> {
            String usuario = campoTextoUsuario.getText();
            String contrasenia = new String(campoTextoContrasenia.getPassword());

            campoTextoUsuario.setText("");
            campoTextoContrasenia.setText("");

            validacionLoggin(usuario, contrasenia);
        });

        panelBienvenido.add(etiquetaBienvenida);
        panelUsuario.add(etiquetaUsuario);
        panelUsuario.add(this.campoTextoUsuario);
        panelContrasenia.add(etiquetaContrasenia);
        panelContrasenia.add(this.campoTextoContrasenia);
        panelSesion.add(botonInicarSesion);

        this.ventanaIniciarSesion.add(panelBienvenido);
        this.ventanaIniciarSesion.add(panelUsuario);
        this.ventanaIniciarSesion.add(panelContrasenia);
        this.ventanaIniciarSesion.add(panelSesion);
        this.ventanaIniciarSesion.setVisible(true);
    }

    //VETANA ADMIN
    private void ventanaAdmin() {
        ventanaAdministrador = new JFrame("Ventana Administrator");
        ventanaAdministrador.setSize(850, 500);
        ventanaAdministrador.setLayout(new BorderLayout());

        String[] columnas = {"ID", "Nombre", "Fecha", "Hora", "Tipo de Evento", "Duración", "Costo", "Pagado"};
        this.tablaReservas = new JTable(new DefaultTableModel(columnas, 0));
        JScrollPane scrollTabla = new JScrollPane(this.tablaReservas);

        JPanel panelEncabezado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));

        JPanel panelTablaReservas = new JPanel(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));

        JLabel etiquetaBienvenidaMenu = new JLabel("BIENVENIDO AL PANEL ADMINISTRADOR", JLabel.CENTER);

        JLabel etiquetaTablaDeReservas = new JLabel("RESERVAS", JLabel.CENTER);
        JPanel panelContenedorTituloTabla = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 8));
        panelContenedorTituloTabla.add(etiquetaTablaDeReservas);

        Dimension tamanoBotones = new Dimension(180, 40);

        JButton botonGestionColaboradores = new JButton("Gestion Colaboradores");
        botonGestionColaboradores.setPreferredSize(tamanoBotones);
        botonGestionColaboradores.setHorizontalAlignment(JLabel.CENTER);

        JButton botonGestionClientes = new JButton("Gestion Clientes");
        botonGestionClientes.setPreferredSize(tamanoBotones);
        botonGestionClientes.setHorizontalAlignment(JLabel.CENTER);

        JButton botonGestionReservas = new JButton("Gestion Reservas");
        botonGestionReservas.setPreferredSize(tamanoBotones);
        botonGestionReservas.setHorizontalAlignment(JLabel.CENTER);

        JButton botonSalir = new JButton("Cerrar Sesión");
        botonSalir.setPreferredSize(tamanoBotones);
        botonSalir.setHorizontalAlignment(JLabel.CENTER);

        botonGestionColaboradores.addActionListener(e -> {
            ventanaAdministrador.setVisible(false);
            ventanaGestionColaborador();
        });

        botonGestionClientes.addActionListener(e -> {
            ventanaAdministrador.setVisible(false);

            ventanaGestionCliente();
        });

        botonGestionReservas.addActionListener(e -> {
            ventanaAdministrador.setVisible(false);

            ventanaGestionReservas();
        });

        botonSalir.addActionListener(e -> {
            ventanaAdministrador.dispose();
            this.ventanaIniciarSesion.setVisible(true);
        });

        panelEncabezado.add(etiquetaBienvenidaMenu);

        panelTablaReservas.add(panelContenedorTituloTabla, BorderLayout.NORTH);
        panelTablaReservas.add(scrollTabla, BorderLayout.CENTER);

        panelBotones.add(botonGestionColaboradores);
        panelBotones.add(botonGestionClientes);
        panelBotones.add(botonGestionReservas);
        panelBotones.add(botonSalir);

        this.ventanaAdministrador.add(panelEncabezado, BorderLayout.NORTH);
        this.ventanaAdministrador.add(panelTablaReservas, BorderLayout.CENTER);
        this.ventanaAdministrador.add(panelBotones, BorderLayout.SOUTH);

        this.ventanaAdministrador.setVisible(true);
    }

    //ventana para el CRUD de los colaboradores
    private void ventanaGestionColaborador() {
        ventanaGestionEmpleado = new JFrame("Gestionar colaboradores");
        ventanaGestionEmpleado.setSize(750, 500);
        ventanaGestionEmpleado.setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 5, 5));
        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Colaborador"));
        panelDatos.setPreferredSize(new Dimension(750, 150));

        panelDatos.add(new JLabel("Nombre Completo:"));
        txtNombreColaborador = new JTextField();
        panelDatos.add(txtNombreColaborador);

        panelDatos.add(new JLabel("Cédula (Solo números):"));
        txtCedulaColaborador = new JTextField();
        panelDatos.add(txtCedulaColaborador);

        panelDatos.add(new JLabel("Usuario Colaborador:"));
        txtUsuarioColaborador = new JTextField();
        panelDatos.add(txtUsuarioColaborador);

        panelDatos.add(new JLabel("Contraseña Colaborador:"));
        txtContraseniaColaborador = new JPasswordField();
        panelDatos.add(txtContraseniaColaborador);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Añadir Colaborador");
        JButton btnBuscar = new JButton("Buscar (por Cédula)");
        JButton btnEliminar = new JButton("Eliminar Colaborador");
        JButton botonSalirColaborador = new JButton("Regresar al menu");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);
        panelBotones.add(botonSalirColaborador);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelDatos, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        String[] columnas = {"Cédula", "Nombre", "Usuario", "Contraseña"};
        modeloTablaColaboradores = new DefaultTableModel(columnas, 0);
        tablaColaboradores = new JTable(modeloTablaColaboradores);
        JScrollPane scrollPane = new JScrollPane(tablaColaboradores);

        ventanaGestionEmpleado.add(panelSuperior, BorderLayout.NORTH);
        ventanaGestionEmpleado.add(scrollPane, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarColaborador());
        btnBuscar.addActionListener(e -> buscarColaborador());
        btnEliminar.addActionListener(e -> eliminarColaborador());
        botonSalirColaborador.addActionListener(e -> {
            ventanaGestionEmpleado.dispose();
            ventanaAdministrador.setVisible(true);
        });

        this.ventanaGestionEmpleado.setVisible(true);
    }

    //ventana para el CRUD de los clientes
    private void ventanaGestionCliente() {
        ventanaGestionCliente = new JFrame("Gestionar cliente");
        ventanaGestionCliente.setSize(750, 500);
        this.ventanaGestionCliente.setVisible(true);
    }

    //ventana para el CRUD de las reservas
    private void ventanaGestionReservas() {
        ventanaGestionReservas = new JFrame("Gestionar reservas");
        ventanaGestionReservas.setSize(750, 500);
        this.ventanaGestionReservas.setVisible(true);
    }

    //VALIDACIONES PARA EL LOGGIN
    private void validacionLoggin(String usuario, String contrasenia) {
        if (sistemaBloqueado) {
            mostrarMensajeBloqueo();
            return;
        }

        try {
            if (usuario == null || usuario.isBlank() || contrasenia == null || contrasenia.isBlank()) {
                JOptionPane.showMessageDialog(null, MensajesSistema.ERROR_CAMPOS_VACIOS, TituloVentanas.VENTANA_ADVERTENCIA,
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (usuario.equals(Credenciales.USUARIO_ADMIN) && contrasenia.equals(Credenciales.CONTRASENIA_ADMIN)) {
                JOptionPane.showMessageDialog(null, MensajesSistema.EXITO_LOGGIN, TituloVentanas.VENTANA_INICIO, JOptionPane.INFORMATION_MESSAGE);

                this.intentosFallidos = 0;

                ventanaAdmin();
                ventanaIniciarSesion.dispose();
            } else {
                this.intentosFallidos++;

                if (this.intentosFallidos >= 3) {
                    iniciarContadorBloqueo();
                } else {
                    int intentosRestantes = 3 - this.intentosFallidos;
                    JOptionPane.showMessageDialog(null,
                            MensajesSistema.ERROR_USUARIO_O_CONTRASENIA + "\n" + MensajesSistema.INFO_INTENTOS_RESTANTES + ": " + intentosRestantes,
                            TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
                }
                return;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, MensajesSistema.ERROR_DESCONOCIDO_LOGGIN, TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarContadorBloqueo() {
        this.sistemaBloqueado = true;
        this.botonInicarSesion.setEnabled(false);
        this.tiempoRestanteSegundos = 60;

        mostrarMensajeBloqueo();

        Timer timer = new Timer(1000, null);
        timer.addActionListener(e -> {
            this.tiempoRestanteSegundos--;

            if (this.tiempoRestanteSegundos <= 0) {
                this.sistemaBloqueado = false;
                this.intentosFallidos = 0;
                this.botonInicarSesion.setEnabled(true);
                this.botonInicarSesion.setText("Iniciar Sesion");
                timer.stop();
                JOptionPane.showMessageDialog(null, MensajesSistema.ERROR_SISTEMA_BLOQUEADO, TituloVentanas.VENTANA_INICIO, JOptionPane.INFORMATION_MESSAGE);
            } else {
                int minutes = this.tiempoRestanteSegundos / 60;
                int segundos = this.tiempoRestanteSegundos % 60;
                this.botonInicarSesion.setText(String.format("Bloqueado (%02d:%02d)", minutes, segundos));
            }
        });
        timer.start();
    }

    private void mostrarMensajeBloqueo() {
        int minutes = this.tiempoRestanteSegundos / 60;
        int segundos = this.tiempoRestanteSegundos % 60;
        JOptionPane.showMessageDialog(null, MensajesSistema.ERROR_LIMITE_DE_INTENTOS_LOGGIN + " " + String.format("%02d:%02d", minutes, segundos),
                TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
    }

    // --- LÓGICA CRUD COLABORADORES ---
    private void agregarColaborador() {
        String nombre = txtNombreColaborador.getText().trim();
        String cedulaStr = txtCedulaColaborador.getText().trim();
        String usuario = txtUsuarioColaborador.getText().trim();
        String contrasenia = new String(txtContraseniaColaborador.getPassword()).trim();

        if (nombre.isBlank() || cedulaStr.isBlank() || usuario.isBlank() || contrasenia.isBlank()) {
            JOptionPane.showMessageDialog(null, MensajesSistema.ERROR_CAMPOS_VACIOS, TituloVentanas.VENTANA_ADVERTENCIA, JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ec.espe.edu.sft.ttpf.Util.ValidacionCrearUsuarioCliente.validarCedula(cedulaStr)) {
            JOptionPane.showMessageDialog(null, "La cédula ingresada no es válida.", TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cedulaInt;
        try {
            cedulaInt = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La cédula debe contener únicamente números.", TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Validar Duplicados (Cédula o Usuario)
        for (ec.espe.edu.sft.ttpf.model.Empleado colab : listaColaboradores) {
            if (colab.getCedula() == cedulaInt) {
                JOptionPane.showMessageDialog(null, "Error: Ya existe un colaborador registrado con esta Cédula.", TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (colab.getUsuario().equals(usuario)) {
                JOptionPane.showMessageDialog(null, "Error: El nombre de usuario '" + usuario + "' ya está en uso por otro colaborador.", TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        ec.espe.edu.sft.ttpf.model.Empleado nuevoColaborador = new ec.espe.edu.sft.ttpf.model.Empleado(cedulaInt, nombre, "", "", usuario, contrasenia);
        listaColaboradores.add(nuevoColaborador);
        modeloTablaColaboradores.addRow(new Object[]{cedulaStr, nombre, usuario, contrasenia});

        txtNombreColaborador.setText("");
        txtCedulaColaborador.setText("");
        txtUsuarioColaborador.setText("");
        txtContraseniaColaborador.setText("");

        JOptionPane.showMessageDialog(null, MensajesSistema.EXITO_CLIENTE_AGREGADO, TituloVentanas.VENTANA_INFO, JOptionPane.INFORMATION_MESSAGE);
    }

    private void buscarColaborador() {
        String cedulaBuscar = JOptionPane.showInputDialog(null, "Ingrese la Cédula del colaborador a buscar:", "Buscar Credenciales", JOptionPane.QUESTION_MESSAGE);

        if (cedulaBuscar == null || cedulaBuscar.trim().isEmpty()) {
            return;
        }

        int cedulaBusqueda;
        try {
            cedulaBusqueda = Integer.parseInt(cedulaBuscar.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número de cédula válido (solo números).", TituloVentanas.VENTANA_ERROR, JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (ec.espe.edu.sft.ttpf.model.Empleado colab : listaColaboradores) {
            if (colab.getCedula() == cedulaBusqueda) {
                String info = "Información del Colaborador:\n\n"
                        + "Nombre: " + colab.getNombre() + "\n"
                        + "Cédula: " + colab.getCedula() + "\n"
                        + "Usuario: " + colab.getUsuario() + "\n"
                        + "Contraseña: " + colab.getContrasenia();
                JOptionPane.showMessageDialog(null, info, "Credenciales Encontradas", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No se encontró ningún colaborador con la cédula proporcionada.", TituloVentanas.VENTANA_ADVERTENCIA, JOptionPane.WARNING_MESSAGE);
    }

    private void eliminarColaborador() {
        int filaSeleccionada = tablaColaboradores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un colaborador en la tabla para eliminarlo.", TituloVentanas.VENTANA_ADVERTENCIA, JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este colaborador?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            listaColaboradores.remove(filaSeleccionada);
            modeloTablaColaboradores.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(null, "Colaborador eliminado exitosamente.", TituloVentanas.VENTANA_INFO, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
