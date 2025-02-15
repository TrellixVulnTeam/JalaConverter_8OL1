/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Saul Caspa Miranda
 */
package org.fundacion.jala.converter.core;

import java.io.InputStream;

/**
 * This class runs an input stream in a single thread.
 */
class ThreadHandler extends Thread {
    private InputStream input;

    ThreadHandler(final InputStream newInput, final String name) {
        super(name);
        this.input = newInput;
    }

    /**
     * Executes the thread.
     */
    public void run() {
        try {
            int c;
            while ((c = input.read()) != -1) {
                System.out.write(c);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
