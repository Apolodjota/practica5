/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.grafos.DibujarGrafo;
import controlador.listas.ListaEnlazada;
import controlador.antenas.AntenaDao;
import controlador.util.Utilidades;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.tablas.ModeloTablaAdyacencia;

/**
 *
 * @author Apolo
 */
public class FrmMapa extends javax.swing.JFrame {

    private AntenaDao ad = new AntenaDao();
    private ModeloTablaAdyacencia mtAd = new ModeloTablaAdyacencia();
    /**
     * Creates new form FrmMapa
     */
    
    public FrmMapa() {
        initComponents();
        setLocationRelativeTo(null);
        limpiar();
        cargarGrafo();
        
    }
    public FrmMapa(java.awt.Frame parent,Boolean modal) {
        initComponents();
        setLocationRelativeTo(null);
        limpiar();    
    }
    
    private void guardarGrafo(){
        Integer i = JOptionPane.showConfirmDialog(null,
                "Esta de acuerdo con guardar el grafo???",
                "Pregunta", 
                JOptionPane.OK_CANCEL_OPTION);
        
        if(i == JOptionPane.OK_OPTION){
            try {
                ad.guardarGrafo();
                JOptionPane.showMessageDialog(null,
                            "Grafo Guardado", "Niceeeee",
                            JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    private void cargarTabla(){
        try {
            mtAd.setGrafo(ad.getGrafoAntena());
            mtAd.fireTableDataChanged();
            tblTabla.setModel(mtAd);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void limpiar(){
        try {
            UtilesVista.cargarComboAntena(cbxOrigen);
            UtilesVista.cargarComboAntena(cbxDestino);
            UtilesVista.cargarComboAntena(cbxOrigenAleatorio);
            cargarTabla();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void camino() throws Exception{
        if(ad.getGrafoAntena()!= null){
            Integer posO = cbxOrigen.getSelectedIndex() + 1;
            Integer posD = cbxDestino.getSelectedIndex() + 1;
            System.out.println("1ra Iteracion..........\n"+cbxOrigen.getSelectedIndex());
            System.out.println(cbxDestino.getSelectedIndex());
            HashMap<String, ListaEnlazada> mapa = ad.getGrafoAntena().camino(posO, posD);
            System.out.println("Coñazo"+ad.getGrafoAntena().camino(posO, posD));
            if(mapa.isEmpty()){
                JOptionPane.showMessageDialog(null,
                            "No existe camino", "Error",
                            JOptionPane.ERROR_MESSAGE);
            }else{
                ListaEnlazada<Integer> caminos = mapa.get("camino");
                for (int i = 0; i <= caminos.getSize(); i++) {
                    Integer v = caminos.get(i);
                    System.out.println(ad.getGrafoAntena().toString());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,
                            "Errorsote", "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adyacenciaAleatoria(){
        try {
            Integer posO = cbxOrigenAleatorio.getSelectedIndex();
            //poscicion de origen
            
            System.out.println("\n\n1ra Iteracion.........."+cbxOrigenAleatorio.getSelectedIndex());
            
            
            ListaEnlazada<Integer> destinos = new ListaEnlazada<>();
            
            Integer ad1 = Utilidades.generateRandomNumber(ad.getAntenas().getSize() - 1 , posO);
            Integer ad2 = Utilidades.generateRandomNumber(ad.getAntenas().getSize() - 1 , posO);
            Integer ad3 = Utilidades.generateRandomNumber(ad.getAntenas().getSize() - 1 , posO);
            
            destinos.add(ad1);
            if(ad1 != ad2)destinos.add(ad2);
            else destinos.add(ad3);
            
            System.out.println(ad1+"  "+ad2+"  "+ad3);
            System.out.println("destino real :"+(ad1+1)+"  "+(ad2+1)+"  "+(ad3+1));
            System.out.println("tamaño lista "+ destinos.getSize());
            
            for (int i = 0; i < destinos.getSize(); i++) {
                // verificacion de que no sea ya adyacente con esa adyacencia ramdon if(ad.getGrafoAntena().adyacentes(p))
                
                Double peso = Utilidades.distanciaEscuelas(ad.getAntenas().get(posO), ad.getAntenas().get(destinos.get(i)));
                System.out.println("ORIGEN"+ad.getAntenas().get(posO).toString());
                System.out.println("Antena agarrada: "+ad.getAntenas().get(destinos.get(i)).toString());
                ad.getGrafoAntena().insertarAristaE(ad.getAntenas().get(posO),ad.getAntenas().get(destinos.get(i)), peso);
                
                
                
                JOptionPane.showMessageDialog(null,
                            "Adyacencia Agregada", "Adyacencia",
                            JOptionPane.INFORMATION_MESSAGE);
                System.out.println(peso);
                
            }
            limpiar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    private void adyacencia(){
        try {
            Integer posO = cbxOrigen.getSelectedIndex();
            System.out.println("1ra Iteracion..........\n"+cbxOrigen.getSelectedIndex());
            System.out.println(cbxDestino.getSelectedIndex());
            Integer posD = cbxDestino.getSelectedIndex();
            if(posO != posD){
                Double peso = Utilidades.distanciaEscuelas(UtilesVista.getComboAntena(cbxOrigen), UtilesVista.getComboAntena(cbxDestino));
                ad.getGrafoAntena().insertarAristaE(ad.getAntenas().get(posO), ad.getAntenas().get(posD), peso);
                System.out.println("2ra Iteracion..........\n"+
                        ad.getAntenas().get(posO).toString());
                
                System.out.println(ad.getAntenas().get(posD).toString());
                
                JOptionPane.showMessageDialog(null,
                            "Adyacencia Agregada", "Adyacencia",
                            JOptionPane.INFORMATION_MESSAGE);
                
                System.out.println("Origen   "+ UtilesVista.getComboAntena(cbxOrigen));
                System.out.println("Destino   "+UtilesVista.getComboAntena(cbxDestino));
                System.out.println(peso);
                
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    private void dijkstrac(){
        try {
            Integer posO = cbxOrigen.getSelectedIndex();
            System.out.println("1ra Iteracion..........\n"+cbxOrigen.getSelectedIndex());
            System.out.println(cbxDestino.getSelectedIndex());
            Integer posD = cbxDestino.getSelectedIndex();
            if(posO != posD){
                //ingresar el vertice entrada y destino
                //ad.getGrafoAntena().Dijkstrac(posO, posD);
                //devolver el camino mas corto
                
                
                System.out.println("2ra Iteracion..........\n"+ad.getAntenas().get(posO).toString());
                System.out.println(ad.getAntenas().get(posD).toString());
                JOptionPane.showMessageDialog(null,
                            "Adyacencia Agregada", "Adyacencia",
                            JOptionPane.INFORMATION_MESSAGE);
                
                System.out.println("Origen   "+UtilesVista.getComboAntena(cbxOrigen));
                System.out.println("Destino   "+UtilesVista.getComboAntena(cbxDestino));
                //System.out.println(peso);
                
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    private void cargarGrafo(){
        Integer i = JOptionPane.showConfirmDialog(null,
                "Esta de acuerdo con cargar el grafo???",
                "Pregunta", 
                JOptionPane.OK_CANCEL_OPTION);
        
        if(i == JOptionPane.OK_OPTION){
            try {
                ad.cargarGrafo();
                limpiar();
                JOptionPane.showMessageDialog(null,
                            "Grafo Cargado", "Niceeeee",
                            JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void mostrarMapa(){
        try {
            String dir = Utilidades.getDirProject();
            String os = Utilidades.getOs();
            if(os.equalsIgnoreCase("Windows 11")){
                UtilesVista.crearMapa(ad.getGrafoAntena());
                Utilidades.abrirNavegadorPredeterminadoWindows(dir+File.separatorChar+"mapas"+File.separatorChar+"index.html");
            }else
                System.out.println("No existe sistema");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    private void mostrarGrafos(){
        try {
            String dir = Utilidades.getDirProject();
            String os = Utilidades.getOs();
            DibujarGrafo dg = new DibujarGrafo();
            dg.crearArchivo(ad.getGrafoAntena());
            if(os.equalsIgnoreCase("Windows 11"))
                Utilidades.abrirNavegadorPredeterminadoWindows(dir+File.separatorChar+"d3"+File.separatorChar+"grafo.html");
            else
                System.out.println("No existe sistema");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        labelHeader1 = new org.edisoncor.gui.label.LabelHeader();
        panelShadow1 = new org.edisoncor.gui.panel.PanelShadow();
        btnVerMapa = new org.edisoncor.gui.button.ButtonAero();
        btnVerGrafo = new org.edisoncor.gui.button.ButtonAero();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxOrigen = new org.edisoncor.gui.comboBox.ComboBoxRect();
        cbxDestino = new org.edisoncor.gui.comboBox.ComboBoxRect();
        btnAdyacencia = new org.edisoncor.gui.button.ButtonRect();
        btnCamino = new org.edisoncor.gui.button.ButtonAction();
        btnFloyd = new org.edisoncor.gui.button.ButtonAction();
        panelShadow2 = new org.edisoncor.gui.panel.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        buttonPopup1 = new org.edisoncor.gui.button.ButtonPopup();
        buttonPopup2 = new org.edisoncor.gui.button.ButtonPopup();
        btnAnchura = new org.edisoncor.gui.button.ButtonAction();
        btnProfundidad = new org.edisoncor.gui.button.ButtonAction();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbxOrigenAleatorio = new org.edisoncor.gui.comboBox.ComboBoxRect();
        btnAleatorio = new org.edisoncor.gui.button.ButtonRect();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Antenas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        labelHeader1.setText("UBICACION DE LAS ESCUELAS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 631;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 60, 0, 0);
        jPanel1.add(labelHeader1, gridBagConstraints);

        btnVerMapa.setText("VER MAPA");
        btnVerMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMapaActionPerformed(evt);
            }
        });

        btnVerGrafo.setText("VER GRAFO");
        btnVerGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerGrafoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("CONSTRUIR GRAFO"));

        jLabel1.setText("ORIGEN");

        jLabel2.setText("DESTINO");

        btnAdyacencia.setText("Adyacencia");
        btnAdyacencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdyacenciaActionPerformed(evt);
            }
        });

        btnCamino.setText("Dijkstra");
        btnCamino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaminoActionPerformed(evt);
            }
        });

        btnFloyd.setText("Floyd");
        btnFloyd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFloydActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(72, 72, 72)
                        .addComponent(cbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(66, 66, 66)
                        .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnAdyacencia, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(btnCamino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFloyd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdyacencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFloyd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCamino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        panelShadow2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelShadow2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTabla);

        panelShadow2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 881, 432));

        buttonPopup1.setText("Guardar Grafo");
        buttonPopup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPopup1ActionPerformed(evt);
            }
        });
        panelShadow2.add(buttonPopup1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 445, 249, -1));

        buttonPopup2.setText("Cargar");
        buttonPopup2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPopup2ActionPerformed(evt);
            }
        });
        panelShadow2.add(buttonPopup2, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 445, 233, -1));

        btnAnchura.setText("Anchura");
        btnAnchura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnchuraActionPerformed(evt);
            }
        });
        panelShadow2.add(btnAnchura, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 466, 86, 23));

        btnProfundidad.setText("Profundidad");
        btnProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfundidadActionPerformed(evt);
            }
        });
        panelShadow2.add(btnProfundidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 466, 104, 23));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ESTAN CONECTADOS?");
        panelShadow2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 448, 196, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("PROVEER DESTINOS ALEATORIOS"));

        jLabel3.setText("ORIGEN");

        btnAleatorio.setText("Adyacencia Aleatoria");
        btnAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAleatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(cbxOrigenAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel3)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbxOrigenAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnVerMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelShadow1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.ipady = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 18, 0);
        jPanel1.add(panelShadow1, gridBagConstraints);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMapaActionPerformed
        mostrarMapa();
    }//GEN-LAST:event_btnVerMapaActionPerformed

    private void btnAdyacenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdyacenciaActionPerformed
        adyacencia();
    }//GEN-LAST:event_btnAdyacenciaActionPerformed

    private void btnVerGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerGrafoActionPerformed
        mostrarGrafos();
    }//GEN-LAST:event_btnVerGrafoActionPerformed

    private void buttonPopup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPopup1ActionPerformed
        guardarGrafo();
    }//GEN-LAST:event_buttonPopup1ActionPerformed

    private void buttonPopup2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPopup2ActionPerformed
        cargarGrafo();
    }//GEN-LAST:event_buttonPopup2ActionPerformed

    private void btnCaminoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaminoActionPerformed
        try {
            camino();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCaminoActionPerformed

    private void btnAleatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAleatorioActionPerformed
        adyacenciaAleatoria();
    }//GEN-LAST:event_btnAleatorioActionPerformed

    private void btnFloydActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFloydActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFloydActionPerformed

    private void btnAnchuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnchuraActionPerformed
        try {
            if(ad.getGrafoAntena().recorridoAnchura())
                JOptionPane.showMessageDialog(null,
                        "Estan conectados entre todos", ":)",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null,
                        "Non conectados entre todos", ":(", 
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnAnchuraActionPerformed

    private void btnProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfundidadActionPerformed
        try {
            if(ad.getGrafoAntena().recorridoProfundidad())
                JOptionPane.showMessageDialog(null,
                        "Estan conectados entre todos", ":)",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null,
                        "No conectados entre todos", ":(", 
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnProfundidadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMapa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRect btnAdyacencia;
    private org.edisoncor.gui.button.ButtonRect btnAleatorio;
    private org.edisoncor.gui.button.ButtonAction btnAnchura;
    private org.edisoncor.gui.button.ButtonAction btnCamino;
    private org.edisoncor.gui.button.ButtonAction btnFloyd;
    private org.edisoncor.gui.button.ButtonAction btnProfundidad;
    private org.edisoncor.gui.button.ButtonAero btnVerGrafo;
    private org.edisoncor.gui.button.ButtonAero btnVerMapa;
    private org.edisoncor.gui.button.ButtonPopup buttonPopup1;
    private org.edisoncor.gui.button.ButtonPopup buttonPopup2;
    private org.edisoncor.gui.comboBox.ComboBoxRect cbxDestino;
    private org.edisoncor.gui.comboBox.ComboBoxRect cbxOrigen;
    private org.edisoncor.gui.comboBox.ComboBoxRect cbxOrigenAleatorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.label.LabelHeader labelHeader1;
    private org.edisoncor.gui.panel.PanelShadow panelShadow1;
    private org.edisoncor.gui.panel.PanelShadow panelShadow2;
    private javax.swing.JTable tblTabla;
    // End of variables declaration//GEN-END:variables
}
