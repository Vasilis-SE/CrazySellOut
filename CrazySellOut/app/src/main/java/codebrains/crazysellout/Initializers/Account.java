package codebrains.crazysellout.Initializers;

/**
 * Created by Vasilhs on 1/5/2016.
 */
public class Account {

    private String username;
    private String password;
    private String number;
    private String emailAddress;
    private String sex;
    private String accountType;

    //Constructor without parameters
    public  Account(){
        this.username = "";
        this.password = "";
        this.number = "";
        this.emailAddress = "";
        this.sex = "";
        this.accountType = "";
    }

    //Constructor with parameters
    public Account(String usname, String pass, String num, String email, String sex, String type){
        this.username = usname;
        this.password = pass;
        this.number = num;
        this.emailAddress = email;
        this.sex = sex;
        this.accountType = type;
    }

    public String GetUsername(){
        return this.username;
    }

    public void SetUsername(String usname){
        this.username = usname;
    }

    public String GetPassword(){
        return this.password;
    }

    public void SetPassword(String pass){
        this.password = pass;
    }

    public String GetNember(){
        return this.number;
    }

    public void SetNumber(String num){
        this.number = num;
    }

    public String GetEmailAddress(){
        return this.emailAddress;
    }

    public void SetEmailAddress(String email){
        this.emailAddress = email;
    }

    public String GetSex(){
        return this.sex;
    }

    public void SetSex(String sex){
        this.sex = sex;
    }

    public String GetAccountType(){
        return  this.accountType;
    }

    public void SetAccountType(String type){
        this.accountType = type;
    }



}
