import { Injectable } from '@angular/core';
import { LocalStorageInterface } from "@interfaces/auth/LocalStorageInterface";
import { StorageKeys } from "@enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService implements LocalStorageInterface {
    constructor() {
    }

    addValueIntoStorage(key: StorageKeys, value: string): void {
        localStorage.setItem(key, value);
    }

    removeValueFromStorage(key: StorageKeys): void {
        localStorage.removeItem(key);
    }

    getKeyValueFromStorage(key: StorageKeys): string {
        const value: string | null = localStorage.getItem(key);

        return value === null ? "" : `${ key }: ${ value }`;
    }

    getValueFromStorage(key: StorageKeys): string {
        const value: string | null = localStorage.getItem(key);

        return value === null ? "" : value;
    }

    clearStorage(): void {
        localStorage.clear();
    }
}
