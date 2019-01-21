package entrega1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	     urlPatterns={"/GeneraFormulario"},
	     initParams={
	    		    @WebInitParam(name="obra1", value="Laminga"),
	    		    @WebInitParam(name="obra2",value="Las Taradas"),
	    		    @WebInitParam(name="obra3",value="Los Musicletas"),
	    		    @WebInitParam(name="obra4",value="Okupas")}
	     )

public class GeneraFormulario extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public GeneraFormulario() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>"+("Formulario para obtener entradas gratuitas")+"</h1>");
		out.println("<form action='/TTPS2016_ENTREGABLE1_RETAMAR/ImprimeEntrada' method='POST'>");
		out.println("<p><input type='text' name='nombre' required='required' placeholder='Nombre'></p>");
		out.println("<p><input type='text' name='apellido' required='required' placeholder='Apellido'></p>");
		out.println("<p><input type='email' name='email' required='required' placeholder='Email'></p>");
		out.println("<p><input type='text' name='dni' required='required' placeholder='DNI'></p>");
		out.println("<p><input type='radio' name='opcion' value='"+this.getServletConfig().getInitParameter("obra1")+"' required='required'><img src='Imagenes/laminga.jpg' title='"+this.getServletConfig().getInitParameter("obra1")+"'></p>");
		out.println("<p><input type='radio' name='opcion' value='"+this.getServletConfig().getInitParameter("obra2")+"' required='required'><img src='Imagenes/lastaradas.jpg' title='"+this.getServletConfig().getInitParameter("obra2")+"'></p>");
		out.println("<p><input type='radio' name='opcion' value='"+this.getServletConfig().getInitParameter("obra3")+"' required='required'><img src='Imagenes/losmusicletas.jpg' title='"+this.getServletConfig().getInitParameter("obra3")+"'></p>");
		out.println("<p><input type='radio' name='opcion' value='"+this.getServletConfig().getInitParameter("obra4")+"' required='required'><img src='Imagenes/okupas.jpg' title='"+this.getServletConfig().getInitParameter("obra4")+"'></p>");
		out.println("<input type='submit' name='enviar' value='Enviar'>");
		out.println("</form>");
		out.println("<a href='index.html'><input type='button' name='cancelar' value='Cancelar'></>");
		out.println("</body></html>");
		out.close();
	}
	
}