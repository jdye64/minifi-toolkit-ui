package org.apache.nifi.minifi.toolkit.ui.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.nifi.minifi.commons.schema.ConfigSchema;
import org.apache.nifi.minifi.commons.schema.serialization.SchemaSaver;
import org.apache.nifi.minifi.toolkit.configuration.ConfigMain;

import com.codahale.metrics.annotation.Timed;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Dummy resource. For examples only. Should be removed in your actually application.
 *
 * Created by jeremydyer on 11/19/14.
 */
@Path("/transform")
@Produces(MediaType.APPLICATION_JSON)
public class TransformResource {

    @POST
    @Timed
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response createReceiptEntry(@FormDataParam("xmlTemplate") InputStream fileInputStream,
            @FormDataParam("xmlTemplate") FormDataContentDisposition contentDispositionHeader) throws Exception {

        try {

            final ConfigSchema configSchema = ConfigMain.transformTemplateToSchema(fileInputStream);
            if (!configSchema.isValid()) {
                System.out.println("There are validation errors with the template, still outputting YAML but it will need to be edited.");
                for (String s : configSchema.getValidationIssues()) {
                    System.out.println(s);
                }
                System.out.println();
            } else {
                System.out.println(ConfigMain.NO_VALIDATION_ERRORS_FOUND_IN_TEMPLATE);
            }

            StreamingOutput stream = new StreamingOutput() {
                public void write(OutputStream os) throws IOException, WebApplicationException {
                    SchemaSaver.saveConfigSchema(configSchema, os);
                }
            };

            return Response.ok(stream).build();

        } catch (Exception e) {
            System.out.println("Error closing output. (" + e + ")");
            System.out.println();
        }

        return Response.serverError().build();
    }

    @GET
    @Timed
    public String[] getSearchCache() {

        return new String[]{"dummy", "resource"};
    }
}
