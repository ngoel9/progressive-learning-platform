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

import plptool.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author wira
 */
public class SimShell extends javax.swing.JFrame {

    static final int MAX_STEPS = 60000;
    ProjectDriver plp;

    private OptionsFrame          opts;

    public SimShell(ProjectDriver plp) {
        this.plp = plp;

        initComponents();
        this.setLocationRelativeTo(null);
        Msg.M("SimShell Loaded\n");

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent we) {
                destroySimulation();
            }
        });

        for(int i = 0; i < plptool.mods.Presets.presets.length; i++)
            menuPresets.add(new javax.swing.JMenuItem((String) plptool.mods.Presets.presets[i][0]));

        cmenuRun.setSelected(false);
        tglRun.setSelected(false);
    }

    public void plpMsgRouteBack() {
        Msg.output = null;
    }

    public void summonFrame(javax.swing.JInternalFrame frame) {
        simDesktop.add(frame);
        frame.setVisible(true);
    }

    public void unselectTglRun() {
        tglRun.setSelected(false);
    }

    public void destroySimulation() {
        if(plp.sim != null) {
            if(plp.g_sim != null)
                plp.g_sim.dispose();
            plp.g_err.dispose();
            plp.ioreg.removeAllModules(plp.sim);
        }

        if(plp.g_simrun != null) {
            plp.g_simrun.stepCount = 0;
        }

        simDesktop.removeAll();
        if(plp.g_ioreg != null)
            plp.g_ioreg.dispose();
        plp.g_ioreg = null;
        tglIODisplay.setSelected(false);
        this.setVisible(false);
    }

    public javax.swing.JDesktopPane getSimDesktop() {
        return simDesktop;
    }

    public javax.swing.JTextField getTxtSteps() {
        return txtSteps;
    }

    private void reset() {
        if(plp.g_simrun != null)
            plp.g_simrun.stepCount = 0;
        plp.sim.reset();
        plp.g_sim.updateComponents();
        plp.g_err.clearError();
    }

    private void step() {
        if(plp.g_simrun != null)
            plp.g_simrun.stepCount = 0;
        plp.g_err.clearError();

        try {
            int steps = Integer.parseInt(txtSteps.getText());
            if(steps <= MAX_STEPS && steps > 0) {
                for(int i = 0; i < steps; i++)
                    plp.sim.step();
                plp.g_sim.updateComponents();
            } else {
                txtSteps.setText("1");
            }
        } catch(Exception e) {
            txtSteps.setText("1");
        }

        if(Msg.lastError != 0)
            plp.g_err.setError(Msg.lastError);
    }

    public void tileWindows() {
        javax.swing.JInternalFrame windows[] = simDesktop.getAllFrames();

        int curTallestWindow = 0;
        int curY = 0;
        int curX = 0;
        
        int W, H, X, Y;

        for(int i = 0; i < windows.length; i++) {
            if(windows[i].isVisible()) {
                W = windows[i].getWidth();
                H = windows[i].getHeight();

                if(curX + W < simDesktop.getWidth()) {
                    X = curX;
                    Y = curY;
                    curX += W;

                    if(H > curTallestWindow)
                        curTallestWindow = H;
                }
                else { // new row of windows
                    if(curY + curTallestWindow > simDesktop.getHeight())
                        curY = 0;
                    else
                        curY += curTallestWindow;

                    X = 0;
                    Y = curY;
                    curX = W;
                    curTallestWindow = H;
                }

                windows[i].setLocation(X, Y);
            }
        }

        plp.g_ioreg.refreshModulesTable();
    }

    private void restoreWindows() {
        javax.swing.JInternalFrame windows[] = simDesktop.getAllFrames();

        try {

        for(int i = 0; i < windows.length; i++) {
            windows[i].setVisible(true);
        }

        plp.g_ioreg.refreshModulesTable();

        }catch(Exception e) {}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        simControls = new javax.swing.JPanel();
        txtSteps = new javax.swing.JTextField();
        btnStep = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        tglRun = new javax.swing.JToggleButton();
        tglIODisplay = new javax.swing.JToggleButton();
        btnOpts = new javax.swing.JButton();
        simDesktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        rootmenuSim = new javax.swing.JMenu();
        menuReset = new javax.swing.JMenuItem();
        menuStep = new javax.swing.JMenuItem();
        cmenuRun = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuClose = new javax.swing.JMenuItem();
        rootmenuTools = new javax.swing.JMenu();
        menuIOReg = new javax.swing.JMenuItem();
        menuPresets = new javax.swing.JMenu();
        menuRemoveIO = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuNewWatcher = new javax.swing.JMenuItem();
        rootmenuWindow = new javax.swing.JMenu();
        menuTile = new javax.swing.JMenuItem();
        menuRestore = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(plptool.gui.PLPToolApp.class).getContext().getResourceMap(SimShell.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        simControls.setName("simControls"); // NOI18N

        txtSteps.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSteps.setText(resourceMap.getString("txtSteps.text")); // NOI18N
        txtSteps.setName("txtSteps"); // NOI18N

        btnStep.setText(resourceMap.getString("btnStep.text")); // NOI18N
        btnStep.setName("btnStep"); // NOI18N
        btnStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStepActionPerformed(evt);
            }
        });

        btnReset.setText(resourceMap.getString("btnReset.text")); // NOI18N
        btnReset.setName("btnReset"); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tglRun.setText(resourceMap.getString("tglRun.text")); // NOI18N
        tglRun.setName("tglRun"); // NOI18N
        tglRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglRunActionPerformed(evt);
            }
        });

        tglIODisplay.setText(resourceMap.getString("tglIODisplay.text")); // NOI18N
        tglIODisplay.setName("tglIODisplay"); // NOI18N
        tglIODisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglIODisplayActionPerformed(evt);
            }
        });

        btnOpts.setText(resourceMap.getString("btnOpts.text")); // NOI18N
        btnOpts.setName("btnOpts"); // NOI18N
        btnOpts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOptsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout simControlsLayout = new javax.swing.GroupLayout(simControls);
        simControls.setLayout(simControlsLayout);
        simControlsLayout.setHorizontalGroup(
            simControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStep, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tglRun, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tglIODisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOpts, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );
        simControlsLayout.setVerticalGroup(
            simControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simControlsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(simControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSteps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStep)
                    .addComponent(tglRun)
                    .addComponent(btnReset)
                    .addComponent(tglIODisplay)
                    .addComponent(btnOpts))
                .addContainerGap())
        );

        simDesktop.setBackground(resourceMap.getColor("simDesktop.background")); // NOI18N
        simDesktop.setName("simDesktop"); // NOI18N

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        rootmenuSim.setText(resourceMap.getString("rootmenuSim.text")); // NOI18N
        rootmenuSim.setName("rootmenuSim"); // NOI18N

        menuReset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuReset.setText(resourceMap.getString("menuReset.text")); // NOI18N
        menuReset.setName("menuReset"); // NOI18N
        menuReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuResetActionPerformed(evt);
            }
        });
        rootmenuSim.add(menuReset);

        menuStep.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menuStep.setText(resourceMap.getString("menuStep.text")); // NOI18N
        menuStep.setName("menuStep"); // NOI18N
        menuStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStepActionPerformed(evt);
            }
        });
        rootmenuSim.add(menuStep);

        cmenuRun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        cmenuRun.setText(resourceMap.getString("cmenuRun.text")); // NOI18N
        cmenuRun.setName("cmenuRun"); // NOI18N
        cmenuRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmenuRunActionPerformed(evt);
            }
        });
        rootmenuSim.add(cmenuRun);

        jSeparator1.setName("jSeparator1"); // NOI18N
        rootmenuSim.add(jSeparator1);

        menuClose.setText(resourceMap.getString("menuClose.text")); // NOI18N
        menuClose.setName("menuClose"); // NOI18N
        menuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCloseActionPerformed(evt);
            }
        });
        rootmenuSim.add(menuClose);

        jMenuBar1.add(rootmenuSim);

        rootmenuTools.setText(resourceMap.getString("rootmenuTools.text")); // NOI18N
        rootmenuTools.setName("rootmenuTools"); // NOI18N

        menuIOReg.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuIOReg.setText(resourceMap.getString("menuIOReg.text")); // NOI18N
        menuIOReg.setName("menuIOReg"); // NOI18N
        menuIOReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIORegActionPerformed(evt);
            }
        });
        rootmenuTools.add(menuIOReg);

        menuPresets.setText(resourceMap.getString("menuPresets.text")); // NOI18N
        menuPresets.setName("menuPresets"); // NOI18N
        rootmenuTools.add(menuPresets);

        menuRemoveIO.setText(resourceMap.getString("menuRemoveIO.text")); // NOI18N
        menuRemoveIO.setName("menuRemoveIO"); // NOI18N
        rootmenuTools.add(menuRemoveIO);

        jSeparator2.setName("jSeparator2"); // NOI18N
        rootmenuTools.add(jSeparator2);

        menuNewWatcher.setText(resourceMap.getString("menuNewWatcher.text")); // NOI18N
        menuNewWatcher.setName("menuNewWatcher"); // NOI18N
        rootmenuTools.add(menuNewWatcher);

        jMenuBar1.add(rootmenuTools);

        rootmenuWindow.setText(resourceMap.getString("rootmenuWindow.text")); // NOI18N
        rootmenuWindow.setName("rootmenuWindow"); // NOI18N

        menuTile.setText(resourceMap.getString("menuTile.text")); // NOI18N
        menuTile.setName("menuTile"); // NOI18N
        menuTile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTileActionPerformed(evt);
            }
        });
        rootmenuWindow.add(menuTile);

        menuRestore.setText(resourceMap.getString("menuRestore.text")); // NOI18N
        menuRestore.setName("menuRestore"); // NOI18N
        menuRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRestoreActionPerformed(evt);
            }
        });
        rootmenuWindow.add(menuRestore);

        jMenuBar1.add(rootmenuWindow);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(simControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(simDesktop, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(simDesktop, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStepActionPerformed
        step();
    }//GEN-LAST:event_btnStepActionPerformed

    private void tglRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglRunActionPerformed
        if(tglRun.isSelected()) {
            plp.g_simrun = new SimRunner(plp);
            plp.g_simrun.start();
            cmenuRun.setSelected(true);
        } else {
            if(plp.g_simrun != null) {
                try {
                    plp.g_simrun.stepCount = 0;
                } catch(Exception e) {}
            }
            cmenuRun.setSelected(false);
        }
    }//GEN-LAST:event_tglRunActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset();

    }//GEN-LAST:event_btnResetActionPerformed

    private void tglIODisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglIODisplayActionPerformed
        if(tglIODisplay.isSelected()) {
            if(plp.g_ioreg == null) {
                plp.g_ioreg = new IORegistryFrame(plp);
                simDesktop.add(plp.g_ioreg);
            }
            plp.g_ioreg.setVisible(true);
        } else
            plp.g_ioreg.setVisible(false);
    }//GEN-LAST:event_tglIODisplayActionPerformed

    private void btnOptsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOptsActionPerformed
        if(opts == null)
            opts = new OptionsFrame();
        opts.setVisible(true);
    }//GEN-LAST:event_btnOptsActionPerformed

    private void cmenuRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmenuRunActionPerformed
        if(cmenuRun.isSelected()) {
            plp.g_simrun = new SimRunner(plp);
            plp.g_simrun.start();
            tglRun.setSelected(true);
        } else {
            if(plp.g_simrun != null) {
                try {
                    plp.g_simrun.stepCount = 0;
                } catch(Exception e) {}
            }
            tglRun.setSelected(false);
        }
    }//GEN-LAST:event_cmenuRunActionPerformed

    private void menuResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuResetActionPerformed
        reset();
    }//GEN-LAST:event_menuResetActionPerformed

    private void menuStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStepActionPerformed
        step();
    }//GEN-LAST:event_menuStepActionPerformed

    private void menuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCloseActionPerformed
        destroySimulation();
    }//GEN-LAST:event_menuCloseActionPerformed

    private void menuTileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTileActionPerformed
        tileWindows();
    }//GEN-LAST:event_menuTileActionPerformed

    private void menuRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRestoreActionPerformed
        restoreWindows();
    }//GEN-LAST:event_menuRestoreActionPerformed

    private void menuIORegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIORegActionPerformed
        if(plp.g_ioreg == null) {
            plp.g_ioreg = new IORegistryFrame(plp);
            simDesktop.add(plp.g_ioreg);
        }
        tglIODisplay.setSelected(true);
        plp.g_ioreg.setVisible(true);
    }//GEN-LAST:event_menuIORegActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpts;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStep;
    private javax.swing.JCheckBoxMenuItem cmenuRun;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem menuClose;
    private javax.swing.JMenuItem menuIOReg;
    private javax.swing.JMenuItem menuNewWatcher;
    private javax.swing.JMenu menuPresets;
    private javax.swing.JMenu menuRemoveIO;
    private javax.swing.JMenuItem menuReset;
    private javax.swing.JMenuItem menuRestore;
    private javax.swing.JMenuItem menuStep;
    private javax.swing.JMenuItem menuTile;
    private javax.swing.JMenu rootmenuSim;
    private javax.swing.JMenu rootmenuTools;
    private javax.swing.JMenu rootmenuWindow;
    private javax.swing.JPanel simControls;
    private javax.swing.JDesktopPane simDesktop;
    private javax.swing.JToggleButton tglIODisplay;
    private javax.swing.JToggleButton tglRun;
    private javax.swing.JTextField txtSteps;
    // End of variables declaration//GEN-END:variables

}
