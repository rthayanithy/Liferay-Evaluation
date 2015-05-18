package com.gem.generic;

import java.io.IOException;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class HelloWorld extends GenericPortlet {

    public void init() {
        viewJSP = getInitParameter("view-template");
    }
   
    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {
       String currentViewPage=renderRequest.getParameter("jspPage");
       if(currentViewPage!=null&&!currentViewPage.equals("")){
              include(currentViewPage, renderRequest, renderResponse);
       }else{
              include(viewJSP, renderRequest, renderResponse);
       }
    }
   
    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }

    protected String viewJSP;

    private static Log _log = LogFactoryUtil.getLog(HelloWorld.class);

}