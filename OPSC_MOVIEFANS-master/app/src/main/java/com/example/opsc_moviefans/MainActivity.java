package com.example.opsc_moviefans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView inputUsername;
    public TextView inputPassword;

    TextView txtLoginError;

    public Category currentCollection;
    public Collectable currentCollectable;

    public ConstraintLayout layout;

    int collectionLimit;

    final String JPEG_FILE_PREFIX = ""; //TODO: NOT IMPLEMENTED
    final String JPEG_FILE_SUFFIX = ".jpeg";//TODO: NOT IMPLEMENTED


    List<CurrentUser> savedUsers;
    CurrentUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = (TextView) findViewById(R.id.inputUsername);
        inputPassword = (TextView) findViewById(R.id.inputPassword);

        txtLoginError = (TextView) findViewById(R.id.txtLoginError);
        txtLoginError.setVisibility(View.GONE);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String usernameCheck = inputUsername.getText().toString();
                String passwordCheck = inputPassword.getText().toString();

                if(savedUsers != null){

                    txtLoginError.setVisibility(View.VISIBLE);
                    txtLoginError.setText("Please");

                    for (int i = 0; i < savedUsers.size();i++){



                        if(savedUsers.get(i).Username.equals(usernameCheck) && savedUsers.get(i).Password.equals(passwordCheck)){

                            setContentView(R.layout.activity_main_screen);
                            Log.d("Username", "" + savedUsers.get(i).Username);
                            Log.d("Password", "" + savedUsers.get(i).Password);
                            user = savedUsers.get(i);
                            CreateMainScreen();
                        }
                        else{

                            txtLoginError.setVisibility(View.VISIBLE);
                            txtLoginError.setText("Please login with a saved user");
                            return;
                        }
                    }
                }
                else{

                    txtLoginError.setVisibility(View.VISIBLE);
                    txtLoginError.setText("Please create an account");
                }


            }
        });

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                savedUsers = new ArrayList<>();

                String usernameText = inputUsername.getText().toString();
                String passwordText = inputPassword.getText().toString();

                if(usernameText.equals("Username") || passwordText.equals("Password")){

                    txtLoginError.setVisibility(View.VISIBLE);
                    txtLoginError.setText("Please set your username and password to something unique...");
                    return;
                }

                Log.d("MY Tag", usernameText + " " + passwordText);

                CurrentUser newUser = new CurrentUser(usernameText,passwordText);
                savedUsers.add(newUser);
                txtLoginError.setText("User saved! Username: " + usernameText + " Password: " + passwordText);
                txtLoginError.setVisibility(View.VISIBLE);
            }
        });
    }

    public void CreateMainScreen(){

        TextView welcomeText;

        TextView usernameText;
        TextView passwordText;

        Log.d("Username:", user.Username);

        Log.d("Password:", user.Password);

        welcomeText = findViewById(R.id.txtWelcome);
        welcomeText.setText("Welcome " + user.Username);

        usernameText = findViewById(R.id.txtUsername);
        passwordText = findViewById(R.id.txtPassword);

        usernameText.setVisibility(View.INVISIBLE);
        passwordText.setVisibility(View.INVISIBLE);

        usernameText.setText("Username: " + user.Username);
        passwordText.setText("Password: " + user.Password);

        Button btnCollections = (Button) findViewById(R.id.btnCollections);
        btnCollections.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setContentView(R.layout.activity_collections);
                CreateCollectionsScreen();
            }
        });

        Button btnPhotos = (Button) findViewById(R.id.btnPhotos);
        btnPhotos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                setContentView(R.layout.activity_photos);
                CreatePhotoScreen();

            }
        });

        Button btnDetails = (Button) findViewById(R.id.btnDetails);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(usernameText.getVisibility() == View.INVISIBLE && usernameText.getVisibility() == View.INVISIBLE) {
                    usernameText.setVisibility(View.VISIBLE);
                    passwordText.setVisibility(View.VISIBLE);

                    btnDetails.setText("Hide Details");
                }
                else if(usernameText.getVisibility() == View.VISIBLE && usernameText.getVisibility() == View.VISIBLE){

                    usernameText.setVisibility(View.INVISIBLE);
                    passwordText.setVisibility(View.INVISIBLE);

                    btnDetails.setText("View Details");
                }
                else{
                    Log.e("DetailsButton:", "Nothing happened");
                }
            }
        });
    }

    public void CreatePhotoScreen(){

        Button btnDisplay = (Button) findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //TODO: View existing photos
                //viewPhoto();

            }
        });

        Button btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //TODO: Take a photo
                //takePhoto();
            }
        });
    }

    public void CreateCollectionsScreen(){

        Button btnViewCollections = (Button) findViewById(R.id.btnViewCollections);
        Button btnAddCollections = (Button) findViewById(R.id.btnAddCollections);
        Button btnCreateCollection = (Button) findViewById(R.id.btnCreateCollection);
        Button btnEditCollection = (Button) findViewById(R.id.btnEditCollection);

        ScrollView addCollectionView = (ScrollView) findViewById(R.id.scrollAddCollection);

        TextView txtCollectionNum = (TextView) findViewById(R.id.txtCollectionNum);

        layout = (ConstraintLayout) findViewById(R.id.Layout2);
        layout.setVisibility(View.GONE);

        addCollectionView.setVisibility(View.INVISIBLE);
        addCollectionView.setClickable(false);

        TextView collectionsText = (TextView) findViewById(R.id.txtCollectionsView);

        btnViewCollections.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //TODO: View existing collections


                if(layout.getVisibility() == View.VISIBLE){
                    layout.setVisibility(View.GONE);
                }
                else{
                    layout.setVisibility(View.VISIBLE);
                }

                if(user.Categories != null){

                    int collectionNum = Integer.parseInt(txtCollectionNum.getText().toString());

                    for(int i = 0; i < user.Categories.size();i++){
                        if(collectionNum >= user.Categories.size()){

                            collectionsText.setText("There are no more collections");
                            return;
                        }
                        else{

                            collectionsText.setText(user.Categories.get(collectionNum).toString());
                            currentCollection = user.Categories.get(collectionNum);
                            Log.d("CollectionNumPulled", " " + collectionNum);
                            return;
                        }
                    }
                }
                else{
                        collectionsText.setText("You have no collections currently");
                    }
                return;
                }
        });

        btnAddCollections.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //TODO: Display options for adding a collection

                TextView collectionName = (TextView) findViewById(R.id.txtCollectionName);
                TextView collectionDescription = (TextView) findViewById(R.id.txtDescriptionText);
                TextView collectionGoal = (TextView) findViewById(R.id.txtGoal);

                TextView errorText = (TextView) findViewById(R.id.txtErrorText);
                errorText.setVisibility(View.INVISIBLE);

                if(addCollectionView.getVisibility() == View.VISIBLE){
                    addCollectionView.setVisibility(View.GONE);
                }
                else{
                    addCollectionView.setVisibility(View.VISIBLE);
                }

                btnCreateCollection.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View addCollection) {

                        //TODO: Save the
                        Category newCategory;

                        String name = collectionName.getText().toString();
                        Log.d("CollectionNameText",name);
                        String description;
                        int goal;

                        if(collectionName.getText().toString().equals("")){

                            Log.d("NullName", "Please name your collection");
                            errorText.setText("Please name your collection");
                            errorText.setVisibility(View.VISIBLE);
                            return;
                        }
                        else{
                            errorText.setVisibility(View.INVISIBLE);
                        }

                        if(collectionGoal.getText().toString().equals("0") || collectionGoal.getText().toString().equals("")){

                            name = collectionName.getText().toString();
                            description = collectionDescription.getText().toString();

                            newCategory = new Category(name,description);
                            AddCategory(newCategory);
                            Log.d("New Category:" , "Name:" + name + " Description:" + description);

                        }
                        else if(collectionDescription.getText().toString().equals("")){

                            name = collectionName.getText().toString();

                            newCategory = new Category(name);
                            AddCategory(newCategory);

                        }
                        else{

                            name = collectionName.getText().toString();
                            description = collectionDescription.getText().toString();
                            goal = Integer.parseInt(collectionGoal.getText().toString());

                            newCategory = new Category(name,description,goal);
                            AddCategory(newCategory);

                            Log.d("TestTag3"," " + newCategory);

                            //Category index = CurrentUser.Categories.get(collectionSize);
                            //Log.d("TestTag3", " " + index.name);

                        }
                    }
                });

            }
        });

        btnEditCollection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                setContentView(R.layout.activity_collectable);
                CreateCollectableScreen();
            }
        });
    }

    public void CreateCollectableScreen(){

        ScrollView collectableView = findViewById(R.id.scrollCollectableView);
        ScrollView collectableAdd = findViewById(R.id.scrollCollectableAdd);

        Button btnViewCollectable = (Button) findViewById(R.id.btnViewCollectable);
        Button btnCreateCollectable = (Button) findViewById(R.id.btnCreateCollectable);
        Button btnAddCollectable = (Button) findViewById(R.id.btnAddCollectable);

        collectionLimit = currentCollection.goal;

        TextView txtCollectableName;
        TextView txtCollectableDescription;
        TextView txtCollectableGenre;
        TextView txtCollectableDate;
        TextView txtCollectableError;
        TextView txtCollectableNum;
        TextView txtCollectableView;

        txtCollectableName = (TextView) findViewById(R.id.txtCollectableName);
        txtCollectableDescription = (TextView) findViewById(R.id.txtCollectableDescription);
        txtCollectableGenre = (TextView) findViewById(R.id.txtCollectableGenre);
        txtCollectableDate = (TextView) findViewById(R.id.txtCollectableDate);
        txtCollectableError = (TextView) findViewById(R.id.txtCollectableErrorText);
        txtCollectableView = (TextView) findViewById(R.id.txtCollectableView);
        txtCollectableNum = (TextView) findViewById(R.id.txtCollectableNum);

        collectableView.setVisibility(View.GONE);
        collectableAdd.setVisibility(View.GONE);

        btnViewCollectable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(collectableView.getVisibility() == View.VISIBLE){
                    collectableView.setVisibility(View.GONE);
                }
                else{
                    collectableView.setVisibility(View.VISIBLE);
                }

                if(currentCollection.savedCollectables != null){

                    int collectableNum = Integer.parseInt(txtCollectableNum.getText().toString());

                    for(int i = 0; i < currentCollection.savedCollectables.size();i++){
                        if(collectableNum >= currentCollection.savedCollectables.size()){

                            txtCollectableView.setText("There are no more collectables");
                            return;
                        }
                        else{

                            txtCollectableView.setText(currentCollection.savedCollectables.get(collectableNum).toString());
                            currentCollectable = currentCollection.savedCollectables.get(collectableNum);
                            return;
                        }
                    }
                }
                else{
                    txtCollectableView.setText("You have no collectables currently");
                }
                return;

            }
        });

        btnCreateCollectable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(collectableAdd.getVisibility() == View.VISIBLE){
                    collectableAdd.setVisibility(View.GONE);
                }
                else{
                    collectableAdd.setVisibility(View.VISIBLE);
                }
            }
        });

        btnAddCollectable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Collectable newCollectable;

                String collectableName = txtCollectableName.getText().toString();
                String collectableDescription;
                String collectableGenre;
                String collectableDate;

                if(collectableName.equals("")){

                    txtCollectableError.setText("Please name your collection");
                    txtCollectableError.setVisibility(View.VISIBLE);
                    return;
                }
                else{
                    txtCollectableError.setVisibility(View.INVISIBLE);
                }

                if(txtCollectableDate.getText().toString().equals("")){

                    collectableName = txtCollectableName.getText().toString();
                    collectableDescription = txtCollectableDescription.getText().toString();
                    collectableGenre = txtCollectableGenre.getText().toString();

                    newCollectable = new Collectable(collectableName,collectableDescription,collectableGenre);
                    AddCollectable(newCollectable);

                }
                else if(txtCollectableDate.getText().toString().equals("") &&
                        txtCollectableGenre.getText().toString().equals("")){

                    collectableName = txtCollectableName.getText().toString();
                    collectableDescription = txtCollectableDescription.getText().toString();

                    newCollectable = new Collectable(collectableName,collectableDescription);
                    AddCollectable(newCollectable);

                }
                else if(txtCollectableDate.getText().toString().equals("") &&
                        txtCollectableGenre.getText().toString().equals("") &&
                        txtCollectableDescription.getText().toString().equals("")){

                    collectableName = txtCollectableName.getText().toString();

                    newCollectable = new Collectable(collectableName);
                    AddCollectable(newCollectable);

                }
                else{

                    collectableName = txtCollectableName.getText().toString();
                    collectableDescription = txtCollectableDescription.getText().toString();
                    collectableGenre = txtCollectableGenre.getText().toString();
                    collectableDate = txtCollectableDate.getText().toString();

                    newCollectable = new Collectable(collectableName,collectableDescription,collectableGenre,collectableDate);
                    AddCollectable(newCollectable);

                }
            }
        });
    }

    private void takePhoto(int act) {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhotoIntent, act);
    } //TODO: NOT IMPLEMENTED

    public static boolean canHandle(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);

        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return ((List<?>) list).size() > 0;
    } //TODO: NOT IMPLEMENTED

    private void viewPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        Bitmap mImageBitmap = (Bitmap) extras.get("data");
        ImageView mImageView = null;
        mImageView.setImageBitmap(mImageBitmap);
    }//TODO: NOT IMPLEMENTED

    void AddCategory(Category newCategory){

        if(user.Categories == null){

            CreateCategoryList();
            user.Categories.add(newCategory);
            Log.d("CreateCategory", "");
        }
        else{

            user.Categories.add(newCategory);
            Log.d("AddCategory", "");
            Log.d("CollectionSize", "" + user.Categories.size());
        }
    }

    void CreateCategoryList(){

        user.Categories = new ArrayList<>();
    }

    void AddCollectable(Collectable newCollectable){

        if(currentCollection.savedCollectables == null){
            CreateCollectableList();
            currentCollection.savedCollectables.add(newCollectable);
        }
        else{
            currentCollection.savedCollectables.add(newCollectable);
        }
    }

    void CreateCollectableList(){

        currentCollection.savedCollectables = new ArrayList<>(collectionLimit);
    }
}

//Source: https://stuff.mit.edu/afs/sipb/project/android/docs/training/camera/photobasics.html#TaskPath


