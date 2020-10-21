import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { List } from './list';

@Injectable({
  providedIn: 'root'
})
export class ListService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = "http://localhost:8080/api/todolist/";
   }

   addList(toDoList:List): Observable<any> {
    return this.httpClient.post<List>(this.url, toDoList, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json', 'Access-Control-Allow-Origin':'*',
      })
    });
  }

  deleteList(id:number) {
    const completeUrl = this.url+id; // DELETE api/heroes/42
    return this.httpClient.delete(completeUrl, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json', 'Access-Control-Allow-Origin':'*',
      })});
  }

  selectAll(): Observable<List[]>{
    return this.httpClient.get<List[]>(this.url);
  }
}
