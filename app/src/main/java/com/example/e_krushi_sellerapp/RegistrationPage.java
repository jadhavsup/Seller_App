package com.example.e_krushi_sellerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class RegistrationPage extends AppCompatActivity {
    private TextInputEditText name;
    private TextInputEditText phoneNo;
    private TextInputEditText pinCode;
    private TextInputEditText address;
    private TextInputEditText shopName;
    private TextInputEditText LicenceNo;
    CheckBox checkbox;
    Button Registration;
    Button uploadImg;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference transportRef = db.collection("Seller's Details");
   private DocumentReference dataRef = db.document("Seller's Details/Registration_Details");
   StorageReference storageReference = FirebaseStorage.getInstance().getReference();
   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("upLoadFile");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        name=findViewById(R.id.name1);
        phoneNo=findViewById(R.id.phone1);
        pinCode=findViewById(R.id.pincode1);
        address=findViewById(R.id.address1);
        shopName=findViewById(R.id.shopname1);
        LicenceNo=findViewById(R.id.licenceno1);
        Registration=findViewById(R.id.Register);
        uploadImg=findViewById(R.id.upload_btn);
        checkbox=findViewById(R.id.checkbox);
        uploadImg.setEnabled(false);

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             AddData();
             }
        });

    }

   private void AddData() {
        String n1 = name.getText().toString();
        String ph1 = phoneNo.getText().toString();
        String pi1 = pinCode.getText().toString();
        String ad1 = address.getText().toString();
        String shn1 = shopName.getText().toString();
        String l1 = LicenceNo.getText().toString();

        if(n1.isEmpty()){
            Toast.makeText(RegistrationPage.this, "Please Enter valid name", Toast.LENGTH_LONG).show();
        }else if(ph1.isEmpty()){
            Toast.makeText(RegistrationPage.this, "Please enter valid Phone number", Toast.LENGTH_LONG).show();

        }else if(ph1.length()!=10){
            Toast.makeText(RegistrationPage.this, "Please enter valid Phone number", Toast.LENGTH_SHORT).show();

        }
        else if(pi1.isEmpty()){
            Toast.makeText(RegistrationPage.this, "Please enter valid PinCode", Toast.LENGTH_LONG).show();

        }else if(ad1.isEmpty()){
            Toast.makeText(RegistrationPage.this, "Please enter valid Address", Toast.LENGTH_SHORT).show();

        }else if(shn1.isEmpty()){
            Toast.makeText(RegistrationPage.this, "Please enter valid ShopName", Toast.LENGTH_SHORT).show();

        }else if(l1.isEmpty()){
            Toast.makeText(RegistrationPage.this, "Please enter valid Licence Number", Toast.LENGTH_LONG).show();

        }
        /*else if(!checkbox.isChecked()){
            Toast.makeText(RegistrationPage.this, "Terms and conditions must be accepted", Toast.LENGTH_LONG).show();
        }*/
        else{

        Data1 data = new Data1(n1, ph1, pi1, ad1, shn1, l1);
        transportRef.add(data);
            Intent intent=new Intent(RegistrationPage.this,AddProduct.class);
            startActivity(intent);
       }
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();

            }

        });




    }

    private void selectFile() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"File Select"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12 && resultCode==RESULT_OK && data !=null && data.getData()!=null){
            uploadImg.setEnabled(true);
            uploadImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    upLoadFileToFireBase(data.getData());
                }
            });
        }
    }

    private void upLoadFileToFireBase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("File is Loading");
        progressDialog.show();
        StorageReference reference=storageReference.child("uploadFile"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri=uriTask.getResult();
                        Toast.makeText(RegistrationPage.this, "File Upload", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.0* snapshot.getBytesTransferred())/ snapshot.getTotalByteCount();
                progressDialog.setMessage("File Uploaded..."+(int)progress+"%");

            }
        });

    }
    /* public void AddData(View v) {
        String n1 = name.getText().toString();
        String ph1 = phoneNo.getText().toString();
        String pi1 = pinCode.getText().toString();
        String ad1 = address.getText().toString();
        String shn1 = shopName.getText().toString();
        String l1 = LicenceNo.getText().toString();

        Data1 data = new Data1(n1, ph1, pi1, ad1, shn1, l1);
        transportRef.add(data);


    }*/
}




