/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Edson Añawaya Rios
 */
package org.fundacion.jala.converter.view.utilities;

import javax.swing.JOptionPane;

/**
 * This class verify si is selected a file.
 */
public class CheckFile {

    /**
     * Verify that a file is selected
     *
     * @param file Path String
     * @return a boolean with the confirmation
     */
    public static boolean checkFileSelect(final String file) {
        if (file != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "You need select a file", "Error Message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
