import { Injectable } from '@angular/core';
import { StorageKeys } from "../../../core/enums/StorageKeys";

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService {
    constructor() {
    }

    addValueIntoStorage<V>(key: StorageKeys, value: V): void {
        window.localStorage.setItem(key, JSON.stringify(value));
    }

    removeValueFromStorage<V>(key: StorageKeys): void {
        window.localStorage.removeItem(key);
    }

    getValueFromStorage<V>(key: StorageKeys): string {
        const value: string | null = window.localStorage.getItem(key);

        return value == null ? {} as string : value;
    }

    clearStorage(): void {
        window.localStorage.clear();
    }
}
