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
package org.fundacion.jala.converter.core;

import org.fundacion.jala.converter.core.parameter.AudioParameter;
import org.fundacion.jala.converter.core.results.Result;

/**
 * This class handles the requests to convert an audio with the given operations from an audio parameters form.
 */
public final class AudioConverter {
    private AudioParameter parameter;
    private String outputFileName = "";
    private RunCommand runCommand = new RunCommand();
    private Result result;

    public AudioConverter(final AudioParameter audioParameter) {
        this.parameter = audioParameter;
    }

    /**
     * Creates a command for audio converter.
     */
    public void audioConverter() {
        String relativePath = "cd archive/ && ";
        String ffmpeg = "ffmpeg -i ";
        String bitrate = parameter.formatBitrate();
        String hz = parameter.formatHz();
        String volume = parameter.formatVolume();
        String audioChannel = parameter.formatAudioChannel();
        String input = parameter.getFilePath().substring(parameter.getFilePath()
                       .lastIndexOf(System.getProperty("file.separator")) + 1);
        setOutputFileName(parameter.getFilePath().substring((parameter.getFilePath()
                         .lastIndexOf(System.getProperty("file.separator")) + 1),
                         parameter.getFilePath().lastIndexOf(".") + 1) + parameter.getFormat());
        String overwrite = " -y";
        String command = relativePath + ffmpeg + input + audioChannel + bitrate + hz + volume + getOutputFileName()
                         + overwrite;
        System.out.println(command);
        runCommand.run(command);
    }

    /**
     * Sets a new outputFileName of the audio converter.
     *
     * @param newOutputFileName output file name to be set.
     */
    public void setOutputFileName(final String newOutputFileName) {
        this.result = new Result();
        result.setFilename(newOutputFileName);
        this.outputFileName = newOutputFileName;
    }

    /**
     * Gets the name of the output filename.
     *
     * @return a String of output filename.
     */
    public String getOutputFileName() {
        return this.outputFileName;
    }

    /**
     * Returns the object result for the operation.
     *
     * @return converterResult.
     */
    public Result getResult() {
        return result;
    }
}
