package entrega1;

import javax.servlet.DispatcherType;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(
		urlPatterns={"/ImprimeEntrada"},
		dispatcherTypes = {DispatcherType.REQUEST}
		)
public class FiltroEntradas implements Filter {

    public FiltroEntradas() {
        // TODO Auto-generated constructor stub
    }
    
    public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getParameter("opcion") != null) {
			ServletContext context = req.getServletContext();
			String seleccionado = req.getParameter("opcion");
			int cant;
			cant = (int) context.getAttribute(seleccionado);
			cant++;
			context.setAttribute(seleccionado, cant);
		}
		chain.doFilter(request, response);
	}

}
