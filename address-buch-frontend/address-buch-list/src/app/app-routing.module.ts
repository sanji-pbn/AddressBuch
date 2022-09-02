import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePeopleComponent } from './create-people/create-people.component';
import { PeopleListComponent } from './people-list/people-list.component';
import { PersonDetailsComponent } from './person-details/person-details.component';
import { UpdatePeopleComponent } from './update-people/update-people.component';

const routes: Routes = [
  { path: 'people', component: PeopleListComponent },
  { path: 'create-people', component: CreatePeopleComponent },
  { path: '', redirectTo: 'people', pathMatch: 'full' },
  { path: 'update-people/:pk', component: UpdatePeopleComponent },
  { path: 'person-details/:pk', component: PersonDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
