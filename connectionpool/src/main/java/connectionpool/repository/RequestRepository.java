package connectionpool.repository;

import java.util.List;

import connectionpool.dao.RequestDAO;
import connectionpool.model.Request;

public class RequestRepository {
	
	private RequestDAO reqDAO;
	
	public RequestRepository(String method) {
		reqDAO=new RequestDAO(method);
	}

	public boolean insert(Request request) {
		return reqDAO.insert(request);
	}

	public List<Request> select() {
		return reqDAO.select();
	}
}
