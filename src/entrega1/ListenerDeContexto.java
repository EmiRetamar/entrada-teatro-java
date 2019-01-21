package entrega1;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerDeContexto implements ServletContextListener {

	private ServletContext context = null;

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		context.setAttribute("Laminga", 0);
		context.setAttribute("Las Taradas", 0);
		context.setAttribute("Los Musicletas", 0);
		context.setAttribute("Okupas", 0);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

}
