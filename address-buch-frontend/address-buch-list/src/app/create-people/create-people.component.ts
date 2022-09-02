import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../person';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-create-people',
  templateUrl: './create-people.component.html',
  styleUrls: ['./create-people.component.css']
})
export class CreatePeopleComponent implements OnInit {


  person: Person = new Person();

  constructor(private personService: PersonService,
    private router: Router) { }

  ngOnInit(): void {
  }

  savePerson(){
    this.personService.createPerson(this.person).subscribe( data =>{
      console.log(data);
      this.goToPeopleList();
    },
    error => console.log(error)); 
  }

  goToPeopleList(){
    this.router.navigate(['/people']);
  }

  onSubmit(){
    console.log(this.person);
    this.savePerson();
  }

}
