/*
    Copyright 2012 David Fritz, Brian Gordon, Wira Mulia

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

package plptool.hc11;

import plptool.*;
import plptool.gui.ProjectDriver;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Wira
 */
public class Buffalo extends javax.swing.JFrame {
    private ProjectDriver plp;

    private CommPort commPort;
    private CommPortIdentifier portIdentifier;
    protected InputStream in;
    private OutputStream out;
    private SerialPort port;
    private boolean streamReaderRunning;
    private String lastCommand;

    protected boolean stop;

    /** Creates new form Buffalo */
    public Buffalo(ProjectDriver plp) {
        initComponents();
        this.plp = plp;

        cmbPort.removeAllItems();
        cmbBaud.removeAllItems();
        
        if(PLPToolbox.getOS(false) == Constants.PLP_OS_LINUX_32 ||
           PLPToolbox.getOS(false) == Constants.PLP_OS_LINUX_64) {
            cmbPort.addItem("/dev/ttyUSB0");
            cmbPort.addItem("/dev/ttyUSB1");
            cmbPort.addItem("/dev/ttyS0");
            cmbPort.addItem("/dev/ttyS1");
        }
        if(PLPToolbox.getOS(false) == Constants.PLP_OS_WIN_32 ||
           PLPToolbox.getOS(false) == Constants.PLP_OS_WIN_64) {
            cmbPort.addItem("COM1");
            cmbPort.addItem("COM2");
            cmbPort.addItem("COM3");
            cmbPort.addItem("COM4");
        }
        else
            cmbPort.addItem("Specify your serial port here.");

        cmbBaud.addItem(9600);
        cmbBaud.addItem(57600);
        cmbBaud.addItem(115200);
        cmbBaud.setSelectedIndex(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbPort = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbBaud = new javax.swing.JComboBox();
        btnOpen = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        btnProgram = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnCopy = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PLP HC11 BUFFALO Interface");

        jLabel1.setText("Port : ");

        cmbPort.setEditable(true);
        cmbPort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Baud rate : ");

        cmbBaud.setEditable(true);
        cmbBaud.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnOpen.setFont(new java.awt.Font("Dialog", 1, 12));
        btnOpen.setText("OPEN");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        txtConsole.setBackground(new java.awt.Color(0, 0, 0));
        txtConsole.setColumns(20);
        txtConsole.setEditable(false);
        txtConsole.setForeground(new java.awt.Color(0, 255, 0));
        txtConsole.setRows(5);
        txtConsole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConsoleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtConsole);

        btnProgram.setText("PROGRAM");
        btnProgram.setEnabled(false);
        btnProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgramActionPerformed(evt);
            }
        });

        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnCopy.setText("COPY");
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });

        jButton1.setText("IMPORT S19");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPort, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBaud, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addComponent(btnOpen))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnProgram)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                        .addComponent(btnCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbBaud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProgram)
                    .addComponent(btnClear)
                    .addComponent(btnCopy)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtConsoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsoleKeyPressed
        char data = evt.getKeyChar();
        int code = evt.getKeyCode();

        try {
            if(code == java.awt.event.KeyEvent.VK_CONTROL) {

            } else if(code == java.awt.event.KeyEvent.VK_ALT) {

            } else if(code == java.awt.event.KeyEvent.VK_SHIFT) {

            } else if(code == java.awt.event.KeyEvent.VK_META) {

            } else if(code == java.awt.event.KeyEvent.VK_ENTER) {
                out.write(0xD);
            } else {
                out.write(data);
            }

        } catch(Exception e) {
            try {
                PLPToolbox.showErrorDialog(this, "Send failed.");
            } catch(Exception eb) {

            }
        }
    }//GEN-LAST:event_txtConsoleKeyPressed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        if(btnOpen.isSelected()) {
            if(openPort() != 0) {
                closePort();
                btnOpen.setSelected(false);
            }
        }  else
            closePort();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgramActionPerformed
        try {
            if(plp.isAssembled() && plp.asm != null) {
                program(((Asm)plp.asm).generateS19());
            } else
                Msg.E("Program has to be assembled first", Constants.PLP_PRG_SOURCES_NOT_ASSEMBLED, null);
        } catch(java.io.IOException e) {
            Msg.E("Programming failed", Constants.PLP_PRG_SERIAL_TRANSMISSION_ERROR, null);
        }
    }//GEN-LAST:event_btnProgramActionPerformed

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        if(txtConsole.getSelectedText() == null || txtConsole.getSelectedText().equals(""))
            PLPToolbox.copy(txtConsole.getText());
        else
            PLPToolbox.copy(txtConsole.getSelectedText());
    }//GEN-LAST:event_btnCopyActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtConsole.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            java.io.File f = PLPToolbox.openFileDialog(Constants.launchPath, null);
            if(f != null) {
                program(PLPToolbox.readFileAsString(f.getAbsolutePath()));
            }
        } catch(Exception e) {
            Msg.E("Programming failed", Constants.PLP_PRG_SERIAL_TRANSMISSION_ERROR, null);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void program(String S19) throws java.io.IOException {
        String lines[];
        out.write("load t".getBytes());
        out.write(0xd);
        lines = S19.split("\\r?\\n");
        for(int i = 0; i < lines.length; i++) {
            out.write(lines[i].getBytes());
            out.write(0xd);
        }           
    }

    private void appendString(String str) {
        txtConsole.append(str);
        txtConsole.setCaretPosition(txtConsole.getText().length() - 1);
    }

    public int openPort() {
        try {
            String portName = (String) cmbPort.getSelectedItem();
            Msg.M("Opening port " + portName + ".");
            int baudRate = (Integer) cmbBaud.getSelectedItem();
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            if (portIdentifier.isCurrentlyOwned()) {
                Msg.M("Serial port " + portName + " is in use.");
                return -1;
            } else {
                commPort = portIdentifier.open(this.getClass().getName(), 2000);

                if (commPort instanceof SerialPort) {
                    port = (SerialPort) commPort;
                    port.setSerialPortParams(baudRate, SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);

                    port.enableReceiveTimeout(1000);
                    in = port.getInputStream();
                    out = port.getOutputStream();

                    cmbBaud.setEnabled(false);
                    cmbPort.setEnabled(false);
                    btnProgram.setEnabled(true);
                    txtConsole.setEnabled(true);
                    stop = false;

                    (new SerialStreamReader()).start();
                    Msg.M("Connected.");
                } else {
                    Msg.M(portName + " is not a serial port.");
                    return -1;
                }
            }
        } catch (Exception e) {
            Msg.M("Error opening port.");
            System.err.println(e);
            return -1;
        }

        return 0;
    }

    public int closePort() {
        try {

        stop = true;

        in.close();
        out.close();
        port.close();
        commPort.close();

        cmbBaud.setEnabled(true);
        cmbPort.setEnabled(true);
        btnProgram.setEnabled(false);
        txtConsole.setEnabled(false);

        Msg.M("Port closed.");

        } catch(Exception e) {
            Msg.M("Error closing port.");
        }

        return 0;
    }

    public void terminate() {
        closePort();
    }

    class SerialStreamReader extends Thread {
        int bytes;

        @Override
        public void run() {
            final byte[] buffer = new byte[Config.serialTerminalBufferSize];
            byte c;
            int i;

            try {
                if(streamReaderRunning) {
                   Msg.M("Another stream reader thread is already running.");
                   return;
                }
                Msg.M("Stream reader is running.");

                streamReaderRunning = true;

                while(!stop) {
                    bytes = in.read(buffer);
                    Msg.D("term: " + bytes + " bytes read.", 6, this);
                    if(bytes > 0)
                        try {
                            for(i = 0; i < bytes; i++) {
                                c = buffer[i];
                                if(c > 127 || c < 9 || (c < 32 && c > 13) || c == 11 || c == 12)
                                    buffer[i] = ' ';
                            }

                            appendString(new String(buffer, 0, bytes, "US-ASCII"));
                        } catch(Exception e) {

                        }
                    Thread.sleep(Config.serialTerminalReadDelayMs);
                }

                streamReaderRunning = false;
                Msg.M("Stream reader is stopped.");

            } catch(Exception e) {
                streamReaderRunning = false;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCopy;
    private javax.swing.JToggleButton btnOpen;
    private javax.swing.JButton btnProgram;
    private javax.swing.JComboBox cmbBaud;
    private javax.swing.JComboBox cmbPort;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables

}
