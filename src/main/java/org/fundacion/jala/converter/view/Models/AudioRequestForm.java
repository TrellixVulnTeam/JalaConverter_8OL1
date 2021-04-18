/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Saul Caspa Miranda
 * @version 1.0
 */
package org.fundacion.jala.converter.view.Models;

import java.util.ArrayList;
import java.util.List;

public class AudioRequestForm implements IrequestForm {
    private List<Parameter> bodyParameters = new ArrayList<>();
    private static final String URL = "http://localhost:8080/api/convertAudio";
    private static final String FILE = "file";
    private static final String FORMAT = "format";
    private static final String BITRATE = "bitrate";
    private static final String VOLUME = "volume";
    private static final String HZ = "hz";
    private static final String AUDIOCHANNEL = "audiochannel";
    private static final String METADATA = "metadata";

    private String filepathValue;
    private String formatValue;
    private String bitrateValue;
    private String volumeValue;

    /**
     * Audio Request Form stores parameters for an audio request
     */
    public AudioRequestForm() {
    }

    /**
     * Adds filepath parameter
     * @param filepathValue
     */
    public void addFilepath(final String filepathValue) {
        addParameters(new Parameter(FILE, filepathValue, true));
    }

    /**
     * Adds format parameter
     * @param formatValue
     */
    public void addFormat(final String formatValue) {
        addParameters(new Parameter(FORMAT, formatValue, false));
    }

    /**
     * Adds bitrate parameter
     * @param bitrateValue
     */
    public void addBitrate(final String bitrateValue) {
        addParameters(new Parameter(BITRATE, bitrateValue, false));
    }

    /**
     * Adds volume parameter
     * @param volumeValue
     */
    public void addVolume(final String volumeValue) {
        addParameters(new Parameter(VOLUME, volumeValue, false));
    }

    /**
     * Adds hz parameter
     * @param hzValue
     */
    public void addHz(final String hzValue) {
        addParameters(new Parameter(HZ, hzValue, false));
    }

    /**
     * Adds audiochannel parameter
     * @param metadataValue
     */
    public void addAudiochannel(final String metadataValue) {
        addParameters(new Parameter(AUDIOCHANNEL, metadataValue, false));
    }
    /**
     * Adds metadata parameter
     * @param metadataValue
     */
    public void addMetadata(final String metadataValue) {
        addParameters(new Parameter(METADATA, metadataValue, false));
    }


    /**
     * @return bodyParameters
     */
    @Override
    public List<Parameter> getBodyParameters() {
        return bodyParameters;
    }

    /**
     * Adds parameters to bodyParameters
     * @param parameter
     */
    @Override
    public void addParameters(final Parameter parameter) {
        bodyParameters.add(parameter);
    }

    /**
     * @return URL
     */
    @Override
    public String getURL() {
        return URL;
    }
}
