package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Вася", "ВасяLast", "Вася@mail.ru");
      User user2 = new User("Петя", "ПетяLast", "Петя@mail.ru");
      User user3 = new User("Саша", "СашаLast", "Саша@mail.ru");
      User user4 = new User("Женя", "ЖеняLast", "Женя@mail.ru");

      Car car1 = new Car("Форд",132);
      user1.setCar(car1);
      Car car2 = new Car("Хонда",112);
      user2.setCar(car2);
      Car car3 = new Car("Мерс",333);
      user3.setCar(car3);
      Car car4 = new Car("Жигули",569);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      System.out.println("-------------- Список юзеров-----------------------------------");

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println("-------------- Список юзеров-----------------------------------\n");


      System.out.println("------------------Вывод юзера по авто и серии-------------------------------");

      List<User> carList = userService.listUsersCar("Жигули", 569);
      for (User user : carList) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
