/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @colaborathor Cristian Choque Quispe
 */

package org.fundacion.jala.converter.view.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacion.jala.converter.view.controllers.ClientRequest;
import org.fundacion.jala.converter.view.Models.AudioRequestForm;
import org.fundacion.jala.converter.view.utilities.JLabelStyle;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import static org.fundacion.jala.converter.service.ChecksumService.getFileChecksum;

public class AudioConverterInterface extends JPanel implements ActionListener {
    private SelectFile file;
    private ConvertTypeSelectAudio audioSelect;
    private QualityAudio quality;
    private OutputSettingsAudio settings;
    private ClientRequest clientRequest = new ClientRequest();
    private static final Logger LOGGER = LogManager.getLogger();
    private final int alignLabelStyle = 2;
    private final int widthLabelStyle = 70;
    private final int heightLabelStyle = 30;
    private final int topBorder = 40;
    private final int leftBorder = 40;
    private final int bottomBorder = 100;
    private final int rightBorder = 0;
    private final int fontStyle = 0;
    private final int fontSize = 12;

    /**
     * Initializes the graphics elements for Audio converter interface.
     */
    public AudioConverterInterface() {
        JLabelStyle audioTitle = new JLabelStyle("Audio converter", "h1",
                alignLabelStyle, widthLabelStyle, heightLabelStyle);
        JLabelStyle audioSettings = new JLabelStyle("Audio settings", "h1",
                alignLabelStyle, widthLabelStyle, heightLabelStyle);
        audioTitle.setAlignmentX(LEFT_ALIGNMENT);
        audioSettings.setAlignmentX(LEFT_ALIGNMENT);
        JButton convertAudio = new JButton("Convert");
        convertAudio.setAlignmentX(LEFT_ALIGNMENT);
        convertAudio.setFont(new Font("Barlow", fontStyle, fontSize));
        convertAudio.addActionListener(this::actionPerformed);
        file = new SelectFile();
        file.setAlignmentX(LEFT_ALIGNMENT);
        audioSelect = new ConvertTypeSelectAudio();
        audioSelect.setAlignmentX(LEFT_ALIGNMENT);
        quality = new QualityAudio();
        quality.setAlignmentX(LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(topBorder, leftBorder, bottomBorder, rightBorder));
        settings = new OutputSettingsAudio();
        settings.setAlignmentX(LEFT_ALIGNMENT);
        add(audioTitle.getTextLabel());
        add(file);
        add(audioSettings.getTextLabel());
        add(audioSelect);
        add(quality);
        add(settings);
        add(convertAudio);
    }

    /**
     * Converts, sends information for metadataClass conversion.
     * Shows a Dialog with the information.
     * @param e event of the JButton.
     */
    @Override
    public void actionPerformed(final ActionEvent e)  {
        LOGGER.info("start");
        try {
            LOGGER.info("Execute Try");
            JOptionPane.showMessageDialog(this, "File Path: "
                    + file.getOriginFilePath()
                    + "\nConvert to: "
                    + audioSelect.getConvertTo()
                    + "\nQuality: "
                    + quality.getQualityAudio()
                    + "\nVolume: "
                    + settings.getVolume()
                    + "\nAudio Channel: "
                    + settings.getAudioChannel()
                    + "\nHz: "
                    + settings.getHz()
                    + "\nwith metadata: "
                    + settings.isMetadata()
                    + "\nChecksum: "
                    + getFileChecksum(file.getOriginFilePath()));
            callRequest();
            LOGGER.info("finish");
        } catch (IOException ioException) {
            ioException.printStackTrace();
            LOGGER.error("Execute Exception");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
            LOGGER.error("Execute Exception");
        }
        LOGGER.info("Finish");
    }
    /**
     * Obtains the request
     * @throws IOException
     */
    private void callRequest() throws IOException {
        LOGGER.info("start");

        try {
            LOGGER.info("Execute Try");
            String[] s = quality.getQualityAudio().split(" ");
            String bitrate = s[0];
            AudioRequestForm audioRequestForm = new AudioRequestForm();
            audioRequestForm.addFilepath(file.getOriginFilePath());
            audioRequestForm.addFormat(audioSelect.getConvertTo());
            audioRequestForm.addBitrate(bitrate);
            audioRequestForm.addVolume(settings.getVolume());
            audioRequestForm.addHz(settings.getHz());
            audioRequestForm.addAudiochannel(settings.getAudioChannel());
            audioRequestForm.addChecksum(getFileChecksum(file.getOriginFilePath()));
            audioRequestForm.addMetadata(String.valueOf(settings.isMetadata()));
            clientRequest.executeRequest(audioRequestForm);

            String result = clientRequest.executeRequest(audioRequestForm);
            System.out.println(result);
            JOptionPane.showMessageDialog(this, "Download Link:\n" + result);
            LOGGER.info("finish");
        } catch (IOException | NoSuchAlgorithmException ioException) {
            ioException.printStackTrace();
            LOGGER.error("Execute Exception");
        }
        LOGGER.info("Finish");
    }
}
