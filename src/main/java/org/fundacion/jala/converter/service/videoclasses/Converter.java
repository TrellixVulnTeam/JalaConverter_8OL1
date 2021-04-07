/**
 * Copyright (c) 2021 Fundacion Jala.
 * <p>
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacion.jala.converter.service.videoclasses;

public class Converter {
    private String startFirstCommand = "ffmpeg -i ";
    private VideoParameter parameter;
    private String format;
    private String output;
    private String pathOutput;
    private static final int INIT_NUMBER = 20;

    public Converter(final VideoParameter vParameter) {
        this.parameter = vParameter;
    }

    /**
     * Converts the input video
     * @param pathFile string with the input path
     */
    public void convertVideo(final String pathFile) {
        String adaptPath = pathFile;
        format = parameter.getOutputFormat();
        output = adaptPath.substring((adaptPath.lastIndexOf("\\") + 1), adaptPath.lastIndexOf(".") + 1) + format;
        pathOutput = adaptPath.substring(0, (adaptPath.lastIndexOf("storage"))) + "output\\";

        String fCommand = startFirstCommand + adaptPath + " ";
        String parameters = changeResolution() + changeFrameRate() + removeAudio();
        String theCommand = fCommand + parameters + pathOutput + output + generateATumbnail();

        System.out.println(theCommand);
        try {
            Process petition = Runtime.getRuntime().exec("cmd /c " + theCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (parameter.hasMetaData()) {
            generateMetaDataJsonFormat();
        }

    }

    /**
     * Changes the resolution and aspect ratio of the input video
     * @return resolution command
     */
    private String changeResolution() {
        int width = parameter.getWidth();
        int height = parameter.getHeight();
        if (width > 0 && height > 0) {
            String scale = width + ":" + height;
            String acpectRatio = ":force_original_aspect_ratio=decrease,pad=";
            String resolutionCommand = "-vf \"scale=" + scale + acpectRatio + scale + ":-1:-1:color=white\"";
            return resolutionCommand;
        }
        return "";
    }

    /**
     * Generates a json with the output video metadata
     */
    private void generateMetaDataJsonFormat() {
        String startCommand = "ffprobe -v quiet -print_format json -show_format -show_streams ";
        String outputCommand = pathOutput + output + " > " + pathOutput + output + ".json";
        String jsonCommand = startCommand + outputCommand;
        try {
            Process petition = Runtime.getRuntime().exec("cmd /c" + jsonCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a input video tumbnail
     * @return tumbnail command
     */
    private String generateATumbnail() {
        boolean tumbnail = parameter.hasTumbnail();
        if (tumbnail) {
            String tumbnailCommand = " -ss 00:00:01 -vframes 1 " +  pathOutput + "VideoTumbnail.png";
            return tumbnailCommand;
        }
        return "";
    }

    /**
     * Removes the audio of the input video
     * @return audio command
     */
    private String removeAudio() {
        boolean audio = parameter.hasAudio();
        if (audio) {
            String audioCommand = "-an ";
            return audioCommand;
        }
        return "";
    }

    /**
     * Changes the input video frame rate
     * @return frame command
     */
    private String changeFrameRate() {
        int frameRate = parameter.getFrameRate();
        if (frameRate > INIT_NUMBER) {
            String frameCommand = " -r " + frameRate + " -y ";
            return frameCommand;
        }
        return "";
    }

}
