package com.rkdev.dkr.crm.ui.views.list;

import com.rkdev.dkr.crm.model.Person;
import com.rkdev.dkr.crm.repository.LanguageRepository;
import com.rkdev.dkr.crm.repository.PersonRepository;
import com.rkdev.dkr.crm.ui.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route(value = "database", layout = MainLayout.class)
@PageTitle("DKR | Adatbázis")
public class MainView extends VerticalLayout {

    final Grid<Person> grid;
    final TextField filter;
    private final Button addNewButton;
    private final Button refreshButton;
    Dialog diag = new Dialog(new H4("A szűrőfeltétel alapján nem található személy a rendszerben!"));
    private PersonEditor personEditor;
    private PersonRepository personRepository;
    private PersonEditor languageEditor;
    private LanguageRepository languageRepository;


    public MainView(PersonEditor personEditor, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.personEditor = personEditor;
        this.grid = new Grid<>(Person.class);
        this.filter = new TextField();
        this.addNewButton = new Button("Új önéletrajz hozzáadása", VaadinIcon.PLUS.create());
        this.refreshButton = new Button("Frissités", VaadinIcon.REFRESH.create());


        HorizontalLayout actions = new HorizontalLayout(filter, addNewButton, refreshButton);

        add(actions, grid, personEditor);
        filter.setPlaceholder("Szűrés....");
        grid.setHeight("780px");
        grid.setColumns("vezeteknev", "keresztnev", "emailcim", "telefonszam", "szuletesidatum", "telepules", "rogzitesdatuma");
        grid.setItems(personRepository.findAll());

        filter.setWidth("400px");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> grid.setItems(filterBy(filter.getValue())));

        grid.asSingleSelect().addValueChangeListener(e -> {
            personEditor.szemelyesComp.open();
            personEditor.editPerson(e.getValue());
            personEditor.data();

        });

        addNewButton.addClickListener(e -> {
            personEditor.szemelyesComp.open();
            personEditor.editPerson(new Person("", "", "", "", "", "", "", "", "", "", "", "", LocalDate.now().toString()));
        });

        personEditor.setChangeHandler(() -> {
            personEditor.setVisible(true);
            filterBy(filter.getValue());
        });

        refreshButton.addClickListener(e -> grid.setItems(personRepository.findAll()));

    }


    public List<Person> filterBy(String filter) {
        List<Person> allPerson = personRepository.findAll();
        List<Person> filtered = new ArrayList<>();
        if (filter.isEmpty()) {
            return allPerson;
        } else {
            for (Person p : allPerson
            ) {
                if (
                        textFilter(p.getVezeteknev(), filter) ||
                                textFilter(p.getKeresztnev(), filter) ||
                                textFilter(p.getId().toString(), filter) ||
                                textFilter(p.getTelepules(), filter)) {
                    filtered.add(p);
                }
            }
            if (filtered.isEmpty()) {
                diag.open();
                return null;

            } else {
                return filtered;
            }

        }
    }

    public boolean textFilter(String vezeteknev, String filter) {
        if (vezeteknev == null || vezeteknev.isEmpty() || vezeteknev.toLowerCase().contains(filter.toLowerCase())) {
            return true;
        }
        return false;
    }


}
