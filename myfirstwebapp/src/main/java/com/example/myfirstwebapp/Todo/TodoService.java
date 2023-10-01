package com.example.myfirstwebapp.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	public TodoService() {
		
	}
	
	private static int todosCount = 0;
	
	private static List<Todo> todos = new ArrayList<Todo>();
	static {
		todos.add(new Todo(++todosCount, "in28minutes", "Learn AWS",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn DevOps",
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "in28minutes", "Learn Java",
				LocalDate.now().plusYears(3), false));
		
	}
	
	public List<Todo> findByUsername(String username){
		List<Todo> x = new ArrayList<>();
		for(int i = 0; i < todos.size(); i++) {
			if(todos.get(i).getUsername().equals(username)) {	
				x.add(todos.get(i));			
			}
		}
		return x;
		
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		for(int i = 0; i < todos.size(); i++) {
			if(todos.get(i).getId() == id)
				todos.remove(i);
		}
	}
	
	public Todo findById(int id) {
		for(int i = 0; i < todos.size(); i++) {
			if(todos.get(i).getId() == id) {
				Todo t = todos.get(i);
				return t;
			}
				
		}
		return null;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
	}
	
}
