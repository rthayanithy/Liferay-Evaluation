package com.ofs.generic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class HelloWorld
 */
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
    
     public void processAction(ActionRequest request,ActionResponse response)throws PortletException,java.io.IOException{
    	 String emplyeeName=request.getParameter("employeeName");
    	 String employeeAddress=request.getParameter("employeeAddress");
    	 Map<String,String> employeeMap=new HashMap<String,String>();
    	 employeeMap.put("employeeName",emplyeeName);
    	 employeeMap.put("employeeAddress",employeeAddress);
    	 request.setAttribute("employeeMap", employeeMap);
    	 response.setRenderParameter("jspPage","/html/helloworld/displayEmployees.jsp");
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
