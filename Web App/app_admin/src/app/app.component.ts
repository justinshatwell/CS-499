import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { EventListingComponent } from './event-listing/event-listing.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, EventListingComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'nemaEvent-admin';
}
