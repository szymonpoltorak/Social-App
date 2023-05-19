import { StorageKeys } from "@core/enums/StorageKeys";

/**
 * Represents the interface for utility service functionality.
 */
export interface UtilServiceInterface {
    /**
     * Navigates to the specified URL.
     *
     * @param url - The URL to navigate to.
     */
    navigate(url: string): void;

    /**
     * Clears the storage.
     */
    clearStorage(): void;

    /**
     * Adds a value to the storage.
     *
     * @param key - The key to identify the value in the storage.
     * @param value - The value to be stored.
     */
    addValueToStorage<V>(key: StorageKeys, value: V): void;

    /**
     * Retrieves the value from the storage.
     *
     * @param key - The key to identify the value in the storage.
     * @returns The value from the storage as a string.
     */
    getValueFromStorage(key: StorageKeys): string;

    /**
     * Retrieves the key-value pair from the storage.
     *
     * @param key - The key to identify the value in the storage.
     * @returns The key-value pair from the storage as a string.
     */
    getKeyValuePairFromStorage(key: StorageKeys): string;

    /**
     * Removes the value from the storage.
     *
     * @param key - The key to identify the value in the storage.
     */
    removeValueFromStorage(key: StorageKeys): void;
}
