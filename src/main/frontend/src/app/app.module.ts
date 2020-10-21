import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { ListComponent } from './list/list.component';
import { BodyComponent } from './body/body.component';
import { ListDirective } from './list.directive';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ListComponent,
    BodyComponent,
    ListDirective
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [ ListComponent ],
  bootstrap: [AppComponent]
})
export class AppModule { }
