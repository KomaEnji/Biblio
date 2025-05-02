package com.biblio.controller;


import com.biblio.model.Source;
import com.biblio.model.SourceForm;
import com.biblio.model.InternetSourceForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import com.biblio.util.FXUtil;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BiblioController {

    @FXML public DatePicker accessionDatePicker;
    @FXML public GridPane formFieldsContainer;
    @FXML public GridPane formFieldsContainer1;
    @FXML public TextArea biblioDescriptionTextArea;
    @FXML public TableView<Source> biblioTableView;
    @FXML public TableColumn numberColumn;
    @FXML public TableColumn<Source,String> descriptionColumn;
    @FXML public TableColumn<Source,String> typeColumn;

    private SourceForm sourceForm;
    private InternetSourceForm webForm;
    private final FXUtil util = new FXUtil();
    private Map<String, TextField> bookFields = new HashMap<>();
    private Map<String, TextField> webFields = new HashMap<>();
    private ObservableList<Source> masterData = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
//        заполнение hashMap textField'ами для книг
        for (Node node : formFieldsContainer.getChildren()) {
            if (node instanceof TextField tf) {
                bookFields.put(tf.getId(), tf);
            }
        }

//        заполнение hashMap textField'ами для сайтов
        for (Node node : formFieldsContainer1.getChildren()) {
            if (node instanceof TextField tf) {
                webFields.put(tf.getId(), tf);
            }
        }

//      сортировка номера
        numberColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getIndex() >= biblioTableView.getItems().size()) {
                    setText(null);
                } else {
                    setText(String.valueOf(getTableRow().getIndex() + 1));
                }
            }
        });

        webForm = new InternetSourceForm(webFields, accessionDatePicker);
        sourceForm = new SourceForm(bookFields);

//  сортировка и заполнение таблицы
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("source"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        SortedList<Source> sortedData = new SortedList<>(masterData);
        sortedData.comparatorProperty().bind(biblioTableView.comparatorProperty());
        biblioTableView.setItems(sortedData);

        //начальная сортировка: сначала по типу, потом по описанию
        biblioTableView.getSortOrder().clear();
        biblioTableView.getSortOrder().setAll(typeColumn, descriptionColumn);
        typeColumn.setSortType(TableColumn.SortType.ASCENDING);
        descriptionColumn.setSortType(TableColumn.SortType.ASCENDING);
//

    }

    //поиск по источникам?
    @FXML
    public void onClickedSearch(javafx.event.ActionEvent actionEvent) {

    }


    @FXML
    public void onActionAdd(ActionEvent e) {
        String text = biblioDescriptionTextArea.getText().trim();
        if (text.isEmpty()) {
            util.showError("Заполните поле описания");
            return;
        }
        masterData.add(new Source(text, "Книга"));
        biblioDescriptionTextArea.clear();
    }

    @FXML
    public void onActionAdd1(ActionEvent actionEvent) {
        if (!sourceForm.isValid()) {
            util.showError("Заполните обязательные поля: Название, Автор, Год, Издатель, Редакция");
            return;
        }

        Source source = sourceForm.toSource();
        masterData.add(source);
        sourceForm.clear();
    }

    @FXML
    public void onActionAdd2(ActionEvent actionEvent) {
        if(!webForm.isValid()) {
            util.showError("Заполните обязательные поля: URL, Название сайта, Заголовок статьи, Дата обращения");
            return;
        }
        Source source = webForm.toSource();
        masterData.add(source);
        webForm.clear();
    }

    @FXML
    public void onActionGitHubPage(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/KomaEnji/Biblio.git"));
        } catch (Exception ignored) {}
    }

    public void onActionGenerateWord(ActionEvent actionEvent) {


    }
}