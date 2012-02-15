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

package lecturepublisher;

import plptool.gui.ProjectDriver;

/**
 *
 * @author wira
 */
public class Controls extends javax.swing.JFrame {

    private ProjectDriver plp;
    private PLPToolConnector connector;
    private ProgressUpdater progressUpdater;

    /** Creates new form Controls */
    public Controls(ProjectDriver plp, PLPToolConnector connector) {
        this.plp = plp;
        this.connector = connector;
        initComponents();
    }

    public void setRecordState(boolean b) {
        radioRecordWithAudio.setEnabled(!b);
        radioRecordWithoutAudio.setEnabled(!b);
        radioPlayAndOverlay.setEnabled(!b);
        tabbedPane.setEnabledAt(1, !b);
        tabbedPane.setEnabledAt(2, !b);
        tglBtnRecord.setSelected(b);
    }

    public void setPlaybackState(boolean b) {
        tabbedPane.setEnabledAt(0, !b);
        tabbedPane.setEnabledAt(2, !b);
        tglBtnPlayPause.setSelected(b);
    }

    public void updateComponents() {
        long t = connector.getLectureLength();
        if(t > 0) {
            txtStatus.setText("Lecture length: " + t + "ms");
            sliderProgress.setMaximum((int) t);
            sliderProgress.setMinimum(0);
            progressBar.setMaximum((int) t);
            progressBar.setMinimum(0);
        }
    }

    public void externalStop() {
        if(progressUpdater != null) {
            progressUpdater.stopUpdate();
        }
        sliderProgress.setValue(0);
        progressBar.setValue(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupRecord = new javax.swing.ButtonGroup();
        tabbedPane = new javax.swing.JTabbedPane();
        panelRecord = new javax.swing.JPanel();
        radioRecordWithAudio = new javax.swing.JRadioButton();
        radioPlayAndOverlay = new javax.swing.JRadioButton();
        radioRecordWithoutAudio = new javax.swing.JRadioButton();
        tglBtnRecord = new javax.swing.JToggleButton();
        lblLecturePublisher = new javax.swing.JLabel();
        panelPlayback = new javax.swing.JPanel();
        sliderProgress = new javax.swing.JSlider();
        tglBtnPlayPause = new javax.swing.JToggleButton();
        btnStop = new javax.swing.JButton();
        panelEmbedVideo = new javax.swing.JPanel();
        lblVideoInfo1 = new javax.swing.JLabel();
        lblVideoInfo2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        txtStatus = new javax.swing.JTextField();

        setTitle("Lecture Controls");
        setResizable(false);

        btnGroupRecord.add(radioRecordWithAudio);
        radioRecordWithAudio.setSelected(true);
        radioRecordWithAudio.setText("Record with audio (overwrite)");
        radioRecordWithAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioRecordWithAudioActionPerformed(evt);
            }
        });

        btnGroupRecord.add(radioPlayAndOverlay);
        radioPlayAndOverlay.setText("Play and overlay audio only");

        btnGroupRecord.add(radioRecordWithoutAudio);
        radioRecordWithoutAudio.setText("Record without audio");

        tglBtnRecord.setFont(new java.awt.Font("Tahoma", 1, 12));
        tglBtnRecord.setText("RECORD LECTURE");
        tglBtnRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglBtnRecordActionPerformed(evt);
            }
        });

        lblLecturePublisher.setFont(new java.awt.Font("Tahoma", 3, 11));
        lblLecturePublisher.setText("Lecture Publisher");

        javax.swing.GroupLayout panelRecordLayout = new javax.swing.GroupLayout(panelRecord);
        panelRecord.setLayout(panelRecordLayout);
        panelRecordLayout.setHorizontalGroup(
            panelRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioPlayAndOverlay)
                    .addComponent(radioRecordWithAudio)
                    .addComponent(radioRecordWithoutAudio)
                    .addComponent(lblLecturePublisher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(tglBtnRecord)
                .addContainerGap())
        );
        panelRecordLayout.setVerticalGroup(
            panelRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglBtnRecord, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addGroup(panelRecordLayout.createSequentialGroup()
                        .addComponent(radioRecordWithAudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioPlayAndOverlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioRecordWithoutAudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(lblLecturePublisher)))
                .addContainerGap())
        );

        tabbedPane.addTab("Record", panelRecord);

        tglBtnPlayPause.setText("Play");
        tglBtnPlayPause.setMaximumSize(new java.awt.Dimension(120, 23));
        tglBtnPlayPause.setMinimumSize(new java.awt.Dimension(120, 23));
        tglBtnPlayPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglBtnPlayPauseActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPlaybackLayout = new javax.swing.GroupLayout(panelPlayback);
        panelPlayback.setLayout(panelPlaybackLayout);
        panelPlaybackLayout.setHorizontalGroup(
            panelPlaybackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlaybackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPlaybackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPlaybackLayout.createSequentialGroup()
                        .addComponent(tglBtnPlayPause, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sliderProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPlaybackLayout.setVerticalGroup(
            panelPlaybackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlaybackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sliderProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPlaybackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tglBtnPlayPause, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Playback", panelPlayback);

        lblVideoInfo1.setText("PLP Lecture Publisher supports embedding of Theora");

        lblVideoInfo2.setText("videos into the project file.");

        jButton1.setText("Browse for Video File");

        jButton2.setText("Embed Video into Project");

        javax.swing.GroupLayout panelEmbedVideoLayout = new javax.swing.GroupLayout(panelEmbedVideo);
        panelEmbedVideo.setLayout(panelEmbedVideoLayout);
        panelEmbedVideoLayout.setHorizontalGroup(
            panelEmbedVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmbedVideoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmbedVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .addComponent(lblVideoInfo1)
                    .addComponent(lblVideoInfo2)
                    .addGroup(panelEmbedVideoLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        panelEmbedVideoLayout.setVerticalGroup(
            panelEmbedVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmbedVideoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVideoInfo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVideoInfo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmbedVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Embed Video", panelEmbedVideo);

        txtStatus.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioRecordWithAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioRecordWithAudioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioRecordWithAudioActionPerformed

    private void tglBtnRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglBtnRecordActionPerformed
        if(tglBtnRecord.isSelected()) {
            if(radioRecordWithAudio.isSelected()) {
                connector.setRecordPlaybackParams(true, false);
                connector.hook("record");
            } else if(radioRecordWithoutAudio.isSelected()) {
                connector.setRecordPlaybackParams(false, false);
                connector.hook("record");
            } else if(radioPlayAndOverlay.isSelected()) {
                connector.setRecordPlaybackParams(false, true);
                updateComponents();
                progressUpdater = new ProgressUpdater(sliderProgress, progressBar);
                progressUpdater.start();
                connector.hook("replay");
            }
            setRecordState(true);
        } else {
            setRecordState(false);
            connector.hook("stop");
        }
    }//GEN-LAST:event_tglBtnRecordActionPerformed

    private void tglBtnPlayPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglBtnPlayPauseActionPerformed
        if(tglBtnPlayPause.isSelected()) {
            if(connector.getLectureLength() > 0) {
                connector.setRecordPlaybackParams(true, false);
                connector.hook("replay");
                updateComponents();
                progressUpdater = new ProgressUpdater(sliderProgress, progressBar);
                progressUpdater.start();
                setPlaybackState(true);
            } else {
                tglBtnPlayPause.setSelected(false);
            }
        } else {
            connector.hook("pause");
            progressUpdater.stopUpdate();
            setPlaybackState(false);
        }
    }//GEN-LAST:event_tglBtnPlayPauseActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        connector.hook("stop");
        setPlaybackState(false);
    }//GEN-LAST:event_btnStopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupRecord;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblLecturePublisher;
    private javax.swing.JLabel lblVideoInfo1;
    private javax.swing.JLabel lblVideoInfo2;
    private javax.swing.JPanel panelEmbedVideo;
    private javax.swing.JPanel panelPlayback;
    private javax.swing.JPanel panelRecord;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JRadioButton radioPlayAndOverlay;
    private javax.swing.JRadioButton radioRecordWithAudio;
    private javax.swing.JRadioButton radioRecordWithoutAudio;
    private javax.swing.JSlider sliderProgress;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JToggleButton tglBtnPlayPause;
    private javax.swing.JToggleButton tglBtnRecord;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables

    class ProgressUpdater extends Thread {
        private int startTime;
        private javax.swing.JSlider slider;
        private javax.swing.JProgressBar progress;
        private boolean stop;

        public ProgressUpdater(javax.swing.JSlider slider,
                javax.swing.JProgressBar progress) {
            this.slider = slider;
            this.progress = progress;
            stop = false;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch(Exception e) {}
            startTime = (int) ( System.currentTimeMillis() % (long) Math.pow(2, 32) );
            int sys_t = (int) ( System.currentTimeMillis() % (long) Math.pow(2, 32) );
            while(!stop && (sys_t-startTime) < progress.getMaximum()) {
                sys_t = (int) ( System.currentTimeMillis() % (long) Math.pow(2, 32) );
                slider.setValue((int) (sys_t-startTime));
                progress.setValue((int) (sys_t-startTime));
                try {
                    Thread.sleep(50);
                } catch(Exception e) {}
            }
        }

        public void stopUpdate() {
            stop = true;
        }
    }

}
