package com.pravin.client.bk;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pravin.client.binding.Book;

import reactor.core.Disposable;

// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import com.pravin.client.binding.Book;

//for rest template
// @Service
// public class BookClient {
//   public void invokeBookTicket() {
//     String apiURL = "http://127.0.0.1:8080/api/books";
//     Book book = new Book();
//     book.setTitle("The Mistry");
//     book.setAuthor("Legend pravin");

//     RestTemplate rt = new RestTemplate();
//     ResponseEntity<String> postResponseEntity = rt.postForEntity(apiURL, book, String.class);
//     System.out.println(postResponseEntity.getBody());
//   }

//   public void invokeGetTicket() {
//     String apiUrl = "http://127.0.0.1:8080/api/books";
//     RestTemplate rt = new RestTemplate();
//     ResponseEntity<String> getResponseEntity = rt.getForEntity(apiUrl, String.class);
//     String Body = getResponseEntity.getBody();
//     System.out.println(Body);
//   }

//   public void invokeNewObjectGetBook() {
//     String apiUrl = "http://127.0.0.1:8080/api/books";
//     RestTemplate rt = new RestTemplate();
//     ResponseEntity<Book[]> getResponseEntity = rt.getForEntity(apiUrl, Book[].class);
//     Book[] book = getResponseEntity.getBody();
//     for(Book b : book) {
//         System.out.println(b);
//     }
//   }
@Service
public class BookClient {
// sync
  public void invokegetBooke() {
    String apiURL = "http://127.0.0.1:8080/api/books";

    WebClient client = WebClient.create();

    Book[] block = client.get()
        .uri(apiURL)
        .retrieve()
        .bodyToMono(Book[].class)
        .block();

    for (Book b : block) {
      System.out.println(b);
    }
  }
  

//sync
  public void invokeAddBookTicket() {
    String apiURL = "http://127.0.0.1:8080/api/books";
    Book book = new Book();
    book.setTitle("555555555555555555555555555");
    book.setAuthor("Legend pravin");

    WebClient client = WebClient.create();

    String block = client.post()
        .uri(apiURL)
        .bodyValue(book)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    System.out.println(block);

  }
  //aync
  public void invokegetBookAsync() {
    String apiURL = "http://127.0.0.1:8080/api/books";

    WebClient client = WebClient.create();

    client.get()
        .uri(apiURL)
        .retrieve()
        .bodyToMono(Book[].class)
        .subscribe(BookClient::responseHandler);
    System.out.println("+++++++++++++++++++++++++++ Request Sent ++++++++++++++++++++++++++++++");
  }

  public static void responseHandler(Book[] book) {
    for (Book book2 : book) {
      System.out.println(book2);
    }
  }
  

}
