/**
 * Copyright (c) 2021 Fundacion Jala.
 * <p>
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacion.jala.converter.controller;

import org.fundacion.jala.converter.models.Project;
import org.fundacion.jala.converter.service.AudioConverter;
import org.fundacion.jala.converter.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.fundacion.jala.converter.models.ProjectSQL.insertProjectData;
import static org.fundacion.jala.converter.models.ProjectSQL.listProject;

import static org.fundacion.jala.converter.service.ExtractMetadata.extractMetadata;

@RestController
@RequestMapping("/api")
public class AudioConverterController {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    AudioConverter audioConverter;

    /**
     * Endpoint for audio converter
     */
    @PostMapping("/convertAudio")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("format") String format,
                             @RequestParam("bitrate") String bitrate,
                             @RequestParam("volume") String volume,
                             @RequestParam("hz") String hz,
                             @RequestParam("checksum") String checksum) throws IllegalStateException, IOException {
        System.out.println("here check checksum before upload");
        String filename;
        String storagePath;
        List<Project> projects = listProject();
        List<String> resultTitle = projects.stream().filter(project -> project.getChecksum().equals(checksum))
                .map(project -> project.getTitle())
                .collect(Collectors.toList());
        List<String> resultPath = projects.stream().filter(project -> project.getChecksum().equals(checksum))
                .map(project -> project.getPath())
                .collect(Collectors.toList());
        System.out.println("this is result checksum" + resultPath+ " # "+resultTitle);
        boolean exist = false;
        exist = resultTitle.size() > 0;
        System.out.println("this is the boolean" + exist);
        if (exist) {
            filename = resultTitle.get(0);
            storagePath = resultPath.get(0)+filename;
        }else{
            System.out.println("here is false");
            filename = file.getOriginalFilename();
            storagePath = fileStorageService.uploadFile(file);
            System.out.println("false : "+filename+" storage "+storagePath);
        }
        System.out.println("aqui1------- " + filename);
        System.out.println("aqui2------- " + storagePath);


        AudioConverter audio = new AudioConverter();
        audio.setFormat(format);
        audio.setBitrate(bitrate);
        audio.setVolume(volume);
        audio.setHz(hz);
        System.out.println(filename);
        audio.audioConverter(storagePath);
        String outputFilename = audio.getOutputFileName();
        String outputPath = FileStorageService.getOutputPath(filename);
        System.out.println("aqui3------- " + outputFilename);
        System.out.println("aqui4------- " + outputPath);
        //DB
        String pathFile = storagePath.substring(0, storagePath.lastIndexOf("\\") + 1);
        System.out.println("aqui5------- " + pathFile);
        if (!(resultTitle.size() > 0)) {
            insertProjectData(filename, pathFile, checksum, 2);
        }


        //
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String downloadLink = baseUrl + "/api/download/" + outputFilename;
        return downloadLink;
    }
}
