package cmput301.qi5_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Comparator;

public class ListActivity extends AppCompatActivity {
    private EmotionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView emotion_list = findViewById(R.id.emotion_list);

        // set adapter to list view
        adapter = new EmotionAdapter(this, R.layout.list_item, DataHolder.getInstance(this));
        emotion_list.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // sort emotions
        adapter.sort(new Comparator<Emotion>() {
            @Override
            public int compare(Emotion o1, Emotion o2) {
                return o2.getTimestamp().compareTo(o1.getTimestamp());
            }
        });


    }
}
