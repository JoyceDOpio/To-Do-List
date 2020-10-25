import { Component, Input, OnInit } from '@angular/core';
import { TodoService } from '../todo.service';
import { ToDo } from '../todo';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input() id;
  todoItems:ToDo[];

  constructor(private toDoService:TodoService) { }

  ngOnInit(): void {
    this.toDoService.findAll(this.id).subscribe(data => {
      this.todoItems = data;
      for(let u of this.todoItems)
      console.log(u);
    });
    console.log("list id:"+this.id);
  }

  onAdd(item){
    if(item.value !== "")
    {
      var toDo = {} as ToDo;
      toDo.task = item.value;
      toDo.listId = this.id;

      item.value = null;

    this.toDoService.addToDo(toDo).subscribe(response =>
    {
      console.log(response);
      this.todoItems.push(response);
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
    });
  }
}
