import { StorageKeys } from "@enums/StorageKeys";

/**
 * The interface for local storage interactions.
 */
export interface LocalStorageInterface {
    /**
     * Adds the provided value to local storage using the specified key.
     *
     * @param key The key to use for storing the value in local storage.
     * @param value The value to store in local storage.
     */
    addValueIntoStorage(key: StorageKeys, value: string): void;

    /**
     * Removes the value associated with the specified key from local storage.
     *
     * @param key The key associated with the value to remove from local storage.
     */
    removeValueFromStorage(key: StorageKeys): void;

    /**
     * Retrieves the value associated with the specified key from local storage.
     *
     * @param key The key associated with the value to retrieve from local storage.
     * @returns The value associated with the specified key, or an empty object if no value is found.
     */
    getKeyValueFromStorage(key: StorageKeys): string;

    getValueFromStorage(key: StorageKeys): string;

    /**
     * Removes all key-value pairs from local storage.
     */
    clearStorage(): void;
}
