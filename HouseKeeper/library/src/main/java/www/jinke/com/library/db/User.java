package www.jinke.com.library.db;

import org.litepal.crud.DataSupport;

/**
 * Created by root on 18-5-17.
 */

public class User  extends DataSupport {

    private String userName;
    private String pwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
