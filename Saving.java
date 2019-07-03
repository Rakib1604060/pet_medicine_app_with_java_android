package com.elanco.elancoapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.MediaStore;

import com.elanco.elancoapps.FirstButton.Dog;

import java.util.ArrayList;

public class Saving {


    public  static  void SaveADog(Dog dog, Context context,int position){
        SharedPreferences sharedPreferences=context.getSharedPreferences("DOGSLIST",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("DOGNAME"+position,dog.getDog_name());
        editor.putString("DOGBIRTH"+position,dog.getDog_birthdate());
        editor.putString("DOGIMAGE"+position,dog.getDog_imagepath());
        editor.putString("MEDICINEONENAME"+position,dog.getOne_medicine_name());
        editor.putString("MEDICINEONEDATE"+position,dog.getOne_medicine_date());
        editor.putString("MEDICINETWONAME"+position,dog.getTwo_medicine_name());
        editor.putString("MEDICINETWODATE"+position,dog.getTwo_medicine_date());

        editor.apply();
    }

    public  static ArrayList<Dog> GetAllDogs(Context context){
        ArrayList<Dog>dogs=new ArrayList<>();
        SharedPreferences sharedPreferences=context.getSharedPreferences("DOGSLIST",Context.MODE_PRIVATE);
         dogs.clear();
        for (int i=0;i<4;i++)
        {
            String name=null,imagepath=null,birthdate=null;
            name =sharedPreferences.getString("DOGNAME"+i,context.getResources().getString(R.string.unregistred));
            imagepath=sharedPreferences.getString("DOGIMAGE"+i,"NULL");
            birthdate=sharedPreferences.getString("DOGBIRTH"+i,"NULL");
            Dog dog=new Dog(name,birthdate,imagepath);
             dogs.add(dog);

        }
        return dogs;
    }
    public  static  Dog getAdogByPosition(int i,Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("DOGSLIST",Context.MODE_PRIVATE);

        Dog dog=new Dog();
        String name=sharedPreferences.getString("DOGNAME"+i,context.getResources().getString(R.string.unregistred));
        String  imagepath=sharedPreferences.getString("DOGIMAGE"+i,"NULL");
        String birthdate=sharedPreferences.getString("DOGBIRTH"+i,"NULL");
        String medicineonename=sharedPreferences.getString("MEDICINEONENAME"+i,"NULL");
        String medicineonedate=sharedPreferences.getString("MEDICINEONEDATE"+i,"NULL");
        String medicinetwoname=sharedPreferences.getString("MEDICINETWONAME"+i,"NULL");
        String medicinetwodate=sharedPreferences.getString("MEDICINETWODATE"+i,"NULL");

        dog.setDog_imagepath(imagepath);
        dog.setDog_name(name);
        dog.setDog_birthdate(birthdate);

        dog.setOne_medicine_date(medicineonedate);
        dog.setOne_medicine_name(medicineonename);
        dog.setTwo_medicine_date(medicinetwodate);
        dog.setTwo_medicine_name(medicinetwoname);

        return dog;
    }

    public static void DeleteAdogatPosition(int i,Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("DOGSLIST",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("DOGNAME"+i,context.getResources().getString(R.string.unregistred));
        editor.putString("DOGIMAGE"+i,"NULL");
        editor.putString("DOGBIRTH"+i,"NULL");
        editor.apply();

    }

 public  void saveAmedicine(Context context,int i,int medicineno,String medicineName,String date){
     SharedPreferences sharedPreferences=context.getSharedPreferences("DOGSLIST",Context.MODE_PRIVATE);
     SharedPreferences.Editor editor= sharedPreferences.edit();
         switch (medicineno){
            case 1:
                editor.putString("MEDICINEONENAME"+i,medicineName);
                editor.putString("MEDICINEONEDATE"+i,date);

                break;
            case 2:
                editor.putString("MEDICINETWONAME"+i,medicineName);
                editor.putString("MEDICINETWODATE"+i,date);
                break;

         }

 }





}
