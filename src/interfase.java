/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ann
 */
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class interfase extends javax.swing.JFrame {

    int contador = 0;
    Pila PilaTorre1;
    Pila PilaTorre2;
    Pila PilaTorre3;

    DefaultTableModel ModeloTablaTorre1, ModeloTablaTorre2, ModeloTablaTorre3;

    int objeto = 0;
    double numMinMov = 0;
    boolean stop = false;

    public interfase() {
        initComponents();
        //Definimos elnumero de datos dentro de las torres 
        ModeloTablaTorre1 = (DefaultTableModel) Torre1.getModel();
        ModeloTablaTorre1.setRowCount(10);

        ModeloTablaTorre2 = (DefaultTableModel) Torre2.getModel();
        ModeloTablaTorre2.setRowCount(10);

        ModeloTablaTorre3 = (DefaultTableModel) Torre3.getModel();
        ModeloTablaTorre3.setRowCount(10);

        //centralizamos los datos 
        DefaultTableCellRenderer render1 = new DefaultTableCellRenderer();
        render1.setHorizontalAlignment(SwingConstants.CENTER);
        Torre1.getColumnModel().getColumn(0).setCellRenderer(render1);

        DefaultTableCellRenderer render2 = new DefaultTableCellRenderer();
        render2.setHorizontalAlignment(SwingConstants.CENTER);
        Torre2.getColumnModel().getColumn(0).setCellRenderer(render2);

        DefaultTableCellRenderer render3 = new DefaultTableCellRenderer();
        render3.setHorizontalAlignment(SwingConstants.CENTER);
        Torre3.getColumnModel().getColumn(0).setCellRenderer(render3);
    }

    public Icon icono(String path, int width, int heigth) {
        Icon img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()
                .getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH));
        return img;
    }

    private void limpiar() {
        contador = 0;
        numMinMov = 0;
        ComboBox_numDisco.setSelectedItem(String.valueOf(objeto));
    }

    private void NumMovimientos() {
        contador++;
        Label_numMov.setText(String.valueOf(contador));
    }

    private void iniciar() {

        try {

            PilaTorre1 = new Pila();
            PilaTorre2 = new Pila();
            PilaTorre3 = new Pila();

            objeto = Integer.parseInt(ComboBox_numDisco.getSelectedItem().toString());

            numMinMov = Math.pow(2, objeto) - 1;
            Label_numMov.setText(String.valueOf(contador));
            Label_minMov.setText(String.format("%.0f", numMinMov));

            //dubujamos los discos de las torres
            for (int x = objeto; x >= 1; x--) {
                Nodo plataforma = new Nodo();
                String disco = "";
                
                for (int y = x; y > 0; y--) {
                    disco+="__";
                    
                }
                plataforma.setDato(disco);
                PilaTorre1.Push(plataforma);
            }
            
            presentarTorre1();
            presentarTorre2();
            presentarTorre3();

        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }

    private void presentarTorre1() {
        //limpiar la torre
        ((DefaultTableModel) Torre1.getModel()).setRowCount(0);
        //numero de filas dentro dela torre
        ModeloTablaTorre1.setRowCount(10);
        //recorremos la torre
        Nodo recorrer;

        int discoAbajo = (10 - PilaTorre1.getI());

        if (PilaTorre1.getI() > 0) {

            for (recorrer = PilaTorre1.getCabeza(); recorrer.getAbajo() != null; recorrer = recorrer.getAbajo()) {
                String[] vector = {recorrer.getDato()};
                ModeloTablaTorre1.insertRow(discoAbajo, vector);
                discoAbajo++;

            }
            if (recorrer.getAbajo() == null) {
                String[] vector = {recorrer.getDato()};
                ModeloTablaTorre1.insertRow(discoAbajo, vector);
            }
        }
        Torre1.setModel(ModeloTablaTorre1);
        ModeloTablaTorre1.setRowCount(10);
    }

    private void presentarTorre2() {
        ((DefaultTableModel) Torre2.getModel()).setRowCount(0);

        ModeloTablaTorre2.setRowCount(10);

        Nodo recorrer;

        int discoAbajo = (10 - PilaTorre2.getI());

        if (PilaTorre2.getI() > 0) {

            for (recorrer = PilaTorre2.getCabeza(); recorrer.getAbajo() != null; recorrer = recorrer.getAbajo()) {
                String[] vector = {recorrer.getDato()};
                ModeloTablaTorre2.insertRow(discoAbajo, vector);
                discoAbajo++;

            }
            if (recorrer.getAbajo() == null) {
                String[] vector = {recorrer.getDato()};
                ModeloTablaTorre2.insertRow(discoAbajo, vector);
            }
        }
        Torre2.setModel(ModeloTablaTorre2);
        ModeloTablaTorre2.setRowCount(10);
    }

    private void presentarTorre3() {
        ((DefaultTableModel) Torre3.getModel()).setRowCount(0);

        ModeloTablaTorre3.setRowCount(10);

        Nodo recorrer;

        int discoAbajo = (10 - PilaTorre3.getI());

        if (PilaTorre3.getI() > 0) {

            for (recorrer = PilaTorre3.getCabeza(); recorrer.getAbajo() != null; recorrer = recorrer.getAbajo()) {
                String[] vector = {recorrer.getDato()};
                ModeloTablaTorre3.insertRow(discoAbajo, vector);
                discoAbajo++;

            }
            if (recorrer.getAbajo() == null) {
                String[] vector = {recorrer.getDato()};
                ModeloTablaTorre3.insertRow(discoAbajo, vector);
            }
        }
        Torre3.setModel(ModeloTablaTorre3);
        ModeloTablaTorre3.setRowCount(10);
    }

    private void moverPlataforma(Pila inicio, Pila fin) {
        if (stop == false) {

            Nodo plataforma = new Nodo();
            plataforma.setDato(inicio.Peek());
            inicio.Pop();

            fin.Push(plataforma);

            presentarTorre1();
            presentarTorre2();
            presentarTorre3();
            NumMovimientos();

            JOptionPane alert = new JOptionPane("Paso # " + Label_numMov.getText() + "\n\n ¿Desea continuar?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            JDialog dialog = alert.createDialog("Numero de pasos");

            dialog.setLocation(500, 300);
            dialog.setVisible(true);

            int op = (int) alert.getValue();

            if (op == JOptionPane.NO_OPTION) {
                stop = true;
            }

        }
        
    }

    private void recursividad(int n, Pila inicio, Pila aux, Pila fin) {
        if (n == 1) {
            moverPlataforma(inicio, fin);

        } else {
            recursividad(n - 1, inicio, fin, aux);
            moverPlataforma(inicio, fin);

            recursividad(n - 1, aux, inicio, fin);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        Torre2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Torre3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Torre1 = new javax.swing.JTable();
        Button1_a2 = new javax.swing.JButton();
        Button2_a1 = new javax.swing.JButton();
        Button1_a3 = new javax.swing.JButton();
        Button3_a1 = new javax.swing.JButton();
        Button2_a3 = new javax.swing.JButton();
        Button3_a2 = new javax.swing.JButton();
        ComboBox_numDisco = new javax.swing.JComboBox<>();
        Label_minMov = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Label_numMov = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonIniciar = new javax.swing.JButton();
        jButtonReiniciar = new javax.swing.JButton();
        jButtonResolver = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UMG PROGRA-lll");
        setBackground(new java.awt.Color(41, 59, 36));
        setForeground(new java.awt.Color(0, 0, 204));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        Torre2.setBackground(new java.awt.Color(87, 106, 103));
        Torre2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Torre2.setForeground(new java.awt.Color(255, 255, 255));
        Torre2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre2.setAutoscrolls(false);
        Torre2.setFocusable(false);
        Torre2.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(Torre2);
        if (Torre2.getColumnModel().getColumnCount() > 0) {
            Torre2.getColumnModel().getColumn(0).setResizable(false);
        }

        Torre3.setBackground(new java.awt.Color(87, 106, 103));
        Torre3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Torre3.setForeground(new java.awt.Color(255, 255, 255));
        Torre3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre3.setAutoscrolls(false);
        Torre3.setFocusable(false);
        Torre3.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(Torre3);
        if (Torre3.getColumnModel().getColumnCount() > 0) {
            Torre3.getColumnModel().getColumn(0).setResizable(false);
        }

        Torre1.setBackground(new java.awt.Color(87, 106, 103));
        Torre1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Torre1.setForeground(new java.awt.Color(255, 255, 255));
        Torre1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre1.setAutoscrolls(false);
        Torre1.setFocusable(false);
        Torre1.setName("T_1"); // NOI18N
        Torre1.setRequestFocusEnabled(false);
        Torre1.setRowSelectionAllowed(false);
        jScrollPane3.setViewportView(Torre1);
        if (Torre1.getColumnModel().getColumnCount() > 0) {
            Torre1.getColumnModel().getColumn(0).setResizable(false);
        }

        Button1_a2.setBackground(new java.awt.Color(0, 102, 0));
        Button1_a2.setForeground(new java.awt.Color(255, 255, 255));
        Button1_a2.setText("2");
        Button1_a2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Button1_a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1_a2ActionPerformed(evt);
            }
        });

        Button2_a1.setBackground(new java.awt.Color(0, 102, 0));
        Button2_a1.setForeground(new java.awt.Color(255, 255, 255));
        Button2_a1.setText("1");
        Button2_a1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Button2_a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2_a1ActionPerformed(evt);
            }
        });

        Button1_a3.setBackground(new java.awt.Color(0, 102, 0));
        Button1_a3.setForeground(new java.awt.Color(255, 255, 255));
        Button1_a3.setText("3");
        Button1_a3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Button1_a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1_a3ActionPerformed(evt);
            }
        });

        Button3_a1.setBackground(new java.awt.Color(0, 102, 0));
        Button3_a1.setForeground(new java.awt.Color(255, 255, 255));
        Button3_a1.setText("1");
        Button3_a1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Button3_a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button3_a1ActionPerformed(evt);
            }
        });

        Button2_a3.setBackground(new java.awt.Color(0, 102, 0));
        Button2_a3.setForeground(new java.awt.Color(255, 255, 255));
        Button2_a3.setText("3");
        Button2_a3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Button2_a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2_a3ActionPerformed(evt);
            }
        });

        Button3_a2.setBackground(new java.awt.Color(0, 102, 0));
        Button3_a2.setForeground(new java.awt.Color(255, 255, 255));
        Button3_a2.setText("2");
        Button3_a2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Button3_a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button3_a2ActionPerformed(evt);
            }
        });

        ComboBox_numDisco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8", "9", "10" }));
        ComboBox_numDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_numDiscoActionPerformed(evt);
            }
        });

        Label_minMov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_minMov.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jLabel2.setText("#Discos");

        Label_numMov.setBackground(new java.awt.Color(0, 102, 51));
        Label_numMov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_numMov.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jLabel4.setText("#Min. Movimientos");

        jLabel5.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jLabel5.setText("# Movimientos");

        jButtonIniciar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonIniciar.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jButtonIniciar.setForeground(new java.awt.Color(0, 102, 0));
        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        jButtonReiniciar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonReiniciar.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jButtonReiniciar.setForeground(new java.awt.Color(102, 102, 0));
        jButtonReiniciar.setText("Reiniciar");
        jButtonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReiniciarActionPerformed(evt);
            }
        });

        jButtonResolver.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResolver.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jButtonResolver.setForeground(new java.awt.Color(51, 0, 0));
        jButtonResolver.setText("Resolver");
        jButtonResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResolverActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jLabel6.setText("--------------------------   Torres Hanoi    ------------------------");

        jLabel7.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jLabel7.setText("Angel Najarro");

        jLabel8.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(Label_minMov, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Label_numMov, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(ComboBox_numDisco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(Button1_a2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Button1_a3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Button2_a1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Button2_a3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Button3_a1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Button3_a2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonIniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonReiniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonResolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(41, 41, 41))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button2_a1)
                    .addComponent(Button3_a1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Button3_a2)
                        .addComponent(Button2_a3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Button1_a2)
                        .addComponent(Button1_a3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboBox_numDisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonIniciar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonReiniciar)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Label_minMov, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonResolver)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_numMov, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button1_a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1_a2ActionPerformed

        try {
            if (PilaTorre1.getI() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(PilaTorre1.Peek());

                if (PilaTorre2.getI() > 0) {
                    if (plataforma.getDato().compareTo(PilaTorre2.Peek()) > 0) {
                        return;
                    }
                }
                PilaTorre1.Pop();
                PilaTorre2.Push(plataforma);

                presentarTorre1();
                presentarTorre2();
                NumMovimientos();
            }
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }//GEN-LAST:event_Button1_a2ActionPerformed

    private void Button2_a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2_a1ActionPerformed
        try {
            if (PilaTorre2.getI() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(PilaTorre2.Peek());

                if (PilaTorre1.getI() > 0) {
                    if (plataforma.getDato().compareTo(PilaTorre1.Peek()) > 0) {
                        return;
                    }
                }
                PilaTorre2.Pop();
                PilaTorre1.Push(plataforma);

                presentarTorre1();
                presentarTorre2();
                NumMovimientos();
            }
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }//GEN-LAST:event_Button2_a1ActionPerformed

    private void Button1_a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1_a3ActionPerformed
        try {
            if (PilaTorre1.getI() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(PilaTorre1.Peek());

                if (PilaTorre3.getI() > 0) {
                    if (plataforma.getDato().compareTo(PilaTorre3.Peek()) > 0) {
                        return;
                    }
                }
                PilaTorre1.Pop();
                PilaTorre3.Push(plataforma);

                presentarTorre1();
                presentarTorre2();
                presentarTorre3();
                NumMovimientos();

                if (PilaTorre3.getI() == objeto && contador == numMinMov) {
                                   
                    JOptionPane.showMessageDialog(null, "Haz finalizado el juego utilizando el minimo de movimientos\n\n "
                            + "Avanza al siguiente nivel", "¡Felicidades!", JOptionPane.PLAIN_MESSAGE, icono("/imgs/logoUMG.png", 60, 90));
                    
                } else if (PilaTorre3.getI() == objeto && contador != numMinMov) {
                   
                    JOptionPane.showMessageDialog(null, "Haz finalizado el juego\n\n "
                            + "Intenta mejorar tu racha o continua al siguiente nivel", "¡Felicidades!", JOptionPane.PLAIN_MESSAGE, icono("/imgs/logoUMG.png", 60, 90));
                    
                }
            }
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }//GEN-LAST:event_Button1_a3ActionPerformed

    private void Button3_a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button3_a1ActionPerformed
        try {
            if (PilaTorre3.getI() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(PilaTorre3.Peek());

                if (PilaTorre1.getI() > 0) {
                    if (plataforma.getDato().compareTo(PilaTorre1.Peek()) > 0) {
                        return;
                    }
                }
                PilaTorre3.Pop();
                PilaTorre1.Push(plataforma);

                presentarTorre1();
                presentarTorre2();
                presentarTorre3();
                NumMovimientos();
            }
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }//GEN-LAST:event_Button3_a1ActionPerformed

    private void Button2_a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2_a3ActionPerformed
        try {
            if (PilaTorre2.getI() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(PilaTorre2.Peek());

                if (PilaTorre3.getI() > 0) {
                    if (plataforma.getDato().compareTo(PilaTorre3.Peek()) > 0) {
                        return;
                    }
                }
                PilaTorre2.Pop();
                PilaTorre3.Push(plataforma);

                presentarTorre1();
                presentarTorre2();
                presentarTorre3();
                NumMovimientos();

                if (PilaTorre3.getI() == objeto && contador == numMinMov) {
                    
                    JOptionPane.showMessageDialog(null, "¡Felicidades! haz finalizado el juego utilizando el minimo de movimientos\n\n "
                            + "Haz avanzado al siguiente nivel", "¡Felicidades!", JOptionPane.PLAIN_MESSAGE, icono("/imgs/logoUMG.png", 60, 90));
                    
                } else if (PilaTorre3.getI() == objeto && contador != numMinMov) {
                    
                    JOptionPane.showMessageDialog(null, "¡Felicidades! haz finalizado el juego\n\n "
                            + "Intenta mejorar tu racha o continua al siguiente nivel", "¡Felicidades!", JOptionPane.PLAIN_MESSAGE, icono("/imgs/logoUMG.png", 60, 90));
                    
                }
            }
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }//GEN-LAST:event_Button2_a3ActionPerformed

    private void Button3_a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button3_a2ActionPerformed
        try {
            if (PilaTorre3.getI() > 0) {
                Nodo plataforma = new Nodo();
                plataforma.setDato(PilaTorre3.Peek());

                if (PilaTorre2.getI() > 0) {
                    if (plataforma.getDato().compareTo(PilaTorre2.Peek()) > 0) {
                        return;
                    }
                }
                PilaTorre3.Pop();
                PilaTorre2.Push(plataforma);

                presentarTorre1();
                presentarTorre2();
                presentarTorre3();
                NumMovimientos();
            }
        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }//GEN-LAST:event_Button3_a2ActionPerformed

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        contador = 0;
        iniciar();

    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jButtonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReiniciarActionPerformed

        try {

            limpiar();
            iniciar();

        } catch (Exception E) {
            System.out.println("Error: " + E.getMessage());
        }
    }//GEN-LAST:event_jButtonReiniciarActionPerformed

    private void jButtonResolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResolverActionPerformed
        if (!Label_minMov.getText().equals("") && PilaTorre3.getI() != objeto) {
            limpiar();

            recursividad(objeto, PilaTorre1, PilaTorre2, PilaTorre3);
        }

    }//GEN-LAST:event_jButtonResolverActionPerformed

    private void ComboBox_numDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_numDiscoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBox_numDiscoActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

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
            java.util.logging.Logger.getLogger(interfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button1_a2;
    private javax.swing.JButton Button1_a3;
    private javax.swing.JButton Button2_a1;
    private javax.swing.JButton Button2_a3;
    private javax.swing.JButton Button3_a1;
    private javax.swing.JButton Button3_a2;
    private javax.swing.JComboBox<String> ComboBox_numDisco;
    private javax.swing.JLabel Label_minMov;
    private javax.swing.JLabel Label_numMov;
    private javax.swing.JTable Torre1;
    private javax.swing.JTable Torre2;
    private javax.swing.JTable Torre3;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButtonReiniciar;
    private javax.swing.JButton jButtonResolver;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
