import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { ToDo } from './todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = "http://localhost:8080/api/todolist/list/";
   }

  addToDo(toDo:ToDo): Observable<any> {
    return this.httpClient.post<ToDo>(this.url, toDo, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json', 'Access-Control-Allow-Origin':'*',
      })
    });
  }

  deleteToDo(id:number) {
    const completeUrl = this.url+id; // DELETE api/heroes/42
    return this.httpClient.delete(completeUrl, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json', 'Access-Control-Allow-Origin':'*',
      })});
  }

  findAll(id:any): Observable<ToDo[]>{
    return this.httpClient.get<ToDo[]>(this.url + id);
  }

  updateToDo(toDo:ToDo){
    let isChecked = 0;
    if(toDo.isChecked === true){
      isChecked = 1;
    }
    return this.httpClient.put(this.url+toDo.id+"/"+isChecked,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json', 'Access-Control-Allow-Origin':'*',
      })});
  }
}
