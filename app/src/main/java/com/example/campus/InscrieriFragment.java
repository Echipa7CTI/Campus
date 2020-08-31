package com.example.campus;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static com.google.firebase.storage.FirebaseStorage.getInstance;

public class InscrieriFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    StorageReference storageReference;
    StorageReference ref;

    String storagePath = "Users_Buletin_Semnatura_Imgs/";

    String status;

    Button buletinBtn, semnaturaBtn, trimiteCerere, citesteContract;
    TextView mesajBuletin, mesajSemnatura, mesajTrimitere, statusTv;
    EditText nume, prenume, facultate, adresa, telefon;
    Spinner ciclu, an, camin;
    List<String> spinnerCiclu, spinnerAn, spinnerCamin;

    ProgressDialog pd;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;
    private static final int IMAGE_PICK_GALLERY_CODE = 300;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;

    String cameraPermissions[];
    String storagePermissions[];

    Uri image_uri;

    String buletinSauSemnaturaPoza;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_inscrieri, container, false) ;

        pd = new ProgressDialog(getActivity());

        buletinBtn = view.findViewById(R.id.poza1Btn);
        semnaturaBtn = view.findViewById(R.id.poza2Btn);
        mesajBuletin = view.findViewById(R.id.text4);
        mesajSemnatura = view.findViewById(R.id.text5);
        citesteContract = view.findViewById(R.id.citesteContractBtn);
        statusTv = view.findViewById(R.id.text2);
        nume = view.findViewById(R.id.edit1);
        prenume = view.findViewById(R.id.edit2);
        facultate = view.findViewById(R.id.edit3);
        adresa = view.findViewById(R.id.edit4);
        telefon = view.findViewById(R.id.edit5);
        ciclu = view.findViewById(R.id.tip1);
        an = view.findViewById(R.id.tip2);
        camin = view.findViewById(R.id.tip3);
        mesajTrimitere = view.findViewById(R.id.text6);
        trimiteCerere = view.findViewById(R.id.submitBtn);

        //setare spinnere cu mancare
        spinnerCiclu = new ArrayList<>();
        spinnerAn = new ArrayList<>();
        spinnerCamin = new ArrayList<>();
        ArrayAdapter<CharSequence> staticAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.ciclu_studii, android.R.layout.simple_spinner_item);
        staticAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ciclu.setAdapter(staticAdapter1);
        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.an_studii, android.R.layout.simple_spinner_item);
        staticAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        an.setAdapter(staticAdapter2);
        ArrayAdapter<CharSequence> staticAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.camin_dorit, android.R.layout.simple_spinner_item);
        staticAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        camin.setAdapter(staticAdapter3);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Contracte");
        storageReference = getInstance().getReference();

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //buton poza buletin
        buletinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Se incarca poza la buletin!");
                buletinSauSemnaturaPoza = "pozaBuletin";
                showImagePicDialog();
            }
        });
        mesajBuletin.setVisibility(View.GONE);

        //buton poza semnatura
        semnaturaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Se incarca poza la semnatura!");
                buletinSauSemnaturaPoza = "pozaSemnatura";
                showImagePicDialog();
            }
        });
        mesajSemnatura.setVisibility(View.GONE);

        //buton trimitere cerere
        trimiteCerere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numeCerere = String.valueOf(nume.getText());
                String prenumeCerere = String.valueOf(prenume.getText());
                String numeFacultate = String.valueOf(facultate.getText());
                String cicluStudii = String.valueOf(ciclu.getSelectedItem());
                String anStudii = String.valueOf(an.getSelectedItem());
                String caminDorit = String.valueOf(camin.getSelectedItem());
                String adresaCerere = String.valueOf(adresa.getText());
                String nrTel = String.valueOf(telefon.getText());
                String uid = user.getUid();

                if(Objects.equals(statusTv.getText(), "Status contract: trimis")) {
                    Toast.makeText(getActivity(),"Ai trimis deja o cerere.", Toast.LENGTH_SHORT).show();
                }else if(Objects.equals(numeCerere, "") || Objects.equals(prenumeCerere, "") || Objects.equals(numeFacultate, "") || Objects.equals(adresaCerere, "") || Objects.equals(nrTel, "") || !mesajBuletin.isShown() || !mesajSemnatura.isShown()) {
                    Toast.makeText(getActivity(), "Te rog completeaza datele corespunzator", Toast.LENGTH_SHORT).show();
                }else{
                    inregistrare(numeCerere, prenumeCerere, numeFacultate, cicluStudii, anStudii, caminDorit, adresaCerere, nrTel, uid);
                }
            }
        });
        mesajTrimitere.setVisibility(View.GONE);

        databaseReference.child(user.getUid()).child("status").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                status = (String) snapshot.getValue();
                if(Objects.equals(status, null)){
                    statusTv.setText("Status contract: neinregistrat");
                }else{
                    statusTv.setText("Status contract: " + status);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        citesteContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descarcaContract();
            }
        });

        return view;
    }

    private void descarcaContract() {
        ref=storageReference.child("Contract.docx");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(getActivity(), "contract_original", ".docx ", DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory, fileName+fileExtension);

        downloadManager.enqueue(request);

        Toast.makeText(context, "Contract descarcat!", Toast.LENGTH_SHORT).show();

    }

    private void inregistrare(String numeCerere, String prenumeCerere, String numeFacultate, String cicluStudii, String anStudii, String caminDorit, String adresaCerere, String nrTel, String uid) {
        HashMap<String, Object> results = new HashMap<>();
        results.put("numeStudent", numeCerere);
        results.put("prenumeStudent", prenumeCerere);
        results.put("numeFacultate", numeFacultate);
        results.put("cicluStudii", cicluStudii);
        results.put("an", anStudii);
        results.put("caminDorit", caminDorit);
        results.put("adresa", adresaCerere);
        results.put("telefon", nrTel);
        results.put("idUser", uid);
        results.put("status", "Trimis");

        databaseReference.child(uid).updateChildren(results);
    }


    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void requestStoragePermission(){
        requestPermissions(storagePermissions,STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }
    private void requestCameraPermission(){
        requestPermissions(cameraPermissions,CAMERA_REQUEST_CODE);
    }


    private void showImagePicDialog() {

        String options[] = {"Camera", "Galerie"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alege imaginea");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else {
                        pickFromCamera();
                    }
                }

                else if (which == 1){
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else{
                        pickFromGallery();
                    }
                }
            }
        });

        builder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length >0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writeStorageAccepted){
                        pickFromCamera();
                    }
                    else {
                        Toast.makeText(getActivity(),"Activeaza permisiunile pentru camera si stocare.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length >0){
                    boolean writeStorageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted){
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(getActivity(),"Activeaza permisiunile pentru stocare", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                image_uri = data.getData();

                uploadProfileCoverPhoto(image_uri);
            }
            if(requestCode == IMAGE_PICK_CAMERA_CODE){

                uploadProfileCoverPhoto(image_uri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadProfileCoverPhoto(Uri uri) {

        pd.show();

        String filePathAndName = storagePath+ ""+ buletinSauSemnaturaPoza +"_"+user.getUid();

        StorageReference storageReference2nd = storageReference.child(filePathAndName);
        storageReference2nd.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                Uri downloadUri = uriTask.getResult();

                if (uriTask.isSuccessful()){
                    HashMap<String, Object> results = new HashMap<>();
                    results.put(buletinSauSemnaturaPoza,downloadUri.toString());

                    databaseReference.child(user.getUid()).updateChildren(results)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    pd.dismiss();
                                    Toast.makeText(getActivity(),"Imagine incarcata", Toast.LENGTH_SHORT).show();
                                    if (buletinSauSemnaturaPoza == "pozaBuletin"){
                                        mesajBuletin.setVisibility(View.VISIBLE);
                                    } else {
                                        mesajSemnatura.setVisibility(View.VISIBLE);
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(getActivity(),"Eroare la incarcarea imaginii", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    pd.dismiss();
                    Toast.makeText(getActivity(),"Eroare" , Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getActivity(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pickFromCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Temp Pic");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Temp Description");

        image_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);
    }

    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);

    }

}
