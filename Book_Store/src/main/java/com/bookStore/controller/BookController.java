package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;


@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBookListService myBookListService;
	
	@GetMapping("/")
	public String homePage() {
		return"home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return"bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=bookService.getAllBooks();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
	return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		bookService.save(b);
		return"redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list=myBookListService.getAllMyBooks();
		model.addAttribute("book",list);
		return"myBooks";
	}
	
	@RequestMapping("/myList/{id}")
	public String getMyList(@PathVariable("id") long id) {
		Book b=bookService.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookListService.saveMyBooks(mb);
		return"redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") long id,Model model) {
		Book b=bookService.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") long id) {
		bookService.deleteById(id);
		return"redirect:/available_books";
	}
	
	
	
	
}
