package tw.hd.com.entest7000;

public class EnData {
    private int _id;
    private String enString;
    private String cnString;
    private String enSentence;
    private String cnSentence;

    public EnData(int _id, String enString, String cnString, String enSentence, String cnSentence) {
        this._id = _id;
        this.enString = enString;
        this.cnString = cnString;
        this.enSentence = enSentence;
        this.cnSentence = cnSentence;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEnString() {
        return enString;
    }

    public void setEnString(String enString) {
        this.enString = enString;
    }

    public String getCnString() {
        return cnString;
    }

    public void setCnString(String cnString) {
        this.cnString = cnString;
    }

    public String getEnSentence() {
        return enSentence;
    }

    public void setEnSentence(String enSentence) {
        this.enSentence = enSentence;
    }

    public String getCnSentence() {
        return cnSentence;
    }

    public void setCnSentence(String cnSentence) {
        this.cnSentence = cnSentence;
    }
}
