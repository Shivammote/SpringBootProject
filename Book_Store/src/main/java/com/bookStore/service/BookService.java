package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public void save(Book b) {
		bookRepository.save(b);
	}
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book getBookById(long id) {
		return bookRepository.findById(id).get();
	}
	
	public void deleteById(long id) {
		bookRepository.deleteById(id);
	}
}
