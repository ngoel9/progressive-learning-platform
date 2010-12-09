/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SwitchesFrame.java
 *
 * Created on Dec 8, 2010, 8:28:03 AM
 */

package plptool.mods;

/**
 *
 * @author wira
 */
public class SwitchesFrame extends javax.swing.JInternalFrame {

    Switches module;

    /** Creates new form SwitchesFrame */
    public SwitchesFrame(Switches module) {
        this.module = module;
        initComponents();
        txtAddress.setText(String.format("0x%08x", module.startAddr()));
        eval();
    }

    private void eval() {
        long value = 0;

        if(bit0.isSelected())
            value |= 1;
        if(bit1.isSelected())
            value |= 2;
        if(bit2.isSelected())
            value |= 4;
        if(bit3.isSelected())
            value |= 8;
        if(bit4.isSelected())
            value |= 16;
        if(bit5.isSelected())
            value |= 32;
        if(bit6.isSelected())
            value |= 64;
        if(bit7.isSelected())
            value |= 128;

        module.writeReg(module.startAddr(), value, false);
        txtValue.setText(String.format("0x%08x", value));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblValue = new javax.swing.JLabel();
        txtValue = new javax.swing.JTextField();
        bit6 = new javax.swing.JToggleButton();
        bit7 = new javax.swing.JToggleButton();
        bit5 = new javax.swing.JToggleButton();
        bit4 = new javax.swing.JToggleButton();
        bit2 = new javax.swing.JToggleButton();
        bit3 = new javax.swing.JToggleButton();
        bit1 = new javax.swing.JToggleButton();
        bit0 = new javax.swing.JToggleButton();

        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plp.PLPToolApp.class).getContext().getResourceMap(SwitchesFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        lblAddress.setText(resourceMap.getString("lblAddress.text")); // NOI18N
        lblAddress.setName("lblAddress"); // NOI18N

        txtAddress.setEditable(false);
        txtAddress.setName("txtAddress"); // NOI18N

        lblValue.setText(resourceMap.getString("lblValue.text")); // NOI18N
        lblValue.setName("lblValue"); // NOI18N

        txtValue.setEditable(false);
        txtValue.setName("txtValue"); // NOI18N

        bit6.setText(resourceMap.getString("bit6.text")); // NOI18N
        bit6.setName("bit6"); // NOI18N
        bit6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit6ActionPerformed(evt);
            }
        });

        bit7.setText(resourceMap.getString("bit7.text")); // NOI18N
        bit7.setName("bit7"); // NOI18N
        bit7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit7ActionPerformed(evt);
            }
        });

        bit5.setText(resourceMap.getString("bit5.text")); // NOI18N
        bit5.setName("bit5"); // NOI18N
        bit5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit5ActionPerformed(evt);
            }
        });

        bit4.setText(resourceMap.getString("bit4.text")); // NOI18N
        bit4.setName("bit4"); // NOI18N
        bit4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit4ActionPerformed(evt);
            }
        });

        bit2.setText(resourceMap.getString("bit2.text")); // NOI18N
        bit2.setName("bit2"); // NOI18N
        bit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit2ActionPerformed(evt);
            }
        });

        bit3.setText(resourceMap.getString("bit3.text")); // NOI18N
        bit3.setName("bit3"); // NOI18N
        bit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit3ActionPerformed(evt);
            }
        });

        bit1.setText(resourceMap.getString("bit1.text")); // NOI18N
        bit1.setName("bit1"); // NOI18N
        bit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit1ActionPerformed(evt);
            }
        });

        bit0.setText(resourceMap.getString("bit0.text")); // NOI18N
        bit0.setName("bit0"); // NOI18N
        bit0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bit0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAddress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValue, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bit7, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bit6, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bit5, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bit4, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bit3, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bit2, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bit1, 0, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bit0, 0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddress)
                    .addComponent(lblValue))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(bit7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bit6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bit5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bit4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bit3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bit2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bit1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bit0, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bit0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit0ActionPerformed
        eval();
    }//GEN-LAST:event_bit0ActionPerformed

    private void bit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit1ActionPerformed
        eval();
    }//GEN-LAST:event_bit1ActionPerformed

    private void bit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit2ActionPerformed
        eval();
    }//GEN-LAST:event_bit2ActionPerformed

    private void bit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit3ActionPerformed
        eval();
    }//GEN-LAST:event_bit3ActionPerformed

    private void bit4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit4ActionPerformed
        eval();
    }//GEN-LAST:event_bit4ActionPerformed

    private void bit5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit5ActionPerformed
        eval();
    }//GEN-LAST:event_bit5ActionPerformed

    private void bit6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit6ActionPerformed
        eval();
    }//GEN-LAST:event_bit6ActionPerformed

    private void bit7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bit7ActionPerformed
        eval();
    }//GEN-LAST:event_bit7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bit0;
    private javax.swing.JToggleButton bit1;
    private javax.swing.JToggleButton bit2;
    private javax.swing.JToggleButton bit3;
    private javax.swing.JToggleButton bit4;
    private javax.swing.JToggleButton bit5;
    private javax.swing.JToggleButton bit6;
    private javax.swing.JToggleButton bit7;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblValue;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables

}
