package entrega1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EstadisticaDescuento")
public class EstadisticaDescuento extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public EstadisticaDescuento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>"+("Estadistica de entradas")+"</h1>");
		out.println("<p><ul><li>Cantidad de entradas para La Minga: " + (int) context.getAttribute("Laminga") + "</li>");
		out.println("<li>Cantidad de entradas para Las Taradas: " + (int) context.getAttribute("Las Taradas") + "</li>");
		out.println("<li>Cantidad de entradas para Los Musicletas: " + (int) context.getAttribute("Los Musicletas") + "</li>");
		out.println("<li>Cantidad de entradas para Okupas: " + (int) context.getAttribute("Okupas") + "</li></ul></p>");
		out.print("<a href='index.html'>Volver</a>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}

}
