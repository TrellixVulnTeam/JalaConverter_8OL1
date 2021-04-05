/**
 * Copyright (c) 2021 Fundacion Jala.
 * <p>
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacion.jala.converter.service.metadata;

import java.io.File;
import java.io.IOException;

public class Exiftool {
    final static String ADDRESS = "cd src\\main\\java\\org\\fundacionjala\\JalaConverter\\Metadata\\ExiftoolApp/ ";
    private String exportFile = "";
    private File file;
    private String nameFileComplete;
    private String moreInformation = " ";
    private ExportTypeFile exportTypeFile;

    Exiftool(File file, String nameExport, TypeFileExport typeFileExport, boolean moreInfo) {
        this.file = file;
        this.nameFileComplete = file.getName();
        if (moreInfo) setMoreInformation();
        exportTypeFile = new ExportTypeFile(nameFileComplete, nameExport, typeFileExport);
        exportFile = exportTypeFile.getNameFileCompleteToExport();
        executionExiftool();
        System.out.println("Entro");
    }

    /**
     * Code assembly in order to run in Exiftool.
     */
    public void executionExiftool() {
        try {
            String command = "cmd /c " + ADDRESS + " && exiftool.exe " + "\"" + nameFileComplete + "\"" + moreInformation + exportFile;
            Process process = Runtime.getRuntime().exec(command);
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Fail");
        }
    }

    /**
     * The method permit Exiftool get more information about metadata of file.
     * set parameter moreInformation.
     */
    private void setMoreInformation() {
        this.moreInformation = " -api largefilesupport=1 -" + "ee";
    }
}
