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

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	

	public TodoControllerJpa() {
		
	}
	
	@Autowired
	private TodoRepository todoRepository;
	
	
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = (String)model.get("name");
		List<Todo> todos = todoRepository.findByUsername(username);
		
		System.out.println("BasicListcalled");

		model.put("todos", todos);

		return "listTodos";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = new Todo(0,username, "", LocalDate.now().plusYears(1),false);
		model.put("todo",todo);
		System.out.println("GetAddcalled");
		return "add-todo";
	}
	
	//value is jsp file name :: just take the submit of that jsp form request.
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		
		String username = getLoggedUsername(model);
		todo.setUsername(username);
		
		todoRepository.save(todo);
		System.out.println("PostAddcalled");
//		todoService.addTodo((String)model.get("name"),todo.getDescription(),
//				todo.getTargetDate(),todo.isDone());
		return "redirect:list-todos"; //redirect make reuse other controller method.
	}
	
	@RequestMapping(value="delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		System.out.println("GetDeletecalled");
		return "redirect:list-todos"; //redirect make reuse other controller method.
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo",todo);
		System.out.println("GetShowcalled");
		return "add-todo"; //redirect make reuse other controller method.
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		String username = getLoggedUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		System.out.println("PostUpdatecalled");
		return "redirect:list-todos"; //redirect make reuse other controller method.
	}

	private String getLoggedUsername(ModelMap model) {
		org.springframework.security.core.Authentication authentication =   SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Logincalled");
		return authentication.getName();
	}
	
	
}
