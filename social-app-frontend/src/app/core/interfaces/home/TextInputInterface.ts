/**
 * Represents the interface for a text input component.
 */
export interface TextInputInterface {
    /**
     * Updates the character counter of the text input.
     */
    updateCharacterCounter(): void;

    /**
     * Gets the size of the input.
     *
     * @returns the size of the input as a string
     */
    getInputSize(): string;
}
