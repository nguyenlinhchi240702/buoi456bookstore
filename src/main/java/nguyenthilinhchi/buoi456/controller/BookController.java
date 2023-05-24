package nguyenthilinhchi.buoi456.controller;

import jakarta.validation.Valid;
import nguyenthilinhchi.buoi456.entity.Book;
import nguyenthilinhchi.buoi456.services.BookService;
import nguyenthilinhchi.buoi456.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books=bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid  @ModelAttribute("book") Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }
//    @GetMapping("/edit/{id}")
//    public String editBookForm(@PathVariable("id") Long id, Model model) {
//        Book editBook = null;
//        for (Book book : bookService.getAllBooks()) {
//            if (book.getId().equals(id)) {
//                editBook = book;
//            }
//        }
//            if (editBook != null) {
//                model.addAttribute("book", editBook);
//                model.addAttribute("categories",categoryService.getAllCategories());
//                return "book/edit";
//            } else {
//                return "not-found";
//            }
//
//    }
//
//    @PostMapping("/edit")
//    public String editBook(@ModelAttribute("book") Book updatedBook) {
//        var optionalBook = bookService.getAllBooks().stream()
//                .filter(book -> book.getId() == updatedBook.getId())
//                .findFirst();
//        optionalBook.ifPresent(book -> bookService.getAllBooks().set(bookService.getAllBooks().indexOf(book), updatedBook));
//
//        return "redirect:/books";
//
//    }
//
//    @GetMapping ("/delete/{id}")
//    public String deleteBook (@PathVariable("id") Long id)  {
//        bookService.getAllBooks().removeIf(book -> book.getId().equals(id));
//        return "redirect:/books";
//    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        Book editBook = bookService.getBookById(id);
        if(editBook != null){
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book")Book updateBook, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        bookService.getAllBooks().stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {
                    bookService.updateBook(updateBook);
                });
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}