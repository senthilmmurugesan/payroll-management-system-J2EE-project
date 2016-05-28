

import com.opensymphony.xwork2.ActionSupport;

import databaseAccess.DBConnect;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;
 
    public String execute() {
    	
    	DBConnect db = new DBConnect();
    	db.OpenConnection();
    	boolean res = db.ValidateEmployee(username, password);
        if (res) {
            if("admin".equals(username))   
            	return "admin";
            return "success";
        } else {
            addActionError(getText("error.login"));
            return "error";
        }
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}

