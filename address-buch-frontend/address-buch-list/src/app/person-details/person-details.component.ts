import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Person } from '../person';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  styleUrls: ['./person-details.component.css']
})
export class PersonDetailsComponent implements OnInit {


  pk!: string;
  person!: Person;
  constructor(private personService : PersonService,
    private route : ActivatedRoute,
    private router : Router) { }

  ngOnInit(): void {
    this.pk = this.route.snapshot.params['pk'];

    this.person = new Person();
    this.personService.getPersonById(this.pk).subscribe(data => {
      this.person = data;
    });
  }

}
