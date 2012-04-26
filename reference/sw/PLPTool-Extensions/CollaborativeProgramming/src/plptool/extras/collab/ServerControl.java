/*
    Copyright 2012 PLP Contributors

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */

package plptool.extras.collab;

import plptool.gui.ProjectDriver;

/**
 *
 * @author Wira
 */
public class ServerControl extends javax.swing.JFrame {
    public static final int MAX_CLIENTS = 256;
    private ProjectDriver plp;
    private ServerService service;

    /** Creates new form ServerControl */
    public ServerControl(ProjectDriver plp) {
        initComponents();
        this.plp = plp;
    }

    public synchronized void update() {
        
    }

    public void startListening() {
        service = new ServerService(this, plp,
                Integer.parseInt(txtPort.getText()), 256);
        service.start();
        setStates(true);
    }

    public void stopListening() {
        service.stopListening();
        setStates(false);
    }

    public void setStates(boolean c) {
        paneServerConfig.setVisible(c);
        paneCollabControl.setVisible(c);
        if(!c)
            tabbedPane.setSelectedIndex(0);
        txtPort.setEnabled(!c);
        tglGoLive.setSelected(c);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblStatus = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        paneServerConfig = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tglGoLive = new javax.swing.JToggleButton();
        btnClose = new javax.swing.JButton();
        paneClients = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClients = new javax.swing.JTable();
        paneCollabControl = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        radioSingleContributor = new javax.swing.JRadioButton();
        radioMultipleContributors = new javax.swing.JRadioButton();
        radioFreeForAll = new javax.swing.JRadioButton();
        txtMaximumContributors = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tglSolicit = new javax.swing.JToggleButton();
        tglLive = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();

        lblStatus.setText("Server status :");

        jLabel1.setText("Listen on port :");

        txtPort.setText("11000");

        jLabel2.setText("Server options :");

        tglGoLive.setText("GO LIVE");
        tglGoLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglGoLiveActionPerformed(evt);
            }
        });

        btnClose.setText("Close");

        javax.swing.GroupLayout paneServerConfigLayout = new javax.swing.GroupLayout(paneServerConfig);
        paneServerConfig.setLayout(paneServerConfigLayout);
        paneServerConfigLayout.setHorizontalGroup(
            paneServerConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneServerConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneServerConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneServerConfigLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtPort, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                    .addComponent(jLabel2)
                    .addGroup(paneServerConfigLayout.createSequentialGroup()
                        .addComponent(tglGoLive)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        paneServerConfigLayout.setVerticalGroup(
            paneServerConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneServerConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneServerConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(paneServerConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tglGoLive)
                    .addComponent(btnClose))
                .addContainerGap())
        );

        tabbedPane.addTab("Server Config", paneServerConfig);

        tblClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "IP Addr", "Nickname", "BAN", "LIVE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClients.setEnabled(false);
        jScrollPane1.setViewportView(tblClients);

        javax.swing.GroupLayout paneClientsLayout = new javax.swing.GroupLayout(paneClients);
        paneClients.setLayout(paneClientsLayout);
        paneClientsLayout.setHorizontalGroup(
            paneClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );
        paneClientsLayout.setVerticalGroup(
            paneClientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Clients", paneClients);

        paneCollabControl.setEnabled(false);

        jLabel3.setText("Mode :");

        buttonGroup1.add(radioSingleContributor);
        radioSingleContributor.setSelected(true);
        radioSingleContributor.setText("Single contributor");

        buttonGroup1.add(radioMultipleContributors);
        radioMultipleContributors.setText("Multiple contributors");

        buttonGroup1.add(radioFreeForAll);
        radioFreeForAll.setText("Free for All");

        jLabel4.setText("maximum contributors");

        tglSolicit.setText("SOLICIT");
        tglSolicit.setPreferredSize(new java.awt.Dimension(100, 23));

        tglLive.setText("LIVE");
        tglLive.setPreferredSize(new java.awt.Dimension(100, 23));

        jLabel5.setText("Currently live client ID(s):");

        javax.swing.GroupLayout paneCollabControlLayout = new javax.swing.GroupLayout(paneCollabControl);
        paneCollabControl.setLayout(paneCollabControlLayout);
        paneCollabControlLayout.setHorizontalGroup(
            paneCollabControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCollabControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCollabControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneCollabControlLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(paneCollabControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioFreeForAll)
                            .addGroup(paneCollabControlLayout.createSequentialGroup()
                                .addComponent(radioMultipleContributors)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaximumContributors, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(radioSingleContributor)))
                    .addGroup(paneCollabControlLayout.createSequentialGroup()
                        .addComponent(tglSolicit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tglLive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        paneCollabControlLayout.setVerticalGroup(
            paneCollabControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCollabControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCollabControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(radioSingleContributor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneCollabControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioMultipleContributors)
                    .addComponent(txtMaximumContributors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioFreeForAll)
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(paneCollabControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tglSolicit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tglLive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabbedPane.addTab("Collaboration Control", paneCollabControl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(lblStatus))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tglGoLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglGoLiveActionPerformed
        if(tglGoLive.isSelected())
            startListening();
        else
            stopListening();
    }//GEN-LAST:event_tglGoLiveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel paneClients;
    private javax.swing.JPanel paneCollabControl;
    private javax.swing.JPanel paneServerConfig;
    private javax.swing.JRadioButton radioFreeForAll;
    private javax.swing.JRadioButton radioMultipleContributors;
    private javax.swing.JRadioButton radioSingleContributor;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tblClients;
    private javax.swing.JToggleButton tglGoLive;
    private javax.swing.JToggleButton tglLive;
    private javax.swing.JToggleButton tglSolicit;
    private javax.swing.JTextField txtMaximumContributors;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables

}
