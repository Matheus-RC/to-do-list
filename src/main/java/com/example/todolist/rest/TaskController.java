package com.example.todolist.rest;

import com.example.todolist.core.business.excepition.CategoriaException;
import com.example.todolist.core.business.excepition.PrioridadeException;
import com.example.todolist.core.business.excepition.TaskException;
import com.example.todolist.core.sevice.TaskService;
import com.example.todolist.domain.Prioridade;
import com.example.todolist.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id){
        try{
            Optional<Task> task = taskService.findTask(id);
            return ResponseEntity.ok(task.get());
        }catch (TaskException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task){
        try{
            taskService.saveTask(task);
            return new ResponseEntity<>("Task salva com sucesso", HttpStatus.OK);
        }catch (TaskException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task){
        try{
            taskService.updateTask(task);
            return new ResponseEntity<>("Task atualizada com sucesso!", HttpStatus.OK);
        }catch (TaskException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        try{
            taskService.deleteTask(id);
            return new ResponseEntity<>("Task excluída com sucesso", HttpStatus.OK);
        }catch (TaskException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
