import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import {Observable,throwError} from 'rxjs';
import {ToDo} from './todo';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class TodoService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = "http://localhost:8080/api/todolist/";
   }

  addToDo(toDo:ToDo): Observable<ToDo> {
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

  // private handleError(error: HttpErrorResponse) {
  //   if (error.error instanceof ErrorEvent) {
  //     // A client-side or network error occurred. Handle it accordingly.
  //     console.error('An error occurred:', error.error.message);
  //   } else {
  //     // The backend returned an unsuccessful response code.
  //     // The response body may contain clues as to what went wrong.
  //     console.error(
  //       `Backend returned code ${error.status}, ` +
  //       `body was: ${error.error}`);
  //   }
  //   // Return an observable with a user-facing error message.
  //   return throwError(
  //     'Something bad happened; please try again later.');
  // }

  selectAll(): Observable<ToDo[]>{
    return this.httpClient.get<ToDo[]>(this.url);
  }
}
