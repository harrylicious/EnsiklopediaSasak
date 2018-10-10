package id.haqiqi_studio.ensiklopediasasak.Model;

/**
 * Created by Gentong on 03/02/2018.
 */

public class ModelKata {

    String word;
    String meaning;
    String form;

    public ModelKata(String word, String meaning, String form ) {
        this.word=word;
        this.meaning=meaning;
        this.form=form;

    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getForm() {
        return form;
    }
}