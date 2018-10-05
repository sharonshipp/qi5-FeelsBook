package cmput301.qi5_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


// get idea from https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
public class EmotionAdapter extends ArrayAdapter<Emotion> {
    private int resourceLayout;
    private Context mContext;

    EmotionAdapter(Context context, int resource, List<Emotion> emotions) {
        super(context, resource, emotions);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    @NonNull public View getView(final int position, View convertView, @NonNull final ViewGroup parent) {

        View v = convertView;
        // render layout
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        final Emotion emotion = getItem(position);
        if (emotion != null) {
            // get views
            TextView t_date =  v.findViewById(R.id.emotion_date);
            TextView t_catalog = v.findViewById(R.id.emotion_catalog);
            TextView t_comment = v.findViewById(R.id.emotion_comment);
            Button btn_edit = v.findViewById(R.id.btn_edit);
            Button btn_delete = v.findViewById(R.id.btn_delete);

            // set text for emotion
            t_catalog.setText(v.getResources().getStringArray(R.array.emotion_catalogs)[emotion.getEmotion()]);
            t_date.setText(emotion.getISOTimestamp());
            t_comment.setText(emotion.getComment());

            // bind onclick event to buttons
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // delete emotion from list, and save list to file
                    EmotionAdapter.this.remove(emotion);
                    DataHolder.saveInFile(mContext);
                }
            });
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // goto EditActivity, put emotion position as input argument
                    Intent intent = new Intent(v.getContext(),EditActivity.class);
                    intent.putExtra("position",position);
                    mContext.startActivity(intent);
                }
            });

        }

        return v;
    }
}
