package com.ecs160.homestaymanager.app;

// EditProfile.java

        import java.util.HashMap;

        import android.os.Bundle;

        import android.widget.EditText;

        import android.app.Activity;
        import android.content.Intent;

        import android.view.View;

public class EditProfile extends Activity{

    // Allows access to data in the EditTexts

    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText homeAddress;

    // The database tool class

    DBTools dbTools = new DBTools(this);

    // Sets up everything when the Activity is displayed

    public void onCreate(Bundle savedInstanceState) {

        // Get saved data if there is any

        super.onCreate(savedInstanceState);

        // Designate that edit_profile.xml is the interface used

        setContentView(R.layout.edit_profile);

        // Get the EditText objects

        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
        homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

        // Intent defines that an operation will be performed

        Intent theIntent = getIntent();

        // Get the extended data provided to this activity
        // putExtra("profileId", profileIdValue); in MainActivity
        // will pass profileId here

        String profileId = theIntent.getStringExtra("profileId");

        // Get the HashMap of data associated with the profileId

        HashMap<String, String> profileList = dbTools.getProfileInfo(profileId);

        // Make sure there is something in the profileList

        if(profileList.size()!=0) {

            // Put the values in the EditText boxes

            firstName.setText(profileList.get("firstName"));
            lastName.setText(profileList.get("lastName"));
            phoneNumber.setText(profileList.get("phoneNumber"));
            emailAddress.setText(profileList.get("emailAddress"));
            homeAddress.setText(profileList.get("homeAddress"));

        }
    }
    public void editProfile(View view) {
        HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();

        // Get the EditText objects

        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        emailAddress = (EditText) findViewById(R.id.emailAddressEditText);
        homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

        // Intent defines that an operation will be performed

        Intent theIntent = getIntent();

        // Get the extended data provided to this activity
        // putExtra("profileId", profileIdValue); in MainActivity
        // will pass profileId here

        String profileId = theIntent.getStringExtra("profileId");

        // Put the values in the EditTexts in the HashMap

        queryValuesMap.put("profileId", profileId);
        queryValuesMap.put("firstName", firstName.getText().toString());
        queryValuesMap.put("lastName", lastName.getText().toString());
        queryValuesMap.put("phoneNumber", phoneNumber.getText().toString());
        queryValuesMap.put("emailAddress", emailAddress.getText().toString());
        queryValuesMap.put("homeAddress", homeAddress.getText().toString());

        // Send the HashMap to update the data in the database

        dbTools.updateProfile(queryValuesMap);

        // Call for MainActivity

        this.callMainActivity(view);

    }
    public void removeProfile(View view) {
        Intent theIntent = getIntent();
        String profileId = theIntent.getStringExtra("profileId");

        // Call for the profile with the profileId provided
        // to be deleted

        dbTools.deleteProfile(profileId);

        // Call for MainActivity

        this.callMainActivity(view);

    }

    // Calls for a switch to the MainActivity

    public void callMainActivity(View view) {

        // getApplication returns an Application object which allows 
        // you to manage your application and respond to different actions.
        // It returns an Application object which extends Context.

        // A Context provides information on the environment your application 
        // is currently running in. It provides services like how tp obtain 
        // access to a database and preferences.

        // Google says a Context is an entity that represents various 
        // environment data. It provides access to local files, databases, 
        // class loaders associated to the environment, services including 
        // system-level services, and more.

        // The following Intent states that you want to switch to a new 
        // Activity being the MainActivity

        Intent objIntent = new Intent(getApplication(), MainActivity.class);
        startActivity(objIntent);
    }
}
