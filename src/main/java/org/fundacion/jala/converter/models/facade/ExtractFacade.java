/**
 * Copyright (c) 2021 Fundacion Jala.
 * <p>
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacion.jala.converter.models.facade;

import org.fundacion.jala.converter.models.parameter.ExtractTextParameter;
import org.fundacion.jala.converter.service.ExtractMetadata;
import org.fundacion.jala.converter.service.ExtractText;
import org.fundacion.jala.converter.service.FileStorageService;
import org.fundacion.jala.converter.service.ObjectMetadata;
import org.fundacion.jala.converter.service.metadata.TypeFileExport;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class ExtractFacade {

    private ExtractFacade() {
    }

    /**
     * The method to extract text from image
     *
     * @param file is the path of file to extract text.
     * @param language is the language which file are written
     * @param nameOutput is the name of file where text are extracted
     * @throws IOException is the exception if File doesn't exist
     */
    public static void getTextExtract(final MultipartFile file, final String language,
                                      final String nameOutput) throws IOException {
        FileStorageService fileStorageService = new FileStorageService();
        String filename = file.getOriginalFilename();
        String storagePath = fileStorageService.uploadFile(file);
        ExtractText extractText = new ExtractText(new ExtractTextParameter(storagePath, language, nameOutput));
        extractText.extractText();
    }

    /**
     * The method to extract metadata from file
     *
     * @param file is the path of file to extract metadata.
     * @param isMoreInfo is get more info of file
     * @param nameExport  is the name of file where metadata are extracted
     * @param format is the format of file where metadata are extracted
     * @return string with name of file which contains metadata
     */
    public static String getMetadataExtract(final String file, final Boolean isMoreInfo,
                                            final String nameExport, final String format) {
        FileStorageService fileStorageService = new FileStorageService();
        TypeFileExport typeFileExport = stringToEnum(format);
        String outputPath = fileStorageService.getOutputPathWithoutFileName(fileStorageService.getOutputPath(file));
        File fileToExtract = new File(file);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setNameExport(nameExport + "");
        objectMetadata.setFileToExtract(fileToExtract);
        objectMetadata.setFileToExport(new File(outputPath));
        objectMetadata.setMoreInfo(isMoreInfo);
        objectMetadata.setTypeFileExport(typeFileExport);
        ExtractMetadata extractMetadata = new ExtractMetadata(objectMetadata);
        extractMetadata.extractMetadata();
        if ("Default".equals(nameExport)) {
            return fileToExtract.getName().substring(0, fileToExtract.getName().lastIndexOf(".") + 0);
        } else {
            return nameExport;
        }
    }

    /**
     * Convert string to enum for metadata.
     *
     * @param format define type of file which it is exported.
     * @return format type TypeFileExport
     */
    private static TypeFileExport stringToEnum(final String format) {
        if ("txt".equals(format)) return TypeFileExport.TXT;
        if ("html".equals(format)) return TypeFileExport.HTML;
        if ("xmp".equals(format)) return TypeFileExport.XMP;
        return TypeFileExport.TXT;
    }

}
