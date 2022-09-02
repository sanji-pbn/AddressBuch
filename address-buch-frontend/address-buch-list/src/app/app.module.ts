import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PeopleListComponent } from './people-list/people-list.component';

import { HttpClientModule } from '@angular/common/http';
import { CreatePeopleComponent } from './create-people/create-people.component'
import { FormsModule } from '@angular/forms';
import { UpdatePeopleComponent } from './update-people/update-people.component';
import { PersonDetailsComponent } from './person-details/person-details.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    PeopleListComponent,
    CreatePeopleComponent,
    UpdatePeopleComponent,
    PersonDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
