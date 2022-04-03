import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDBBerkland;

@WebServlet("/SimpleInsertHB")
public class SimpleInsertHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String gameName = request.getParameter("GameName").trim();
      String year = request.getParameter("year").trim();
      String price = request.getParameter("price").trim();
      UtilDBBerkland.createVideoGames(gameName, year, price);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Your Added Game Information";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<table border=\"1\"><tr><td>Name</td><td>Year</td><td>Price</td></tr><tr>");
      out.println("<td>" + gameName + "</td>");
      out.println("<td>" + year + "</td>");
      out.println("<td>" + price + "</td>");
      out.println("</tr></table>");
      out.println("<a href=\"./" + searchWebName + "\">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
