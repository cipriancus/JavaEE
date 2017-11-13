package connectionpool.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connectionpool.model.Request;
import connectionpool.repository.RequestRepository;

public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		PrintWriter out = new PrintWriter(resp.getWriter());

		RequestRepository rep = new RequestRepository(method);

		List<Request> list = rep.select();
		if (list != null)
			out.write(rep.select().toString());
		else
			out.write("fail");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String request_time = req.getParameter("request_time");
		String remote_addr = req.getParameter("remote_addr");
		String request_params = req.getParameter("request_params");
		String method = req.getParameter("method");
		PrintWriter out = new PrintWriter(resp.getWriter());
		RequestRepository rep = new RequestRepository(method);

		if (rep.insert(new Request(request_time, remote_addr, request_params)) == true)
			out.println("success");
		else
			out.print("fail");
	}
}
