import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.VideoGame;
import util.UtilDBBerkland;

@WebServlet("/MyServletHibernateDB")
public class MyServletHibernateDBBerkland extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernateDBBerkland() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDBBerkland.createVideoGames("GTA V", "2013","$20");
      UtilDBBerkland.createVideoGames("Minecraft", "2009","$25.99");
      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<VideoGame> listVideoGames = UtilDBBerkland.listVideoGames();
      out.println("<table border=\"1\"><tr><td>ID</td><td>Name</td><td>Year</td><td>Price</td></tr>");
      for (VideoGame videoGame : listVideoGames) {
         System.out.println("[DBG] " + videoGame.getId() + ", " //
               + videoGame.getName() + ", " //
               + videoGame.getYear() + ", " + videoGame.getPrice());

         out.println("<tr><td>" + videoGame.getId() + "</td><td>" //
               + videoGame.getName() + "</td><td>" //
               + videoGame.getYear() + "</td><td>" + videoGame.getPrice() + "</td></tr>");
      }
      out.println("</table>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
