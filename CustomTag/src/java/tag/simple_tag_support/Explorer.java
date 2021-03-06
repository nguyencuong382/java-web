/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tag.simple_tag_support;

import java.io.File;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import util.ListFilesUtil;
import javax.servlet.http.Cookie;

/**
 *
 * @author Admin
 */
public class Explorer extends SimpleTagSupport {

    private String path;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();

            if (path == null || path.equals("")) {
                return;
            }

            ListFilesUtil listFilesUtil = new ListFilesUtil();
            //Windows directory example
            final String directoryWindows = "C://";
            File[] files = listFilesUtil.listFilesAndFolders(directoryWindows);
            
            JspContext ct = getJspContext();
            
            for (File file : files) {
                
                ct.setAttribute("path", file.getAbsolutePath());
                ct.setAttribute("size", file.length());
               
                if (f != null) {
                    f.invoke(out);
                }
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Explorer tag", ex);
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

}
