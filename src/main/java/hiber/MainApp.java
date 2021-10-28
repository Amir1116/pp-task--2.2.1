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
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("car1", 111);
      Car car2 = new Car("car2", 222);
      Car car3 = new Car("car3", 333);
      Car car4 = new Car("car4", 444);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCarId(car1);
      car1.setUser(user1);

      user2.setCarId(car2);
      car2.setUser(user2);

      user3.setCarId(car3);
      car3.setUser(user3);

      user4.setCarId(car4);
      car4.setUser(user4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();      }

      System.out.println(userService.getUserByCarModelSeries("car1", 111));
      System.out.println(userService.getUserByCarModelSeries("car2", 222));
      System.out.println(userService.getUserByCarModelSeries("car3", 333));
      System.out.println(userService.getUserByCarModelSeries("car4", 444));

      context.close();
   }
}
