import { Injectable } from '@angular/core';
import { LocalStorageInterface } from "../interfaces/LocalStorageInterface";
import { StorageKeys } from "../enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService implements LocalStorageInterface{
    constructor() {
    }

    addValueIntoStorage<V>(key: StorageKeys, value: V): void {
        window.localStorage.setItem(key, JSON.stringify(value));
    }

    removeValueFromStorage<V>(key: StorageKeys): void {
        window.localStorage.removeItem(key);
    }

    getValueFromStorage<V>(key: StorageKeys): V {
        const value: string | null = window.localStorage.getItem(key);

        if (value == null) {
            return {} as V;
        }
        const stringValue: string = '{"' + key + '": ' + value + '}';

        console.log(stringValue);

        return JSON.parse(stringValue) as V;
    }

    clearStorage(): void {
        window.localStorage.clear();
    }
}
