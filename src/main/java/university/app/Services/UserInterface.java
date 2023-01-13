package university.app.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import university.app.Interfaces.Locale_If;
import university.app.Services.Exceptions.LocaleNotSupportedException;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Scanner;

@ShellComponent
@RequiredArgsConstructor
public class UserInterface {

    private final artistService artistService;
    private final Massage_localization message;
    private final Locale_If locale;
    Scanner in = new Scanner(System.in);

    @ShellMethod("Find all persons")
    public void findAll() throws SQLException {
        System.out.println(artistService.findAllartist().toString());
    }

    @ShellMethod("Find by parameter")
    public void findby(@ShellOption String parameter){
        switch (parameter){
            case "country" -> {
                try {
                    System.out.print(message.localize("countryENTER"));
                    String country = in.next();
                    if (!Objects.equals(artistService.findAllartistbycountry(country).toString(), "[]"))
                        System.out.println(artistService.findAllartistbycountry(country));
                    else System.out.println(message.localize("NoMatchingData"));
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            case "date" -> {
                Calendar date = new GregorianCalendar();
                try{
                    System.out.print(message.localize("dateENTER"));
                    date.set(in.nextInt(), in.nextInt(),in.nextInt());
                    if (!Objects.equals(artistService.findAllartistbydate(date).toString(), "[]"))
                        System.out.println(artistService.findAllartistbydate(date));
                    else System.out.println(message.localize("NoMatchingData"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            case "id" -> {
                try {
                    System.out.print(message.localize("EnterID"));
                    long id = in.nextLong();
                    if (!Objects.equals(artistService.findById(id).toString(), "[]"))
                        System.out.println(artistService.findById(id));
                    else System.out.println(message.localize("NoMatchingData"));
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            default -> System.out.println(message.localize("defaultFindByMSG"));
        }
    }

    @ShellMethod("Change Language")
    public void changeLanguage(){
        System.out.print(message.localize("ChangeLanguageMSG"));
        try {
            locale.set(in.next().toLowerCase());
        }catch (LocaleNotSupportedException e) {
            e.printStackTrace();
        }
        }
}
