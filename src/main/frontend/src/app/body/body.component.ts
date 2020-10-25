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

    var componentFactory = this.componentFactoryResolver.resolveComponentFactory(ListComponent);

    var viewContainerRef = this.listDirective.viewContainerRef;
    viewContainerRef.clear();

    var componentRef = viewContainerRef.createComponent<ListComponent>(componentFactory);
    componentRef.instance.id = listItem.id;
  }

  onAdd(item){
    if(item.value !== "")
    {
      var toDoList = {} as List;
      toDoList.title = item.value;

      item.value = null;

      this.listService.addList(toDoList).subscribe(response =>
        {
          console.log(response);
          this.lists.push(response);
        });
      }
  }

  onDelete(id:number,idx:number){

    this.listService.deleteList(id)
    .subscribe(response => {
      console.log(response);
      if (response === 1){
        this.lists.splice(idx,1);
        this.listDirective.viewContainerRef.clear();
      }
    });
  }
}
