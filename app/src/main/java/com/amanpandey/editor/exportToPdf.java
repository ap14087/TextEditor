package com.amanpandey.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class exportToPdf extends AppCompatActivity {
    EditText title;
    EditText body;
    Button export;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_export_to_pdf);

        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
        export = findViewById(R.id.export);

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bodyT = body.getText().toString();
                String titleT = title.getText().toString();

                String path = getExternalFilesDir(null).toString()+"/"+titleT+".pdf";
                File file = new File(path);
                if(!file.exists()){
                    try{
                        file.createNewFile();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                com.itextpdf.text.Document document = new Document(PageSize.A4);
                try{
                    PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
                }catch (DocumentException e){
                    e.printStackTrace();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                document.open();
                try{
                    document.add(new Paragraph(titleT));
                    document.add(new Paragraph("\n"));
                    document.add(new Paragraph(bodyT));
                }catch(DocumentException e){
                    e.printStackTrace();
                }
                Toast.makeText(exportToPdf.this, "Exported Successfully", Toast.LENGTH_SHORT).show();
                document.close();

                onBackPressed();
            }
        });
    }
}