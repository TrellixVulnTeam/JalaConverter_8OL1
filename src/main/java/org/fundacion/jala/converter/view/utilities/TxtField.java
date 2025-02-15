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
package org.fundacion.jala.converter.view.utilities;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

/**
 * This class defines the JTextField style for the project.
 */
public class TxtField extends JTextField {
    private final int FONT_SIZE = 12;
    private final int MARGIN_SPACE = 5;
    private final int MARGIN_SPACE_HEIGHT = 2;
    private final Font DEFAULT_FONT = new Font("Barlow", Font.PLAIN, FONT_SIZE);

    public TxtField(final int width, final int height, final boolean enabled) {
        setFont(DEFAULT_FONT);
        setEnabled(enabled);
        setPreferredSize(new Dimension(width, height));
        setBorder(new EmptyBorder(MARGIN_SPACE_HEIGHT, MARGIN_SPACE, MARGIN_SPACE_HEIGHT, 0));
        setForeground(Color.DARK_GRAY);
    }

    public TxtField(final int columns, final boolean enabled) {
        setFont(DEFAULT_FONT);
        setEnabled(enabled);
        setColumns(columns);
        setBorder(new EmptyBorder(MARGIN_SPACE_HEIGHT, MARGIN_SPACE, MARGIN_SPACE_HEIGHT, 0));
        setForeground(Color.DARK_GRAY);
    }
}
