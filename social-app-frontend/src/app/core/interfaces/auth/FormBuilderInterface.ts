import { FormGroup } from "@angular/forms";

/**
 * Interface for form building creation.
 */
export interface FormBuilderInterface {
    /**
     * Builds and returns the form group for user registration.
     *
     * @returns The form group for user registration.
     */
    buildRegisterForm(): FormGroup;

    /**
     * Builds and returns form group for user login.
     * @returns the form group for user login.
     */
    buildLoginForm(): FormGroup;
}
