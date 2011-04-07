/*
    Copyright 2010 David Fritz, Brian Gordon, Wira Mulia

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

package plptool.gui;

import plptool.mods.IORegistry;
import plptool.mods.Preset;
import javax.swing.table.DefaultTableModel;

/**
 * Front-end for IORegistry for PLPToolView
 *
 * @see IORegistry
 * @author wira
 */
public class IORegistryFrame extends javax.swing.JInternalFrame {

    Object[][] modInfo;
    ProjectDriver plp;

    /** Creates new form PLPIORegistry */
    public IORegistryFrame(ProjectDriver plp) {

        this.plp = plp;
        IORegistry ioRegistry = plp.ioreg;
        modInfo = ioRegistry.getAvailableModulesInformation();

        initComponents();
        cmbModuleSelect.removeAllItems();

        for(int i = 0; i < ioRegistry.getNumOfMods(); i++)
            cmbModuleSelect.addItem(modInfo[i][0]);

        cmbPresets.removeAllItems();
        for(int i = 0; i < Preset.presets.length; i++)
            cmbPresets.addItem(Preset.presets[i][0]);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        paneAdd = new javax.swing.JPanel();
        lblModule = new javax.swing.JLabel();
        cmbModuleSelect = new javax.swing.JComboBox();
        lblAddr = new javax.swing.JLabel();
        txtModuleAddress = new javax.swing.JTextField();
        lblRegfileSize = new javax.swing.JLabel();
        txtModuleRegfileSize = new javax.swing.JTextField();
        txtModInfoScroll = new javax.swing.JScrollPane();
        txtModInfo = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        paneList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMods = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        paneIOConfig = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbPresets = new javax.swing.JComboBox();
        btnLoadPreset = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(IORegistryFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        paneAdd.setName("addPane"); // NOI18N

        lblModule.setText(resourceMap.getString("lblModule.text")); // NOI18N
        lblModule.setName("lblModule"); // NOI18N

        cmbModuleSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbModuleSelect.setName("cmbModuleSelect"); // NOI18N
        cmbModuleSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbModuleSelectActionPerformed(evt);
            }
        });

        lblAddr.setText(resourceMap.getString("lblAddr.text")); // NOI18N
        lblAddr.setName("lblAddr"); // NOI18N

        txtModuleAddress.setText(resourceMap.getString("txtModuleAddress.text")); // NOI18N
        txtModuleAddress.setName("txtModuleAddress"); // NOI18N

        lblRegfileSize.setText(resourceMap.getString("lblRegfileSize.text")); // NOI18N
        lblRegfileSize.setName("lblRegfileSize"); // NOI18N

        txtModuleRegfileSize.setText(resourceMap.getString("txtModuleRegfileSize.text")); // NOI18N
        txtModuleRegfileSize.setName("txtModuleRegfileSize"); // NOI18N

        txtModInfoScroll.setName("txtModInfoScroll"); // NOI18N

        txtModInfo.setColumns(20);
        txtModInfo.setEditable(false);
        txtModInfo.setFont(resourceMap.getFont("txtModInfo.font")); // NOI18N
        txtModInfo.setLineWrap(true);
        txtModInfo.setRows(5);
        txtModInfo.setWrapStyleWord(true);
        txtModInfo.setName("txtModInfo"); // NOI18N
        txtModInfoScroll.setViewportView(txtModInfo);

        btnAdd.setText(resourceMap.getString("btnAdd.text")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneAddLayout = new javax.swing.GroupLayout(paneAdd);
        paneAdd.setLayout(paneAddLayout);
        paneAddLayout.setHorizontalGroup(
            paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtModInfoScroll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(btnAdd)
                    .addGroup(paneAddLayout.createSequentialGroup()
                        .addGroup(paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModule)
                            .addComponent(lblAddr)
                            .addComponent(lblRegfileSize))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModuleRegfileSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(cmbModuleSelect, javax.swing.GroupLayout.Alignment.TRAILING, 0, 341, Short.MAX_VALUE)
                            .addComponent(txtModuleAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))))
                .addContainerGap())
        );
        paneAddLayout.setVerticalGroup(
            paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModule)
                    .addComponent(cmbModuleSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModuleAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModuleRegfileSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegfileSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtModInfoScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("addPane.TabConstraints.tabTitle"), paneAdd); // NOI18N

        paneList.setName("paneList"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblMods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Registry Pos.", "Bus Position", "Start Address", "End Address", "Show Frame"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMods.setName("tblMods"); // NOI18N
        tblMods.getTableHeader().setReorderingAllowed(false);
        tblMods.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblModsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMods);
        tblMods.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblMods.columnModel.title0")); // NOI18N
        tblMods.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblMods.columnModel.title5")); // NOI18N
        tblMods.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblMods.columnModel.title1")); // NOI18N
        tblMods.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblMods.columnModel.title3")); // NOI18N
        tblMods.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tblMods.columnModel.title4")); // NOI18N
        tblMods.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("tblMods.columnModel.title5")); // NOI18N

        btnRemove.setText(resourceMap.getString("btnRemove.text")); // NOI18N
        btnRemove.setName("btnRemove"); // NOI18N
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneListLayout = new javax.swing.GroupLayout(paneList);
        paneList.setLayout(paneListLayout);
        paneListLayout.setHorizontalGroup(
            paneListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addGroup(paneListLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)))
                .addContainerGap())
        );
        paneListLayout.setVerticalGroup(
            paneListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("paneList.TabConstraints.tabTitle"), paneList); // NOI18N

        paneIOConfig.setName("paneIOConfig"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        cmbPresets.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPresets.setName("cmbPresets"); // NOI18N

        btnLoadPreset.setText(resourceMap.getString("btnLoadPreset.text")); // NOI18N
        btnLoadPreset.setName("btnLoadPreset"); // NOI18N
        btnLoadPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadPresetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneIOConfigLayout = new javax.swing.GroupLayout(paneIOConfig);
        paneIOConfig.setLayout(paneIOConfigLayout);
        paneIOConfigLayout.setHorizontalGroup(
            paneIOConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneIOConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbPresets, 0, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoadPreset)
                .addContainerGap())
        );
        paneIOConfigLayout.setVerticalGroup(
            paneIOConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneIOConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneIOConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnLoadPreset)
                    .addComponent(cmbPresets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(413, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("paneIOConfig.TabConstraints.tabTitle"), paneIOConfig); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbModuleSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbModuleSelectActionPerformed
        if(modInfo != null && cmbModuleSelect.getSelectedIndex() >= 0) {

        if(!(Boolean) modInfo[cmbModuleSelect.getSelectedIndex()][1]) {
            txtModuleRegfileSize.setEnabled(false);
            txtModuleRegfileSize.setText("" + (Integer) modInfo[cmbModuleSelect.getSelectedIndex()][2]);
        } else {
            txtModuleRegfileSize.setEnabled(true);
            txtModuleRegfileSize.setText("");
        }

        txtModInfo.setText((String) modInfo[cmbModuleSelect.getSelectedIndex()][3]);

        }
    }//GEN-LAST:event_cmbModuleSelectActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int modIndex = cmbModuleSelect.getSelectedIndex();
        long addr = plptool.PLPToolbox.parseNum(txtModuleAddress.getText());
        long size = plptool.PLPToolbox.parseNum(txtModuleRegfileSize.getText());
        

        if(addr != plptool.Constants.PLP_NUMBER_ERROR && size > 0) {
            if(!(Boolean) modInfo[modIndex][4] || (addr % 4 == 0)) {
                plp.ioreg.attachModuleToBus(modIndex, addr, size, plp.g_simsh.getSimDesktop());
                refreshModulesTable();
            }
        } else {
            plptool.Msg.M("ERROR");
        }

        plp.modified = true;
        plp.updateComponents();
        plp.g_sim.updateBusTable();
        refreshModulesTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        if(tblMods.getModel().getRowCount() > 0 &&
                tblMods.getSelectedRow() > -1)  {

            int index = (Integer) tblMods.getModel().getValueAt(tblMods.getSelectedRow(), 1);
            plp.ioreg.removeModule(index);
            refreshModulesTable();
            plp.modified = true;
            plp.updateWindowTitle();
            plp.updateComponents();
            plp.g_sim.updateBusTable();
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        plp.ioreg.removeAllModules();
        plp.modified = true;
        plp.updateWindowTitle();
        refreshModulesTable();
        plp.updateComponents();
        plp.g_sim.updateBusTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLoadPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadPresetActionPerformed
        int index = cmbPresets.getSelectedIndex();

        if(index > -1) {
            Integer[] modsType = (Integer[]) Preset.presets[index][1];
            Long[] startAddresses = (Long[]) Preset.presets[index][2];
            Long[] sizes = (Long[]) Preset.presets[index][3];
            for(int i = 0; i < modsType.length; i++)
                plp.ioreg.attachModuleToBus(modsType[i], startAddresses[i], sizes[i], plp.g_simsh.getSimDesktop());
            plp.modified = true;
            plp.updateWindowTitle();
            plp.updateComponents();
            plp.g_sim.updateBusTable();
            refreshModulesTable();
        }
    }//GEN-LAST:event_btnLoadPresetActionPerformed

    private void tblModsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblModsMouseClicked
        try {

        for(int row = 0; row < tblMods.getRowCount(); row++) {
            if((Boolean) tblMods.getModel().getValueAt(row, 5)) {
                if(plp.ioreg.getModuleFrame(row) != null) {
                    ((javax.swing.JInternalFrame) plp.ioreg.getModuleFrame(row)).setVisible(true);
                }
            }
            else {
                if(plp.ioreg.getModuleFrame(row) != null) {
                    ((javax.swing.JInternalFrame) plp.ioreg.getModuleFrame(row)).setVisible(false);
                }
            }
        }

        } catch(Exception e) {

        }
    }//GEN-LAST:event_tblModsMouseClicked

    public void refreshModulesTable() {
        DefaultTableModel mods = (DefaultTableModel) tblMods.getModel();
        Object[] modules;

        modules = plp.ioreg.getAttachedModules();

        while(mods.getRowCount() > 0)
            mods.removeRow(0);

        for(int i = 0; i < modules.length; i++) {
            javax.swing.JInternalFrame frame = (javax.swing.JInternalFrame) plp.ioreg.getModuleFrame(i);
            Object row[] = new Object[]
            { modules[i].toString(),
              i,
              plp.ioreg.getPositionInBus(i),
              String.format("0x%08x", plp.ioreg.getModule(i).startAddr()),
              String.format("0x%08x", plp.ioreg.getModule(i).endAddr()),
              (frame != null) ? frame.isVisible() : false
            };
            mods.addRow(row);
        }

        tblMods.setModel(mods);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnLoadPreset;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox cmbModuleSelect;
    private javax.swing.JComboBox cmbPresets;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAddr;
    private javax.swing.JLabel lblModule;
    private javax.swing.JLabel lblRegfileSize;
    private javax.swing.JPanel paneAdd;
    private javax.swing.JPanel paneIOConfig;
    private javax.swing.JPanel paneList;
    private javax.swing.JTable tblMods;
    private javax.swing.JTextArea txtModInfo;
    private javax.swing.JScrollPane txtModInfoScroll;
    private javax.swing.JTextField txtModuleAddress;
    private javax.swing.JTextField txtModuleRegfileSize;
    // End of variables declaration//GEN-END:variables

}
