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
		if (list != null) {
			out.write(list.toString());
			out.close();
		}
		else{
			out.write("fail");
			out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String request_time = req.getHeader("request_time");
		String remote_addr = req.getHeader("remote_addr");
		String request_params = req.getHeader("request_params");
		String method = req.getHeader("method");
		PrintWriter out = new PrintWriter(resp.getWriter());
		RequestRepository rep = new RequestRepository(method);

		if (rep.insert(new Request(request_time, remote_addr, request_params)) == true)
			out.println("success");
		else
			out.print("fail");
	}
}
