package com.pravin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// this is for resttemplate
import com.pravin.client.bk.BookClient;

@SpringBootApplication
public class ClientApplication {

    private final BookClient bookClient;

    ClientApplication(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    public static void main(String[] args) {
      ConfigurableApplicationContext ctx = SpringApplication.run(ClientApplication.class, args);

      ClientApplication app = ctx.getBean(ClientApplication.class);
      // app.bookClient.invokeAddBookTicket();
      // // app.bookClient.invokeBookTicket();
      // app.bookClient.invokegetBooke();
      app.bookClient.invokegetBookAsync();

    }

}



