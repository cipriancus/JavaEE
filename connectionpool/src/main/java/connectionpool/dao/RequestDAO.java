package connectionpool.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionpool.model.Request;

public class RequestDAO {

    private String method;

    private DataSource useSource;

    private Connection connection;

    public static Statement stmtObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
    public static Context ctx = null;


    public RequestDAO(String method) {
        this.method = "singleton";
        switch (this.method) {
            case "singleton":
                try {
                    ctx = new InitialContext();
                    DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/singleton");
                    useSource = ds;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "cps":
                try {
                    ctx = new InitialContext();
                    DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/cps");
                    useSource = ds;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "cp":
                try {
                    ctx = new InitialContext();
                    DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/cp");
                    useSource = ds;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        try {
            connection = useSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean insert(Request request) {
        int saveResult = 0;
        try {
            pstmt = connection.prepareStatement(
                    "insert into request_log (request_time, remote_addr, request_params) values (?, ?, ?)");
            pstmt.setString(1, request.getRequest_time());
            pstmt.setString(2, request.getRemote_addr());
            pstmt.setString(3, request.getRequest_params());
            saveResult = pstmt.executeUpdate();
            connection.close();
            if (saveResult != 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Request> select() {
        List<Request> reqList = new ArrayList<Request>();
        try {
            stmtObj = connection.createStatement();
            resultSetObj = stmtObj.executeQuery("select * from request_log");
            while (resultSetObj.next()) {
                Request req = new Request();
                req.setId(resultSetObj.getInt("id"));
                req.setRequest_time(resultSetObj.getString("request_time"));
                req.setRemote_addr(resultSetObj.getString("remote_addr"));
                req.setRequest_params(resultSetObj.getString("request_params"));
                reqList.add(req);
            }
            connection.close();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return reqList;
    }

}
