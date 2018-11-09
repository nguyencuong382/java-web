/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import entity.User_;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserAction extends ActionSupport {
    private String userName;
    
    // is used by controller
    public String getUserName() {
        return userName;
    }

    // is user by view
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public List<User_> getUsers() {
        List<User_> users = new ArrayList<>();
        users.add(new User_("John", "abc"));
        users.add(new User_("Mary", "dbd"));
        users.add(new User_("Obama", "dfsd"));
        
        return users;
    }
    
    
    public UserAction() {
    }
    
    public String execute() throws Exception {
        User_ u = new User_("sa", "123");
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.set("user", u);
        
        if(userName.isEmpty()) return ERROR;
        else return SUCCESS;
    }
    
}
