package com.biblio.model;

import javafx.scene.control.TextField;
import java.util.Map;

// класс для формирования книжного источника с полей ввода(2ая вкладка)
public class SourceForm implements SourceInterface{
    private final Map<String, TextField> fields;

    public SourceForm(Map<String, TextField> fields) {
        this.fields = fields;
    }

    // проверка на пустоту только для выбранных полей
    public boolean isValid() {
        return !isBlank("nameTextField") && !isBlank("authorTextField") && !isBlank("yearTextField")
                 && !isBlank("publisherTextField") && !isBlank("editorTextField");
    }

    // проверка на пустое значение в поле по ключу
    private boolean isBlank(String key) {
        TextField field = fields.get(key);
        return field == null || field.getText().isBlank();
    }

    // метод для преобразования в модель Source
    public Source toSource() {
        String sourceDescription = String.format("%s %s / %s. – %s: %s, %s. – %s с.",
                fields.get("authorTextField").getText(),
                fields.get("nameTextField").getText(),
                fields.get("editorTextField").getText(),
                fields.get("cityTextField").getText(),
                fields.get("publisherTextField").getText(),
                fields.get("yearTextField").getText(),
                fields.get("numberOfPagesTextField").getText());

        return new Source(sourceDescription, "Книга");
    }

    // очистка всех полей
    public void clear() {
        fields.values().forEach(TextField::clear);
    }
}
