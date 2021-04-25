/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Daniela Santa Cruz
 */
package org.fundacion.jala.converter.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacion.jala.converter.models.facade.ChecksumFacade;
import org.fundacion.jala.converter.models.facade.ConverterFacade;
import org.fundacion.jala.converter.models.facade.ParameterOutputChecksum;
import org.fundacion.jala.converter.models.facade.ZipFileFacade;
import org.fundacion.jala.converter.service.FileStorageService;
import org.fundacion.jala.converter.models.parameter.VideoParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import static org.fundacion.jala.converter.service.ExtractMetadata.extractMetadata;

/**
 * This class calls endpoint for video.
 */
@RestController
@RequestMapping("/api")
public class VideoConverterController {
    private static final Logger LOGGER = LogManager.getLogger();
    private ParameterOutputChecksum paramChecksum;

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * Calls endpoint to convertVideo.
     *
     * @param file is a path of file which will be converted.
     * @param outputFormat is the format with are converted of video.
     * @param resolution is the resolution with are converted of video.
     * @param thumbnail is the resolution with are converted of video.
     * @param frameRate is number of frames od the video.
     * @param width is the width with are converted of video.
     * @param height is the height with are converted of video.
     * @param audio if video has audio.
     * @param checksum is the checksum of video file.
     * @param metadata if metadata is extracted from the video.
     * @return path to download files.
     * @throws IOException is a exception when invalid input is provided.
     * @throws InterruptedException is exception if process is interrupted.
     */
    @PostMapping("/convertVideo")
    public String uploadFile(final @RequestParam("file") MultipartFile file,
                             final @RequestParam("outputformat") String outputFormat,
                             final @RequestParam("resolution") String resolution,
                             final @RequestParam("thumbnail") boolean thumbnail,
                             final @RequestParam("framerate") int frameRate,
                             final @RequestParam("width") int width,
                             final @RequestParam("height") int height,
                             final @RequestParam("audio") boolean audio,
                             final @RequestParam("checksum") String checksum,
                             final @RequestParam("metadata") boolean metadata)
            throws IOException, InterruptedException {
        parameterOutputChecksum = ChecksumFacade.getChecksum(checksum, file);
        String outputFilename = ConverterFacade.getVideoConverter(
                new VideoParameter(parameterOutputChecksum.getOutputFilename(), outputFormat, resolution, thumbnail,
                        frameRate, width, height, audio));
        extractMetadata(metadata, outputFilename, fileStorageService);
        ZipFileFacade.getZipFileVideo(parameterOutputChecksum, metadata, thumbnail, outputFilename);
        return DownloadLinkFacade.getLinkConverter(outputFilename);
    }
}

