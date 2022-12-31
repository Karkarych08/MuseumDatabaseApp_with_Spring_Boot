package university.app.Services;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import university.app.App;
import university.app.Interfaces.Locale_If;
import university.app.Services.Exceptions.LocaleNotSupportedException;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Scanner;

@ComponentScan
public class UserInterface {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
    artistService aserv = ctx.getBean(artistService.class);
    Massage_localization message = ctx.getBean(Massage_localization.class);
    Locale_If locale = ctx.getBean(Locale_If.class);
    Scanner in = new Scanner(System.in);

    public void StartInterface() throws SQLException {
        boolean working = true;
        System.out.print("Choose your language (en,ru): ");
        try {
            locale.set(in.next().toLowerCase());
        }catch (LocaleNotSupportedException e){
            e.printStackTrace();
        }
        System.out.println(message.localize("welcomeMSG"));
        while (working){
            System.out.print(message.localize("startMENU"));
            switch (in.next().toLowerCase()){
                case "exit"-> working = false;
                case "findall" -> System.out.println(aserv.findAllartist().toString());
                case "findby" -> {
                    switch (in.next().toLowerCase()){
                        case "country" -> {
                            try {
                                System.out.print(message.localize("countryENTER"));
                                String country = in.next();
                                if (!Objects.equals(aserv.findAllartistbycountry(country).toString(), "[]"))
                                    System.out.println(aserv.findAllartistbycountry(country));
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
                                if (!Objects.equals(aserv.findAllartistbydate(date).toString(), "[]"))
                                    System.out.println(aserv.findAllartistbydate(date));
                                else System.out.println(message.localize("NoMatchingData"));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        case "id" -> {
                            try {
                                System.out.print(message.localize("EnterID"));
                                long id = in.nextLong();
                                if (!Objects.equals(aserv.findById(id).toString(), "[]"))
                                    System.out.println(aserv.findById(id));
                                else System.out.println(message.localize("NoMatchingData"));
                            }catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                        default -> System.out.println(message.localize("defaultFindByMSG"));
                    }
                }
                case "change_language" ->{
                    System.out.print(message.localize("ChangeLanguageMSG"));
                    try {
                        locale.set(in.next().toLowerCase());
                    }catch (LocaleNotSupportedException e){
                        e.printStackTrace();
                    }
                }
                default -> System.out.println(message.localize("defaultMenuMSG"));
            }
        }
    }
}
