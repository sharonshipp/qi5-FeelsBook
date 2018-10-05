package cmput301.qi5_feelsbook;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

class CounterAdapter {
    private List<Emotion> emotions;
    private List<TextView> counters;

    CounterAdapter(@NonNull Activity activity, @NonNull List<Emotion> emotions) {
        // get views from activity
        this.emotions = emotions;
        this.counters = Arrays.asList(
                (TextView) activity.findViewById(R.id.count1),
                (TextView)activity.findViewById(R.id.count2),
                (TextView)activity.findViewById(R.id.count3),
                (TextView)activity.findViewById(R.id.count4),
                (TextView)activity.findViewById(R.id.count5),
                (TextView)activity.findViewById(R.id.count6)
        );

        this.notifyDataSetChanged();
    }

    void notifyDataSetChanged(){
        // count all catalogs and set text to view
        int counts[] = {0,0,0,0,0,0};
        for (Emotion emotion : emotions){
            counts[emotion.getEmotion()]++;
        }
        for (int i = 0; i < 6; ++i){
            this.counters.get(i).setText(String.valueOf(counts[i]));
        }
    }

}


