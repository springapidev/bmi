package com.coderbd.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView welcomeMsg,errorMessage,result,weightLoss;
    EditText fullName, heightFeet,heightInches,weightKg,weightGram;
    Button btnCalculate,btnList,btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedpreferences = getSharedPreferences(SignupActivity.BMI_SIGNUP_PREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString(SignupActivity.FULL_NAME,null);
        welcomeMsg=findViewById(R.id.welcomeMsg);
        errorMessage=findViewById(R.id.errorMsg);
        result=findViewById(R.id.result);
        weightLoss=findViewById(R.id.weightLoss);
        if(name.length()>0){
           welcomeMsg.setText("Welcome "+name);
        }
        fullName=findViewById(R.id.fullName);
        heightFeet=findViewById(R.id.heightFeet);
        heightInches=findViewById(R.id.heightInch);
        weightKg=findViewById(R.id.weightKg);
        weightGram=findViewById(R.id.weightGram);
        btnCalculate=findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double feet=0.0, inches=0.0,kg=0.0,gram = 0.0,height=0.0,weight=0.0,bmiScore=0.0;
                String savedName="";
                try {
                    if(!fullName.getText().toString().isEmpty()){
                        savedName = fullName.getText().toString();
                    }
                    if(!heightFeet.getText().toString().isEmpty()){
                        feet =Double.parseDouble(heightFeet.getText().toString());
                    }
                    if(!heightInches.getText().toString().isEmpty()){
                        inches =Double.parseDouble(heightInches.getText().toString());
                    }
                    if(!weightKg.getText().toString().isEmpty()){
                        kg =Double.parseDouble(weightKg.getText().toString());
                    }
                    if(!weightGram.getText().toString().isEmpty()){
                        gram =Double.parseDouble(weightGram.getText().toString());
                    }
                }catch (NumberFormatException ne){
                    errorMessage.setText("Only Number is allowed!");
                }
                height = feet * 12 + inches;
                weight = (kg * 1000 + gram)/1000 ;
                bmiScore =  weight / Math.pow(height * 0.0254 , 2);
                String status = "";
                double targetBmi = 23.0;
                double targetWeight = targetBmi * Math.pow(height * 0.0254 , 2);
                double weightLosses = weight - targetWeight;
                if (bmiScore > 29){
                    status  = "Obesity!";
                    weightLoss.setText("You need to reduce weight "+Math.round(weightLosses * 100.0) / 100.0+" Kg");
                }else  if (bmiScore > 23){
                    status  = "Over Weight!";
                    weightLoss.setText("You need to reduce weight "+Math.round(weightLosses * 100.0) / 100.0+" Kg");
                }else  if (bmiScore >= 18.5){
                    status  = "Normal!";
                    weightLoss.setText("");
                }else {
                     targetBmi = 18.5;
                    targetWeight = targetBmi * Math.pow(height * 0.0254 , 2);
                    weightLosses = targetWeight - weight;
                    status = "Under Weight!";
                    weightLoss.setText("You need to increase weight "+Math.round(weightLosses * 100.0) / 100.0+" Kg");
                }
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.addContact(new BmiData(savedName, status,String.valueOf(Math.round(bmiScore * 100.0) / 100.0),String.valueOf(LocalDate.now())));
                result.setText(status+"("+Math.round(bmiScore * 100.0) / 100.0+")");
            }
        });
        btnList = findViewById(R.id.btnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(SignupActivity.SIGN_IN_STATUS, "false");
                editor.commit();

                Intent intent=new Intent(HomeActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}