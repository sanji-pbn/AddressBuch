import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Person } from '../person';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-update-people',
  templateUrl: './update-people.component.html',
  styleUrls: ['./update-people.component.css']
})
export class UpdatePeopleComponent implements OnInit {

  pk !: string;
  person: Person = new Person();
  constructor(
    private personService : PersonService,
    private route : ActivatedRoute,
    private router : Router,
  ) { }

  ngOnInit(): void {
    this.pk = this.route.snapshot.params['pk'];

    this.personService.getPersonById(this.pk).subscribe(data => {
      this.person = data;
    }, error => console.log(error));
  }

  goToPeopleList(){
    this.router.navigate(['/people']);
  }


  onSubmit(){
    this.personService.updatePerson(this.pk, this.person).subscribe(data => {
      this.goToPeopleList();
    },
    error => console.log(error));
  }

}
