import { Component, OnInit } from '@angular/core';
import { TodoService } from '../todo.service';
import {ToDo} from '../todo';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  todoItems:ToDo[];

  constructor(private toDoService:TodoService) { }

  ngOnInit(): void {
    this.toDoService.selectAll().subscribe(data => {
            this.todoItems = data;
            for(let u of this.todoItems)
            console.log(u);
          });
  }

  onAdd(item){
    if(item.value !== "")
    {
      let id = uuidv4();
      var toDo = {} as ToDo;
      toDo.id = id;
      toDo.task = item.value;
    // toDo.isChecked = false;
    // this.todoItems.push(toDo);
      item.value = null;


    this.toDoService.addToDo(toDo).subscribe((data: ToDo) =>
    {
      console.log(data);
      this.todoItems.push(toDo);
    });
    }
  }

  onDelete(id:number,idx:number){
    
    this.toDoService.deleteToDo(id)
    .subscribe(response => {
      console.log(response);
      if (response === 1){
        this.todoItems.splice(idx,1);
      }
    });
  }

  toggleCheck(toDoItem:ToDo){
    toDoItem.isChecked = !toDoItem.isChecked;

    // Send information to server
    this.toDoService.updateToDo(toDoItem).subscribe(response => {
      console.log(response);
    })
  }

}
