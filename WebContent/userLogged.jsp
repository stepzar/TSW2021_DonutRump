<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="model.UserBean"
   %>
<% 
// Check user credentials
  UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
if ((currentUser==null)||(!currentUser.isValid()))
{	
    response.sendRedirect("./invalidLogin.jsp");
    return;
}

%>
 
   <!DOCTYPE html>

   <html>

      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
      </head>
	
      <body>

         <center>
            
			
            Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>
         </center>

      </body>
	
   </html>