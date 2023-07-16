/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author aalmasri
 */
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //cast request and the reponse
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        UserDB userdb = new UserDB();
        User user = userdb.get(email);
        int role = user.getRole().getRoleId();
        
        if(role != 1){
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("notes");
            return; //Stop here
        }
        // user is valid
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
    
}
