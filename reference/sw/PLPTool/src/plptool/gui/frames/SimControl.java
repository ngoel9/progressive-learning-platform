/*
    Copyright 2010-2011 David Fritz, Brian Gordon, Wira Mulia

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

package plptool.gui.frames;

import plptool.Msg;
import plptool.Config;
import plptool.Constants;
import plptool.gui.ProjectDriver;
import plptool.dmf.CallbackRegistry;

import java.awt.event.ActionEvent;

/**
 *
 * @author wira
 */
public class SimControl extends javax.swing.JFrame {

    private ProjectDriver plp;

    /** Creates new form SimControl */
    public SimControl(ProjectDriver plp) {
        initComponents();
        this.plp = plp;
        this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("resources/toolbar_remote.png")));
        updateSlider();
        this.setLocationRelativeTo(null);

        javax.swing.KeyStroke stepKeyStroke = javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0, false);
        javax.swing.KeyStroke runKeyStroke = javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0, false);
        javax.swing.KeyStroke resetKeyStroke = javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0, false);

        javax.swing.Action stepAction = new javax.swing.AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btnSimStepActionPerformed(null);
            }
        };

        javax.swing.Action runAction = new javax.swing.AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btnSimRun.setSelected(!btnSimRun.isSelected());
                btnSimRunActionPerformed(null);
            }
        };

        javax.swing.Action resetAction = new javax.swing.AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btnSimResetActionPerformed(null);
            }
        };

        this.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(stepKeyStroke, "STEP");
        this.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(runKeyStroke, "RUN");
        this.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(resetKeyStroke, "RESET");
        this.getRootPane().getActionMap().put("STEP", stepAction);
        this.getRootPane().getActionMap().put("RUN", runAction);
        this.getRootPane().getActionMap().put("RESET", resetAction);

        btnOnTop.setToolTipText("This window will always be on top of other windows when this button is selected.");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        btnSimStep = new javax.swing.JButton();
        btnSimRun = new javax.swing.JToggleButton();
        btnSimReset = new javax.swing.JButton();
        sliderSimSpeed = new javax.swing.JSlider();
        btnOnTop = new javax.swing.JToggleButton();
        txtStepSize = new javax.swing.JTextField();
        lblStepSize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(SimControl.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setName("toolBar"); // NOI18N

        btnSimStep.setIcon(resourceMap.getIcon("btnSimStep.icon")); // NOI18N
        btnSimStep.setText(resourceMap.getString("btnSimStep.text")); // NOI18N
        btnSimStep.setFocusable(false);
        btnSimStep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimStep.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnSimStep.setName("btnSimStep"); // NOI18N
        btnSimStep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimStepActionPerformed(evt);
            }
        });
        toolBar.add(btnSimStep);

        btnSimRun.setIcon(resourceMap.getIcon("btnSimRun.icon")); // NOI18N
        btnSimRun.setText(resourceMap.getString("btnSimRun.text")); // NOI18N
        btnSimRun.setFocusable(false);
        btnSimRun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimRun.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnSimRun.setName("btnSimRun"); // NOI18N
        btnSimRun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimRunActionPerformed(evt);
            }
        });
        toolBar.add(btnSimRun);

        btnSimReset.setIcon(resourceMap.getIcon("btnSimReset.icon")); // NOI18N
        btnSimReset.setText(resourceMap.getString("btnSimReset.text")); // NOI18N
        btnSimReset.setFocusable(false);
        btnSimReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimReset.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnSimReset.setName("btnSimReset"); // NOI18N
        btnSimReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimResetActionPerformed(evt);
            }
        });
        toolBar.add(btnSimReset);

        sliderSimSpeed.setMaximum(1000);
        sliderSimSpeed.setPaintLabels(true);
        sliderSimSpeed.setPaintTicks(true);
        sliderSimSpeed.setName("sliderSimSpeed"); // NOI18N
        sliderSimSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderSimSpeedStateChanged(evt);
            }
        });
        toolBar.add(sliderSimSpeed);

        btnOnTop.setText(resourceMap.getString("btnOnTop.text")); // NOI18N
        btnOnTop.setFocusable(false);
        btnOnTop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOnTop.setName("btnOnTop"); // NOI18N
        btnOnTop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnTopActionPerformed(evt);
            }
        });
        toolBar.add(btnOnTop);

        txtStepSize.setText(resourceMap.getString("txtStepSize.text")); // NOI18N
        txtStepSize.setName("txtStepSize"); // NOI18N
        txtStepSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStepSizeActionPerformed(evt);
            }
        });
        txtStepSize.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStepSizeKeyPressed(evt);
            }
        });

        lblStepSize.setText(resourceMap.getString("lblStepSize.text")); // NOI18N
        lblStepSize.setName("lblStepSize"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStepSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStepSize, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStepSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStepSize))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderSimSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderSimSpeedStateChanged
        Config.simRunnerDelay = sliderSimSpeed.getValue();
        plp.g_opts.restoreSavedOpts();
    }//GEN-LAST:event_sliderSimSpeedStateChanged

    private void txtStepSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStepSizeActionPerformed

    }//GEN-LAST:event_txtStepSizeActionPerformed

    private void txtStepSizeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStepSizeKeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            int steps = Config.simCyclesPerStep;

            try {
                steps = Integer.parseInt(txtStepSize.getText());
            } catch(Exception e) {
                txtStepSize.setText("" + Config.simCyclesPerStep);
            }

            if(steps > 0 && steps < Constants.PLP_MAX_STEPS)
                Config.simCyclesPerStep = steps;
            else
                txtStepSize.setText("" + Config.simCyclesPerStep);
        }
    }//GEN-LAST:event_txtStepSizeKeyPressed

    private void btnSimRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimRunActionPerformed
        if(btnSimRun.isSelected())
            plp.runSimulation();
        else
            plp.stopSimulation();
    }//GEN-LAST:event_btnSimRunActionPerformed

    private void btnSimStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimStepActionPerformed
        boolean breakpoint = false;
        CallbackRegistry.callback_Sim_Step_Aggregate(Config.simCyclesPerStep);
        for(int i = 0; i < Config.simCyclesPerStep && !breakpoint; i++) {            
            plp.sim.step();
            CallbackRegistry.callback_Sim_Step();
            if(plp.sim.breakpoints.hasBreakpoint() && plp.sim.breakpoints.isBreakpoint(plp.sim.visibleAddr)) {
                Msg.M("--- breakpoint encountered: " + String.format("0x%02x", plp.sim.visibleAddr));
                breakpoint = true;
            }
        }
        CallbackRegistry.callback_Sim_Post_Step_Aggregate();
        plp.updateComponents(true);
    }//GEN-LAST:event_btnSimStepActionPerformed

    private void btnSimResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimResetActionPerformed
        if(plp.g_simrun != null)
            plp.stopSimulation();
        plp.sim.reset();
        CallbackRegistry.callback_Sim_Reset();

        plp.updateComponents(true);
        //plp.refreshProjectView(false);
        plp.g_dev.safeRefresh(false);
    }//GEN-LAST:event_btnSimResetActionPerformed

    private void btnOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnTopActionPerformed
        this.setAlwaysOnTop(btnOnTop.isSelected());
    }//GEN-LAST:event_btnOnTopActionPerformed

    public void runSimState() {
        btnSimRun.setSelected(true);
        btnSimStep.setEnabled(false);
    }

    public void stopSimState() {
        btnSimRun.setSelected(false);
        btnSimStep.setEnabled(true);
    }

    public void updateSlider() {
        sliderSimSpeed.setValue(Config.simRunnerDelay);
        plp.g_opts.restoreSavedOpts();
    }

    public void update() {
        sliderSimSpeed.setValue(Config.simRunnerDelay);
        txtStepSize.setText("" + Config.simCyclesPerStep);
        this.setTitle("Simulation Control - Cycle: " + plp.sim.getInstrCount());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnOnTop;
    private javax.swing.JButton btnSimReset;
    private javax.swing.JToggleButton btnSimRun;
    private javax.swing.JButton btnSimStep;
    private javax.swing.JLabel lblStepSize;
    private javax.swing.JSlider sliderSimSpeed;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JTextField txtStepSize;
    // End of variables declaration//GEN-END:variables

}
