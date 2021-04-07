/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */

package org.fundacion.jala.converter.view;


import org.fundacion.jala.converter.view.converter.AudioConverterInterface;
import org.fundacion.jala.converter.view.utilities.JLabelStyle;
import org.fundacion.jala.converter.view.compiler.CompilerInterface;
import org.fundacion.jala.converter.view.converter.VideoConverterInterface;
import org.fundacion.jala.converter.view.metadata.MetaDataInterface;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;

public class MainInterface extends JFrame{
    private ImageIcon compilerIcon;
    private ImageIcon converterIcon;
    private CompilerInterface compilerJP;
    private JPanel converterJP;
    private JLabelStyle titleTxt;
    private VideoConverterInterface video;
    private MetaDataInterface metaData;
    private AudioConverterInterface audio;

    /**
     * Initialize all the graphics components in the
     * main Frame
     */
    public void initMainInterface() {
        compilerJP = new CompilerInterface();
        video = new VideoConverterInterface();
        converterJP = new JPanel();
        compilerIcon = new ImageIcon("src/Images/IconCompiler-02.png");
        converterIcon = new ImageIcon("src/Images/IconConverter-03.png");
        titleTxt = new JLabelStyle("Jala Converter Project v1.0", "h2", 0, 0, 90);
        metaData =  new MetaDataInterface();
        audio = new AudioConverterInterface();

        JTabbedPane mainTabPanel = new JTabbedPane();
        mainTabPanel.add("Compiler", compilerJP);
        mainTabPanel.add("Video Converter", video);
        //mainTabPanel.add("Audio Converter", audio);
        mainTabPanel.add("Meta Data", metaData);
        mainTabPanel.setIconAt(0, compilerIcon);
        mainTabPanel.setIconAt(1, converterIcon);
        mainTabPanel.setIconAt(2, converterIcon);
        mainTabPanel.setIconAt(3, converterIcon);
        mainTabPanel.setBackgroundAt(0, Color.DARK_GRAY);
        mainTabPanel.setBackgroundAt(1, Color.DARK_GRAY);
        mainTabPanel.setBackgroundAt(2, Color.DARK_GRAY);
        mainTabPanel.setBackgroundAt(3, Color.DARK_GRAY);

        setLayout(new BorderLayout());
        add(titleTxt.getTextLabel(), BorderLayout.NORTH);
        add(mainTabPanel, BorderLayout.CENTER);

        setSize(700, 800);
        setTitle("Prog102 - Final Project");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
