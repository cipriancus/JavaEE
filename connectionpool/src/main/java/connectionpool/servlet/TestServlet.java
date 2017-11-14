package connectionpool.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
		String request_time = Long.toString(System.currentTimeMillis());
		String remote_addr = req.getRemoteAddr();
		String request_params=new String();

		Map map = req.getParameterMap();
		for (Object key: map.keySet())
		{
			String keyStr = (String)key;
			String[] value = (String[])map.get(keyStr);
			request_params = request_params + (String)key + "   :   " + Arrays.toString(value);
		}

		String method = req.getParameter("method");
		PrintWriter out = new PrintWriter(resp.getWriter());
		RequestRepository rep = new RequestRepository(method);

		if (rep.insert(new Request(request_time, remote_addr, request_params)) == true)
			out.println("success");
		else
			out.print("fail");
	}
}
