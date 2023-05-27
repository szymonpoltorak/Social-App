import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserSearchData } from "@interfaces/home/UserSearchData";
import { SearchService } from "@services/search/search.service";
import { Subject, takeUntil } from "rxjs";
import { SearchSiteInterface } from "@interfaces/search/SearchSiteInterface";

@Component({
  selector: 'app-search-site',
  templateUrl: './search-site.component.html',
  styleUrls: ['./search-site.component.scss']
})
export class SearchSiteComponent implements OnInit, OnDestroy, SearchSiteInterface {
    private initUsersList$: Subject<void> = new Subject<void>();
    private loadingData$: Subject<void> = new Subject<void>();
    private loadMore$: Subject<void> = new Subject<void>();
    private numOfSite: number = 0;
    isItAll !: boolean;
    userList: UserSearchData[] = [];

    constructor(private searchService: SearchService) {
    }

    loadMoreData(): void {
        this.searchService.getListOfUsersOfPattern(this.numOfSite)
            .pipe(takeUntil(this.loadMore$))
            .subscribe((data: UserSearchData[]): void => {
                this.userList = data;

                if (data.length === 100) {
                    this.numOfSite++;
                } else {
                    this.isItAll = true;
                }
            });
    }

    loadData(): void {
        this.searchService.getListOfUsersOfPattern(this.numOfSite)
            .pipe(takeUntil(this.loadingData$))
            .subscribe((data: UserSearchData[]): void => {
                this.userList = data;

                if (data.length === 100) {
                    this.numOfSite++;
                } else {
                    this.isItAll = true;
                }
            });
    }

    ngOnInit(): void {
        this.isItAll = false;

        this.searchService.getListOfUsersOfPattern(this.numOfSite)
            .pipe(takeUntil(this.userList))
            .subscribe((data: UserSearchData[]): void => {
                this.userList = data;

                if (data.length === 100) {
                    this.numOfSite++;
                } else {
                    this.isItAll = true;
                }
            });
    }

    ngOnDestroy(): void {
        this.initUsersList$.next();
        this.initUsersList$.complete();

        this.loadingData$.next();
        this.loadingData$.complete();
    }
}
