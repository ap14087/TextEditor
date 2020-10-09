package com.amanpandey.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import net.dankito.richtexteditor.android.RichTextEditor;
import net.dankito.richtexteditor.android.toolbar.AllCommandsEditorToolbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private RichTextEditor editor;
    Button exporter;
    private AllCommandsEditorToolbar editorToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        editor = (RichTextEditor) findViewById(R.id.editor);

        exporter = findViewById(R.id.exporter);
        editorToolbar = (AllCommandsEditorToolbar) findViewById(R.id.editorToolbar);
        editorToolbar.setEditor(editor);

        editor.setEditorFontSize(20);
        editor.setPadding((int) (4 * getResources().getDisplayMetrics().density));

        // some properties you also can set on editor
        // editor.setEditorBackgroundColor(Color.YELLOW);
        // editor.setEditorFontColor(Color.MAGENTA);
        // editor.setEditorFontFamily("cursive");

        // show keyboard right at start up
        editor.focusEditorAndShowKeyboardDelayed();

        exporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),exportToPdf.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(editorToolbar.handlesBackButtonPress() == false) {
            super.onBackPressed();
        }
    }

}