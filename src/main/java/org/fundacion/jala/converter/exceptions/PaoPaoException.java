/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Jessicka Moya Andrade
 */
package org.fundacion.jala.converter.exceptions;

public class PaoPaoException extends Exception{

    public PaoPaoException() {
        super();
    }

    public PaoPaoException(String message) {
        super(message);
    }

    public PaoPaoException(Throwable exception) {
        super(exception);
    }

    public PaoPaoException(String message, Throwable exception) {
        super(message, exception);
    }

}
