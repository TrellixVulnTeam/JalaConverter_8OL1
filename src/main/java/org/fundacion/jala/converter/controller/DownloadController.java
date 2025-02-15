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
package org.fundacion.jala.converter.controller;

import org.fundacion.jala.converter.core.FileStorageService;
import org.fundacion.jala.converter.core.exceptions.FileStorageException;
import org.fundacion.jala.converter.core.exceptions.PaoPaoException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class calls endpoint to download controller.
 */
@RestController
@RequestMapping("/api")
public class DownloadController {
    private FileStorageService fileStorageService = new FileStorageService();

    /**
     * Creates endpoint to download controller.
     *
     * @param fileName is a name of the file to download.
     * @return response entity ok with the resource get file name.
     */
    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downloadFile(final @PathVariable String fileName) {
        Resource resource = null;
        try {
            resource = fileStorageService.downloadFile(fileName);
        } catch (PaoPaoException exception) {
            exception.getMessage();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + resource.getFilename())
                .body(resource);
    }
}
