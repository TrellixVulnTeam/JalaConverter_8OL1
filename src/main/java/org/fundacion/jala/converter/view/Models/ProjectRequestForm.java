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
 * This class represents a form with a list of project parameters.
 */
public class ProjectRequestForm implements IRequestForm {
    private List<Parameter> bodyParameters = new ArrayList<>();
    private final String URL = "http://localhost:8080/api/projects";
    private final String PROJECT_NANE = "projectName";
    private final String USER_ID = "userId";

    public ProjectRequestForm() {
    }

    /**
     * Adds name parameter.
     *
     * @param name is project name.
     */
    public void addProjectName(final String name) {
        addParameters(new Parameter(PROJECT_NANE, name, false));
    }

    /**
     * Adds id parameter.
     *
     * @param id is user id.
     */
    public void addUserId(final String id) {
        addParameters(new Parameter(USER_ID, id, false));
    }

    /**
     * Gets the body parameters.
     *
     * @return a List<Parameter> that contains all parameters of project.
     */
    @Override
    public List<Parameter> getBodyParameters() {
        return bodyParameters;
    }

    /**
     * Adds parameters to bodyParameters.
     *
     * @param parameter represents all attributes of project.
     */
    @Override
    public void addParameters(final Parameter parameter) {
        bodyParameters.add(parameter);
    }

    /**
     * Gets the url.
     *
     * @return a String with the project's url.
     */
    @Override
    public String getURL() {
        return URL;
    }
}

