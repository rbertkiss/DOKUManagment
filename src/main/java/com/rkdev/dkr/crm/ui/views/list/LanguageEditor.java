package com.rkdev.dkr.crm.ui.views.list;

import com.rkdev.dkr.crm.model.Language;
import com.rkdev.dkr.crm.repository.LanguageRepository;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class LanguageEditor extends VerticalLayout implements KeyNotifier {

    private final LanguageRepository languageRepository;
    ComboBox<String> nyelv = new ComboBox<String>("Nyelv:");
    ComboBox<String> nyelviszint = new ComboBox<String>("Nyelvi szint:");

    Button add = new Button("Mentés", VaadinIcon.PLUS.create());
    Button del = new Button("Törlés",VaadinIcon.TRASH.create());
    Button exit = new Button("Vissza",VaadinIcon.LEVEL_LEFT.create());
    PersonEditor personEditor;

    HorizontalLayout actions = new HorizontalLayout(add, del,exit);
    Dialog nyelvicomp = new Dialog();
    Binder<Language> binder = new Binder<>(Language.class);

    private Language language;
    private ChangeHandler changeHandler;

    @Autowired
    public LanguageEditor(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
        nyelviszint.setItems("A1","A2","B1","B2","C1","C2");
        nyelv.setItems(
                "Magyar","Angol","Német","Francia","Olasz", "Román",
                "Ukrán","Orosz","Arab","Kínai","Lengyel","Dán");

        nyelvicomp.setWidth("700px");
        nyelvicomp.setHeight("300px");
        nyelv.setWidth("400px");
        nyelvicomp.add(new VerticalLayout( new H3("Nyelvi képességek hozzáadása / módosítása"),
                new HorizontalLayout(nyelv,nyelviszint),actions));
        binder.bindInstanceFields(this);

        setSpacing(true);

        add.getElement().getThemeList().add("save");
        del.getElement().getThemeList().add("error");

        Dialog savedialog = new Dialog();
        savedialog.add(new Text("Adatsor mentése sikeresen megtörtént"));
        savedialog.setWidth("350px");
        savedialog.setHeight("80px");
        add.addClickListener(e -> {
            save();
            savedialog.open();
        });

        Dialog deletedialog = new Dialog();
        deletedialog.add(new Text("Adatsor törlése sikeresen megtörtént!"));
        deletedialog.setWidth("350px");
        deletedialog.setHeight("80px");
        del.addClickListener(e -> {
            delete();
            deletedialog.open();
        });

        exit.addClickListener(e->{
            nyelvicomp.close();
        });
    }
    public void save() {
        languageRepository.save(language);
        changeHandler.onChange();
    }

    public void delete() {
        languageRepository.delete(language);
    }

    public void editLanguage(Language l) {
        if (l == null) {
            setVisible(false);
            return;
        }

        boolean isPersisted = l.getId() != null;
        if (isPersisted) {
            language = languageRepository.findById(l.getId()).get();
        } else {
            language = l;
        }
        binder.setBean(language);
        setVisible(true);
        nyelv.focus();
    }



    public void setChangeHandler(ChangeHandler h) { changeHandler = h; }
    public interface ChangeHandler {  void onChange(); }
}
