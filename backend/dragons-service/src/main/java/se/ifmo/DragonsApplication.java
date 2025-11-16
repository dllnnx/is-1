package se.ifmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DragonsApplication {
  public static void main(String[] args) {
    SpringApplication.run(DragonsApplication.class, args);
  }
}
