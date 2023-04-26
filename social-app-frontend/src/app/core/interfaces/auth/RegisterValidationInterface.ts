import { ValidatorFn } from "@angular/forms";

/**
 * Defines the methods for validating user registration.
 */
export interface RegisterValidationInterface {
    /**
     * Validates if password and repeat password fields match.
     * @param passwordFieldName The name of the password field.
     * @param repeatFieldName The name of the repeat password field.
     * @returns A function that returns an error if the passwords do not match.
     */
    passwordMatchValidator(passwordFieldName: string, repeatFieldName: string): ValidatorFn;

    /**
     * Validates if user is of proper age.
     * @returns A function that returns an error if the user is not of proper age.
     */
    properAgeValidator(): ValidatorFn;
}
