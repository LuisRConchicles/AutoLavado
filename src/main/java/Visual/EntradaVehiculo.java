package Visual;

import Clases.Auto;
import Clases.GestorDatos;
import Clases.Servicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

public class EntradaVehiculo extends javax.swing.JFrame {

    private List<Servicios> serviciosActuales = new ArrayList<>();
    private Auto autoActual = null;
    private ButtonGroup grupoLavados = new ButtonGroup();

    public EntradaVehiculo() {
        initComponents();
        this.setLocationRelativeTo(null);
        configurarRestricciones();
        configurarEventosCalculo();
    }

    private void configurarRestricciones() {
        // Validación telefono, solo numeros
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar())) evt.consume();
            }
        });
        //numero orden
        txtNumeroOrden.setText(String.valueOf(GestorDatos.obtenerSiguienteNumeroOrden()));
        txtNumeroOrden.setEditable(false);
        txtPrecioFinal.setEditable(false);
        txtPrecioUnitario.setEditable(false);
        
        // bloquear radio button
        grupoLavados.add(jrbExpress);
        grupoLavados.add(jrbBasico);
        grupoLavados.add(jrbDetallado);

        // fecha y hora automatica
        Date ahora = new Date();
        jdcFechaEntrada.setDate(ahora);
        jdcFechaEntrada.setEnabled(false); // no permite cambiar fecha

        // spinner hora 8 a 18 hrs
        int horaActual = ahora.getHours();
        if(horaActual < 8) horaActual = 8;
        if(horaActual > 18) horaActual = 18;
        spnHora.setModel(new SpinnerNumberModel(horaActual, 8, 18, 1));
        spnHora.setEnabled(false);
        
        spnMinuto.setModel(new SpinnerNumberModel(ahora.getMinutes(), 0, 59, 1));
        spnMinuto.setEnabled(false);
    }

    private void configurarEventosCalculo() {
        ActionListener calculador = (ActionEvent e) -> calcularPreciosEnTiempoReal();
        
        jrbExpress.addActionListener(calculador);
        jrbBasico.addActionListener(calculador);
        jrbDetallado.addActionListener(calculador);
        
        jcbLavadoMotor.addActionListener(calculador);
        jcbAire.addActionListener(calculador);
        jcbChasis.addActionListener(calculador);
        jcbNiveles.addActionListener(calculador);
        jcbPulido.addActionListener(calculador);
        
        jcbPañoMicrofibra.addActionListener(calculador);
        jcbAromatizante.addActionListener(calculador);
        jcbLimpiaparabrisas.addActionListener(calculador);
    }

    private void calcularPreciosEnTiempoReal() {
        serviciosActuales.clear();
        double total = 0.0;
        StringBuilder desglosePrecios = new StringBuilder();

        // lavado y descripción
        if (jrbExpress.isSelected()) {
            txtDescripcion.setText("EXPRESS:\n- Solo exterior del vehículo.");
            agregarServicio(90.0, "Express", "Servicio", desglosePrecios);
        } else if (jrbBasico.isSelected()) {
            txtDescripcion.setText("BÁSICO:\n- Exterior, tapetes, aspirado, abrillantador.");
            agregarServicio(150.0, "Básico", "Servicio", desglosePrecios);
        } else if (jrbDetallado.isSelected()) {
            txtDescripcion.setText("DETALLADO:\n- Limpieza profunda, eliminación olores, lavado asientos.");
            agregarServicio(950.0, "Detallado", "Servicio", desglosePrecios);
        } else {
            txtDescripcion.setText("");
        }

        // sumar extras
        if (jcbLavadoMotor.isSelected()) agregarServicio(200.0, "Lavado de Motor", "Extra", desglosePrecios);
        if (jcbAire.isSelected()) agregarServicio(30.0, "Aire llantas", "Extra", desglosePrecios);
        if (jcbChasis.isSelected()) agregarServicio(150.0, "Lavado Chasis", "Extra", desglosePrecios);
        if (jcbNiveles.isSelected()) agregarServicio(50.0, "Verificar Niveles", "Extra", desglosePrecios);
        if (jcbPulido.isSelected()) agregarServicio(450.0, "Pulido y Encerado", "Extra", desglosePrecios);

        // sumar productos
        if (jcbPañoMicrofibra.isSelected()) agregarServicio(60.0, "Paño Microfibra", "Producto", desglosePrecios);
        if (jcbAromatizante.isSelected()) agregarServicio(40.0, "Aromatizante", "Producto", desglosePrecios);
        if (jcbLimpiaparabrisas.isSelected()) agregarServicio(80.0, "Limpia Parabrisas", "Producto", desglosePrecios);

        // actualizar txt
        for(Servicios s : serviciosActuales) { total += s.getCosto(); }
        
        txtPrecioUnitario.setText(desglosePrecios.toString().replace("\n", ", "));
        txtPrecioFinal.setText(String.format("%.2f", total));
    }

    private void agregarServicio(double costo, String nombre, String tipo, StringBuilder desglose) {
        serviciosActuales.add(new Servicios(0, nombre, costo, tipo));
        desglose.append(nombre).append(": $").append(costo).append("\n");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JTextField();
        btnRellenar = new javax.swing.JButton();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        txtReferencia = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jrbExpress = new javax.swing.JRadioButton();
        jrbBasico = new javax.swing.JRadioButton();
        jrbDetallado = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jcbLavadoMotor = new javax.swing.JCheckBox();
        jcbNiveles = new javax.swing.JCheckBox();
        jcbAire = new javax.swing.JCheckBox();
        jcbPulido = new javax.swing.JCheckBox();
        jcbChasis = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jcbPañoMicrofibra = new javax.swing.JCheckBox();
        jcbAromatizante = new javax.swing.JCheckBox();
        jcbLimpiaparabrisas = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        txtPrecioFinal = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        txtNumeroOrden = new javax.swing.JTextField();
        btnRegistrarE = new javax.swing.JButton();
        txtPrecioUnitario = new javax.swing.JTextField();
        jcbTipoPago = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        spnHora = new javax.swing.JSpinner();
        spnMinuto = new javax.swing.JSpinner();
        jdcFechaEntrada = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS CLIENTE"));

        txtTelefono.setBackground(new java.awt.Color(153, 255, 153));
        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder("TELEFONO"));

        btnRellenar.setBackground(new java.awt.Color(153, 153, 255));
        btnRellenar.setText("RELLENAR");
        btnRellenar.addActionListener(this::btnRellenarActionPerformed);

        txtMarca.setBackground(new java.awt.Color(153, 255, 153));
        txtMarca.setBorder(javax.swing.BorderFactory.createTitledBorder("MARCA"));

        txtModelo.setBackground(new java.awt.Color(153, 255, 153));
        txtModelo.setBorder(javax.swing.BorderFactory.createTitledBorder("MODELO"));

        txtColor.setBackground(new java.awt.Color(153, 255, 153));
        txtColor.setBorder(javax.swing.BorderFactory.createTitledBorder("COLOR"));

        txtReferencia.setBackground(new java.awt.Color(153, 255, 153));
        txtReferencia.setBorder(javax.swing.BorderFactory.createTitledBorder("REFERENCIA"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtReferencia)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRellenar)
                        .addGap(24, 24, 24)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRellenar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SERVICIO DE LAVADO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 0, 13), new java.awt.Color(30, 41, 59))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(30, 41, 59));

        jrbExpress.setText("EXPRESS");

        jrbBasico.setText("BASICO");

        jrbDetallado.setText("DETALLADO");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 255, 102)));
        jScrollPane1.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbExpress, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbBasico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbDetallado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jrbExpress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbBasico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbDetallado))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("SERVICIOS EXTRA"));

        jcbLavadoMotor.setText("LAVADO DE MOTOR");

        jcbNiveles.setText("VERIFICAR NIVELES");

        jcbAire.setText("AIRE PARA LLANTAS");

        jcbPulido.setText("PULIDO Y ENCERADO");

        jcbChasis.setText("LAVADO DE CHASIS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbLavadoMotor)
                    .addComponent(jcbAire)
                    .addComponent(jcbChasis)
                    .addComponent(jcbNiveles)
                    .addComponent(jcbPulido))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbLavadoMotor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbAire)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbChasis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbNiveles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbPulido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("PRODUCTOS ADICIONALES"));

        jcbPañoMicrofibra.setText("PAÑO DE MICROFIBRA");

        jcbAromatizante.setText("AROMATIZANTE");

        jcbLimpiaparabrisas.setText("LIMPIA PARABRISAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbPañoMicrofibra)
                    .addComponent(jcbAromatizante)
                    .addComponent(jcbLimpiaparabrisas))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbPañoMicrofibra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbAromatizante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbLimpiaparabrisas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("COSTO"));

        txtPrecioFinal.setBackground(new java.awt.Color(204, 255, 255));
        txtPrecioFinal.setBorder(javax.swing.BorderFactory.createTitledBorder("PRECIO FINAL"));

        btnRegresar.setBackground(new java.awt.Color(153, 153, 255));
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);

        txtNumeroOrden.setBackground(new java.awt.Color(204, 255, 255));
        txtNumeroOrden.setBorder(javax.swing.BorderFactory.createTitledBorder("NUMERO DE ORDEN"));

        btnRegistrarE.setBackground(new java.awt.Color(40, 167, 69));
        btnRegistrarE.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        btnRegistrarE.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarE.setText("REGISTRAR ENTRADA");
        btnRegistrarE.addActionListener(this::btnRegistrarEActionPerformed);

        txtPrecioUnitario.setBackground(new java.awt.Color(204, 255, 255));
        txtPrecioUnitario.setBorder(javax.swing.BorderFactory.createTitledBorder("PRECIO UNITARIO"));

        jcbTipoPago.setBackground(new java.awt.Color(255, 51, 51));
        jcbTipoPago.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jcbTipoPago.setForeground(new java.awt.Color(255, 255, 255));
        jcbTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARJETA", "TRANFERENCIA" }));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("TIPO DE PAGO:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecioFinal)
                    .addComponent(txtNumeroOrden)
                    .addComponent(txtPrecioUnitario)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegistrarE)
                            .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnRegistrarE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("COMENTARIOS / SOLICITUDES"));

        txtComentarios.setColumns(20);
        txtComentarios.setRows(5);
        txtComentarios.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 255, 102)));
        jScrollPane2.setViewportView(txtComentarios);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("ENTRADA"));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(spnHora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spnMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jdcFechaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jdcFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRellenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRellenarActionPerformed
        // TODO add your handling code here:
        String telefonoBuscado = txtTelefono.getText();
        autoActual = GestorDatos.buscarPorTelefono(telefonoBuscado);
        
        if (autoActual != null) {
            txtMarca.setText(autoActual.getMarca());
            txtModelo.setText(autoActual.getModelo());
            txtColor.setText(autoActual.getColor());
            txtReferencia.setText(autoActual.getObservaciones()); 
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún cliente con ese teléfono");
        }
    }//GEN-LAST:event_btnRellenarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegistrarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarEActionPerformed
        // TODO add your handling code here:
        if (autoActual == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar y 'Rellenar' los datos del cliente primero.");
            return;
        }
        if (serviciosActuales.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un tipo de lavado.");
            return;
        }

        double total = Double.parseDouble(txtPrecioFinal.getText().replace(",", "."));
        String fechaActual = jdcFechaEntrada.getDate().toString();

        GestorDatos.Orden nuevaOrden = new GestorDatos.Orden(
            Integer.parseInt(txtNumeroOrden.getText()), 
            autoActual, 
            new ArrayList<>(serviciosActuales), 
            total, 
            fechaActual
        );

        GestorDatos.registrarOrden(nuevaOrden);
        JOptionPane.showMessageDialog(this, "Entrada registrada con éxito. Estado: PENDIENTE.");
        
        new VentanaPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegistrarEActionPerformed
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntradaVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new EntradaVehiculo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarE;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRellenar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox jcbAire;
    private javax.swing.JCheckBox jcbAromatizante;
    private javax.swing.JCheckBox jcbChasis;
    private javax.swing.JCheckBox jcbLavadoMotor;
    private javax.swing.JCheckBox jcbLimpiaparabrisas;
    private javax.swing.JCheckBox jcbNiveles;
    private javax.swing.JCheckBox jcbPañoMicrofibra;
    private javax.swing.JCheckBox jcbPulido;
    private javax.swing.JComboBox<String> jcbTipoPago;
    private com.toedter.calendar.JDateChooser jdcFechaEntrada;
    private javax.swing.JRadioButton jrbBasico;
    private javax.swing.JRadioButton jrbDetallado;
    private javax.swing.JRadioButton jrbExpress;
    private javax.swing.JSpinner spnHora;
    private javax.swing.JSpinner spnMinuto;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextArea txtComentarios;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNumeroOrden;
    private javax.swing.JTextField txtPrecioFinal;
    private javax.swing.JTextField txtPrecioUnitario;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}