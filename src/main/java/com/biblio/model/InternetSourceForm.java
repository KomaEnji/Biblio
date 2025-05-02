package com.biblio.model;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class InternetSourceForm implements SourceInterface {
    private final Map<String, TextField> fields;
    private final DatePicker accessionDatePicker;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public InternetSourceForm(Map<String, TextField> fields, DatePicker accessionDatePicker) {
        this.fields = fields;
        this.accessionDatePicker = accessionDatePicker;
    }

    // проверка на пустое значение в поле по ключу
    private boolean isBlank(String key) {
        TextField field = fields.get(key);
        return field == null || field.getText().isBlank();
    }

    @Override
    public boolean isValid() {
        return  !isBlank("titleTextField")
                && !isBlank("siteNameTextField")
                && !isBlank("urlTextField")
                && accessionDatePicker.getValue() != null;
    }


    @Override
    public Source toSource() {
        String author = fields.get("authorITextField").getText().trim();
        String title = fields.get("titleTextField").getText().trim();
        String siteName = fields.get("siteNameTextField").getText().trim();
        String url = fields.get("urlTextField").getText().trim();
        LocalDate date = accessionDatePicker.getValue();
        String formattedDate = date.format(DATE_FORMATTER);

        String description = String.format(
                "%s %s / %s [Электронный ресурс] // %s : [сайт]. — URL: %s (дата обращения: %s).",
                author,
                title,
                author,
                siteName,
                url,
                formattedDate
        );

        return new Source(description,"Электронный ресурс");
    }

    @Override
    public void clear() {
        fields.values().forEach(TextField::clear);
        accessionDatePicker.setValue(null);
    }

}
