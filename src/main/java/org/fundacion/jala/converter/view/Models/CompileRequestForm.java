/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Joel Rodrigo Rojas Roman
 */

package org.fundacion.jala.converter.view.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class shows the compilers form.
 */
public class CompileRequestForm implements IRequestForm {
    private List<Parameter> bodyParameters = new ArrayList<>();
    private final String URL;
//    private final String CODE = "code";

//    public CompileRequestForm(final int choose) {
//        switch (choose) {
//            case 1:
//                URL = "http://localhost:8080/api/compileJava";
//                break;
//            default:
//                URL = "http://localhost:8080/api/compilePython";
//                break;
//        }
//    }

    public CompileRequestForm(final String idProj) {
        URL = "http://localhost:8080/api/projects/" + idProj;
    }

//    /**
//     * Adds inputCode parameter.
//     *
//     * @param inputCode String with the input code.
//     */
//    public void addCode(final String inputCode) {
//        addParameters(new Parameter(CODE, inputCode, false));
//    }

    /**
     * Gets the body parameters.
     *
     * @return a List<Parameter> with body's Parameters.
     */
    @Override
    public List<Parameter> getBodyParameters() {
        return bodyParameters;
    }

    /**
     * Adds parameters to bodyParameters.
     *
     * @param parameter Object Parameter.
     */
    @Override
    public void addParameters(final Parameter parameter) {
        bodyParameters.add(parameter);
    }

    /**
     * Gets the url.
     *
     * @return a String with the file's url.
     */
    @Override
    public String getURL() {
        return URL;
    }
}
