package com.biblio.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//класс сформированных источников для вывода на таблицу
public class Source {
    private StringProperty source;
    private StringProperty type;


    public Source(String source, String type) {
        this.source = new SimpleStringProperty(source);
        this.type = new SimpleStringProperty(type);
    }


    public String getSource() {
        return source.get();
    }

    public StringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

}
