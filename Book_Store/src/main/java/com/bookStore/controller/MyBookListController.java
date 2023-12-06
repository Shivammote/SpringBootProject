package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookStore.service.MyBookListService;

@Controller
public class MyBookListController {

	@Autowired
	private MyBookListService myBookListService;
	
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") long id) {
		myBookListService.deleteById(id);
		return"redirect:/my_books";
	}
	
}
