package cmput301.qi5_feelsbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Emotion {
    private Date timestamp;
    private Integer emotion;
    private String comment;

    static final DateFormat ISO_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.CANADA);

    Emotion(Integer emotion, String comment) {
        this.emotion = emotion;
        this.comment = comment;
        this.timestamp = new Date();
    }

    Integer getEmotion() {
        return emotion;
    }

    String getISOTimestamp(){
        return ISO_FORMAT.format(timestamp);
    }

    String getComment() {
        return comment;
    }

    Date getTimestamp() {
        return timestamp;
    }

    void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    void setEmotion(Integer emotion) {
        this.emotion = emotion;
    }

    void setComment(String comment) {
        this.comment = comment;
    }
}
