import { Component, OnInit, ViewChild, ComponentFactoryResolver } from '@angular/core';
import {List} from '../list';
import { ListService } from '../list.service';
import { v4 as uuidv4 } from 'uuid';
import { ListComponent } from '../list/list.component';
import { ListDirective } from '../list.directive';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  lists:List[];
  listId;

  @ViewChild(ListDirective, {static: true}) listDirective: ListDirective;
  constructor(private listService:ListService, private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
    this.listService.selectAll().subscribe(response => {
      this.lists = response;
      for(let u of this.lists)
      console.log(u);
    })
  }

  createListComponent(listItem:List){

    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(ListComponent);

    const viewContainerRef = this.listDirective.viewContainerRef;
    viewContainerRef.clear();

    const componentRef = viewContainerRef.createComponent<ListComponent>(componentFactory);
    componentRef.instance.id = listItem.id;
  }

  onAdd(item){
    if(item.value !== "")
    {
      let id = uuidv4();
      var toDoList = {} as List;
      toDoList.id = id;
      toDoList.title = item.value;

      item.value = null;

      this.listService.addList(toDoList).subscribe(response =>
        {
          console.log(response);
          if(response === 1){
            this.lists.push(toDoList);
          }
        });
      }
  }

  onDelete(id:number,idx:number){
    
    this.listService.deleteList(id)
    .subscribe(response => {
      console.log(response);
      if (response === 1){
        this.lists.splice(idx,1);
      }
    });
  }
}
