/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Paola Ximena Aguilar Quiñones
 */
package org.fundacion.jala.converter.view.compiler;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class customizes a panel with custom buttons to compile.
 */
class CompilerButtons extends JPanel implements ActionListener {
    private JButton runCode;
    private JButton clearConsole;
    private JButton saveFiles;
    private final int DIMENSION_WIDTH = 80;
    private final int DIMENSION_HEIGHT = 20;
    private final int FONT_STYLE = 0;
    private final int FONT_SIZE = 11;
    private final int HGAP = 15;
    private final int VGAP = 15;

    protected CompilerButtons() {
        runCode = makeButton("Run");
        clearConsole = makeButton("Clear");
        saveFiles = makeButton("Save");
        setLayout(new FlowLayout(FlowLayout.RIGHT, HGAP, VGAP));
        add(saveFiles);
        add(runCode);
        add(clearConsole);
    }

    /**
     * Selects a file (Action of JButton).
     *
     * @param e event of the JButton.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
    }

    /**
     * Gets runCode to manipulate.
     *
     * @return a JButton to run the compiler.
     */
    public JButton getRunButton() {
        return runCode;
    }

    /**
     * Makes a custom button.
     *
     * @param text represents title that the button has.
     * @return a JButton to run the compiler.
     */
    public JButton makeButton(final String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        button.setFont(new Font("Barlow", FONT_STYLE, FONT_SIZE));
        return button;
    }

    /**
     * Gets selectFile button to manipulate it.
     *
     * @return a JButton to save files.
     */
    public JButton getSaveFiles() {
        return saveFiles;
    }

    /**
     * Gets clearConsole button to manipulate it.
     *
     * @return a JButton to clean text zone code.
     */
    public JButton getClearConsole() {
        return clearConsole;
    }
}

