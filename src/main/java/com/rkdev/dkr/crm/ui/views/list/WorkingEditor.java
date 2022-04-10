package com.rkdev.dkr.crm.ui.views.list;

import com.rkdev.dkr.crm.model.Working;
import com.rkdev.dkr.crm.repository.WorkingRepository;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class WorkingEditor extends VerticalLayout implements KeyNotifier {
    private final WorkingRepository workingRepository;
    TextField munkahelyneve = new TextField("Munkahely megnevezése:");
    TextField beosztas = new TextField("Beosztás:");
    TextArea osszefoglalas = new TextArea("Munka összefoglalása röviden:");
    TextField kezdeseve = new TextField("Kezdés éve:");
    TextField zaraseve = new TextField("Befejezés éve:");

    Button add = new Button("Mentés", VaadinIcon.PLUS.create());
    Button del = new Button("Törlés", VaadinIcon.TRASH.create());
    Button exit = new Button("Vissza", VaadinIcon.LEVEL_LEFT.create());
    PersonEditor personEditor;
    HorizontalLayout actions = new HorizontalLayout(add, del, exit);
    Dialog wcomp = new Dialog();
    Binder<Working> binder = new Binder<>(Working.class);

    private Working working;
    private ChangeHandler changeHandler;

    public WorkingEditor(WorkingRepository workingRepository) {
        this.workingRepository = workingRepository;
        munkahelyneve.setWidth("400px");
        wcomp.setWidth("700px");
        wcomp.setHeight("800px");
        osszefoglalas.setWidth("620px");
        osszefoglalas.setHeight("400px");
        wcomp.add(new VerticalLayout(new H3("Szakmai tapasztalat hozzáadása / módosítása"),
                new HorizontalLayout(munkahelyneve, beosztas),
                new HorizontalLayout(osszefoglalas),
                new HorizontalLayout(kezdeseve, zaraseve), actions));
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
        exit.addClickListener(e -> {
            wcomp.close();
        });
    }

    public void save() {
        workingRepository.save(working);
        changeHandler.onChange();
    }

    public void delete() {
        workingRepository.delete(working);
    }

    public void editWorking(Working w) {
        if (w == null) {
            setVisible(false);
            return;
        }

        boolean isPersisted = w.getId() != null;
        if (isPersisted) {
            working = workingRepository.findById(w.getId()).get();
        } else {
            working = w;
        }
        binder.setBean(working);
        setVisible(true);
        munkahelyneve.focus();
    }


    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public interface ChangeHandler {
        void onChange();
    }
}
