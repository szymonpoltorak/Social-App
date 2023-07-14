import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { UtilService } from "@services/utils/util.service";
import { StorageKeys } from "@enums/StorageKeys";
import { RoutePaths } from "@enums/RoutePaths";
import { Subject, takeUntil } from "rxjs";

@Component({
    selector: 'app-oauth',
    templateUrl: './oauth.component.html',
    styleUrls: ['./oauth.component.scss']
})
export class OauthComponent implements OnInit, OnDestroy {
    private destroyRoute$: Subject<void> = new Subject<void>();

    constructor(private activatedRoute: ActivatedRoute,
                private utilService: UtilService) {
    }

    ngOnInit(): void {
        this.activatedRoute.queryParams
            .pipe(takeUntil(this.destroyRoute$))
            .subscribe(params => {
            const authToken = params['authToken'];
            const refreshToken = params['refreshToken'];

            this.utilService.addValueToStorage(StorageKeys.AUTH_TOKEN, authToken);
            this.utilService.addValueToStorage(StorageKeys.REFRESH_TOKEN, refreshToken);

            this.utilService.navigate(RoutePaths.HOME_PATH);
        });
    }

    ngOnDestroy(): void {
        this.destroyRoute$.next();
        this.destroyRoute$.complete();
    }
}
