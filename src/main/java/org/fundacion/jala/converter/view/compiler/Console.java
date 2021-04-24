/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Paola Aguilar Quiñones
 */
package org.fundacion.jala.converter.view.compiler;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

/**
 * This class customizes a panel as a console.
 */
class Console extends JPanel {
    private JTextArea console;
    private final int WIDTH_C = 600;
    private final int HEIGHT_C = 200;

    protected Console() {
        console = new JTextArea();
        console.setBackground(Color.black);
        console.setForeground(Color.white);
        console.setEditable(false);
        console.setPreferredSize(new Dimension(WIDTH_C, HEIGHT_C));
        console.setBorder(new EmptyBorder(0, 0, 0, 0));
        setLayout(new BorderLayout());
        add(console, BorderLayout.CENTER);
    }

    /**
     * Gets access to JTextArea
     *
     * @return a JTextArea
     */
    public JTextArea getConsole() {
        return console;
    }
}

