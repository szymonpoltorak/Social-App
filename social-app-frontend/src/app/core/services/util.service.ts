import { Injectable } from '@angular/core';
import { Router } from "@angular/router";
import { LocalStorageService } from "./local-storage.service";
import { StorageKeys } from "../enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class UtilService {
    constructor(private router: Router,
                private localStorageService: LocalStorageService) {
    }

    navigate(url: string): void {
        this.router.navigateByUrl(url);
    }

    clearStorage(): void {
        this.localStorageService.clearStorage();
    }

    addValueToStorage<V>(key: StorageKeys, value: V): void {
        this.localStorageService.addValueIntoStorage(key, value);
    }

    getValueFromStorage(key: StorageKeys): string {
        return this.localStorageService.getValueFromStorage(key);
    }

    getKeyValuePairFromStorage(key: StorageKeys): string {
        return this.localStorageService.getKeyValueFromStorage(key);
    }

    removeValueFromStorage(key: StorageKeys): void {
        this.localStorageService.removeValueFromStorage(key);
    }
}
