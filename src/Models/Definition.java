package Models;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * A class designed to handle the object structure of an urban dictionary response.
 */
public class Definition {

    //Variable declaration with serialized names as needed to maintain lower camel case.
    private String definition, permalink;

    @SerializedName("thumbs_Up")
    private int thumbsUp;

    @SerializedName("sound_urls")
    private String[] soundURLs;

    private String author, word;

    @SerializedName("defid")
    private int defID;

    @SerializedName("current_vote")
    private String currentVote;

    @SerializedName("written_on")
    private String writtenOn;

    private String example;

    @SerializedName("thumbs_down")
    private String thumbsDown;

    /***
     * Constructor for creating new definition objects
     * @param definition a string that explains what the term means
     * @param permalink a link that links to the urban dictionary definition of the searched term
     * @param thumbsUp a number responsible for showing community approval of a term
     * @param soundURLs (unsure) likely the location of sound files for pronouncing a word
     * @param author The username of the individual who submitted the definition
     * @param word The word in question
     * @param defID (unsure) an internalID used by the internals.
     * @param currentVote (unsure) the users current vote, (thumbs up or down)
     * @param writtenOn the date the term was written/submitted on
     * @param example an example of how to use the term in a sentence
     * @param thumbsDown a number responsible for showing community disapproval of a term
     */
    public Definition(String definition, String permalink, int thumbsUp, String[] soundURLs, String author, String word, int defID, String currentVote, String writtenOn, String example, String thumbsDown) {
        setDefinition(definition);
        setPermalink(permalink);
        setThumbsUp(thumbsUp);
        setSoundURLs(soundURLs);
        setAuthor(author);
        setWord(word);
        setDefID(defID);
        setCurrentVote(currentVote);
        setWrittenOn(writtenOn);
        setExample(example);
        setThumbsDown(thumbsDown);
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public String[] getSoundURLs() {
        return soundURLs;
    }

    public void setSoundURLs(String[] soundURLs) {
        this.soundURLs = soundURLs;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getDefID() {
        return defID;
    }

    public void setDefID(int defID) {
        this.defID = defID;
    }

    public String getCurrentVote() {
        return currentVote;
    }

    public void setCurrentVote(String currentVote) {
        this.currentVote = currentVote;
    }

    public String getWrittenOn() {
        return writtenOn;
    }

    public String getYMD() {
        return writtenOn.substring(0, 10);
    }

    public void setWrittenOn(String writtenOn) {
        this.writtenOn = writtenOn;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(String thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public String toString(){
        return String.format("%s as defined by %s", word, author);
    }
}
