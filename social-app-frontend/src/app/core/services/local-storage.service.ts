import { Injectable } from '@angular/core';
import { LocalStorageInterface } from "../interfaces/LocalStorageInterface";
import { StorageKeys } from "../enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService implements LocalStorageInterface {
    constructor() {
    }

    addValueIntoStorage<V>(key: StorageKeys, value: V): void {
        window.localStorage.setItem(key, JSON.stringify(value));
    }

    removeValueFromStorage<V>(key: StorageKeys): void {
        window.localStorage.removeItem(key);
    }

    getValueFromStorage(key: StorageKeys): string {
        const value: string | null = window.localStorage.getItem(key);

        return value === null ? "" : `"${ key }": ${ value }`;
    }

    clearStorage(): void {
        window.localStorage.removeItem(StorageKeys.REFRESH_TOKEN);
        window.localStorage.removeItem(StorageKeys.AUTH_TOKEN);
    }
}
