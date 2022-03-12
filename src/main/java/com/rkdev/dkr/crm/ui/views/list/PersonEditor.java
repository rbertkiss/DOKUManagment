package com.rkdev.dkr.crm.ui.views.list;

import com.rkdev.dkr.crm.model.*;
import com.rkdev.dkr.crm.repository.*;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
@UIScope
public class PersonEditor extends VerticalLayout implements KeyNotifier {

    //SZEMÉLYES ADATOK
    private final PersonRepository personRepository;
    private Person person;
    private ChangeHandler changeHandler;
    private Long jelenlegiID = null;
    TextField vezeteknev = new TextField("Vezetéknév:");
    TextField keresztnev = new TextField("Keresztnév:");
    TextField szuletesidatum = new TextField("Születési dátum:");
    TextField szuletesihely = new TextField("Születési hely:");
    ComboBox<String> neme = new ComboBox<String>("Neme:");
    EmailField emailcim = new EmailField("Email cím:");
    TextField telefonszam = new TextField("Telefonszám:");
    TextField webhely = new TextField("Webhely:");
    TextField iranyitoszam = new TextField("Irányítószám:");
    TextField telepules = new TextField("Település:");
    TextField cim = new TextField("Utca, házszám:");
    TextArea motivacioslevel = new TextArea();

    //MENÜ
    Button save = new Button("Mentés", VaadinIcon.CHECK.create());
    Button cancel = new Button("Mégse", VaadinIcon.EXIT.create());
    Button delete = new Button("Törlés", VaadinIcon.TRASH.create());
    Button refresh = new Button(VaadinIcon.REFRESH.create());
    Button refreshW = new Button( VaadinIcon.REFRESH.create());
    Button refreshS = new Button( VaadinIcon.REFRESH.create());
    Button refreshSC = new Button( VaadinIcon.REFRESH.create());

    HorizontalLayout actions = new HorizontalLayout(save, delete, cancel);

    //MINDEN
    Dialog szemelyesComp = new Dialog();
    Binder<Person> binder = new Binder<>(Person.class);

    //NYELVI DOLGOK
    private LanguageEditor languageEditor;
    private LanguageRepository languageRepository;
    Button nyelvhozz = new Button("Nyelv hozzáadása",VaadinIcon.PLUS.create());
    Grid<Language> gridL;

    private Language language;
    //Dialog diag = new Dialog( new Text("Nincs a rendszerben rögzített adat!"));

    //MUNKAHELY DOLGOK
    private WorkingEditor workingEditor;
    private WorkingRepository workingRepository;
    Button munkahozz = new Button("Munkahely hozzáadása",VaadinIcon.PLUS.create());
    Grid<Working> gridW;
    private Working working;

    //ISKOLA DOLGOK
    private SchoolsEditor schoolsEditor;
    private SchoolRepository schoolRepository;
    Button schoolhozz = new Button("Iskola hozzáadása",VaadinIcon.PLUS.create());
    Grid<School> gridSC;
    private School school;

    //SZAKMAI DOLGOK
    private SkillsEditor skillsEditor;
    private SkillsRepositroy skillsRepositroy;
    Button skillhozz = new Button("Skills hozzáadása",VaadinIcon.PLUS.create());
    Grid<Skills> gridS;
    private Skills skills;

    //KÉP
    MemoryBuffer buffer = new MemoryBuffer();
    Upload upload = new Upload(buffer);
    Div output = new Div();

    @Autowired
    public PersonEditor(PersonRepository personRepository,
                        LanguageRepository languageRepository, LanguageEditor languageEditor,
                        WorkingEditor workingEditor, WorkingRepository workingRepository,
                        SchoolsEditor schoolsEditor, SchoolRepository schoolRepository,
                        SkillsEditor skillsEditor, SkillsRepositroy skillsRepositroy) {

        this.personRepository = personRepository;
        this.languageEditor = languageEditor;
        this.languageRepository = languageRepository;
        this.workingEditor = workingEditor;
        this.workingRepository = workingRepository;
        this.schoolsEditor = schoolsEditor;
        this.schoolRepository = schoolRepository;
        this.skillsEditor = skillsEditor;
        this.skillsRepositroy = skillsRepositroy;

        this.gridL = new Grid<>(Language.class);
        this.gridW = new Grid<>(Working.class);
        this.gridS = new Grid<>(Skills.class);
        this.gridSC = new Grid<>(School.class);

        neme.setItems("Férfi", "Nő");
        motivacioslevel.setWidth("800px");
        motivacioslevel.setHeight("300px");
        szemelyesComp.setHeight("890px");
        szemelyesComp.setWidth("900px");
        cim.setWidth("400px");
        upload.setWidth("750px");
        telefonszam.setValue("+36");
        szemelyesComp.add(new VerticalLayout(
                new H3("Profilkép feltöltése"),new HorizontalLayout(upload,output),
                new H3("Személyes adatok"),
                new HorizontalLayout(vezeteknev, keresztnev, szuletesihely, szuletesidatum),
                new HorizontalLayout(neme, emailcim, telefonszam, webhely),
                new HorizontalLayout(iranyitoszam, telepules, cim),
                new H3("Nyelvi ismeretek"),new HorizontalLayout(refresh,nyelvhozz), new HorizontalLayout(gridL),
                new H3("Skills"),new HorizontalLayout(refreshS,skillhozz),new HorizontalLayout(gridS),
                new H3("Tanulmányok"),new HorizontalLayout(refreshSC,schoolhozz),new HorizontalLayout(gridSC),
                new H3("Szakmai tapasztalat"),new HorizontalLayout(refreshW,munkahozz),new HorizontalLayout(gridW),
                new H3("Motivációs levél"),new HorizontalLayout(motivacioslevel),
                actions));
        szemelyesComp.setVisible(true);
        add(szemelyesComp);



        refresh.addClickListener(e->{
            jelenlegiID = personRepository.getOne(person.getId()).getId();
            gridL.setItems(filterLang(jelenlegiID.toString()));

        });
        refreshW.addClickListener(e->{
            jelenlegiID = personRepository.getOne(person.getId()).getId();
            gridW.setItems(filterWork(jelenlegiID.toString()));

        });
        refreshS.addClickListener(e->{
            jelenlegiID = personRepository.getOne(person.getId()).getId();
            gridS.setItems(filterSK(jelenlegiID.toString()));
        });
        refreshSC.addClickListener(e->{
            jelenlegiID = personRepository.getOne(person.getId()).getId();
            gridSC.setItems(filterSc(jelenlegiID.toString()));
        });

        //region NYELVI FUNKCIÓGOMBOK ÉS GRID
        gridL.setHeight("200px");
        gridL.setWidth("800px");
        gridL.setColumns("nyelv", "nyelviszint");
        gridL.asSingleSelect().addValueChangeListener(e -> {
            languageEditor.nyelvicomp.open();
            languageEditor.editLanguage(e.getValue());

        });
        nyelvhozz.addClickListener(e -> {
            languageEditor.nyelvicomp.open();
            languageEditor.editLanguage(new Language(personRepository.getOne(person.getId()).getId(), "", ""));
        });

        languageEditor.setChangeHandler(() -> {
            languageEditor.setVisible(true);
        });
        //endregion

        //region MUNKAHELY FUNKCIÓGOMBOK ÉS GRID
        gridW.setHeight("200px");
        gridW.setWidth("800px");
        gridW.setColumns("munkahelyneve", "beosztas","kezdeseve", "zaraseve");
        gridW.asSingleSelect().addValueChangeListener(e -> {
            workingEditor.wcomp.open();
            workingEditor.editWorking(e.getValue());

        });
        munkahozz.addClickListener(e -> {
            workingEditor.wcomp.open();
            workingEditor.editWorking(new Working(personRepository.getOne(person.getId()).getId(), "","","","","", LocalDate.now().toString()));
        });

        workingEditor.setChangeHandler(() -> {
            workingEditor.setVisible(true);
        });
        //endregion

        //region SKILSS FUNKCIÓGOMBOK ÉS GRID
        gridS.setHeight("200px");
        gridS.setWidth("800px");
        gridS.setColumns("megnevezes", "tudasszint");
        gridS.asSingleSelect().addValueChangeListener(e -> {
            skillsEditor.scomp.open();
            skillsEditor.editSkill(e.getValue());

        });
        skillhozz.addClickListener(e -> {
            skillsEditor.scomp.open();
            skillsEditor.editSkill(new Skills(personRepository.getOne(person.getId()).getId(), "","", LocalDate.now().toString()));
        });

        skillsEditor.setChangeHandler(() -> {
            skillsEditor.setVisible(true);
        });
        //endregion

        //region ISKOLA FUNKCIÓGOMBOK ÉS GRID
        gridSC.setHeight("200px");
        gridSC.setWidth("800px");
        gridSC.setColumns("iskolaneve", "kepzetseg","szakirany", "kezdeseve","vegzeseve");
        gridSC.asSingleSelect().addValueChangeListener(e -> {
            schoolsEditor.sccomp.open();
            schoolsEditor.editSchool(e.getValue());

        });
        schoolhozz.addClickListener(e -> {
            schoolsEditor.sccomp.open();
            schoolsEditor.editSchool(new School(personRepository.getOne(person.getId()).getId(), "","","","","", LocalDate.now().toString()));
        });

        schoolsEditor.setChangeHandler(() -> {
            schoolsEditor.setVisible(true);
        });
        //endregion

        binder.bindInstanceFields(this);
        setSpacing(true);
        save.getElement().getThemeList().add("save");
        delete.getElement().getThemeList().add("error");
        addKeyPressListener(Key.ENTER, e -> save());
        Dialog savedialog = new Dialog();
        savedialog.add(new Text("Dokumentum mentése sikeresen megtörtént!"));
        savedialog.setWidth("380px");
        savedialog.setHeight("80px");
        save.addClickListener(e -> { save(); savedialog.open(); szemelyesComp.close(); gridS.setItems(); gridSC.setItems();;gridW.setItems();;gridL.setItems();});

        Dialog deletedialog = new Dialog();
        deletedialog.add(new Text("Adatsor törlése sikeresen megtörtént!"));
        deletedialog.setWidth("380px");
        deletedialog.setHeight("80px");
        delete.addClickListener(e -> { delete(); deletedialog.open(); szemelyesComp.close(); gridS.setItems(); gridSC.setItems();;gridW.setItems();;gridL.setItems();});
        cancel.addClickListener(e -> { editPerson(person); szemelyesComp.close(); gridS.setItems(); gridSC.setItems();;gridW.setItems();;gridL.setItems();});

        //region Képhez tartolzó funkciógombok
        /**
         * Képhez tartozó funkciógombok
         */
        upload.addSucceededListener(event -> {
            Component component = (Component) createComponent(event.getMIMEType(),
                    event.getFileName(), buffer.getInputStream());
            output.removeAll();
            showOutput(event.getFileName(), component, output);
        });

        upload.addFileRejectedListener(event -> {
            Paragraph component = new Paragraph();
            output.removeAll();
            showOutput(event.getErrorMessage(), (Component) component, output);
        });

        upload.getElement().addEventListener("file-remove", event -> {
            output.removeAll();
        });
        //endregion
    }
    public void data(){
        jelenlegiID = personRepository.getOne(person.getId()).getId();
        gridL.setItems(filterLang(jelenlegiID.toString()));
        jelenlegiID = personRepository.getOne(person.getId()).getId();
        gridW.setItems(filterWork(jelenlegiID.toString()));
        jelenlegiID = personRepository.getOne(person.getId()).getId();
        gridS.setItems(filterSK(jelenlegiID.toString()));
        jelenlegiID = personRepository.getOne(person.getId()).getId();
        gridSC.setItems(filterSc(jelenlegiID.toString()));
    }

    public void save() {
        personRepository.save(person);
        changeHandler.onChange();
    }

    public void delete() {
        personRepository.delete(person);
        languageRepository.delete(language);
    }

    public void editPerson(Person p) {
        if (p == null) {
            setVisible(false);
            return;
        }

        boolean isPersisted = p.getId() != null;
        if (isPersisted) {
            person = personRepository.findById(p.getId()).get();
        } else {
            person = p;
        }
        cancel.setVisible(isPersisted);
        binder.setBean(person);
        setVisible(true);
        vezeteknev.focus();
    }

    //region NYELVI FILTER
    public List<Language> filterLang(String filter) {
        List<Language> allLang = languageRepository.findAll();
        List<Language> filtered = new ArrayList<>();
        if (filter.isEmpty()) {
            return allLang;
        } else {
            for (Language l : allLang
            ) {
                if (
                        textFilter(l.getIds().toString(), filter)) {
                    filtered.add(l);
                }
            }
            if(filtered.isEmpty()){

                System.out.println("Nem található nyelvi adatok a rendszerben!");

            } else {
                return filtered;
            }                return filtered;

        }
    }
    //endregion

    //region ISKOLA FILTER
    public List<School> filterSc(String filter) {
        List<School> allSchool = schoolRepository.findAll();
        List<School> filtered = new ArrayList<>();
        if (filter.isEmpty()) {
            return allSchool;
        } else {
            for (School sc : allSchool
            ) {
                if (
                        textFilter(sc.getIds().toString(), filter)) {
                    filtered.add(sc);
                }
            }
            if(filtered.isEmpty()){

                System.out.println("Nem található iskolai adatok a rendszerben!");

            } else {
                return filtered;
            }                return filtered;

        }
    }
    //endregion

    //region MUNKAHELY FILTER
    public List<Working> filterWork(String filter) {
        List<Working> allWork = workingRepository.findAll();
        List<Working> filtered = new ArrayList<>();
        if (filter.isEmpty()) {
            return allWork;
        } else {
            for (Working w : allWork
            ) {
                if (
                        textFilter(w.getIds().toString(), filter)) {
                    filtered.add(w);
                }
            }
            if(filtered.isEmpty()){

                System.out.println("Nem található szakmai tapasztalati adatok a rendszerben!");
            } else {
                return filtered;
            }

            return filtered;

        }
    }
    //endregion

    //region SKILLS FILTER
    public List<Skills> filterSK(String filter) {
        List<Skills> allSki = skillsRepositroy.findAll();
        List<Skills> filtered = new ArrayList<>();
        if (filter.isEmpty()) {
            return allSki;
        } else {
            for (Skills sk : allSki
            ) {
                if (
                        textFilter(sk.getIds().toString(), filter)) {
                    filtered.add(sk);
                }
            }
            if(filtered.isEmpty()){

                System.out.println("Nem található skill adatok a rendszerben!");

            } else {
                return filtered;
            }
            return filtered;

        }
    }
    //endregion

    public boolean textFilter(String id, String filter) {
        if (id == null || id.isEmpty() || id.toLowerCase().contains(filter.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void setChangeHandler(ChangeHandler h) { changeHandler = h; }
    public interface ChangeHandler {  void onChange(); }



     //region Képfeltöltés
    private com.vaadin.flow.component.Component createComponent(String mimeType, String fileName, InputStream stream) {
        if (mimeType.startsWith("image")) {
            return createTextComponent(stream);
        } else if (mimeType.startsWith("text")) {
            Image image = new Image();
            try {

                byte[] bytes = IOUtils.toByteArray(stream);
                image.getElement().setAttribute("src", new StreamResource(
                        fileName, () -> new ByteArrayInputStream(bytes)));
                try (ImageInputStream in = ImageIO.createImageInputStream(
                        new ByteArrayInputStream(bytes))) {
                    final Iterator<ImageReader> readers = ImageIO
                            .getImageReaders(in);
                    if (readers.hasNext()) {
                        ImageReader reader = readers.next();
                        try {
                            reader.setInput(in);
                            image.setWidth(reader.getWidth(0) + "px");
                            image.setHeight(reader.getHeight(0) + "px");
                        } finally {
                            reader.dispose();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setSizeFull();
            return image;
        }
        Div content = new Div();
        String text = String.format("Mime type: '%s'\nSHA-256 hash: '%s'",
                mimeType, MessageDigestUtil.sha256(stream.toString()));
        content.setText(text);
        return content;

    }

    private Text createTextComponent(InputStream stream) {
        String text;
        try {
            text = IOUtils.toString(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            text = "Hiba történt!";
        }
        return new Text(text);
    }

    private void showOutput(String text, Component content, HasComponents outputContainer) {
        HtmlComponent p = new HtmlComponent(Tag.P);
        p.getElement().setText(text);
        outputContainer.add(p);
        outputContainer.add((com.vaadin.flow.component.Component) content);
    }
    //endregion
}
