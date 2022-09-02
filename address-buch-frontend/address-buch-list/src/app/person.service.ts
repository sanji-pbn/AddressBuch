import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from './person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private baseUrl = "http://localhost:8080/addressbook/v1/people";

  constructor(private httpservice : HttpClient) { }

  getPersonList() : Observable<Person[]>{
    return this.httpservice.get<Person[]>(`${this.baseUrl}`);
  }

  createPerson(person : Person) : Observable<object>{
    return this.httpservice.post(`${this.baseUrl}`, person);
  }

  getPersonById(pk: string) : Observable<Person>{
    return this.httpservice.get<Person>(`${this.baseUrl}/${pk}`);
  }

  updatePerson(pk: string, person: Person) : Observable<object> {
    return this.httpservice.put(`${this.baseUrl}/${pk}`, person);
  }

  deletePerson(pk : string): Observable<Object> {
    return this.httpservice.delete(`${this.baseUrl}/${pk}`);
  }

}
