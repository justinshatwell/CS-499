import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventCardComponent } from '../event-card/event-card.component';
import { Event } from '../models/event';
import { EventDataService } from '../services/event-data.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-event-listing',
  standalone: true,
  imports: [CommonModule, EventCardComponent],
  templateUrl: './event-listing.component.html',
  styleUrl: './event-listing.component.css',
  providers: [EventDataService]
})
export class EventListingComponent implements OnInit{

  events!: Event[];
  message: string = '';

  constructor(
    private eventDataService: EventDataService,
    private router: Router) {
    console.log('event-listing constructor');
  }
    
  public addEvent(): void {
    this.router.navigate(['add-event'])
  }
  private getStuff(): void {
    this.eventDataService.getEvents()
      .subscribe({
        next: (value: any) => {
          this.events = value;
          if(value.length > 0)
          {
            this.message = 'There are ' + value.length + ' events available.'
          }
          else
          {
            this.message = 'There are no events retrieved from the database';
          }
          console.log(this.message);

        },
        error: (error: any) => {
          console.log('Error: ' + error);
        }
      })
  }

ngOnInit(): void {
  console.log('ngOnInit');
  this.getStuff();
}
  }

