import { Routes } from '@angular/router';
import { EventListingComponent } from './event-listing/event-listing.component';
import { AddEventComponent } from './add-event/add-event.component';

export const routes: Routes = [
    { path: 'add-event', component: AddEventComponent },
    { path: '', component: EventListingComponent, pathMatch: 'full' }
];
