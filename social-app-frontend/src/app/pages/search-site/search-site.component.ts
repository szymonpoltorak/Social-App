import { Component } from '@angular/core';
import { FriendData } from "@interfaces/home/FriendData";

@Component({
  selector: 'app-search-site',
  templateUrl: './search-site.component.html',
  styleUrls: ['./search-site.component.scss']
})
export class SearchSiteComponent {
    friendList: FriendData[] = [];
}
