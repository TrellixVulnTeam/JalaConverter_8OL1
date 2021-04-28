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
package org.fundacion.jala.converter.response;

/**
 * This class creates the application's responses.
 */
public class PaoPaoResponse {
    private String status;

    public PaoPaoResponse(String initialStatus) {
        this.status = initialStatus;
    }

    /**
     * Gets the response status.
     *
     * @return a String with the current status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the response status.
     *
     * @param newStatus is a String to change the status.
     */
    public void setStatus(String newStatus) {
        this.status = status;
    }
}
