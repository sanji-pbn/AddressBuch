import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../person';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-people-list',
  templateUrl: './people-list.component.html',
  styleUrls: ['./people-list.component.css']
})
export class PeopleListComponent implements OnInit {

  people!: Person[];

  constructor(private personService: PersonService,
    private router: Router) { }

  ngOnInit(): void {
    this.getPeople();
  }

  private getPeople() {
    this.personService.getPersonList().subscribe(data => {
      this.people = data;
    })
  }

  updatePerson(pk: string) {
    this.router.navigate(['/update-people', pk]);
  }

  deletePerson(pk: string) {
    this.personService.deletePerson(pk).subscribe(data => {
      this.getPeople();
    })
  }

  personDetails(pk : string){
    this.router.navigate(['/person-details', pk]);
  }

}
