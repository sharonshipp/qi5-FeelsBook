package cmput301.qi5_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CounterAdapter counterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        counterAdapter = new CounterAdapter(this,DataHolder.getInstance(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_list) {
            startActivity(new Intent(this,ListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        counterAdapter.notifyDataSetChanged();
    }

    public void onAddEmotion(View v){
        // find catalog index and input comment, create new emotion object
        String text = ((Button)v).getText().toString();
        List<String> catalogs = Arrays.asList(getResources().getStringArray(R.array.emotion_catalogs));
        EditText input_comment = findViewById(R.id.input_comment);
        Emotion emotion = new Emotion(catalogs.indexOf(text),input_comment.getText().toString());

        // add new emotion
        DataHolder.getInstance(this).add(emotion);

        //update counter views and save data in file
        counterAdapter.notifyDataSetChanged();

        // save data in file
        DataHolder.saveInFile(this);

        // clean up comment view
        input_comment.setText("");
    }

}
