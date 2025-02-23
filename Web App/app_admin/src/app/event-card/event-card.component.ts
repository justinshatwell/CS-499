import { Component, OnInit, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-event-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './event-card.component.html',
  styleUrl: './event-card.component.css'
})
export class EventCardComponent implements OnInit {

  @Input('event') event: any;

  constructor() {}

  ngOnInit(): void {
    
  }

}
