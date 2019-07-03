package com.elanco.elancoapps.FirstButton;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.elanco.elancoapps.BuildConfig;
import com.elanco.elancoapps.R;
import com.elanco.elancoapps.Saving;
import com.elanco.elancoapps.SelcectDateFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Doglist extends Fragment implements DogAdapter.OnDogClickListener {

    RecyclerView recyclerView;
    DogAdapter adapter;
    ArrayList<Dog>mydogs=new ArrayList<>();
    View rootview;

    public  static  final int GALERYREQCODE=1000;
    public static  final int CAMERAREQCODE=2000;
    DogAdapter.OnDogClickListener onDogClickListener;
    public static final int REQUESTPERMISSIONCODE=5000;
    public static  final int REQUESTCROPING=4000;
    int position=55;
    Uri uri;
    String date;
    Button datepickerButton;
    EditText dogname;

    Context context;
    Fragment myfragment;
    DatePickerDialog.OnDateSetListener onDateSetListener;


    String mCurrentPhotoPath;
    ImageView dogimage;
    Button savebutton;
    int capable=0;
    ImageView ic_finger;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          rootview=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_doglist,container,false);
          LoadDogsFromMemory();
          InitializeComponents();
          EnableRunTimePermission();

        ObjectAnimator animation = ObjectAnimator.ofFloat(ic_finger, "translationX", 20f);
        animation.setDuration(500);
        animation.setRepeatCount(Animation.INFINITE);
        animation.start();

          onDateSetListener=new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                  month++;
                  date=year+"/"+month+"/"+day;
                  datepickerButton.setText(date);
                  if (capable>0){
                      savebutton.setEnabled(true);

                  }





              }
          };

        return  rootview;
    }
    @Override
    public void OnDogClick(int position) {
       //if click then build a dialogue to save info
        Dog dog=mydogs.get(position);
        if (dog.getDog_name().equals(getActivity().getResources().getString(R.string.unregistred))){
            this.position=position;
            BuildDialogue(position);

        }
        else {
            // create new profile fragment and send data;
            Fragment myfragment=new Fragment_Dogprofile();
            Bundle mybundles=new Bundle();
            mybundles.putInt("POSITION",position);
            myfragment.setArguments(mybundles);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Fragment_contatiner,myfragment)
                    .commit();
        }



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

            CropImage.ActivityResult result= CropImage.getActivityResult(data);

            if (resultCode==getActivity().RESULT_OK){
                uri=result.getUri();
               // Toast.makeText(getActivity(),"uri"+uri, Toast.LENGTH_SHORT).show();
                dogimage.setImageURI(uri);
                capable++;


            }
            else if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                Exception e=result.getError();
                Toast.makeText(getActivity(),"error: "+e, Toast.LENGTH_SHORT).show();
            }

        }



    }


    private  void  EnableRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)){
            Toast.makeText(getActivity(), "Permission Granted!!", Toast.LENGTH_SHORT).show();
        }
        else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.CAMERA},REQUESTPERMISSIONCODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUESTPERMISSIONCODE:
              if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                  Toast.makeText(getActivity(), "Permission granted!!", Toast.LENGTH_SHORT).show();
              }
              else {
                  Toast.makeText(getActivity(), "You have to give Camera Permission", Toast.LENGTH_SHORT).show();
              }
              break;
        }
    }

    private void BuildDialogue(final int position) {
        final  Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dogsavingdialogue);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setTitle("Register a dog");
        dialog.setCancelable(false);

 //get components from dialogue view
        dogimage=dialog.findViewById(R.id.imageView3);
        dogname=dialog.findViewById(R.id.editText);
        savebutton=dialog.findViewById(R.id.registerdogbutton);
        Button cancelbutton=dialog.findViewById(R.id.cancellbutton);
        datepickerButton=dialog.findViewById(R.id.datebutton);
        savebutton.setEnabled(true);


        // cancel button if press calcel
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        datepickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //date picker
                Calendar calendar= Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener
                ,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dogname.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Insert a Name please!!", Toast.LENGTH_SHORT).show();
                }

                else {
                    Dog dog=new Dog();
                    dog.setDog_birthdate(date);
                    dog.setDog_name(dogname.getText().toString());
                    dog.setDog_imagepath(uri+"");
                    showAConfimationDIalogue();
                    Saving.SaveADog(dog,getActivity(),position);
                    dialog.dismiss();

                }


            }
        });





        //if click the image icon
        dogimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().start(context,myfragment);


            }
        });

        dialog.show();

    }

    private void showAConfimationDIalogue() {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        builder.setTitle("New Pet Added!!");
        builder.setMessage("Update Successful!!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             getFragmentManager().beginTransaction().replace(R.id.Fragment_contatiner,new Fragment_Doglist())
                     .commit();

            }
        });
       AlertDialog dialog=builder.create();
       dialog.show();
    }



    private void LoadDogsFromMemory() {
        mydogs.clear();
        mydogs=Saving.GetAllDogs(getActivity());
    }

    private void InitializeComponents() {
        context=getContext();
        myfragment=this;
        onDogClickListener=this;
        recyclerView=rootview.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        adapter =new DogAdapter(mydogs,getActivity(),onDogClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ic_finger=rootview.findViewById(R.id.icfinger);


    }





}
