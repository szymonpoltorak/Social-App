import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserSearchData } from "@interfaces/home/UserSearchData";
import { SearchService } from "@services/search/search.service";
import { Subject, takeUntil } from "rxjs";

@Component({
  selector: 'app-search-site',
  templateUrl: './search-site.component.html',
  styleUrls: ['./search-site.component.scss']
})
export class SearchSiteComponent implements OnInit, OnDestroy {
    private initUsersList$: Subject<void> = new Subject<void>();
    private xd$: Subject<void> = new Subject<void>();
    userList: UserSearchData[] = [];

    constructor(private searchService: SearchService) {
    }

    loadData(): void {
        this.searchService.getListOfUsersOfPattern()
            .pipe(takeUntil(this.xd$))
            .subscribe((data: UserSearchData[]): void => {
                console.log(data);

                this.userList = data;
            });
    }

    ngOnInit(): void {
        this.searchService.getListOfUsersOfPattern()
            .pipe(takeUntil(this.userList))
            .subscribe((data: UserSearchData[]): void => {
                console.log(data);

                this.userList = data;
            });
    }

    ngOnDestroy(): void {
        this.initUsersList$.next();
        this.initUsersList$.complete();
    }
}
