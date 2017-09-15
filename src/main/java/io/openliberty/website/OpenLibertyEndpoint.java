/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package io.openliberty.website;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
@Path("/")
public class OpenLibertyEndpoint extends Application {

    @GET
    @Path("builds")
    @Produces({ "application/json" })
    public JsonObject status() {
        BuildsManager buildsManager = BuildsManager.getInstance();
        return buildsManager.getStatus();
    }

    @GET
    @Path("builds/data")
    @Produces({ "application/json" })
    public JsonObject builds() {
        BuildsManager buildsManager = BuildsManager.getInstance();
        JsonObjectBuilder data = Json.createObjectBuilder();
        data.add(Constants.LATEST_RELEASES, buildsManager.getLatestReleases());
        data.add(Constants.BUILDS, buildsManager.getBuilds());
        return data.build();
    }

    @GET
    @Path("builds/latest")
    @Produces({ "application/json" })
    public JsonObject latestsReleases() {
        BuildsManager buildsManager = BuildsManager.getInstance();
        return buildsManager.getLatestReleases();
    }

    @PUT
    @Path("builds")
    @Produces({ "application/json" })
    public JsonObject update() {
        BuildsManager buildsManager = BuildsManager.getInstance();
        return buildsManager.updateBuilds();
    }

    @GET
    @Path("github/issues")
    @Produces({ "application/json" })
    public String githubIssues() {
        return GitHubManager.getInstance().getIssues();
    }

}
