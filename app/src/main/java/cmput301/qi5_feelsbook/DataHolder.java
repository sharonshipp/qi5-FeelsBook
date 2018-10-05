package cmput301.qi5_feelsbook;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.ArrayList;

class DataHolder extends ArrayList<Emotion> {

    private static DataHolder ourInstance;
    private static final String FILENAME = "feels.json";


    static DataHolder getInstance(Context context) {
        // get instance of DataHolder class
        if (ourInstance == null && readFromFile(context.getApplicationContext()) == null){
            ourInstance = new DataHolder();
        }
        return ourInstance;
    }

    private static DataHolder readFromFile(Context context){
        // get from lonely twitter
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();

            ourInstance = gson.fromJson(reader,DataHolder.class);
            reader.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return ourInstance;
    }


    // private constructor
    private DataHolder() {
        super();
    }

    static void saveInFile(Context context){
        // put IO into AsyncTask for better performance
        new SaveTask().execute(context.getApplicationContext());

    }

    static class SaveTask extends AsyncTask<Context,Void,Void>{
        @Override
        protected Void doInBackground(Context... contexts) {
            // get from lonely twitter
            try {
                FileOutputStream fos = contexts[0].openFileOutput(FILENAME, Context.MODE_PRIVATE);
                OutputStreamWriter writer = new OutputStreamWriter(fos);
                Gson gson = new Gson();
                gson.toJson(ourInstance,writer);
                writer.close();
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
