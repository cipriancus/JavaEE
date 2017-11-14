package connectionpool.model;

public class Request {
	private int id;
	private String request_time;
	private String remote_addr; 
	private String request_params;
	
	public Request(String request_time, String remote_addr, String request_params) {
		this.request_time=request_time;
		this.remote_addr=remote_addr;
		this.request_params=request_params;
	}
	
	public Request() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRequest_time() {
		return request_time;
	}
	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}
	public String getRemote_addr() {
		return remote_addr;
	}
	public void setRemote_addr(String remote_addr) {
		this.remote_addr = remote_addr;
	}
	public String getRequest_params() {
		return request_params;
	}
	public void setRequest_params(String request_params) {
		this.request_params = request_params;
	}
	
	@Override
	public String toString() {
		return request_params+' '+remote_addr+' '+request_params;
	}
}
