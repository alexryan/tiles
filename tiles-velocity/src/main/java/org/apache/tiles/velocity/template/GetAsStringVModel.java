package org.apache.tiles.velocity.template;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.Attribute;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.servlet.context.ServletUtil;
import org.apache.tiles.template.GetAsStringModel;
import org.apache.tiles.velocity.context.VelocityUtil;
import org.apache.velocity.context.Context;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.Renderable;

public class GetAsStringVModel implements Executable, BodyExecutable {

    private GetAsStringModel model;

    private ServletContext servletContext;

    public GetAsStringVModel(GetAsStringModel model,
            ServletContext servletContext) {
        this.model = model;
        this.servletContext = servletContext;
    }

    public Renderable execute(HttpServletRequest request,
            HttpServletResponse response, Context velocityContext,
            Map<String, Object> params) {
        return new AbstractDefaultToStringRenderable(velocityContext, params,
                response, request) {

            public boolean render(InternalContextAdapter context, Writer writer)
                    throws IOException, MethodInvocationException,
                    ParseErrorException, ResourceNotFoundException {
                TilesContainer container = ServletUtil.getCurrentContainer(
                        request, servletContext);
                model.execute(container, writer,
                        VelocityUtil.toSimpleBoolean((Boolean) params
                                .get("ignore"), false), (String) params
                                .get("preparer"), (String) params.get("role"),
                        params.get("defaultValue"), (String) params
                                .get("defaultValueRole"), (String) params
                                .get("defaultValueType"), (String) params
                                .get("name"), (Attribute) params.get("value"),
                        velocityContext, request, response, writer);
                return true;
            }
        };
    }

    public void start(HttpServletRequest request, HttpServletResponse response,
            Context velocityContext, Map<String, Object> params) {
        VelocityUtil.getParameterStack(velocityContext).push(params);
        model.start(ServletUtil.getComposeStack(request), ServletUtil
                .getCurrentContainer(request, servletContext), VelocityUtil
                .toSimpleBoolean((Boolean) params.get("ignore"), false),
                (String) params.get("preparer"), (String) params.get("role"),
                params.get("defaultValue"), (String) params
                        .get("defaultValueRole"), (String) params
                        .get("defaultValueType"), (String) params.get("name"),
                (Attribute) params.get("value"), velocityContext, request,
                response);
    }

    public Renderable end(HttpServletRequest request, HttpServletResponse response,
            Context velocityContext) {
        Map<String, Object> params = VelocityUtil.getParameterStack(
                velocityContext).pop();
        return new AbstractDefaultToStringRenderable(velocityContext, params, response, request){
        
            public boolean render(InternalContextAdapter context, Writer writer)
                    throws IOException, MethodInvocationException, ParseErrorException,
                    ResourceNotFoundException {
                model.end(ServletUtil.getComposeStack(request), ServletUtil
                        .getCurrentContainer(request, servletContext), response
                        .getWriter(), VelocityUtil.toSimpleBoolean(
                        (Boolean) params.get("ignore"), false),
                        velocityContext, request, response, writer);
                return true;
            }
        };
    }
}