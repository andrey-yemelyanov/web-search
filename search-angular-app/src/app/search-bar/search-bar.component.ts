import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import {MessageService} from 'primeng/api';

import { SearchService } from '../search.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
  providers: [MessageService]
})
export class SearchBarComponent implements OnInit {

  @Output() searchTriggered: EventEmitter<string> = new EventEmitter();

  searchQuery: string[] = [];
  terms: string[] = [];
  liveSearchEnabled: boolean = true;

  constructor(private searchService: SearchService, private messageService: MessageService) { }

  ngOnInit(): void {
  }

  search():void{
    const q = this.searchQuery.join(" ").toLowerCase();
    console.log("Searching for '" + q + "'");
    this.searchTriggered.emit(q);
  }

  searchTerms(event):void{
    const q = (<string>event.query).toLowerCase();
    this.searchService.getTerms(q).subscribe(
      data => {
        console.log(data);
        this.terms = data.terms;
      },
      err => {
        this.terms = [];
        this.showError(err);
      }
    );
  }

  showError(errorMsg: string) {
    this.messageService.add({severity:'error', summary: 'Oops...', detail:errorMsg});
  }

}
