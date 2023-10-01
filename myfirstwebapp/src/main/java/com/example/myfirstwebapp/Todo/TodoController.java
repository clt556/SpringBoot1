package com.example.myfirstwebapp.Todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@SessionAttributes("name")
public class TodoController {
	
	public TodoController() {
		
	}
	
	@Autowired
	private TodoService todoService;
	
	
	
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = (String)model.get("name");
		List<Todo> todos = todoService.findByUsername(username);

		model.put("todos", todos);
		
		return "listTodos";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = new Todo(0,username, "", LocalDate.now().plusYears(1),false);
		model.put("todo",todo);
		return "add-todo";
	}
	//value is jsp file name :: just take the submit of that jsp form request.
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		
		todoService.addTodo((String)model.get("name"),todo.getDescription(),
				todo.getTargetDate(),false);
		return "redirect:list-todos"; //redirect make reuse other controller method.
	}
	
	@RequestMapping(value="delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos"; //redirect make reuse other controller method.
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo",todo);
		return "add-todo"; //redirect make reuse other controller method.
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		String username = getLoggedUsername(model);
		todo.setUsername(username);
		todoService.updateTodo(todo);
		return "redirect:list-todos"; //redirect make reuse other controller method.
	}

	private String getLoggedUsername(ModelMap model) {
		org.springframework.security.core.Authentication authentication =   SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
}
