/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Florian MOULY
 */
public class ControllerServlet extends HttpServlet {
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, Exception {
        
        /* Affiche les informations de tous les clients habitant dans un état des USA transmis en paramètre */
        DataSource myDataSource = DataSourceFactory.getDataSource(); // La source de données à utiliser
        DAO dao = new DAO(myDataSource); // L'objet à tester
        
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        
        if(action.equals("DELETE")){
            String code = request.getParameter("code");
            request.setAttribute("mes", dao.deleteDiscountCode(code));
        } else{
            if(action.equals("ADD")){
                
                String key = request.getParameter("code");
                String val = request.getParameter("taux");
                float rate = Float.parseFloat(val);  // Conversion du taux récupéré en float pour l'ajout dans la base de données
                
                System.out.print("On ajoute la valeur" + key);             
                request.setAttribute("mes",dao.addDiscountCode(key, rate));
            }
            }

        request.setAttribute("HT", dao.allCodes());
        RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
        rd.forward(request, response); 
    }
    
}
