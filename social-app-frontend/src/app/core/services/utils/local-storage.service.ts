import { Injectable } from '@angular/core';
import { LocalStorageInterface } from "@interfaces/auth/LocalStorageInterface";
import { StorageKeys } from "@enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService implements LocalStorageInterface {
    constructor() {
    }

    addValueIntoStorage<V>(key: StorageKeys, value: V): void {
        window.localStorage.setItem(key, JSON.stringify(value));
    }

    removeValueFromStorage(key: StorageKeys): void {
        window.localStorage.removeItem(key);
    }

    getKeyValueFromStorage(key: StorageKeys): string {
        const value: string | null = window.localStorage.getItem(key);

        console.log(value);

        return value === null ? "" : `"${ key }": ${ value }`;
    }

    getValueFromStorage(key: StorageKeys): string {
        const value: string | null = window.localStorage.getItem(key);

        return value === null ? "" : value;
    }

    clearStorage(): void {
        window.localStorage.clear();
    }
}
