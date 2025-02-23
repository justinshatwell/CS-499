import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventDataService {

  constructor(private http: HttpClient) {
  
   }

  url = 'http://localhost:3000/api/events';

  getEvents() : Observable<Event[]> {

    return this.http.get<Event[]>(this.url);
  }

  addEvent(formData: Event) : Observable<Event> {
    return this.http.post<Event>(this.url, formData);
  }
}
