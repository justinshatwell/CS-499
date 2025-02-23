import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from "@angular/forms";
import { Router } from '@angular/router';
import { EventDataService } from '../services/event-data.service';

@Component({
  selector: 'app-add-event',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-event.component.html',
  styleUrl: './add-event.component.css'
})

export class AddEventComponent implements OnInit{

  public addForm!: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private eventService: EventDataService
  ) {}

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      _id: [],
      name: ["", Validators.required],
      month: ["", Validators.required],
      day: ["", Validators.required],
      year: ["", Validators.required],
      hour: ["", Validators.required],
      minute: ["", Validators.required],
      description: ["", Validators.required]
    })
  }

  public onSubmit(){
    this.submitted = true;
    if(this.addForm.valid){
      this.eventService.addEvent(this.addForm.value)
        .subscribe({
          next:(data:any) => {
            console.log(data);
            this.router.navigate([]);
          },
          error: (error: any) => {
            console.log('Error:' + error);
          }
        });
    }
  }
  get f() {return this.addForm.controls}
}
