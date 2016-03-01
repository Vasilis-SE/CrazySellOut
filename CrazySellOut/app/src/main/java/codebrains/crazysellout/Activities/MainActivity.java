package codebrains.crazysellout.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import org.json.JSONException;
import org.json.JSONObject;

import codebrains.crazysellout.AsyncTasks.AttemptCreateAccount;
import codebrains.crazysellout.AsyncTasks.AttemptLogin;
import codebrains.crazysellout.Controllers.CreateAccountController;
import codebrains.crazysellout.Controllers.LoginController;
import codebrains.crazysellout.R;
import codebrains.crazysellout.Adapters.Tabsadapter;
import codebrains.crazysellout.System.Connectivity;
import codebrains.crazysellout.System.SystemDialogs;

public class MainActivity extends ActionBarActivity implements  android.support.v7.app.ActionBar.TabListener{

    private ViewPager tabsviewPager;
    private Tabsadapter mTabsAdapter;

    private EditText edtUsername, edtPassword, edtRepassword, edtNumber, edtEmailName;
    private Spinner spinner;
    private RadioGroup sexRadioButtonGroup, accountTypeRadioButtonGroup;
    private boolean isTermsChecked;

    //Constructor
    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Connectivity.WifiEnabledCheck(this);

        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);
        mTabsAdapter = new Tabsadapter(getSupportFragmentManager());
        tabsviewPager.setAdapter(mTabsAdapter);

        this.getSupportActionBar().setHomeButtonEnabled(false);
        this.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab loginTab = getSupportActionBar().newTab().setText("Login").setTabListener(this);
        Tab createAccountTab = getSupportActionBar().newTab().setText("Create Account").setTabListener(this);

        getSupportActionBar().addTab(loginTab);
        getSupportActionBar().addTab(createAccountTab);

        //This helps in providing swiping effect for v7 compat library
        tabsviewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(Tab selectedtab, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        tabsviewPager.setCurrentItem(selectedtab.getPosition()); //update tab position on tap
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    /**
     * Method that prompts the terms and conditions alert dialog box.
     * @param view The view of the activity.
     */
    public void ShowTermsAndConditions(View view){

        String title = "Terms & Conditions";
        String message = "By using this application you are obliged to follow those temrs " +
            "until you decide to stop using this application. The terms and conditions are " +
            "listed below, so please read them cearfuly :\n\n" +
            "GENERAL TERMS : \n" +
            "(a) If you use the App in any unlawful manner or in a manner which promotes or encourages " +
            "illegal activity including (without limitation) copyright infringement; or\n\n" +
            "(b) Attempt to gain unauthorised access to the App or any networks, servers or " +
            "computer systems connected to the App; or\n\n" +
            "(c) Modify, adapt, translate or reverse engineer any part of the App or re-format " +
            "or frame any portion of the pages comprising the App, save to the extent expressly " +
            "permitted by these Terms or by law.\n\n" +
            "CONTENT TERMS : \n" +
            "The copyright in all material contained on, in, or available through the App including " +
            "all information, data, text, music, sound, photographs, graphics and video messages, " +
            "the selection and arrangement thereof, and all source code, software compilations and " +
            "other material (“Material“) is owned by the creators of this application. \n\n" +
            "DATA TERMS : \n" +
            "The application will use user data given by you during the account creation (number, email etc) " +
            "in order to fulfill certain functionalities such as google maps location search.";

        SystemDialogs.DisplayInformationAlertBox(message, title, this);
    }

    /**
     * Event listener for submitting the new account form.
     * @param view The view of the submitted activity.
     */
    public void NewAccountSubmission(View view) {

        //Get all the data from the new account form.
        this.edtUsername = (EditText) findViewById(R.id.username);
        String username = this.edtUsername.getText().toString().trim();

        this.edtPassword = (EditText) findViewById(R.id.password);
        String password = this.edtPassword.getText().toString().trim();

        this.edtRepassword = (EditText) findViewById(R.id.repassword);
        String repassword = this.edtRepassword.getText().toString().trim();

        this.edtNumber = (EditText) findViewById(R.id.number);
        String number = this.edtNumber.getText().toString().trim();

        this.edtEmailName = (EditText) findViewById(R.id.emailname);
        String mailName = this.edtEmailName.getText().toString().trim();

        this.spinner = (Spinner) findViewById(R.id.spinner);
        String mailService = this.spinner.getSelectedItem().toString();

        //Get the text of the selected radio button from the sex radio group
        this.sexRadioButtonGroup = (RadioGroup) findViewById(R.id.sexradiogroup);
        int radioButtonID = this.sexRadioButtonGroup.getCheckedRadioButtonId();
        View radioButton = this.sexRadioButtonGroup.findViewById(radioButtonID);
        int idx = this.sexRadioButtonGroup.indexOfChild(radioButton);
        RadioButton sexRadioButton = (RadioButton) this.sexRadioButtonGroup.getChildAt(idx);
        String sex = sexRadioButton.getText().toString();

        //Get the text of the selected radio button from the account type radio group
        this.accountTypeRadioButtonGroup = (RadioGroup) findViewById(R.id.accounttyperadiogroup);
        radioButtonID = this.accountTypeRadioButtonGroup.getCheckedRadioButtonId();
        radioButton = this.accountTypeRadioButtonGroup.findViewById(radioButtonID);
        idx = this.accountTypeRadioButtonGroup.indexOfChild(radioButton);
        RadioButton accountTypeRadioButton = (RadioButton) this.accountTypeRadioButtonGroup.getChildAt(idx);
        String accountType = accountTypeRadioButton.getText().toString();

        this.isTermsChecked = ((CheckBox) findViewById(R.id.terms)).isChecked();

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("username", username);
            jObj.put("password", password);
            jObj.put("repassword", repassword);
            jObj.put("number", number);
            jObj.put("emailName", mailName);
            jObj.put("emailService", mailService);
            jObj.put("sex", sex);
            jObj.put("accountType", accountType);
            jObj.put("terms", this.isTermsChecked);

            //Key value pairs that will be used later during the check oc the new account.
            jObj.put("status", true);
            jObj.put("message", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        CreateAccountController cac = new CreateAccountController(jObj);
        String result = cac.CreateNewAccountControlMethod();

        if(result.equals("")){
            new AttemptCreateAccount(this, jObj).execute();
        }
        else{
            SystemDialogs.DisplayInformationAlertBox(result, "Create Account Process", this);
        }

    }

    /**
     * Event for login button click.
     *
     * @param view The view of the form.
     */
    public void LoginProcess(View view){

        //Get all the data from the new account form.
        this.edtUsername = (EditText) findViewById(R.id.loginusername);
        String username = this.edtUsername.getText().toString().trim();

        this.edtPassword = (EditText) findViewById(R.id.loginpassword);
        String password = this.edtPassword.getText().toString().trim();

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("username", username);
            jObj.put("password", password);

            //Key value pairs that will be used later during the check oc the new account.
            jObj.put("status", true);
            jObj.put("message", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LoginController lic = new LoginController(jObj);
        String result = lic.LoginControlMethod();
        if(result.equals("")){
            new AttemptLogin(this, jObj).execute();
        }
        else{
            SystemDialogs.DisplayInformationAlertBox(result, "Create Account Process", this);
        }

    }



}
